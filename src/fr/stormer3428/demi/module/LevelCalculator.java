package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;

public class LevelCalculator extends Module{

	private float lvlIncreasePow;
	private int lvlBase;
	
	public LevelCalculator() {
		super(new File("LevelCalculator.cfg"));

		CONFIG_KEYS.add(new Key("lvlIncreasePow", "1.05"));
		CONFIG_KEYS.add(new Key("lvlBase", "500"));

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
		return "LevelCalculator";
	}

	@Override
	public String getDescription() {
		return "The module responsible for computing how much exp a level should require";
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		try {
			lvlIncreasePow = Float.parseFloat(CONFIG.get("lvlIncreasePow"));
		} catch (Exception e) {
			OUTPUT.error("Error while parsing value of lvlIncreasePow, expected a float");
			handleTrace(e);
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		OUTPUT.trace("lvlIncreasePow : " + lvlIncreasePow);
		try {
			lvlBase = Integer.parseInt(CONFIG.get("lvlBase"));
		} catch (Exception e) {
			OUTPUT.error("Error while parsing value of lvlBase, expected an integer");
			handleTrace(e);
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		OUTPUT.trace("lvlBase : " + lvlBase);
		
		OUTPUT.ok("Successfully loaded all config parameters");
	}
	
	
}
