package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Demi extends HasConfig{

	static final int JDARETRY = 5;

	public static Demi i;
	static JDA jda;


	static boolean DEBUG_MODE;
	static List<Long> DEBUG_IDS;

	static String SERVER_ID;

	private List<Module> MODULES = new ArrayList<>();
	private List<Module> ACTIVE_MODULES = new ArrayList<>();

	public List<Module> getActiveModules(){
		return ACTIVE_MODULES;
	}

	public List<Module> getRegisteredModules(){
		return MODULES;
	}

	public String getServerID(){
		return SERVER_ID;
	}

	public static void registerModule(Module module) {
		i.MODULES.add(module);
	}

	public static void disableModule(Module module) {
		i.ACTIVE_MODULES.remove(module);
		module.onDisable();
	}

	public void reloadModules() {
		DemiConsole.action("Reloading Modules...");
		if(!ACTIVE_MODULES.isEmpty()) {
			for(Module module : ACTIVE_MODULES) {
				DemiConsole.action("Deactivating module " + module.getName());
				module.onDisable();
			}
			ACTIVE_MODULES.clear();
			DemiConsole.ok("Successfully deactivated all modules");
		}

		if(!MODULES.isEmpty()) {
			//==//
			DemiConsole.action("Checking for duplicate modules registered...");
			List<String> moduleNames = new ArrayList<>();
			boolean hasDuplicates = false;
			for(Module module : MODULES) {
				if(moduleNames.contains(module.getName())) {
					DemiConsole.warning("Found duplicate module ! (" + module.getName() + ")");
					hasDuplicates = true;
				}
			}
			if(!hasDuplicates) DemiConsole.ok("Found no duplicate modules registered");
			else {
				DemiConsole.warning("Found duplicate modules, listing...");
				for(Module module : MODULES) DemiConsole.info(module.getName());
			}

			//==//
			DemiConsole.action("Reactivating modules...");
			for(Module module : MODULES) {
				if(module.enabled()) {
					DemiConsole.action("Activating module " + module.getName());
					ACTIVE_MODULES.add(module);
					module.onEnable();
				}else {
					DemiConsole.cancelled("Module " + module.getName() + " is disabled in the config");
				}
			}
			DemiConsole.ok("Successfully activated all enabled modules");
			DemiConsole.ok("Successfully reloaded modules!");
		}else {
			DemiConsole.warning("No modules registered.");
		}
	}

	public static final void main(String[] args){
		i.DEMI();
	}

	public Demi() {
		super(new File("config.cfg"));
		i = this;
		CONFIG_KEYS.add(new Key("discordBotToken", "TOKEN_HERE"));
		CONFIG_KEYS.add(new Key("debugMode", "false"));
		CONFIG_KEYS.add(new Key("debugIDs", "[]"));
		CONFIG_KEYS.add(new Key("serverId", "DISCORD_SERVER_ID_HERE"));
	}

	public void DEMI() {
		/*
		Returning at this stage will kill DEMI as no module has been activated yet
		*/
		if(!initialConfigIOCreation()) return;
		
		setDebugMode(CONFIG.get("debugMode"), debugIDs());
		
		DEBUG_IDS = debugIDs();
		if(DEBUG_IDS == null) return;
		
		if(!initialJDACreation()) return;
		
		SERVER_ID = CONFIG.get("serverId");
		if(jda.getGuildById(SERVER_ID) == null) {
			DemiConsole.error("Invalid Server ID given, stopping DEMI");
			jda.shutdown();
			jda = null;
			return;
		}

		reloadModules();
	}

	private List<Long> debugIDs(){
		List<Long> list = new ArrayList<>();
		List<String> stringList = CONFIG.getList("debugIDs");
		try {
			for(String debugID : stringList) list.add(Long.parseLong(debugID));
			return list;
		}catch (NumberFormatException e) {
			DemiConsole.error("Failed to retrieve parameter (debugIDs) in main config file ");
			DemiConsole.warning("Retrieved value : " + CONFIG.getList("debugIDs"));
			DemiConsole.warning("Expected an array of user IDs");

			if(DEBUG_MODE) {
				DemiConsole.error("Debug Mode is active, stopping DEMI");
				return null;
			}
			DemiConsole.cancelled("Debug Mode is not active, skipping...");
			return new ArrayList<>();
		}
	}

	private String discordBotToken() {
		return CONFIG.get("discordBotToken");
	}

	private static boolean initialJDACreation() {
		int configIoRetry = 0;
		while (configIoRetry < CONFIGIORETRY) {
			if(!i.refreshJDA()) {
				DemiConsole.error("Failed to create JDA instance");
				DemiConsole.info("Retrying...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					DemiConsole.error("Thread Interrupted!");
				}
			}else return true;
		}
		if(!i.refreshJDA()) {
			DemiConsole.error("Failed to create the JDA instance");
			DemiConsole.error("JDA is essential for DEMI to work properly, it allows it to interact with the Discord Bot");
			return false;
		}
		return true;
	}

	public boolean refreshJDA() {
		DemiConsole.action("Creating new JDA instance...");

		JDABuilder builder = JDABuilder.createDefault(discordBotToken());
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		builder.setRequestTimeoutRetry(true);

		try{
			jda = builder.build();
			jda.addEventListener(new RawListener());
			DemiConsole.ok("JDA instance created");
			return true;
		}catch(Exception e){
			DemiConsole.error("Error");
			DemiConsole.error("Login Crash");
			DemiConsole.error("something went wrong while logging into JDA");
			return false;
		}
	}

	private void setDebugMode(String debugMode, List<Long> list) {
		setDebugMode(debugMode.equalsIgnoreCase("true"), list);
	}

	public void setDebugMode(boolean debugMode, List<Long> debugIDs) {
		if(debugMode) {
			DemiConsole.info("Demi is in debug mode, will only respond to following members id :");
			for(Long debugID : debugIDs) DemiConsole.info(debugID + "");
			return;
		}
		DemiConsole.info("Debug mode is off, DEMI will perform normal actions");
		DEBUG_MODE = debugMode;
	}

	public boolean getDebugMode() {
		return DEBUG_MODE;
	}

	public boolean isDebugger(Long userId) {
		return DEBUG_IDS.contains(userId);
	}
}






