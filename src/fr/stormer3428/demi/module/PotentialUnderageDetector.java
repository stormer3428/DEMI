package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PotentialUnderageDetector extends Module{

	private long potentialUnderageLoggingChannelId;
	private MixedOutput UNDERAGE_LOGGING_OUTPUT;

	public PotentialUnderageDetector() {
		super(new File("PotentialUnderageDetector.cfg"));

		this.CONFIG_KEYS.add(new Key("potentialUnderageLoggingChannelId", "ID_HERE"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getName() {
		return "PotentialUnderageDetector";
	}

	@Override
	public String getDescription() {
		return "PotentialUnderageDetector is a module that will log any messages containing a number under 18 so that mods can manually check the messages out in case a persone mentionned being underage";
	}

	@Override
	public void onEnable() {
		super.onEnable();

		String channelIdString = CONFIG.get("potentialUnderageLoggingChannelId");
		try {
			potentialUnderageLoggingChannelId = Long.parseLong(channelIdString);
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Failed to retrieve parameter (potentialUnderageLoggingChannelId) in config file " + getName());
			this.OUTPUT.warning("Retrieved value : " + this.CONFIG.get("potentialUnderageLoggingChannelId"));
			this.OUTPUT.warning("Expected a channel ID");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		TextChannel textChannel = guild.getTextChannelById(potentialUnderageLoggingChannelId);
		if(textChannel == null) {
			this.OUTPUT.error("Failed to retrieve text channel (potentialUnderageLoggingChannelId) from server");
			this.OUTPUT.warning("Channel id is invalid or channel is not a text channel");
			this.OUTPUT.warning("Expected a channel ID");
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		UNDERAGE_LOGGING_OUTPUT = new MixedOutput(potentialUnderageLoggingChannelId + "", OUTPUT.isOutputToConsole(), OUTPUT.header());
		
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		List<Long> numbers = new ArrayList<>();

		for(String s : message.split(" ")) try {
			numbers.add(Long.parseLong(s));
		} catch (NumberFormatException e) {}

		for(long l : numbers) if(l <= 18) {
			UNDERAGE_LOGGING_OUTPUT.info(event.getAuthor().getAsMention() + " : " + message + "\n" + event.getMessage().getJumpUrl());
			break;
		}
	}
}








