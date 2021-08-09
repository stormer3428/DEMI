package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;

public class Leveling extends Module{

	private int expPerMessage;
	private int expPerMessageVariation;
	
	public Leveling() {
		super(new File("Leveling.cfg"));

		CONFIG_KEYS.add(new Key("expPerMessage", "100"));
		CONFIG_KEYS.add(new Key("expPerMessageVariation", "100"));
		
		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public List<String> getDependencies() {
		ArrayList<String> dependencies = new ArrayList<String>();
		dependencies.add("LevelCalculator");
		return dependencies;
	}

	@Override
	public String getName() {
		return "Leveling";
	}

	@Override
	public String getDescription() {
		return "The module that handles leveling up upon sending messages \n"
				+ "the way the exp variation works is simple with the variation set to 150 and the base set to 100 for exemple, the amount of exp given will be from 100 all the way up to 150";
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		try {
			expPerMessage = Integer.parseInt(CONFIG.get("expPerMessage"));
		} catch (Exception e) {
			OUTPUT.error("Error while parsing value of expPerMessage, expected an integer");
			handleTrace(e);
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		OUTPUT.trace("expPerMessage : " + expPerMessage);
		try {
			expPerMessageVariation = Integer.parseInt(CONFIG.get("expPerMessageVariation"));
		} catch (Exception e) {
			OUTPUT.error("Error while parsing value of expPerMessageVariation, expected an integer");
			handleTrace(e);
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		OUTPUT.trace("expPerMessageVariation : " + expPerMessageVariation);
		
		OUTPUT.ok("Successfully loaded all config parameters");
	}

	
}
