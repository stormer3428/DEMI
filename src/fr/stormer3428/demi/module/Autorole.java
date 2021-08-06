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
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Autorole extends Module{

	private int COOLDOWN;
	private List<Long> ROLES = new ArrayList<>();
	private boolean LISTENSTOBOT;
	private Set<Long> cooldownSet = new HashSet<>();
	private long lastWiped = System.currentTimeMillis();

	public Autorole() {
		super(new File("autorole.cfg"));
		
		CONFIG_KEYS.add(new Key("cooldown", "300000"));
		CONFIG_KEYS.add(new Key("roles", "[]"));
		CONFIG_KEYS.add(new Key("listensToBots", "false"));
		if(!initialConfigIOCreation()) return;
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
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		COOLDOWN = cooldown();
		if(COOLDOWN == -1) return;
		OUTPUT.info("cooldown : " + COOLDOWN);
		LISTENSTOBOT = listensToBots();
		OUTPUT.info("listensToBots : " + (LISTENSTOBOT ? "true" : "false"));
		ROLES = roles();
		if(ROLES == null) return;
		OUTPUT.info("roles :");
		for(Long role : ROLES) OUTPUT.info(role + "");
		OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		if(System.currentTimeMillis() - lastWiped > cooldown()){
			lastWiped = System.currentTimeMillis();
			cooldownSet.clear();
		}
		if(!LISTENSTOBOT && event.getAuthor().isBot()) return;
		Message message = event.getMessage();
		if(message.getChannel().getType() != ChannelType.TEXT) return;

		TextChannel channel = event.getTextChannel();
		if(channel == null) return;
		Guild guild = event.getGuild();
		if(!guild.getId().equals(Demi.i.getServerID())) return;
		Member member = event.getMember();

		if(Demi.i.getDebugMode() && !Demi.i.isDebugger(member.getIdLong())) return;

		OUTPUT.info("Message received from member " + member.getEffectiveName());
		
		if(cooldownSet.contains(member.getIdLong())) return;
		
		List<Role> memberRoles = member.getRoles();
		List<Role> autoRoles = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();
		for(Long autoRoleID : ROLES) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					autoRoles.add(guild.getRoleById(autoRoleID));		
				}
			}));
		}
		for(Thread thread : threads) thread.start();
		for(Thread thread : threads) try {
			thread.wait();
		} catch (InterruptedException e) {
			OUTPUT.error(getName() + " caught an error while multithreading");
			handleTrace(e);
		}
		
		for(Role role : autoRoles) {
			if(memberRoles != null && !memberRoles.isEmpty() &&  memberRoles.contains(role)) continue;
			OUTPUT.info("Member " + member.getEffectiveName() + " is missing role " + role.getName());
			member.getGuild().addRoleToMember(member, role).complete();
			OUTPUT.ok("Role " + role.getName() + " added!");
		}
	}	

	private boolean listensToBots() {
		return CONFIG.get("listensToBots").equalsIgnoreCase("true");
	}	

	private int cooldown() {
		int i = -1;
		try{
			i = Integer.parseInt(CONFIG.get("cooldown"));
		}catch (NumberFormatException e) {
			handleTrace(e);
		}
		if(i > 0) return i;
		OUTPUT.error("Failed to retrieve parameter (cooldown) in config file " + getName());
		OUTPUT.warning("Retrieved value : " + CONFIG.get("cooldown"));
		OUTPUT.warning("Expected a strictly positive number");
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
		return -1;
	}

	public List<Long> roles() {
		List<Long> list = new ArrayList<>();
		List<String> stringList = CONFIG.getList("roles");
		try {
			for(String role : stringList) list.add(Long.parseLong(role));
			if(!list.isEmpty()) return list;
			else {
				OUTPUT.warning("Parameter (roles) in config file " + getName() + " was found to be empty, disabling module");
				Demi.disableModule(this);
				return null;
			}
		}catch (NumberFormatException e) {
			OUTPUT.error("Failed to retrieve parameter (roles) in config file " + getName());
			OUTPUT.warning("Retrieved value : " + CONFIG.getList("roles"));
			OUTPUT.warning("Expected an array of role IDs");
			
			handleTrace(e);
			
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return null;
		}
	}
}
