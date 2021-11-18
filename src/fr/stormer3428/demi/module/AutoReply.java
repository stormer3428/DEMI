package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AutoReply extends Module{

	private boolean requireRoles;
	private List<Long> requiredRoles;
	private boolean whitelistEnabled;
	private List<Long> whitelist = new ArrayList<>();

	HashMap<String, String> autoReplies = new HashMap<>();
	private IO AUTOREPLY_DATABASE;

	public AutoReply() {
		super(new File("AutoReply.cfg"));

		this.CONFIG_KEYS.add(new Key("requireRoles", "false", 
				"Whether the module will look if player has the required roles before replying"));
		this.CONFIG_KEYS.add(new Key("requiredRoles", "[]",
				"The roles required for demi to respond"));
		this.CONFIG_KEYS.add(new Key("whitelistEnabled", "false",
				"Whether only the user should be in the whitelist to be replied to"));
		this.CONFIG_KEYS.add(new Key("whitelist", "[]",
				"The list of users who can be replied to"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public void onEnable() {
		super.onEnable();

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

		this.AUTOREPLY_DATABASE = new IO(new File("autoreply.demidb"), new ArrayList<>(), true);
		autoReplies = AUTOREPLY_DATABASE.getAll();

		OUTPUT.ok("Succesfully loaded all regexes");
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
	public String getDescription() {
		return "AutoReply is a module that will simply make demy autoreply to certain regexes in the config";
	}

	public boolean shouldReply(Member member) {
		if(member == null) return true;
		if(this.whitelistEnabled && !this.whitelist.contains(member.getIdLong())) return false;
		if(this.requireRoles) {
			List<Long> rolesLong = new ArrayList<>();
			for(Role role : member.getRoles()) rolesLong.add(role.getIdLong());
			if(!rolesLong.containsAll(this.requiredRoles)) return false;
		}
		return true;
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel channel = event.getChannel();
		if(channel == null) return;
		Member member = event.getMember();
		if(member == null) return;
		if(!shouldReply(member)) return;

		String message = event.getMessage().getContentRaw();

		for(String regex : autoReplies.keySet()) if(message.matches(regex)) {
			channel.sendMessage(autoReplies.get(regex)).complete();
			return;
		}
	}
}
