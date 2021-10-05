package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class HasConfig {

	static final int CONFIGIORETRY = 5;

	public IO CONFIG;
	private File file;
	protected List<Key> CONFIG_KEYS = new ArrayList<>();
	protected String LOGGING_CHANNEL_ID;
	protected boolean LOG_TO_CHANNEL = false;

	public boolean PRINT_STACK_TRACE;
	
	public HasConfig(File file) {
		this.file = file;
		this.CONFIG_KEYS.add(new Key("printStackTrace", "true", "// Detailled logging"));
		this.CONFIG_KEYS.add(new Key("logToConsole", "true", "// Console logging (true/false)"));
		this.CONFIG_KEYS.add(new Key("logToChannel", "false", "// Logging in channel (true/false)"));
		this.CONFIG_KEYS.add(new Key("loggingChannelID", "ID HERE", "// Logging channel ID"));
	}
	
	protected boolean initialConfigIOCreation() {
		int configIoRetry = 0;
		while (configIoRetry < CONFIGIORETRY) {
			if(!createConfigIO()) {
				DemiConsole.error("Failed to create main config IO");
				DemiConsole.info("Retrying...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					DemiConsole.error("Thread Interrupted!");
					break;
				}
				configIoRetry++;
			}else break;
		}
		
		if(this.CONFIG == null) {
			DemiConsole.error("Failed to create main config IO");
			DemiConsole.error("The main config IO is essential to allow DEMI to work, process will terminate");
			return false;
		}
		
		this.PRINT_STACK_TRACE = this.CONFIG.get("printStackTrace").equalsIgnoreCase("true");
		this.LOGGING_CHANNEL_ID = this.CONFIG.get("loggingChannelID");
		this.LOG_TO_CHANNEL = this.CONFIG.get("logToChannel").equalsIgnoreCase("true");
		DemiConsole.ok("successfully created " + this.file.getName() + " IO");
		return true;
	}

	protected boolean createConfigIO() {
		DemiConsole.action("Creating Config IO...");
		this.CONFIG = new IO(this.file, this.CONFIG_KEYS, this.PRINT_STACK_TRACE, IO.defaultHeaders);
		if(this.CONFIG == null) {
			DemiConsole.error("Failed to create Main Config IO!");
			return false;
		}
		DemiConsole.ok("Main Config IO created");
		return true;
	}
	
	protected abstract void handleTrace(Exception e);
	
}