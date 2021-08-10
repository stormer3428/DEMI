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

public class LevelRoleCalculator extends Module{

	private IO ROLES_DATABASE;
	
	private boolean enableCache;
	private HashMap<Integer, Role> cachedLevelRoleMap;
	private HashMap<Role, Integer> cachedRolelevelMap;
	
	
	public LevelRoleCalculator() {
		super(new File("level/levelRoleCalculator"));

		CONFIG_KEYS.add(new Key("enableCache", "false"));
		
		ROLES_DATABASE = new IO(new File("level/rolesdb" + Demi.i.getServerID() + ".demidb"), new ArrayList<>(), true);
		
		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);	
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
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
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		enableCache = CONFIG.get("enableCache").equalsIgnoreCase("true");
		OUTPUT.trace("enableCache : " + enableCache);
	}
	
	public int retrieveLevelFromRoles(String UID) {
		if(!enabled()) return -1;
		Member member = Demi.jda.getGuildById(Demi.i.getServerID()).getMemberById(UID);
		if(member == null) {
			OUTPUT.warning("Attempted te retrieve level of non guild member, returning 0");
			return 0;
		}
		return retrieveLevelFromRoles(member);
	}

	public int retrieveLevelFromRoles(Member member) {
		if(!enabled()) return -1;
		List<Role> memberRoles = member.getRoles();
		if(memberRoles.isEmpty()) return 0;
		HashMap<Role, Integer> roleLevelMap = computeRoleLevelMap(true);
		if(roleLevelMap == null) return -1;
		for(Role levelRole : roleLevelMap.keySet()) if(memberRoles.contains(levelRole)) return roleLevelMap.get(levelRole);
		return 0;
	}

	private HashMap<Role, Integer> computeRoleLevelMap(boolean returnNullIfError) {
		if(enableCache && cachedRolelevelMap != null) return cachedRolelevelMap;
		HashMap<Integer, Role> levelRolesIDs = computeLevelRoleMap(returnNullIfError);
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
		if(enableCache) cachedRolelevelMap = roleLevelIDs;
		return roleLevelIDs;
	}

	private HashMap<Integer, Role> computeLevelRoleMap(boolean returnNullIfError) {
		if(enableCache && cachedLevelRoleMap != null) return cachedLevelRoleMap;
		HashMap<String, String> levelRolesIDsString = ROLES_DATABASE.getAll();
		if(levelRolesIDsString == null) {
			OUTPUT.error("Error, unable to generate SingleReversedMap for file " + ROLES_DATABASE.getFileName());
			OUTPUT.error("It is crucial for this module to function properly, deactivating module...");
			if(returnNullIfError) {
				Demi.disableModule(this);
				return null;
			}
		}
		HashMap<Integer, Role> levelRolesIDs = new HashMap<>();
		for(String levelString : levelRolesIDsString.keySet()) {
			int level;
			try {
				level = Integer.parseInt(levelString);
			}catch (NumberFormatException e) {
				OUTPUT.error("Error while parsing level value for levelrole, expected a number but got " + levelString);
				handleTrace(e);
				if(returnNullIfError) return null;
				else continue;
			}
			long roleId;
			try {
				roleId = Long.parseLong(levelRolesIDsString.get(levelString));
			}catch (NumberFormatException e) {
				OUTPUT.error("Error while parsing id value for levelrole, expected an integer but got " + levelRolesIDsString.get(levelString));
				handleTrace(e);
				if(returnNullIfError) return null;
				else continue;
			}
			Role role = Demi.jda.getGuildById(Demi.i.getServerID()).getRoleById(roleId);
			if(role == null) {
				OUTPUT.error("Error while attempting to retrieve level, invalid id " + levelRolesIDsString.get(levelString));
				if(returnNullIfError) return null;
				else continue;
			}
			levelRolesIDs.put(level, role);
		}
		if(enableCache) cachedLevelRoleMap = levelRolesIDs;
		return levelRolesIDs;
	}

}
