package fr.stormer3428.demi.module.commands;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;

public class Reload extends CommandModule{

	public Reload() {
		super("Reload");

		this.aliases.add("rl");
		
		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "The reload command reloads all modules within demi";
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		Demi.i.reloadModules();
	}

	@Override
	public String getUsage() {
		return "Usage : reload";
	}

}
