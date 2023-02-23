package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Module;

public class MessageLevelingMultiplierRoles extends Module{

	private IO ROLE_MULTIPLIERS;
	private HashMap<Long, Float> roleMultipliers = new HashMap<>();
	
	public MessageLevelingMultiplierRoles() {
		super(new File("conf/level/messagelevelingmultiplierroles.conf"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public void onEnable() {
		super.onEnable();

		this.ROLE_MULTIPLIERS = new IO(new File("conf/level/multiplierroles" + Demi.i.getServerID() + ".conf"), new ArrayList<>(), true);

		List<String> roleIDs = this.ROLE_MULTIPLIERS.getKeys();
		for(String roleID : roleIDs) {
			long ID;
			try {
				ID = Long.parseLong(roleID);
			}catch (NumberFormatException e) {
				this.OUTPUT.error("Error found in configuration, all keys are supposed to be role id's but found " + roleID);
				this.OUTPUT.cancelled("Skipping...");
				handleTrace(e);
				continue;
			}

			try {
				float multiplier = Float.parseFloat(this.ROLE_MULTIPLIERS.get(ID + ""));
				if(multiplier < 0) {
					this.OUTPUT.error("Error found in configuration, role " + ID + " has a negative multiplier!, lowest multiplier allowed is 0");
					this.OUTPUT.cancelled("Skipping this multiplier...");
					continue;
				}
				this.roleMultipliers.put(ID, multiplier);
			}catch (NumberFormatException e) {
				this.OUTPUT.error("Error found in configuration, value of key " + roleID + " is supposed to be a float, but found " + this.ROLE_MULTIPLIERS.get(ID + ""));
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
	public String getDescription() {
		return "A sub-module of messageLeveling allowing for specific roles to add multipliers to the exp you gain."
				+ "The config is pretty simple to setup, just put idoftherole:multiplier on each line and you're good to go";
	}
	
	public Float getMultiplier(Long roleId) {
		if(!Demi.i.getActiveModules().contains(this)) return 1.0f;
		if(!this.roleMultipliers.containsKey(roleId)) return 1.0f;
		this.OUTPUT.trace("Retrieving multiplier for role " + roleId + " (" + this.roleMultipliers.get(roleId) +")", this.PRINT_STACK_TRACE);
		return this.roleMultipliers.get(roleId);
	}
	
	
}
