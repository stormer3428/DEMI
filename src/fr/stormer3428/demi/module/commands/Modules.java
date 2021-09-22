package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Module;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;

public class Modules extends CommandModule{

	public Modules() {
		super("Modules");

		this.aliases.add("mdls");
		this.aliases.add("md");
		this.aliases.add("m");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		ArrayList<String> args = event.getArgs();
		MixedOutput COMMAND_OUTPUT = event.getOutput();

		if(args.isEmpty()) {
			String str = "Currently loaded modules : \n";
			boolean first = true;
			for(Module module : Demi.i.getActiveModules()) {
				str = str + (first ? "" : ", ") + module.getName();
				if(first) first = false;
			}
			COMMAND_OUTPUT.command(str);
			return;
		}
		String subCommand = args.remove(0);
		for(Module module : Demi.i.getActiveModules()) {
			if(module.getName().equalsIgnoreCase(subCommand) || (module instanceof CommandModule && ((CommandModule)module).getAliases().contains(subCommand.toLowerCase()) ) || subCommand.equalsIgnoreCase("description") || subCommand.equalsIgnoreCase("desc") || subCommand.equalsIgnoreCase("d")) {
				COMMAND_OUTPUT.command("Description of module " + module.getName() + " :");
				COMMAND_OUTPUT.command(module.getDescription());
				return;
			}
		}
		COMMAND_OUTPUT.error("No module with such name");
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
		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	public String getUsage() {
		return "Usage : " + getName() + " optional(desc; moduleName)";
	}
}
