package fr.stormer3428.demi.module;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Module;

public class MessageLevelingMultiplierRoles extends Module{

	private HashMap<Long, Float> roleMultipliers = new HashMap<>();
	
	public MessageLevelingMultiplierRoles() {
		super(new File("level/messageLevelingMultiplierRoles.cfg"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public void onEnable() {
		List<String> roleIDs = this.CONFIG.getKeys();
		for(String roleID : roleIDs) {
			try {
				long ID = Long.parseLong(roleID);
				float multiplier = Integer.parseInt(this.CONFIG.get(ID + ""));
				if(multiplier < 0) {
					this.OUTPUT.error("Error found in configuration, role " + ID + " has a negative multiplier!, lowest multiplier allowed is 0");
					this.OUTPUT.cancelled("Skipping this multiplier...");
					continue;
				}
				this.roleMultipliers.put(ID, multiplier);
			}catch (NumberFormatException e) {
				this.OUTPUT.error("Error found in configuration, all keys are supposed to be role id's but found " + roleID);
				this.OUTPUT.cancelled("Skipping...");
				handleTrace(e);
			}
		}
		if(roleIDs.isEmpty()) {
			this.OUTPUT.warning("No roles were set, disabling module...");
			Demi.disableModule(this);
		}
	}
	
	@Override
	public String getName() {
		return "MessageLevelingMultiplierRoles";
	}

	@Override
	public String getDescription() {
		return "A sub-module of messageLeveling allowing for specific roles to add multipliers to the exp you gain."
				+ "The config is pretty simple to setup, just put idoftherole:multiplier on each line and you're good to go";
	}
	
	public Float getMultiplier(Long roleId) {
		if(!Demi.i.getActiveModules().contains(this)) return 1.0f;
		if(!this.roleMultipliers.containsKey(roleId)) return 1.0f;
		return this.roleMultipliers.get(roleId);
	}
	
	
}
