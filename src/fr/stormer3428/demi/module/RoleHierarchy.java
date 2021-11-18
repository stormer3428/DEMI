package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class RoleHierarchy extends Module{

	private class HierarchyRule {

		private long roleId;
		private List<String> requiredRoles = new ArrayList<>();

		public boolean canWield(Member member) {
			List<Role> roles = member.getRoles();
			for(String requiredRoleId : requiredRoles) {

				boolean has = false;
				for(Role role : roles) {
					OUTPUT.trace("match : `"+requiredRoleId+" "+role.getIdLong()+"`", RoleHierarchy.this.PRINT_STACK_TRACE);
					if(role.getId().equals(requiredRoleId)){
						OUTPUT.trace("matched!", RoleHierarchy.this.PRINT_STACK_TRACE);
						has = true;
						break;
					}
				}
				if(!has) return false;

			}
			return true;
		}

		public long getRoleId() {
			return roleId;
		}

		public HierarchyRule(long roleId, List<String> requiredRoles) {
			this(roleId);
			this.requiredRoles = requiredRoles;
		}

		public HierarchyRule(long roleId) {
			this.roleId = roleId;
		}

	}

	private List<HierarchyRule> rules = new ArrayList<>();

	private void updateRules() {
		rules.clear();
		String output = "";
		for(String key : HIERARCHY_DATABASE.getKeys()) {
			long id = -1;
			try {
				id = Long.parseLong(key);
			} catch (NumberFormatException e) {continue;}

			List<String> value = HIERARCHY_DATABASE.getList(key);
			List<String> parents = new ArrayList<>();

			for(String s : value) try {
				parents.add(Long.parseLong(s) + "");
			} catch (NumberFormatException e) {}
			if(parents.isEmpty()) continue;

			List<String> parentsCopy = new ArrayList<>();
			parentsCopy.addAll(parents);
			rules.add(new HierarchyRule(id, parentsCopy));
			output = output + "\n" + "`role "+id+" : requiredParents : [";
			while (!parents.isEmpty()) output = output + parents.remove(0) + (parents.isEmpty() ? "" : ",");
			output = output +"]`";
		}
		OUTPUT.info(output);
	}

	private IO HIERARCHY_DATABASE;

	private boolean blankShot = true;

	private boolean enableCheckCooldownMS;
	private long checkCooldownMS;

	private List<String> onCoolDownUsers = new ArrayList<>();
	private long lastWiped = System.currentTimeMillis();

	public RoleHierarchy() {
		super(new File("RoleHierarchy.cfg"));

		this.CONFIG_KEYS.add(new Key("blankShot", "true", "true = dry-run, false = normal runtime"));

		this.CONFIG_KEYS.add(new Key("enableCheckCooldownMS", "true", "whether the module should have a cooldown between each check (per user cooldown)"));
		this.CONFIG_KEYS.add(new Key("checkCooldownMS", "60000", "the cooldown in milliseconds between each check"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return getName() + " is a module that will create a hierarchy between roles, for exepmle if you want roles to only be accessible to nitro boosters, you make the nitro booster role parent to the roles you want to be exclusive";
	}

	@Override
	public void onEnable() {
		super.onEnable();

		this.HIERARCHY_DATABASE = new IO(new File("roleHierarchy" + Demi.i.getServerID() + ".demidb"), new ArrayList<>(), true);

		this.enableCheckCooldownMS = this.CONFIG.get("enableCheckCooldownMS").equalsIgnoreCase("true");
		this.OUTPUT.trace("enableCheckCooldownMS : " + this.enableCheckCooldownMS, this.PRINT_STACK_TRACE);

		this.blankShot = this.CONFIG.get("blankShot").equalsIgnoreCase("true");
		this.OUTPUT.trace("blankShot : " + this.blankShot, this.PRINT_STACK_TRACE);

		try {
			this.checkCooldownMS = Long.parseLong(this.CONFIG.get("checkCooldownMS"));
			if(this.checkCooldownMS <= 0) {
				this.OUTPUT.warning("enableExpIncreaseCooldownMS was set to true with checkCooldownMS set to " + this.checkCooldownMS);
				this.OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
				return;
			}
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of checkCooldownMS, expected an integer");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("checkCooldownMS : " + this.checkCooldownMS, this.PRINT_STACK_TRACE);

		updateRules();

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		updateCooldownCache();
		if(event.getAuthor().isBot()) return;
		String memberUID = event.getAuthor().getId();
		this.OUTPUT.trace("Message received from member " + memberUID, this.PRINT_STACK_TRACE);
		if(enableCheckCooldownMS && this.onCoolDownUsers.contains(memberUID)) {
			this.OUTPUT.trace("on cooldown...", this.PRINT_STACK_TRACE);
			return;
		}
		Member member = event.getMember();
		if(member == null) return;

		this.OUTPUT.trace("checking roles", this.PRINT_STACK_TRACE);
		List<Role> roles = member.getRoles();
		for(HierarchyRule rule : rules) {
			this.OUTPUT.trace("checking rule " + rule.roleId, this.PRINT_STACK_TRACE);
			for(Role role : roles) {
				if(rule.getRoleId() == role.getIdLong()){
					this.OUTPUT.trace("found ruled role", this.PRINT_STACK_TRACE);
					if(!rule.canWield(member)) {
						OUTPUT.info("member " + member.getEffectiveName() + " has a role they cannot wield (" + role.getName() +"), removing...");
						if(!blankShot) member.getGuild().removeRoleFromMember(member, role).queue();
						else OUTPUT.info("blankshotted");
					}
				}
			}
		}
		this.onCoolDownUsers.add(memberUID);
	}

	private void updateCooldownCache() {
		while(System.currentTimeMillis() - this.lastWiped >= this.checkCooldownMS) {
			this.lastWiped += this.checkCooldownMS;
			this.onCoolDownUsers.clear();
		}
	}


}
