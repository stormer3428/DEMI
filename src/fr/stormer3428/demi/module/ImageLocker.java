package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ImageLocker extends Module{

	private IO IMAGE_LOCKED_DATABASE;
	
	private boolean exemptAdministrators;
	private ArrayList<Long> exemptRoles = new ArrayList<>();
	private ArrayList<Long> exemptMembers = new ArrayList<>();
	
	private MixedOutput LOG;
	
	public ImageLocker() {
		super(new File("conf/imagelocker.conf"));

		this.CONFIG_KEYS.add(new Key("exemptAdministrators", "true", 
				"//Whether the module should ignore members with admin perms"));
		
		this.CONFIG_KEYS.add(new Key("exemptRoles", "[]", 
				"//A list of role id's the bot will ignore"));
		
		this.CONFIG_KEYS.add(new Key("exemptMembers", "[]", 
				"//A list of user id's the bot will ignore"));

		this.CONFIG_KEYS.add(new Key("imageLockingLoggingChannelID", "CHANNEL_ID",
				"//The id of the channel the logging should happen into"));
		
		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "A module that locks certain channels to only allow messages that contain an image or file";
	}

	@Override
	public void onEnable() {
		super.onEnable();
		
		List<String> exemptRolesString = this.CONFIG.getList("exemptRoles");
		try {
			for(String role : exemptRolesString) exemptRoles.add(Long.parseLong(role));
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Failed to retrieve parameter (exemptRoles) in config file " + getName());
			this.OUTPUT.warning("Retrieved value : " + this.CONFIG.getList("exemptRoles"));
			this.OUTPUT.warning("Expected an array of role IDs");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		
		List<String> exemptMembersString = this.CONFIG.getList("exemptMembers");
		try {
			for(String role : exemptMembersString) exemptMembers.add(Long.parseLong(role));
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Failed to retrieve parameter (exemptMembers) in config file " + getName());
			this.OUTPUT.warning("Retrieved value : " + this.CONFIG.getList("exemptMembers"));
			this.OUTPUT.warning("Expected an array of user IDs");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		
		this.IMAGE_LOCKED_DATABASE = new IO(new File("conf/imagelockedchannels.conf"), new ArrayList<>(), true);
		
		exemptAdministrators = CONFIG.get("exemptAdministrators").equalsIgnoreCase("true");

		String channelId = CONFIG.get("imageLockingLoggingChannelID");

		TextChannel channel = Demi.jda.getGuildById(Demi.i.getServerID()).getTextChannelById(channelId);
		if(channel == null) {
			this.OUTPUT.error("Error, invalid channel id given (imageLockingLoggingChannelID)");
			OUTPUT.error("Disabling module to prevent errors...");
			Demi.disableModule(this);
		}
		this.OUTPUT.trace("imageLockingLoggingChannelID : " + channelId, this.PRINT_STACK_TRACE);

		LOG = new MixedOutput(channelId, true, OUTPUT.isOutputToConsole(), "");
		
		this.OUTPUT.ok("Successfully loaded all config parameters");
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!event.isFromGuild()) return;
		GuildMessageChannelUnion channel = event.getGuildChannel();
		if(!IMAGE_LOCKED_DATABASE.getAllRaw().contains(channel.getId())) return;
		if(exemptMembers.contains(event.getAuthor().getIdLong())) return;
		Member member = Demi.jda.getGuildById(Demi.i.getServerID()).retrieveMember(event.getAuthor()).complete();
		List<Role> memberRoles = member.getRoles();
		for(Role memberRole : memberRoles) {
			if(exemptRoles.contains(memberRole.getIdLong())) return;
			if(exemptAdministrators && memberRole.hasPermission(Permission.ADMINISTRATOR)) return;
		}
		
		Message message = event.getMessage();
		if(message.getEmbeds().size() > 0) return;

		if(!message.getAttachments().isEmpty()) return;
		if(!message.getEmbeds().isEmpty()) return;
		

		LOG.info("Message from member " + member.getEffectiveName() + " imagelocked (" + channel.getAsMention() + ")\n"
				+ "```\n" + message.getContentRaw() + "\n```");
		message.delete().queue();
	}
	
}
