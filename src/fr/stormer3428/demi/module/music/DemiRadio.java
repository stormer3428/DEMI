package fr.stormer3428.demi.module.music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.StageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class DemiRadio extends Module{

	private long streamChannelID;
	private String streamUrl;

	public DemiRadio() {
		super(new File("conf/demiRadio.conf"));

		this.CONFIG_KEYS.add(new Key("streamUrl", "https://synthwave-rex.radioca.st/stream", "The URL to pull the audio from"));
		this.CONFIG_KEYS.add(new Key("streamChannelID", "ID_HERE", "The ID of the channel to play music in"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "Haha ptdr chuis une description";
	}

	@Override
	public void onEnable() {
		super.onEnable();

		String streamChannelIDString = CONFIG.get("streamChannelID");
		try {
			streamChannelID = Long.parseLong(streamChannelIDString);
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Failed to retrieve parameter (streamChannelID) in config file " + getName());
			this.OUTPUT.warning("Retrieved value : " + this.CONFIG.get("streamChannelID"));
			this.OUTPUT.warning("Expected a channel ID");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		streamUrl = CONFIG.get("streamUrl");

		try {
			startRadio(streamUrl);
		} catch (Exception e) {
			e.printStackTrace();
			this.OUTPUT.error("Failed to start the radio");
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();

		Guild guild = Demi.i.getGuild();
		AudioManager manager = guild.getAudioManager();
		manager.closeAudioConnection();
		GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
		musicManager.audioPlayer.stopTrack();
		musicManager.scheduler.queue.clear();
	}

	public void startRadio(String url) throws UnsupportedAudioFileException, IOException {
		CONFIG.setParameter("streamUrl", url);
		Guild guild = Demi.i.getGuild();
		GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
		musicManager.audioPlayer.stopTrack();
		musicManager.scheduler.queue.clear();
		
		AudioManager manager = guild.getAudioManager();
		AudioChannel channel = guild.getVoiceChannelById(streamChannelID);
		if(channel == null) channel = guild.getStageChannelById(1078086859860742184l);
		if(channel == null) {
			this.OUTPUT.error("No audio channel with id " + streamChannelID);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
		}
		manager.openAudioConnection(channel);
		if(channel instanceof StageChannel stageChannel) {
			while(!manager.isConnected() || manager.getConnectedChannel().getIdLong() != streamChannelID) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			stageChannel.requestToSpeak().complete();		
		}
		PlayerManager.getInstance().loadAndPlay(guild, url);
	}









}















