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
		CONFIG_KEYS.add(new Key("printStackTrace", "true"));
		CONFIG_KEYS.add(new Key("logToConsole", "true"));
		CONFIG_KEYS.add(new Key("logToChannel", "false"));
		CONFIG_KEYS.add(new Key("loggingChannelID", "ID HERE"));
	}
	
	protected boolean initialConfigIOCreation() {
		int configIoRetry = 0;
		while (configIoRetry < CONFIGIORETRY) {
			if(!refreshConfigIO()) {
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
		
		if(CONFIG == null) {
			DemiConsole.error("Failed to create main config IO");
			DemiConsole.error("The main config IO is essential to allow DEMI to work, process will terminate");
			return false;
		}
		
		PRINT_STACK_TRACE = CONFIG.get("printStackTrace").equalsIgnoreCase("true");
		LOGGING_CHANNEL_ID = CONFIG.get("loggingChannelID");
		LOG_TO_CHANNEL = CONFIG.get("logToChannel").equalsIgnoreCase("true");
		DemiConsole.ok("successfully created " + file.getName() + " IO");
		return true;
	}

	protected boolean refreshConfigIO() {
		DemiConsole.action("Creating Config IO...");
		CONFIG = new IO(file, CONFIG_KEYS, PRINT_STACK_TRACE, IO.defaultHeaders);
		if(CONFIG == null) {
			DemiConsole.error("Failed to create Main Config IO!");
			return false;
		}
		DemiConsole.ok("Main Config IO created");
		return true;
	}
	
	protected abstract void handleTrace(Exception e);
	
}