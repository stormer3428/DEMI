package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiConsole;
import fr.stormer3428.demi.HasConfig;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Autorole extends HasConfig implements Module{

	private int COOLDOWN;
	private List<Long> ROLES = new ArrayList<>();
	private boolean LISTENSTOBOT;
	private Set<Long> cooldownSet = new HashSet<>();
	private long lastWiped = System.currentTimeMillis();

	static {
		new Autorole();
	}
	
	public Autorole() {
		super(new File("autorole.cfg"));
		
		Demi.registerModule(this);
		
		CONFIG_KEYS.add(new Key("enabled", "false"));
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
	public boolean enabled() {
		return CONFIG.get("enabled").equalsIgnoreCase("true");
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		COOLDOWN = cooldown();
		if(COOLDOWN == -1) return;
		DemiConsole.info("cooldown : " + COOLDOWN);
		LISTENSTOBOT = listensToBots();
		DemiConsole.info("listensToBots : " + (LISTENSTOBOT ? "true" : "false"));
		ROLES = roles();
		if(ROLES == null) return;
		DemiConsole.info("roles :");
		for(Long role : ROLES) DemiConsole.info(role + "");
		DemiConsole.ok("Successfully loaded all config parameters");
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

		DemiConsole.info("Message received from member " + member.getEffectiveName());
		
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
			DemiConsole.error(getName() + " caught an error while multithreading");
			handleTrace(e);
		}
		
		for(Role role : autoRoles) {
			if(memberRoles != null && !memberRoles.isEmpty() &&  memberRoles.contains(role)) continue;
			DemiConsole.info("Member " + member.getEffectiveName() + " is missing role " + role.getName());
			member.getGuild().addRoleToMember(member, role).complete();
			DemiConsole.ok("Role " + role.getName() + " added!");
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
		DemiConsole.error("Failed to retrieve parameter (cooldown) in config file " + getName());
		DemiConsole.warning("Retrieved value : " + CONFIG.get("cooldown"));
		DemiConsole.warning("Expected a strictly positive number");
		DemiConsole.warning("Disabling module to prevent errors");
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
				DemiConsole.warning("Parameter (roles) in config file " + getName() + " was found to be empty, disabling module");
				Demi.disableModule(this);
				return null;
			}
		}catch (NumberFormatException e) {
			DemiConsole.error("Failed to retrieve parameter (roles) in config file " + getName());
			DemiConsole.warning("Retrieved value : " + CONFIG.getList("roles"));
			DemiConsole.warning("Expected an array of role IDs");
			
			handleTrace(e);
			
			DemiConsole.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return null;
		}
	}

	protected void handleTrace(Exception e) {
		if(PRINT_STACK_TRACE) {
			DemiConsole.info("printing stack trace");
			e.printStackTrace();
		}else DemiConsole.cancelled(getName() + " module set to not print stack trace");
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
	}
}
