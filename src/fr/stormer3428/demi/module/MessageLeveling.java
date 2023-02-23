package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageLeveling extends Module{

	private int expPerMessage;
	private int expPerMessageVariation;

	private long expIncreaseCooldownMS;
	private boolean enableExpIncreaseCooldownMS;

	private List<String> onCoolDownUsers = new ArrayList<>();
	private long lastWiped = System.currentTimeMillis();

	private LevelCalculator LEVEL_CALCULATOR;
	private MessageLevelingMultiplierRoles MULTIPLIER_ROLES;
	
	public MessageLeveling() {
		super(new File("conf/level/messageleveling.conf"));

		this.CONFIG_KEYS.add(new Key("enableExpIncreaseCooldownMS", "true", "whether leveling via messaging should have a cooldown"));
		this.CONFIG_KEYS.add(new Key("expIncreaseCooldownMS", "60000", "the cooldown in milliseconds between each exp increase"));
		this.CONFIG_KEYS.add(new Key("expPerMessage", "75", "the minimum amount of exp awarded per message sent"));
		this.CONFIG_KEYS.add(new Key("expPerMessageVariation", "50", "the maximum amount that is given at random, if expperMessage is set to 75 and expPerMessageVariation to 50, the amount of exp will be between 75 and 125 (75 + 50)"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public List<String> getDependencies() {
		ArrayList<String> dependencies = new ArrayList<>();
		dependencies.add("LevelCalculator");
		return dependencies;
	}

	@Override
	public List<String> getSoftDependencies() {
		ArrayList<String> dependencies = new ArrayList<>();
		dependencies.add("MessageLevelingMultiplierRoles");
		return dependencies;
	}

	@Override
	public String getDescription() {
		return "The module that handles leveling up upon sending messages \n"
				+ "the way the exp variation works is simple with the variation set to 150 and the base set to 100 for exemple, the amount of exp given will be from 100 all the way up to 250";
	}

	@Override
	public void onEnable() {
		super.onEnable();
		try {
			for(Module module : Demi.i.getActiveModules()) if(module.getName().equals("LevelCalculator")) {
				this.LEVEL_CALCULATOR = (LevelCalculator) module;
				break;
			}
		}catch (Exception e) {
			this.OUTPUT.error("Error while hooking to dependency LevelCalculator");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		try {
			this.expPerMessage = Integer.parseInt(this.CONFIG.get("expPerMessage"));
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of expPerMessage, expected an integer");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("expPerMessage : " + this.expPerMessage, this.PRINT_STACK_TRACE);
		try {
			this.expPerMessageVariation = Integer.parseInt(this.CONFIG.get("expPerMessageVariation"));
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of expPerMessageVariation, expected an integer");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("expPerMessageVariation : " + this.expPerMessageVariation, this.PRINT_STACK_TRACE);

		this.enableExpIncreaseCooldownMS = this.CONFIG.get("enableExpIncreaseCooldownMS").equalsIgnoreCase("true");
		this.OUTPUT.trace("enableExpIncreaseCooldownMS : " + this.enableExpIncreaseCooldownMS, this.PRINT_STACK_TRACE);

		try {
			this.expIncreaseCooldownMS = Long.parseLong(this.CONFIG.get("expIncreaseCooldownMS"));
			if(this.expIncreaseCooldownMS <= 0) {
				this.OUTPUT.warning("enableExpIncreaseCooldownMS was set to true with expIncreaseCooldownMS set to " + this.expIncreaseCooldownMS);
				this.OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
				return;
			}
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of expIncreaseCooldownMS, expected an integer");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("expIncreaseCooldownMS : " + this.expIncreaseCooldownMS, this.PRINT_STACK_TRACE);


		this.MULTIPLIER_ROLES = (MessageLevelingMultiplierRoles) softLoad("MessageLevelingMultiplierRoles", this.OUTPUT);
		if(this.MULTIPLIER_ROLES == null) if(this.PRINT_STACK_TRACE) {
			this.OUTPUT.warning(getName() + " won't apply multipliers associated with roles, leveling will occur normally");
		}

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}
	
	@Override
	public void onDisable() {
		this.onCoolDownUsers.clear();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!event.isFromGuild()) return;
		updateCooldownCache();
		if(event.getAuthor().isBot()) return;
		String memberUID = event.getAuthor().getId();
		this.OUTPUT.trace("Message received from member " + memberUID, this.PRINT_STACK_TRACE);
		if(this.onCoolDownUsers.contains(memberUID)) return;

		int increase = (this.expPerMessage + Math.round(new Random().nextFloat() * this.expPerMessageVariation));
		increase = handleBoosterRoles(increase, event.getMember());

		this.LEVEL_CALCULATOR.increaseUserExpBy(memberUID, (long) increase);
		this.onCoolDownUsers.add(memberUID);
	}

	private int handleBoosterRoles(int increase, Member member) {
		int returnStatement = increase;
		if(this.MULTIPLIER_ROLES == null) return increase;
		else if(!this.MULTIPLIER_ROLES.enabled()) {
			this.OUTPUT.warning("Soft dependency MULTIPLIER_ROLES is no longer loaded!");
			this.OUTPUT.warning(getName() + " won't apply multipliers anymore, leveling will occur normally");
			this.MULTIPLIER_ROLES = null;
			return increase;
		}
		for(Role role : member.getRoles()) returnStatement = Math.round(returnStatement * this.MULTIPLIER_ROLES.getMultiplier(role.getIdLong()));
		return returnStatement;
	}

	private void updateCooldownCache() {
		while(System.currentTimeMillis() - this.lastWiped >= this.expIncreaseCooldownMS) {
			this.lastWiped += this.expIncreaseCooldownMS;
			this.onCoolDownUsers.clear();
		}
	}

}
