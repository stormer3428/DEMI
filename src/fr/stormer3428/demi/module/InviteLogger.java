package fr.stormer3428.demi.module;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

public class InviteLogger extends Module{

	private long inviteLoggingChannelId;
	private MixedOutput INVITE_LOGGING_OUTPUT;

	public InviteLogger() {
		super(new File("inviteLogger.cfg"));

		this.CONFIG_KEYS.add(new Key("inviteLoggingChannelId", "ID_HERE"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getName() {
		return "InviteLogger";
	}

	@Override
	public String getDescription() {
		return "InviteLogger is a module that simply logs which invite was used whenever a member joins the server";
	}

	@Override
	public void onEnable() {
		String channelIdString = CONFIG.get("inviteLoggingChannelId");
		try {
			inviteLoggingChannelId = Long.parseLong(channelIdString);
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Failed to retrieve parameter (inviteLoggingChannelId) in config file " + getName());
			this.OUTPUT.warning("Retrieved value : " + this.CONFIG.get("inviteLoggingChannelId"));
			this.OUTPUT.warning("Expected a channel ID");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		TextChannel textChannel = guild.getTextChannelById(inviteLoggingChannelId);
		if(textChannel == null) {
			this.OUTPUT.error("Failed to retrieve text channel (inviteLoggingChannelId) from server");
			this.OUTPUT.warning("Channel id is invalid or channel is not a text channel");
			this.OUTPUT.warning("Expected a channel ID");
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		oldInvites = Demi.jda.getGuildById(Demi.i.getServerID()).retrieveInvites().complete();

		INVITE_LOGGING_OUTPUT = new MixedOutput(inviteLoggingChannelId + "", OUTPUT.isOutputToConsole(), OUTPUT.header());

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public void onGuildInviteCreate(GuildInviteCreateEvent event) {
		Invite invite = event.getInvite();
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Invite (" + invite.getCode() + ") has been created by " + invite.getInviter().getAsMention() + " (" + invite.getInviter().getId() + ")");

		EmbedBuilder builder = new EmbedBuilder()
				.setTitle("Invite Create")
				.addField("Invite (" + invite.getCode() + ") has been created by " + invite.getInviter().getAsMention() + " (" + invite.getInviter().getId() + ")","", false)
				.setThumbnail(invite.getInviter().getEffectiveAvatarUrl())
				.setColor(new Color(200, 50, 200));

		INVITE_LOGGING_OUTPUT.embed(builder.build(), embedReplacement);
	}

	static List<Invite> oldInvites = new ArrayList<>();

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		Member member = event.getMember();
		List<Invite> invites = event.getGuild().retrieveInvites().complete();

		if(invites.isEmpty()) return;
		if(oldInvites.isEmpty()) oldInvites = invites;

		TextChannel channel = event.getGuild().getTextChannelById(inviteLoggingChannelId);
		if(channel == null) {
			OUTPUT.warning("Invalid channel id for invite logging");
			OUTPUT.cancelled("Skipping discord logging");
		}

		boolean foundInvite = false;
		List<String> embedReplacement = new ArrayList<>();
		for(Invite invite : invites) {
			for(Invite oldInvite : oldInvites) {
				if(!invite.getCode().equals(oldInvite.getCode())) continue;
				if(invite.getUses() == oldInvite.getUses()) continue;

				embedReplacement.add("Invite (" + invite.getCode() + ") has been used by user " + event.getUser().getName());
				embedReplacement.add("Invite was created by " + invite.getInviter().getName());

				foundInvite = true;
				use(invite, event.getMember());

				break;
			}
			if(foundInvite) break;
		}

		if(!foundInvite) {
			vanity(member);
			
		}

		oldInvites = invites;
	}

	public void vanity(Member member) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("The vanity invite has been used by member " + member.getEffectiveName() + " (" + member.getId() + ")");
		EmbedBuilder builder = new EmbedBuilder()
				.setTitle("Vanity Use")
				.addField("The vanity invite has been used by member " + member.getEffectiveName() + " (" + member.getId() + ")", "", false)
				.setThumbnail(member.getUser().getEffectiveAvatarUrl())
				.setColor(new Color(200, 0, 200));
		
		INVITE_LOGGING_OUTPUT.embed(builder.build(), embedReplacement);
	}
	
	public void use(Invite invite, Member user) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Invite (" + invite.getCode() + ") has been used by user " + user.getEffectiveName() + " (" + user.getId() + ")");
		embedReplacement.add("Invite was created by " + invite.getInviter().getAsMention() + " (" + invite.getInviter().getId() + ")");
		EmbedBuilder builder = new EmbedBuilder()
				.setTitle("Invite Use")
				.addField("Invite (" + invite.getCode() + ") has been used by user " + user.getEffectiveName() + " (" + user.getId() + ")", "Invite was created by " + invite.getInviter().getAsMention() + " (" + invite.getInviter().getId() + ")", false)
				.setThumbnail(user.getUser().getEffectiveAvatarUrl())
				.setColor(new Color(200, 0, 200));
		INVITE_LOGGING_OUTPUT.embed(builder.build(), embedReplacement);
	}
	
}
