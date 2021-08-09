package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public abstract class CommandModule extends Module{

	private boolean requireAdminPerms;
	private boolean requireRoles;
	private List<Long> requiredRoles;
	private boolean whitelistEnabled;
	private List<Long> whitelist = new ArrayList<>();

	protected List<String> aliases = new ArrayList<>();
	protected String commandName;

	public CommandModule(String string) {
		super(new File("commands/" + string + ".cfg"));
		commandName = string;

		CONFIG_KEYS.add(new Key("requireAdminPerms", "true"));
		CONFIG_KEYS.add(new Key("requireRoles", "false"));
		CONFIG_KEYS.add(new Key("requiredRoles", "[]"));
		CONFIG_KEYS.add(new Key("whitelistEnabled", "false"));
		CONFIG_KEYS.add(new Key("whitelist", "[]"));
	}

	@Override
	public void onEnable() {
		super.onEnable();
		requireAdminPerms = CONFIG.get("requireAdminPerms").equalsIgnoreCase("true");
		OUTPUT.trace("requireAdminPerms : " + (requireAdminPerms ? "true" : false));
		requireRoles = CONFIG.get("requireRoles").equalsIgnoreCase("true");
		OUTPUT.trace("requireRoles : " + (requireRoles ? "true" : false));
		requiredRoles = roles();
		if(requiredRoles == null) return;
		if(requiredRoles.isEmpty()) {
			if(requireRoles) {
				OUTPUT.warning("requireRoles was set to true but no required roles were set");
				OUTPUT.cancelled("setting requireRoles to false internally");
				requireRoles = false;
			}
		}else {
			OUTPUT.trace("requireRoles : ");
			for(Long role : requiredRoles) {
				OUTPUT.trace("- " + role);
			}
		}
		whitelistEnabled = CONFIG.get("whitelistEnabled").equalsIgnoreCase("true");
		OUTPUT.trace("whitelistEnabled : " + (whitelistEnabled ? "true" : false));
		whitelist = whitelist();
		if(whitelist == null) return;
		if(whitelist.isEmpty()) {
			if(whitelistEnabled) {
				OUTPUT.warning("whitelistEnabled was set to true but no whitelist was set");
				OUTPUT.cancelled("disabming module");
				whitelistEnabled = false;
				Demi.disableModule(this);
				return;
			}
		}else {
			OUTPUT.trace("whitelist : ");
			for(Long member : whitelist) {
				OUTPUT.trace("- " + member);
			}
		}
	}

	private List<Long> whitelist() {
		List<String> whitelistString = CONFIG.getList("whitelist");
		List<Long> whitelist = new ArrayList<>();
		try {
			for(String str : whitelistString) whitelist.add(Long.parseLong(str));
			return whitelist;
		} catch (NumberFormatException e) {
			OUTPUT.error("Error while retrieving whitelist");
			OUTPUT.error("Expected an array of user ids but got ("+CONFIG.get("whitelist")+")");
			handleTrace(e);
			if(whitelistEnabled) {
				OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
			}else {
				OUTPUT.cancelled("whitelistEnabled is set to false, skipping");
			}
			return null;
		}

	}

	private List<Long> roles() {
		List<String> rolesString = CONFIG.getList("requiredRoles");
		List<Long> roles = new ArrayList<>();
		try {
			for(String str : rolesString) roles.add(Long.parseLong(str));
			return roles;
		} catch (NumberFormatException e) {
			OUTPUT.error("Error while retrieving required roles");
			OUTPUT.error("Expected an array of role ids but got ("+CONFIG.get("requiredRoles")+")");
			handleTrace(e);
			if(requireRoles) {
				OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
			}else {
				OUTPUT.cancelled("requireRoles is set to false, skipping");
			}
			return null;
		}
	}

	@Override
	public String getName() {
		return commandName;
	}
	
	public List<String> getAliases(){
		return aliases;
	}

	public boolean canUseCommand(Member member) {
		if(whitelistEnabled && !whitelist.contains(member.getIdLong())) return false;
		if(requireAdminPerms && !member.hasPermission(Permission.ADMINISTRATOR)) return false;
		if(requireRoles) {
			List<Long> rolesLong = new ArrayList<>();
			for(Role role : member.getRoles()) rolesLong.add(role.getIdLong());
			if(!rolesLong.containsAll(requiredRoles)) return false;
		}
		return true;
	}
	
	@Override
	public boolean onCommand(DemiCommandReceiveEvent event) {
		if(!event.getCommand().equalsIgnoreCase(getName()) && !getAliases().contains(event.getCommand().toLowerCase())) return false;
		if(event.getMessageReceivedEvent() == null) {
			runCommand(event);
			return true;
		}
		Member member = event.getMessageReceivedEvent().getMember();
		if(canUseCommand(member)) {
			runCommand(event);
		}
		return true;

	}

	protected abstract void runCommand(DemiCommandReceiveEvent event);
}
