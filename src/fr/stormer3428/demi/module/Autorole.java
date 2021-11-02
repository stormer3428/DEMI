package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Autorole extends Module{

	private int COOLDOWN;
	private List<Long> ROLES = new ArrayList<>();
	private boolean LISTENSTOBOT;
	private Set<Long> cooldownSet = new HashSet<>();
	private long lastWiped = System.currentTimeMillis();

	public Autorole() {
		super(new File("autorole.cfg"));
		
		this.CONFIG_KEYS.add(new Key("cooldown", "300000", "The cooldown between each application of the autoroles"));
		this.CONFIG_KEYS.add(new Key("roles", "[]", "an array of the id's of the roles to automatically apply"));
		this.CONFIG_KEYS.add(new Key("listensToBots", "false", "should autoroles be applied to bots"));
		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getName() {
		return "Autorole";
	}

	@Override
	public String getDescription() {
		return "A module that automatically add roles whenever a user sends a message with a cooldown to avoid spending too much resources  on it."
				+ "This can be used as a mean to check whether a member has a verified account as having a role bypasses the requirement for having a verified account."
				+ "Auto roles may be roles such as category roles or something like a member role.";
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.COOLDOWN = cooldown();
		if(this.COOLDOWN == -1) return;
		this.OUTPUT.trace("cooldown : " + this.COOLDOWN, this.PRINT_STACK_TRACE);
		this.LISTENSTOBOT = listensToBots();
		this.OUTPUT.trace("listensToBots : " + (this.LISTENSTOBOT ? "true" : "false"), this.PRINT_STACK_TRACE);
		this.ROLES = roles();
		if(this.ROLES == null) return;
		this.OUTPUT.trace("roles :", this.PRINT_STACK_TRACE);
		for(Long role : this.ROLES) this.OUTPUT.info(role + "");
		
		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event){
		if(System.currentTimeMillis() - this.lastWiped > cooldown()){
			this.lastWiped = System.currentTimeMillis();
			this.cooldownSet.clear();
		}
		if(!this.LISTENSTOBOT && event.getAuthor().isBot()) return;
		Message message = event.getMessage();
		if(message.getChannel().getType() != ChannelType.TEXT) return;

		TextChannel channel = event.getChannel();
		if(channel == null) return;
		Guild guild = event.getGuild();
		if(!guild.getId().equals(Demi.i.getServerID())) return;
		Member member = event.getMember();

		if(Demi.i.getDebugMode() && !Demi.i.isDebugger(member.getIdLong())) return;

		this.OUTPUT.info("Message received from member " + member.getEffectiveName());
		
		if(this.cooldownSet.contains(member.getIdLong())) return;
		
		List<Role> memberRoles = member.getRoles();
		List<Role> autoRoles = new ArrayList<>();
		for(Long autoRoleID : this.ROLES) {
					autoRoles.add(guild.getRoleById(autoRoleID));		
		}
		
		for(Role role : autoRoles) {
			if(memberRoles != null && !memberRoles.isEmpty() &&  memberRoles.contains(role)) continue;
			this.OUTPUT.info("Member " + member.getEffectiveName() + " is missing role " + role.getName());
			member.getGuild().addRoleToMember(member, role).complete();
			this.OUTPUT.ok("Role " + role.getName() + " added!");
		}
	}	

	private boolean listensToBots() {
		return this.CONFIG.get("listensToBots").equalsIgnoreCase("true");
	}	

	private int cooldown() {
		int i = -1;
		try{
			i = Integer.parseInt(this.CONFIG.get("cooldown"));
		}catch (NumberFormatException e) {
			handleTrace(e);
		}
		if(i > 0) return i;
		this.OUTPUT.error("Failed to retrieve parameter (cooldown) in config file " + getName());
		this.OUTPUT.warning("Retrieved value : " + this.CONFIG.get("cooldown"));
		this.OUTPUT.warning("Expected a strictly positive number");
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
		return -1;
	}

	public List<Long> roles() {
		List<Long> list = new ArrayList<>();
		List<String> stringList = this.CONFIG.getList("roles");
		try {
			for(String role : stringList) list.add(Long.parseLong(role));
			if(!list.isEmpty()) return list;
			this.OUTPUT.warning("Parameter (roles) in config file " + getName() + " was found to be empty, disabling module");
			Demi.disableModule(this);
			return null;
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Failed to retrieve parameter (roles) in config file " + getName());
			this.OUTPUT.warning("Retrieved value : " + this.CONFIG.getList("roles"));
			this.OUTPUT.warning("Expected an array of role IDs");
			
			handleTrace(e);
			
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return null;
		}
	}
}
