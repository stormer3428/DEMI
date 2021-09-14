package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LevelRoleCalculator extends Module{

	private IO ROLES_DATABASE;

	private boolean enableCache;
	private HashMap<Integer, Role> cachedLevelRoleMap;
	private HashMap<Role, Integer> cachedRolelevelMap;
	private List<Role> cachedLevelRoles;

	private Set<Long> cooldownSet = new HashSet<>();
	private int COOLDOWN;
	private long lastWiped = System.currentTimeMillis();
	

	private boolean keepOnlyLatestRole;

	public LevelRoleCalculator() {
		super(new File("level/levelRoleCalculator.cfg"));

		this.CONFIG_KEYS.add(new Key("enableCache", "false"));
		this.CONFIG_KEYS.add(new Key("keepOnlyLatestRole", "true"));
		this.CONFIG_KEYS.add(new Key("cooldown", "300000"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);	
	}

	@Override
	public String getName() {
		return "LevelRoleCalculator";
	}

	@Override
	public String getDescription() {
		return "A module that handle the computation needed to know what role should be given for what level";
	}
	@Override
	public void onEnable() {
		super.onEnable();
		this.enableCache = this.CONFIG.get("enableCache").equalsIgnoreCase("true");
		this.OUTPUT.trace("enableCache : " + this.enableCache, this.PRINT_STACK_TRACE);
		this.keepOnlyLatestRole = this.CONFIG.get("keepOnlyLatestRole").equalsIgnoreCase("true");
		this.OUTPUT.trace("keepOnlyLatestRole : " + this.keepOnlyLatestRole, this.PRINT_STACK_TRACE);
		
		this.COOLDOWN = cooldown();
		if(this.COOLDOWN == -1) return;
		this.OUTPUT.trace("cooldown : " + this.COOLDOWN, this.PRINT_STACK_TRACE);

		this.ROLES_DATABASE = new IO(new File("level/levelRolesdb" + Demi.i.getServerID() + ".cfg"), new ArrayList<>(), true);
		
		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event){
		if(System.currentTimeMillis() - this.lastWiped > cooldown()){
			this.lastWiped = System.currentTimeMillis();
			this.cooldownSet.clear();
		}
		if(event.getAuthor().isBot()) return;
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
		
		//applyLevelRole(, member);

		
	}	
	
	public void updateLevelRoles(Member member, int level) {
		if(System.currentTimeMillis() - this.lastWiped > cooldown()){
			this.lastWiped = System.currentTimeMillis();
			this.cooldownSet.clear();
		}
		
		if(Demi.i.getDebugMode() && !Demi.i.isDebugger(member.getIdLong())) return;

		this.OUTPUT.info("Message received from member " + member.getEffectiveName());
		
		if(this.cooldownSet.contains(member.getIdLong())) return;
		
		applyLevelRole(level, member);
		this.cooldownSet.add(member.getIdLong());
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
	
	public int retrieveLevelFromRoles(String UID) {
		if(!enabled()) return -1;
		Member member = Demi.jda.getGuildById(Demi.i.getServerID()).retrieveMemberById(UID).complete();
		if(member == null) {
			this.OUTPUT.warning("Attempted te retrieve level from roles of non guild member, returning 0");
			return 0;
		}
		return retrieveLevelFromRoles(member);
	}

	public int retrieveLevelFromRoles(Member member) {
		if(!enabled()) return -1;
		List<Role> memberRoles = member.getRoles();
		if(memberRoles.isEmpty()) return 0;
		HashMap<Role, Integer> roleLevelMap = roleLevelMap(true);
		if(roleLevelMap == null) return -1;
		for(Role levelRole : roleLevelMap.keySet()) if(memberRoles.contains(levelRole)) return roleLevelMap.get(levelRole);
		return 0;
	}

	public Role getLatestObtainedRole(int level) {
		HashMap<Integer, Role> levelRoleMap = levelRoleMap(false);
		if(levelRoleMap == null) return null;
		int highestRoleLevel = -1;
		for(int roleLevel : levelRoleMap.keySet()) {
			if(roleLevel <= level && roleLevel > highestRoleLevel) {
				highestRoleLevel = roleLevel;
			}
		}
		if(highestRoleLevel != -1) return levelRoleMap.get(highestRoleLevel);
		return null;
	}

	public List<Role> getObtainedRoles(int level) {
		HashMap<Integer, Role> levelRoleMap = levelRoleMap(false);
		if(levelRoleMap == null) return null;
		List<Role> obtainedRoles = new ArrayList<>();
		for(int roleLevel : levelRoleMap.keySet()) if(roleLevel <= level) obtainedRoles.add(levelRoleMap.get(roleLevel));
		return obtainedRoles;
	}

	public void applyLevelRole(int level, Member member) {
		//if(member.getIdLong() != 286484960326451200l) return;
		Role latestAttainedRole = getLatestObtainedRole(level);
		if(latestAttainedRole == null) {
			removeAllLevelRoles(member);
			return;
		}
		List<Role> rolesToHave = new ArrayList<>();
		if(this.keepOnlyLatestRole) rolesToHave.add(latestAttainedRole);
		else rolesToHave.addAll(getObtainedRoles(level));
		
		List<Role> memberRoles = member.getRoles();
		for(Role levelRole : levelRoles(false)) {
			if(rolesToHave.contains(levelRole) && !memberRoles.contains(levelRole)) {
				this.OUTPUT.info("Member " + member.getEffectiveName() + "(" + member.getId() + ") is missing levelrole " + levelRole.getName());
				member.getGuild().addRoleToMember(member, levelRole).queue();
				this.OUTPUT.info("levelRole added!");
			}
			else if(!rolesToHave.contains(levelRole) && memberRoles.contains(levelRole)) {
				this.OUTPUT.info("Member " + member.getEffectiveName() + "(" + member.getId() + ") has levelrole " + levelRole.getName() + " but is level " + level);
				member.getGuild().removeRoleFromMember(member, levelRole).queue();
				this.OUTPUT.info("levelRole removed!");
			}
		}
	}

	@SuppressWarnings("unused")
	private void removeLevelRoles(Member member, Role exception) {
		List<Role> memberRoles = member.getRoles();
		for(Role levelRole : levelRoles(false)) if(!levelRole.equals(exception) && memberRoles.contains(levelRole)) member.getGuild().removeRoleFromMember(member, levelRole).queue();
	}

	@SuppressWarnings("unused")
	private void removeLevelRoles(Member member, List<Role> exceptions) {
		List<Role> memberRoles = member.getRoles();
		for(Role levelRole : levelRoles(false)) if(!exceptions.contains(levelRole) && memberRoles.contains(levelRole)) member.getGuild().removeRoleFromMember(member, levelRole).queue();
	}

	private void removeAllLevelRoles(Member member) {
		List<Role> memberRole = member.getRoles();
		for(Role levelRole : levelRoles(false)) if(memberRole.contains(levelRole)) member.getGuild().removeRoleFromMember(member, levelRole).queue();
	}

	private HashMap<Role, Integer> roleLevelMap(boolean returnNullIfError) {
		if(this.enableCache && this.cachedRolelevelMap != null) return this.cachedRolelevelMap;
		HashMap<Integer, Role> levelRolesIDs = levelRoleMap(returnNullIfError);
		if(levelRolesIDs == null) return null;
		HashMap<Role, List<Integer>> reversedMap = new HashMap<>();
		HashMap<Role, Integer> roleLevelIDs = new HashMap<>();
		for(Integer key : levelRolesIDs.keySet()) {
			Role reversedKey = levelRolesIDs.get(key);
			if(!reversedMap.containsKey(reversedKey)) reversedMap.put(reversedKey, new ArrayList<>());
			reversedMap.get(reversedKey).add(key);
		}

		for(Role key : reversedMap.keySet()) {
			List<Integer> list = reversedMap.get(key);
			if(list.size() > 1 && returnNullIfError) return null;
			roleLevelIDs.put(key, list.get(0));
		}
		if(this.enableCache) this.cachedRolelevelMap = roleLevelIDs;
		return roleLevelIDs;
	}

	private HashMap<Integer, Role> levelRoleMap(boolean returnNullIfError) {
		if(this.enableCache && this.cachedLevelRoleMap != null) return this.cachedLevelRoleMap;
		HashMap<String, String> levelRolesIDsString = this.ROLES_DATABASE.getSortedAll();
		if(levelRolesIDsString == null) {
			this.OUTPUT.error("Error, unable to generate SingleReversedMap for file " + this.ROLES_DATABASE.getFileName());
			this.OUTPUT.error("It is crucial for this module to function properly, deactivating module...");
			Demi.disableModule(this);
			return null;
		}
		HashMap<Integer, Role> levelRolesMap = new HashMap<>();
		for(String levelString : levelRolesIDsString.keySet()) {
			int level;
			try {
				level = Integer.parseInt(levelString);
			}catch (NumberFormatException e) {
				this.OUTPUT.error("Error while parsing level value for levelrole, expected a number but got " + levelString);
				handleTrace(e);
				if(returnNullIfError) return null;
				continue;
			}
			long roleId;
			try {
				roleId = Long.parseLong(levelRolesIDsString.get(levelString));
			}catch (NumberFormatException e) {
				this.OUTPUT.error("Error while parsing id value for levelRole, expected an integer but got " + levelRolesIDsString.get(levelString));
				handleTrace(e);
				if(returnNullIfError) return null;
				continue;
			}
			Role role = Demi.jda.getGuildById(Demi.i.getServerID()).getRoleById(roleId);
			if(role == null) {
				this.OUTPUT.error("Error while attempting to retrieve level, invalid id " + levelRolesIDsString.get(levelString));
				if(returnNullIfError) return null;
				continue;
			}
			levelRolesMap.put(level, role);
		}
		if(this.enableCache) this.cachedLevelRoleMap = levelRolesMap;
		return levelRolesMap;
	}

	private List<Role> levelRoles(boolean returnNullIfError){
		if(this.enableCache && this.cachedLevelRoles != null) return this.cachedLevelRoles;
		List<String> roleIdsString = this.ROLES_DATABASE.getValues();
		if(roleIdsString.isEmpty()) return new ArrayList<>();
		List<Long> roleIds = new ArrayList<>();
		for(String roleIdString : roleIdsString) {
			try {
				roleIds.add(Long.parseLong(roleIdString));
			} catch (NumberFormatException e) {
				this.OUTPUT.error("Error while parsing id of levelRole, expected an integer but got " + roleIdString);
				handleTrace(e);
				if(returnNullIfError) return null;
			}
		}
		if(roleIds.isEmpty()) return new ArrayList<>();
		List<Role> levelRoles = new ArrayList<>();
		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		for(Long roleId : roleIds) {
			Role role = guild.getRoleById(roleId);
			if(role == null) {
				this.OUTPUT.error("Error while attempting to retrieve level, invalid id " + roleId);
				if(returnNullIfError) return null;
				continue;
			}
			levelRoles.add(role);
		}
		return levelRoles;
	}

}

