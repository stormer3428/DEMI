package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Module;
import fr.stormer3428.demi.DemiCommandReceiveEvent;

public class Modules extends CommandModule{

	public Modules() {
		super("Modules");

		aliases.add("mdls");
		aliases.add("md");
		aliases.add("m");
		
		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		String str = "Currently loaded modules : \n";
		boolean first = true;
		for(Module module : Demi.i.getActiveModules()) {
			str = str + (first ? "" : ", ") + module.getName();
			if(first) first = false;
		}
		OUTPUT.command(str);
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public String getDescription() {
		return "A comand that lists currently enabled modules";
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		OUTPUT.ok("Successfully loaded all config parameters");
	}
}
