package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class LevelCalculator extends Module{

	private boolean enableCaches;
	private HashMap<Integer, Long> CACHE_expForLevel = new HashMap<>();
	private HashMap<Integer, Long> CACHE_expToNextLevel = new HashMap<>();

	private boolean levelRoleOffSafeMode;
	private LevelRoleCalculator LEVEL_ROLE_CALCULATOR;

	private float levelIncreasePow;
	protected int levelBase;

	private IO LEVEL_DATABASE;

	public IO getLEVEL_DATABASE() {
		return LEVEL_DATABASE;
	}
	
	public LevelCalculator() {
		super(new File("level/levelcalculator.conf"));

		this.CONFIG_KEYS.add(new Key("levelIncreasePow", "1.05"));
		this.CONFIG_KEYS.add(new Key("levelBase", "500"));
		this.CONFIG_KEYS.add(new Key("enableCaches", "true"));
		this.CONFIG_KEYS.add(new Key("levelRoleOffSafeMode", "true"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);

	}

	@Override
	public String getDescription() {
		return "The module responsible for computing how much exp a level should require";
	}

	@Override
	public List<String> getSoftDependencies() {
		ArrayList<String> dependencies = new ArrayList<>();
		dependencies.add("LevelRoleCalculator");
		return dependencies;
	}

	@Override
	public void onDisable() {
		if(this.enableCaches) {
			this.OUTPUT.info("Wiping caches..");
			this.CACHE_expForLevel.clear();
			this.CACHE_expToNextLevel.clear();
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();

		this.enableCaches = this.CONFIG.get("enableCaches").equalsIgnoreCase("true");
		this.levelRoleOffSafeMode = this.CONFIG.get("levelRoleOffSafeMode").equalsIgnoreCase("true");

		this.OUTPUT.trace("enableCaches : " + this.enableCaches, this.PRINT_STACK_TRACE);
		try {
			this.levelIncreasePow = Float.parseFloat(this.CONFIG.get("levelIncreasePow"));
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of levelIncreasePow, expected a float");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("levelIncreasePow : " + this.levelIncreasePow, this.PRINT_STACK_TRACE);
		try {
			this.levelBase = Integer.parseInt(this.CONFIG.get("levelBase"));
		} catch (Exception e) {
			this.OUTPUT.error("Error while parsing value of levelBase, expected an integer");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.trace("levelBase : " + this.levelBase, this.PRINT_STACK_TRACE);


		this.LEVEL_ROLE_CALCULATOR = (LevelRoleCalculator) softLoad("LevelRoleCalculator", this.OUTPUT);
		if(this.LEVEL_ROLE_CALCULATOR == null) if(this.PRINT_STACK_TRACE) {
			this.OUTPUT.warning(getName() + " will be unable to retrieve a member's level based from his roles and thus will ignore them, as well as not making them level up");
			this.OUTPUT.warning("Members already in the database will level up normally");
		}

		this.LEVEL_DATABASE = new IO(new File("level/leveldb" + Demi.i.getServerID() + ".demidb"), new ArrayList<>(), true);

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	public int getLevelForExp(long givenExp) {
		//System.out.println("getLevelForExp " + givenExp);
		if(!enabled()) return -1;
		double exp = 0;
		int level = 0;
		for(; level < 200; level ++) {
			exp = getExpForLevel(level);
			if(exp > givenExp) {
				exp --;
				break;
			}
		}
		//System.out.println("level " + (level - 1));
		return level - 1;
	}

	public long getExpToNextLevel(int level) {
		//System.out.println("getExpToNextLevel " + level);
		if(!enabled()) return -1;
		if(this.enableCaches && this.CACHE_expToNextLevel.containsKey(level)) return this.CACHE_expToNextLevel.get(level);
		long exp = Math.round(this.levelBase * Math.pow(this.levelIncreasePow, level));
		if(this.enableCaches) {
			this.OUTPUT.trace("Writing values to CACHE_expToNextLevel (" + level + ", " + Math.round(exp) + ")", this.PRINT_STACK_TRACE);
			this.CACHE_expToNextLevel.put(level, exp);
		}
		//System.out.println("exp " + exp);
		return exp;
	}

	public long getExpForLevel(int givenLevel) {
		//System.out.println("getExpForLevel " + givenLevel);
		if(!enabled()) return -1;
		if(this.enableCaches && this.CACHE_expForLevel.containsKey(givenLevel)) return this.CACHE_expForLevel.get(givenLevel);

		if(!this.enableCaches) this.OUTPUT.trace("New value requiring computing : " + givenLevel, this.PRINT_STACK_TRACE);

		double totalExp = 0;
		long exp;
		for(int level = 0; level < givenLevel; level++) {
			exp = getExpToNextLevel(level);
			totalExp += exp;
		}

		this.OUTPUT.trace("Writing values to CACHE_expForLevel (" + givenLevel + ", " + Math.round(totalExp) + ")", this.PRINT_STACK_TRACE);
		this.CACHE_expForLevel.put(givenLevel, Math.round(totalExp));
		//System.out.println("totalExp " + Math.round(totalExp));
		return Math.round(totalExp);
	}

	public long getUserExp(String UID) {
		if(!enabled()) return -1;
		if(this.LEVEL_DATABASE.getKeys().contains(UID)) try {
			//System.out.println("dans la bdd!");
			return Long.parseLong(this.LEVEL_DATABASE.get(UID));
		} catch (NumberFormatException e) {
			this.OUTPUT.error("Error while parsing exp for userid " + UID + " it was not an integer");
			handleTrace(e);
			return -1;
		}

		if(this.LEVEL_ROLE_CALCULATOR == null) {
			if(this.levelRoleOffSafeMode) return -2;
			return 0;
		}
		else if(!this.LEVEL_ROLE_CALCULATOR.enabled()) {
			this.OUTPUT.warning("Soft dependency LevelRoleCalculator is no longer loaded!");
			this.OUTPUT.warning(getName() + " will now be unable to retrieve a member's level based from his roles and thus will ignore them, as weel as not making them level up");
			this.OUTPUT.warning("Members already in the database will level up normally");
			this.LEVEL_ROLE_CALCULATOR = null;
			if(this.levelRoleOffSafeMode) return -2;
			return 0;
		}
		this.OUTPUT.trace("New user in database : " + UID, this.PRINT_STACK_TRACE);
		Member member = Demi.jda.getGuildById(Demi.i.getServerID()).retrieveMemberById(UID).complete();
		if(member == null) return -1;
		int retrievedLevel = this.LEVEL_ROLE_CALCULATOR.retrieveLevelFromRoles(UID);
		if(retrievedLevel == -1) {
			this.OUTPUT.error("Module LevelRoleCalculator returned -1 on retrieveLevelFromRoles(), it may be disabled or has encountered an error, skipping writing...");
			return -1;
		}
		this.OUTPUT.trace("Retrieved level from roles (" + retrievedLevel + ")", this.PRINT_STACK_TRACE);
		long exp = getExpForLevel(retrievedLevel);
		this.LEVEL_DATABASE.setParameter(UID, exp + "");
		return exp;
	}

	public int getUserLevel(String UID) {
		if(!enabled()) return -1;
		return getLevelForExp(getUserExp(UID));
	}

	public void setUserExp(String UID, Long exp) {
		this.OUTPUT.trace("Setting exp of user " + UID + " to " + exp, this.PRINT_STACK_TRACE);
		this.LEVEL_DATABASE.setParameter(UID, exp + "");
		if(LEVEL_ROLE_CALCULATOR == null) return;
		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		if(guild == null) return;
		Member member = guild.retrieveMemberById(UID).complete();
		if(member == null) return;
		LEVEL_ROLE_CALCULATOR.applyLevelRole(getLevelForExp(exp), member);
	}

	public void setUserLevel(String UID, int level) {
		this.OUTPUT.trace("Setting level of user " + UID + " to " + level, this.PRINT_STACK_TRACE);
		setUserExp(UID, getExpForLevel(level));
	}

	public void increaseUserExpBy(String UID, Long expIncrease) {
		Long currentExp = getUserExp(UID);
		if(currentExp == -1) {
			this.OUTPUT.error("Error while trying to increase exp for user " + UID + " by " + expIncrease + "xp");
			this.OUTPUT.error("getUserExp returned -1, signaling an error earlier in the process");
			return;
		}
		if(currentExp == -2) {
			this.OUTPUT.cancelled("member is not in database, levelRole softDepend is off and levelRoleOffSafeMode is set to true, skipping...");
			return;
		}
		this.OUTPUT.trace("Increasing exp of user " + UID + " (" + currentExp + ") by " + expIncrease, this.PRINT_STACK_TRACE);
		setUserExp(UID, currentExp + expIncrease);
	}
}
