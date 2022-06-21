package fr.stormer3428.demi.module.commands;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;

public class Version extends CommandModule{

	public Version() {
		super("version");

		this.aliases.add("ver");
		this.aliases.add("v");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		String filename = new java.io.File(Demi.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
		event.getOutput().command("Current version : \n" + filename);
	}

	@Override
	public String getDescription() {
		return "A command that shows the main jar name, thus showing the version DEMI is running on";
	}
	
	@Override
	public String getUsage() {
		return "Usage : version";
	}

}
