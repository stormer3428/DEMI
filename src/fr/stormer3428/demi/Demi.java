package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.module.Autorole;
import fr.stormer3428.demi.module.CommandDispatcher;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Demi extends HasConfig{
	
	protected MixedOutput OUTPUT;
	
	static final int JDARETRY = 5;

	public static Demi i;
	public static JDA jda;


	static boolean DEBUG_MODE;
	static List<Long> DEBUG_IDS;

	static String SERVER_ID;

	static private List<Module> MODULES = new ArrayList<>();
	static protected List<Module> ACTIVE_MODULES = new ArrayList<>();

	static {
		registerModule(new Autorole()); //TODO make modules implement themselves
		registerModule(new CommandDispatcher()); //TODO make modules implement themselves
	}
	
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
		Demi.MODULES.add(module);
	}

	public static void disableModule(Module module) {
		Demi.ACTIVE_MODULES.remove(module);
		module.onDisable();
	}

	public void reloadModules() {
		OUTPUT.action("Reloading Modules...");
		if(!ACTIVE_MODULES.isEmpty()) {
			for(Module module : ACTIVE_MODULES) {
				OUTPUT.action("Deactivating module " + module.getName());
				module.onDisable();
			}
			ACTIVE_MODULES.clear();
			OUTPUT.ok("Successfully deactivated all modules");
		}

		if(!MODULES.isEmpty()) {
			//==//
			OUTPUT.action("Checking for duplicate modules registered...");
			List<String> moduleNames = new ArrayList<>();
			boolean hasDuplicates = false;
			for(Module module : MODULES) {
				if(moduleNames.contains(module.getName())) {
					OUTPUT.warning("Found duplicate module ! (" + module.getName() + ")");
					hasDuplicates = true;
				}
			}
			if(!hasDuplicates) OUTPUT.ok("Found no duplicate modules registered");
			else {
				OUTPUT.warning("Found duplicate modules, listing...");
				for(Module module : MODULES) OUTPUT.info(module.getName());
			}

			//==//
			OUTPUT.action("Reactivating modules...");
			List<Module> toEnable = new ArrayList<>();
			
			for(Module module : MODULES) 
				if(module.enabled()) toEnable.add(module);
				else OUTPUT.cancelled("Module " + module.getName() + " is disabled in it's config");
			
			int oldSize = toEnable.size();
			while(toEnable.size() > 0) {
				for(Module module : toEnable) {
					if(module.canBeLoaded()) {
						OUTPUT.action("Activating module " + module.getName());
						ACTIVE_MODULES.add(module);
						module.onEnable();
					}
				}
				toEnable.removeAll(ACTIVE_MODULES);
				if(oldSize == toEnable.size()) break;
				oldSize = toEnable.size();
			}
			for(Module notLoaded : toEnable) {
				OUTPUT.warning("Module " + notLoaded.getName() + " could not be loaded");
				boolean header = true;
				for(String dependency : notLoaded.getDependencies()) {
					boolean dependencyLoaded = false;
					for(Module module : ACTIVE_MODULES) {
						if(module.getName().equalsIgnoreCase(dependency)) {
							dependencyLoaded = true;
							break;
						}
						if(!dependencyLoaded) {
							if(header) {
								header = false;
								OUTPUT.warning("missing dependencies : ");
							}
							OUTPUT.warning(dependency);
						}
					}
				}
			}
			
			OUTPUT.ok("Successfully activated all enabled modules");
			OUTPUT.ok("Successfully reloaded modules!");
			OUTPUT.info("Enabled modules : ");
			for(Module module : ACTIVE_MODULES) {
				OUTPUT.info(module.getName());
			}
		}else {
			OUTPUT.warning("No modules registered.");
		}
	}

	public static final void main(String[] args){
		i = new Demi();
		i.main();
	}
	
	public void main() {
		/*
		Returning at this stage will kill DEMI as no module has been activated yet
		*/

		OUTPUT = new MixedOutput(CONFIG.get("loggingChannelID"), CONFIG.get("logToChannel").equalsIgnoreCase("true"), CONFIG.get("logToConsole").equalsIgnoreCase("true"), "Core");
		
		DEBUG_IDS = debugIDs();
		setDebugMode(CONFIG.get("debugMode"), DEBUG_IDS);
		
		if(DEBUG_IDS == null) return;
		
		if(!initialJDACreation()) return;
		
		SERVER_ID = CONFIG.get("serverId");
		if(jda.getGuildById(SERVER_ID) == null) {
			OUTPUT.error("Invalid Server ID given, stopping DEMI");
			jda.shutdown();
			jda = null;
			return;
		}
		reloadModules();
	}
	
	public Demi() {
		super(new File("config.cfg"));
		CONFIG_KEYS.add(new Key("discordBotToken", "TOKEN_HERE"));
		CONFIG_KEYS.add(new Key("debugMode", "false"));
		CONFIG_KEYS.add(new Key("debugIDs", "[]"));
		CONFIG_KEYS.add(new Key("serverId", "DISCORD_SERVER_ID_HERE"));
		if(!initialConfigIOCreation()) return;
	}

	private List<Long> debugIDs(){
		List<Long> list = new ArrayList<>();
		List<String> stringList = CONFIG.getList("debugIDs");
		try {
			for(String debugID : stringList) list.add(Long.parseLong(debugID));
			return list;
		}catch (NumberFormatException e) {
			OUTPUT.error("Failed to retrieve parameter (debugIDs) in main config file ");
			OUTPUT.warning("Retrieved value : " + CONFIG.getList("debugIDs"));
			OUTPUT.warning("Expected an array of user IDs");
			handleTrace(e);
			
			if(DEBUG_MODE) {
				OUTPUT.error("Debug Mode is active, stopping DEMI");
				return null;
			}
			OUTPUT.cancelled("Debug Mode is not active, skipping...");
			return new ArrayList<>();
		}
	}

	private String discordBotToken() {
		return CONFIG.get("discordBotToken");
	}

	private boolean initialJDACreation() {
		int configIoRetry = 0;
		while (configIoRetry < CONFIGIORETRY) {
			if(!i.refreshJDA()) {
				OUTPUT.error("Failed to create JDA instance");
				OUTPUT.info("Retrying...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					OUTPUT.error("Thread Interrupted!");
					handleTrace(e);
				}
				configIoRetry ++;
			}else return true;
		}
		if(!i.refreshJDA()) {
			OUTPUT.error("Failed to create the JDA instance");
			OUTPUT.error("JDA is essential for DEMI to work properly, it allows it to interact with the Discord Bot");
			return false;
		}
		return true;
	}

	public boolean refreshJDA() {
		OUTPUT.action("Creating new JDA instance...");

		JDABuilder builder = JDABuilder.createDefault(discordBotToken());
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		builder.setRequestTimeoutRetry(true);

		try{
			jda = builder.build().awaitReady();
			jda.addEventListener(new RawListener());
			OUTPUT.ok("JDA instance created");
			return true;
		}catch(Exception e){
			OUTPUT.error("Error");
			OUTPUT.error("Login Crash");
			OUTPUT.error("something went wrong while logging into JDA");
			handleTrace(e);
			return false;
		}
	}

	private void setDebugMode(String debugMode, List<Long> list) {
		setDebugMode(debugMode.equalsIgnoreCase("true"), list);
	}

	public void setDebugMode(boolean debugMode, List<Long> debugIDs) {
		if(debugMode) {
			OUTPUT.info("Demi is in debug mode, will only respond to following members id :");
			for(Long debugID : debugIDs) OUTPUT.info(debugID + "");
			return;
		}
		OUTPUT.info("Debug mode is off, DEMI will perform normal actions");
		DEBUG_MODE = debugMode;
	}

	public boolean getDebugMode() {
		return DEBUG_MODE;
	}

	public boolean isDebugger(Long userId) {
		return DEBUG_IDS.contains(userId);
	}

	protected void handleTrace(Exception e) {
		if(PRINT_STACK_TRACE) {
			OUTPUT.info("Printing stack trace");
			e.printStackTrace();
		}else OUTPUT.cancelled("Core module set to not print stack trace");
	}

	protected Guild getGuild() {
		return jda.getGuildById(SERVER_ID);
	}
}