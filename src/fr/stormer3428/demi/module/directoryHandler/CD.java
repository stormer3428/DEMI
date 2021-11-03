package fr.stormer3428.demi.module.directoryHandler;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import net.dv8tion.jda.api.entities.User;

public class CD extends CommandModule{

	public static final String ERROR_OUTSIDE_ROOT_DIR = "Illegal WD, outside of root WD";
	
	private static final File rootDir = new File(Demi.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
	private HashMap<Long, File> WDs = new HashMap<>();
	
	public CD() {
		super("CD");
		
		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getName() {
		return "CD";
	}

	@Override
	public String getDescription() {
		return "CD is the module that will handle the current working directory, on its own it doesn't do anything, it is intended for other modules to use";
	}

	@Override
	public void onEnable() {
		super.onEnable();
		
		WDs.clear();
		
		OUTPUT.info("RootDir : " + rootDir.getAbsolutePath());
		
		if(!rootDir.isDirectory()) {
			OUTPUT.error("Error, given root directory is not a directory");
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
	}
	
	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		List<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();
		User user = event.getMessageReceivedEvent().getAuthor();
		if(args.isEmpty()) {
			//OUTPUT.command("getWD(user.getIdLong())" + getWD(user.getIdLong()));
			OUTPUT.command(getWDStringFromRoot(user.getIdLong()) + " $");
			return;
		}
		
		String newPath = args.remove(0);
		File WD = getWD(user.getIdLong());

		//OUTPUT.command("newPath :" + newPath);
		//OUTPUT.command("WD :" + WD);

		while (newPath.startsWith("../")) {
			WD = WD.getParentFile();
			newPath = newPath.replaceFirst("\\.\\./", "");
			//OUTPUT.command("newPath :" + newPath);
			//OUTPUT.command("WD :" + WD.getAbsolutePath());
		}
		
		while (newPath.startsWith("..")) {
			WD = WD.getParentFile();
			newPath = newPath.replaceFirst("\\.\\.", "");
			//OUTPUT.command("newPath :" + newPath);
			//OUTPUT.command("WD :" + WD.getAbsolutePath());
		}
		
		String newDirectoryPath = (newPath.startsWith("/") ? rootDir.getAbsolutePath() : WD.getAbsolutePath()) + "/" + newPath;
		File newWD = new File(newDirectoryPath);
		
		//OUTPUT.command("newDirectoryPath :" + newDirectoryPath);

		String absWDPath = newWD.getAbsolutePath();
		String absRootPath = rootDir.getAbsolutePath();
		if(!absWDPath.startsWith(absRootPath)) {
			OUTPUT.command("Outside of root");
			rootFormat(newWD, user.getIdLong());
			return;
		}
		if(!newWD.exists()) {
			OUTPUT.command("Path does not exist (" + (rootFormat(newWD, user.getIdLong())) + ")");
			return;
		}
		if(!newWD.isDirectory()) {
			OUTPUT.command("Not a directory (" + (rootFormat(newWD, user.getIdLong())) + ")");
			return;
		}
		
		WDs.put(user.getIdLong(), newWD);
		OUTPUT.command(getWDStringFromRoot(user.getIdLong()) + " $");
	}

	public String rootFormat(File file) {
		String absWDPath = file.getAbsolutePath();
		String absRootPath = rootDir.getAbsolutePath();
		if(!absWDPath.startsWith(absRootPath)) {
			return ERROR_OUTSIDE_ROOT_DIR;
		}
		return absWDPath.replaceFirst(absRootPath, "");
	}
	
	public String rootFormat(File file, Long id) {
		String s = rootFormat(file);
		if(s.equals(ERROR_OUTSIDE_ROOT_DIR)) {
			
		}
		return s;
	}
	
	public String getWDStringFromRoot(Long id) {
		File wd = getWD(id);
		return rootFormat(wd);
	}
	
	public File getWD(Long id) {
		if(!WDs.containsKey(id)) WDs.put(id, rootDir);
		return WDs.get(id);
	}
	
	@Override
	public String getUsage() {
		return "CD <newDir>";
	}

}
