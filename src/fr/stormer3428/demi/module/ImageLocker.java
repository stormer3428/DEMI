package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ImageLocker extends Module{

	private ArrayList<Long> imageLockedChannels;
	private boolean exemptAdministrators;
	private ArrayList<Long> exemptRoles;
	private ArrayList<Long> exemptMembers;
	
	public ImageLocker(File file) {
		super(new File("ImageLocker.cfg"));

		this.CONFIG_KEYS.add(new Key("imageLockedChannels", "[]"));
		this.CONFIG_KEYS.add(new Key("exemptAdministrators", "true"));
		this.CONFIG_KEYS.add(new Key("exemptRoles", "[]"));
		this.CONFIG_KEYS.add(new Key("exemptMembers", "[]"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getName() {
		return "ImageLocker";
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
		
		exemptAdministrators = CONFIG.get("exemptAdministrators").equalsIgnoreCase("true");

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel channel = event.getChannel();
		if(!imageLockedChannels.contains(channel.getIdLong())) return;
		if(exemptMembers.contains(event.getAuthor().getIdLong())) return;
		Member member = Demi.jda.getGuildById(Demi.i.getServerID()).retrieveMember(event.getAuthor()).complete();
		List<Role> memberRoles = member.getRoles();
		for(Role memberRole : memberRoles) {
			if(exemptRoles.contains(memberRole.getIdLong())) return;
			if(exemptAdministrators && memberRole.hasPermission(Permission.ADMINISTRATOR)) return;
		}
		
		Message message = event.getMessage();
		if(message.getEmbeds().size() > 0) return;

		List<Attachment> attachements = message.getAttachments();
		
		for(Attachment att : attachements) {
			if(att.isImage()) return;
			if(att.isVideo()) return;
		}
		OUTPUT.info("Message from member " + member.getEffectiveName() + " imagelocked");
		message.delete().queue();
	}
	
}