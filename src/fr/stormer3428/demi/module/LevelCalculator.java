package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;

public class LevelCalculator extends Module{

	private boolean enableCaches;
	private HashMap<Integer, Long> CACHE_expForLevel = new HashMap<>();
	private HashMap<Integer, Long> CACHE_expToNextLevel = new HashMap<>();

	private float levelIncreasePow;
	protected int levelBase;

	private IO LEVEL_DATABASE;

	public LevelCalculator() {
		super(new File("level/levelCalculator.cfg"));

		CONFIG_KEYS.add(new Key("levelIncreasePow", "1.05"));
		CONFIG_KEYS.add(new Key("levelBase", "500"));
		CONFIG_KEYS.add(new Key("enableCaches", "true"));

		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);

		LEVEL_DATABASE = new IO(new File("level/" + Demi.i.getServerID() + ".demidb"), new ArrayList<>(), true);
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
	public void onDisable() {
		if(enableCaches) {
			OUTPUT.info("Wiping caches..");
			CACHE_expForLevel.clear();
			CACHE_expToNextLevel.clear();
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();

		enableCaches = CONFIG.get("enableCaches").equalsIgnoreCase("true");

		OUTPUT.trace("enableCaches : " + enableCaches);
		try {
			levelIncreasePow = Float.parseFloat(CONFIG.get("levelIncreasePow"));
		} catch (Exception e) {
			OUTPUT.error("Error while parsing value of levelIncreasePow, expected a float");
			handleTrace(e);
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		OUTPUT.trace("levelIncreasePow : " + levelIncreasePow);
		try {
			levelBase = Integer.parseInt(CONFIG.get("levelBase"));
		} catch (Exception e) {
			OUTPUT.error("Error while parsing value of levelBase, expected an integer");
			handleTrace(e);
			OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		OUTPUT.trace("levelBase : " + levelBase);

		OUTPUT.ok("Successfully loaded all config parameters");
	}

	public int getLevelForExp(long givenExp) {
		double exp = 0;
		int level = 0;
		for(; level < 200; level ++) {
			exp = getExpForLevel(level);
			if(exp > givenExp) {
				exp --;
				break;
			}
		}
		return (int) level - 1;
	}

	public long getExpToNextLevel(int level) {
		if(enableCaches && CACHE_expToNextLevel.containsKey(level)) return CACHE_expToNextLevel.get(level);
		long exp = Math.round(levelBase * Math.pow(levelIncreasePow, level));
		if(enableCaches) {
			OUTPUT.trace("Writing values to CACHE_expToNextLevel (" + level + ", " + Math.round(exp) + ")");
			CACHE_expToNextLevel.put(level, exp);
		}
		return exp;
	}

	public long getExpForLevel(int givenLevel) {
		if(enableCaches && CACHE_expForLevel.containsKey(givenLevel)) return CACHE_expForLevel.get(givenLevel);
		
		if(!enableCaches) OUTPUT.trace("New value requiring computing : " + givenLevel);

		double totalExp = 0;
		long exp;
		for(int level = 0; level < givenLevel; level++) {
			exp = getExpToNextLevel(level);
			totalExp += exp;
		}

		OUTPUT.trace("Writing values to CACHE_expForLevel (" + givenLevel + ", " + Math.round(totalExp) + ")");
		CACHE_expForLevel.put(givenLevel, Math.round(totalExp));
		return Math.round(totalExp);
	}

	public long getUserExp(String UID) {
		try {
			return Long.parseLong(LEVEL_DATABASE.get(UID));
		} catch (NumberFormatException e) {
			OUTPUT.error("Error while parsing exp for userid " + UID + " it was not an integer");
			handleTrace(e);
			return -1;
		}
	}

	public int getUserLevel(String UID) {
		return getLevelForExp(getUserExp(UID));
	}
}
