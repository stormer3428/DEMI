package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.List;

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
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		this.OUTPUT.ok("Successfully loaded all config parameters");
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
