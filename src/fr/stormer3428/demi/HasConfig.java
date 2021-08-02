package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HasConfig {

	static final int CONFIGIORETRY = 5;

	protected IO CONFIG;
	private File file;
	protected List<Key> CONFIG_KEYS = new ArrayList<>();

	public boolean PRINT_STACK_TRACE;
	
	public HasConfig(File file) {
		this.file = file;
		CONFIG_KEYS.add(new Key("printStackTrace", "true"));
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
				}
			}else break;
		}
		
		if(CONFIG == null) {
			DemiConsole.error("Failed to create main config IO");
			DemiConsole.error("The main config IO is essential to allow DEMI to work, process will terminate");
			return false;
		}
		
		PRINT_STACK_TRACE = CONFIG.get("printStackTrace").equalsIgnoreCase("true");
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
	
	
	
}
