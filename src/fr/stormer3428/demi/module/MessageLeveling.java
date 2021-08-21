package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class MessageLeveling extends Module{

	private int expPerMessage;
	private int expPerMessageVariation;

	private long expIncreaseCooldownMS;
	private boolean enableExpIncreaseCooldownMS;
	
	private List<String> onCoolDownUsers = new ArrayList<>();
	private long lastWiped = System.currentTimeMillis();

	private LevelCalculator LEVEL_CALCULATOR;
	private MessageLevelingMultiplierRoles MULTIPLIER_ROLES; //TODO

	public MessageLeveling() {
		super(new File("level/messageLeveling.cfg"));

		this.CONFIG_KEYS.add(new Key("expIncreaseCooldownMS", "300000"));
		this.CONFIG_KEYS.add(new Key("enableExpIncreaseCooldownMS", "true"));
		this.CONFIG_KEYS.add(new Key("expPerMessage", "100"));
		this.CONFIG_KEYS.add(new Key("expPerMessageVariation", "100"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public List<String> getDependencies() {
		ArrayList<String> dependencies = new ArrayList<>();
		dependencies.add("LevelCalculator");
		dependencies.add("LevelRoleCalculator");
		return dependencies;
	}

	@Override
	public String getName() {
		return "MessageLeveling";
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
		this.OUTPUT.trace("expPerMessage : " + this.expPerMessage);
		try {
			this.expPerMessageVariation = Integer.parseInt(this.CONFIG.get("expPerMessageVariation"));
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of expPerMessageVariation, expected an integer");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("expPerMessageVariation : " + this.expPerMessageVariation);
		
		this.enableExpIncreaseCooldownMS = this.CONFIG.get("enableExpIncreaseCooldownMS").equalsIgnoreCase("true");
		this.OUTPUT.trace("enableExpIncreaseCooldownMS : " + this.enableExpIncreaseCooldownMS);
		
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
		this.OUTPUT.trace("expIncreaseCooldownMS : " + this.expIncreaseCooldownMS);

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		updateCooldownCache();
		String memberUID = event.getAuthor().getId();
		this.OUTPUT.trace("Message received from member " + memberUID);
		if(this.onCoolDownUsers.contains(memberUID)) return;
		
		Long increase = (long) (this.expPerMessage + Math.round(new Random().nextFloat() * this.expPerMessageVariation));
		
		//TODO Nitro booster roles
		
		this.LEVEL_CALCULATOR.increaseUserExpBy(memberUID, increase);
	}

	private void updateCooldownCache() {
		while(System.currentTimeMillis() - this.lastWiped >= this.expIncreaseCooldownMS) {
			this.lastWiped += this.expIncreaseCooldownMS;
			this.onCoolDownUsers.clear();
		}
	}

}
