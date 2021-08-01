package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Demi {

	static final int CONFIGIORETRY = 5;
	static final int JDARETRY = 5;
	
	public static Demi i;
	static JDA jda;
	
	static IO CONFIG;
	static List<Key> CONFIG_KEYS = new ArrayList<>();

	static boolean DEBUG_MODE;
	static List<String> DEBUG_ID;
	
	static {
		CONFIG_KEYS.add(new Key("discordBotToken", "TOKEN_HERE"));
	}
	
	public Demi() {
		i = this;
	}

	public static final void main(String[] args){
		if(!initialConfigIOCreation()) return;
		if(!initialJDACreation()) return;
		setDebugMode(CONFIG.get("debugMode"), CONFIG.getList("debugIDs"));
		
		//Modules Here
	}

	private static boolean initialConfigIOCreation() {
		int configIoRetry = 0;
		while (configIoRetry < CONFIGIORETRY) {
			if(!i.refreshConfigIO()) {
				DemiConsole.error("Failed to create main config IO");
				DemiConsole.info("Retrying...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					DemiConsole.error("Thread Interrupted!");
				}
			}else return true;
		}
		if(!i.refreshConfigIO()) {
			DemiConsole.error("Failed to create main config IO");
			DemiConsole.error("The main config IO is essential to allow DEMI to work, process will terminate");
			return false;
		}
		return true;
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

	public boolean refreshConfigIO() {
		DemiConsole.action("Creating Config IO...");
		CONFIG = new IO(new File("config.cfg"), CONFIG_KEYS, true, IO.defaultHeaders);
		if(CONFIG == null) {
			DemiConsole.error("Failed to create Main Config IO!");
			return false;
		}
		DemiConsole.ok("Main Config IO created");
		return true;
	}
	
	public boolean refreshJDA() {
		DemiConsole.action("Creating new JDA instance...");
		
		JDABuilder builder = JDABuilder.createDefault(CONFIG.get("discordBotToken"));
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

	private static void setDebugMode(String debugMode, List<String> debugIDs) {
		setDebugMode(debugMode.equalsIgnoreCase("true"), debugIDs);
	}
	
	public static void setDebugMode(boolean debugMode, List<String> debugIDs) {
		if(debugMode) {
			DemiConsole.info("Demi is in debug mode, will only respond to following members id :");
			for(String debugID : debugIDs) DemiConsole.info(debugID);
			return;
		}
		DemiConsole.info("Debug mode is off, DEMI will perform normal actions");
	}
}






