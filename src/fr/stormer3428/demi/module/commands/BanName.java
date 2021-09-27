package fr.stormer3428.demi.module.commands;

import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;

public class BanName extends CommandModule{

	public BanName() {
		super("BanName");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);

	}

	@Override
	public String getDescription() {
		return "A module that can ban multiple people based on the name prompted";
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		List<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();
		if(args.size() == 0) {
			OUTPUT.command("I need a command type... like -query or -confirm");
			return;
		}
		String type = args.remove(0);
		if(type.equalsIgnoreCase("-query") || type.equalsIgnoreCase("-q")) {
			if(args.size() == 0) {
				OUTPUT.command("i need a name to search members with");
				return;
			}
			
			//TODO
			return;
		}

		if(type.equalsIgnoreCase("-confirm") || type.equalsIgnoreCase("-c")) {
			//TODO
			return;
		}
		OUTPUT.command("there's only two valid argument, -query and -confirm, and " + type + " isn't one of them");
	}

	@Override
	public String getUsage() {
		return "Usage : " + getName() + " <-query; -confirm>";
	}


}
