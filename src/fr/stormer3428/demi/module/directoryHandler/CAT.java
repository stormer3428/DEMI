package fr.stormer3428.demi.module.directoryHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;

public class CAT extends CommandModule{

	private CD CD;

	public CAT() {
		super("CAT");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public List<String> getDependencies() {
		ArrayList<String> dependencies = new ArrayList<>();
		dependencies.add("CD");		
		return dependencies;
	}

	@Override
	public void onEnable() {
		super.onEnable();
		try {
			for(Module module : Demi.i.getActiveModules()) if(module.getName().equals("CD")) {
				this.CD = (CD) module;
				break;
			}
		}catch (Exception e) {
			this.OUTPUT.error("Error while hooking to dependency CD");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		Long WDid = event.getMessageReceivedEvent() == null ? 0 : event.getMessageReceivedEvent().getAuthor().getIdLong();
		MixedOutput OUTPUT = event.getOutput();

		String path = "";
		for(String s : event.getArgs()) path = path + (path.equals("")?"":" ") + s;

		File file = CD.getFileFromWD(path, WDid);
		if(file == null) {
			OUTPUT.command("Outside of root");
			return;
		}
		if(!file.exists()) {
			OUTPUT.command("No file with suche name ("+path+")");
			return;
		}
		if(file.isDirectory()) {
			OUTPUT.command("Is directory ("+path+")");
			return;
		}


		String content = "";
		String token = Demi.i.discordBotToken();
		IO io = new IO(file, new ArrayList<>(), true);
		io.setPrintStackTrace(false);
		io.destroy();
		ArrayList<String> all = io.getAllRaw(false);
		if(all == null) {
			OUTPUT.command("Unable to read file ("+path+")");
			return;
		}

		for(String s : all) {
			if(s.contains(token)) s = "[REDACTED]";
			if(content.length() + s.length() > 1990) break;
			content = content + (content.equals("")?"":"\n") + s;
		}
		OUTPUT.command(content);
	}

	@Override
	public String getUsage() {
		return "CAT";
	}

	@Override
	public String getDescription() {
		return "Shows the content of a text file";
	}

}
