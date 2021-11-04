package fr.stormer3428.demi.module.directoryHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
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
		File WD = CD.getWD(event.getMessageReceivedEvent().getAuthor().getIdLong());
		File[] files = WD.listFiles();
		
		//TODO
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
