package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;

public class Help extends CommandModule{

	public Help() {
		super("Help");

		this.aliases.add("?");
		this.aliases.add("h");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		MixedOutput COMMAND_OUTPUT = event.getOutput();
		ArrayList<String> args = event.getArgs();
		Member member = null;
		if(event.getMessageReceivedEvent() != null) member = event.getMessageReceivedEvent().getMember();

		if(args.isEmpty()) {
			List<String> embedReplacement = new ArrayList<>();
			embedReplacement.add("Commands available : ");

			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(getName());
			builder.setTitle("Available Commands");
			if(member != null) builder.setThumbnail(member.getUser().getEffectiveAvatarUrl());

			for(Module module : Demi.i.getActiveModules()) {
				if(module instanceof CommandModule) {
					CommandModule command = (CommandModule) module;
					embedReplacement.add(command.getName() + " | " + command.getUsage());
					builder.addField(command.getName(), command.getUsage(), false);
				}
			}
			COMMAND_OUTPUT.embed(builder.build(), embedReplacement);
			return;
		}
		String typedCommandName = args.remove(0);
		boolean ModulesCommandLoaded = false;
		for(Module module : Demi.i.getActiveModules()) if(module.getName().equals("Modules")) {ModulesCommandLoaded = true; break;}
		for(Module module : Demi.i.getActiveModules()) {
			if(module instanceof CommandModule) {
				CommandModule command = (CommandModule) module;
				if(command.getName().equalsIgnoreCase(typedCommandName) || command.getAliases().contains(typedCommandName.toLowerCase())) {
					COMMAND_OUTPUT.command("Help for command " + command.getName() + " : \n" + command.getUsage() + (ModulesCommandLoaded ? "\n for more details, try \"Modules " + command.getName() + "\"" : ""));
					return;
				}
			}
		}
		COMMAND_OUTPUT.error("No command with such name");
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public String getDescription() {
		return "A command that shows the list of commands available to the sender";
	}

	@Override
	public void onDisable() {}

	@Override
	public String getUsage() {
		return "Usage : help";
	}

}
