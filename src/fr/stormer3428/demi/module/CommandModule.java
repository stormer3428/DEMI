package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Module;

public abstract class CommandModule extends Module{

	protected String commandName;
	
	public CommandModule(File file, String string) {
		super(file);
		commandName = string;
	}

	@Override
	public List<String> getDependencies() {
		ArrayList<String> dependencies = new ArrayList<>();
		dependencies.add("CommandDispatcher");
		return dependencies;
	}
	
	@Override
	public String getName() {
		return commandName;
	}
}
