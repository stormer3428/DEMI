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
		this.commandName = string;

		this.CONFIG_KEYS.add(new Key("requireAdminPerms", "true"));
		this.CONFIG_KEYS.add(new Key("requireRoles", "false"));
		this.CONFIG_KEYS.add(new Key("requiredRoles", "[]"));
		this.CONFIG_KEYS.add(new Key("whitelistEnabled", "false"));
		this.CONFIG_KEYS.add(new Key("whitelist", "[]"));
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.requireAdminPerms = this.CONFIG.get("requireAdminPerms").equalsIgnoreCase("true");
		this.OUTPUT.trace("requireAdminPerms : " + (this.requireAdminPerms ? "true" : false), this.PRINT_STACK_TRACE);
		this.requireRoles = this.CONFIG.get("requireRoles").equalsIgnoreCase("true");
		this.OUTPUT.trace("requireRoles : " + (this.requireRoles ? "true" : false), this.PRINT_STACK_TRACE);
		this.requiredRoles = roles();
		if(this.requiredRoles == null) return;
		if(this.requiredRoles.isEmpty()) {
			if(this.requireRoles) {
				this.OUTPUT.warning("requireRoles was set to true but no required roles were set");
				this.OUTPUT.cancelled("setting requireRoles to false internally");
				this.requireRoles = false;
			}
		}else {
			this.OUTPUT.trace("requireRoles : ", this.PRINT_STACK_TRACE);
			for(Long role : this.requiredRoles) {
				this.OUTPUT.trace("- " + role, this.PRINT_STACK_TRACE);
			}
		}
		this.whitelistEnabled = this.CONFIG.get("whitelistEnabled").equalsIgnoreCase("true");
		this.OUTPUT.trace("whitelistEnabled : " + (this.whitelistEnabled ? "true" : false), this.PRINT_STACK_TRACE);
		this.whitelist = whitelist();
		if(this.whitelist == null) return;
		if(this.whitelist.isEmpty()) {
			if(this.whitelistEnabled) {
				this.OUTPUT.warning("whitelistEnabled was set to true but no whitelist was set");
				this.OUTPUT.cancelled("disabming module");
				this.whitelistEnabled = false;
				Demi.disableModule(this);
				return;
			}
		}else {
			this.OUTPUT.trace("whitelist : ", this.PRINT_STACK_TRACE);
			for(Long member : this.whitelist) {
				this.OUTPUT.trace("- " + member, this.PRINT_STACK_TRACE);
			}
		}
	}

	private List<Long> whitelist() {
		List<String> whitelistString = this.CONFIG.getList("whitelist");
		List<Long> returnedWhitelist = new ArrayList<>();
		try {
			for(String str : whitelistString) returnedWhitelist.add(Long.parseLong(str));
			return returnedWhitelist;
		} catch (NumberFormatException e) {
			this.OUTPUT.error("Error while retrieving whitelist");
			this.OUTPUT.error("Expected an array of user ids but got ("+this.CONFIG.get("whitelist")+")");
			handleTrace(e);
			if(this.whitelistEnabled) {
				this.OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
			}else {
				this.OUTPUT.cancelled("whitelistEnabled is set to false, skipping");
			}
			return null;
		}

	}

	private List<Long> roles() {
		List<String> rolesString = this.CONFIG.getList("requiredRoles");
		List<Long> roles = new ArrayList<>();
		try {
			for(String str : rolesString) roles.add(Long.parseLong(str));
			return roles;
		} catch (NumberFormatException e) {
			this.OUTPUT.error("Error while retrieving required roles");
			this.OUTPUT.error("Expected an array of role ids but got ("+this.CONFIG.get("requiredRoles")+")");
			handleTrace(e);
			if(this.requireRoles) {
				this.OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
			}else {
				this.OUTPUT.cancelled("requireRoles is set to false, skipping");
			}
			return null;
		}
	}

	@Override
	public String getName() {
		return this.commandName;
	}
	
	public List<String> getAliases(){
		return this.aliases;
	}

	public boolean canUseCommand(Member member) {
		if(member == null) return true;
		if(this.whitelistEnabled && !this.whitelist.contains(member.getIdLong())) return false;
		if(this.requireAdminPerms && !member.hasPermission(Permission.ADMINISTRATOR)) return false;
		if(this.requireRoles) {
			List<Long> rolesLong = new ArrayList<>();
			for(Role role : member.getRoles()) rolesLong.add(role.getIdLong());
			if(!rolesLong.containsAll(this.requiredRoles)) return false;
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

	public abstract String getUsage();
}
