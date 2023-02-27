package fr.stormer3428.demi.module.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

public class Help extends CommandModule{

	public Help() {
		super("help");

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
			
			EmbedBuilder helpEmbedBuilder = new EmbedBuilder();
			helpEmbedBuilder.setTitle("Commands available for " + member.getEffectiveName());
			helpEmbedBuilder.setAuthor("Help");
			helpEmbedBuilder.setColor(new Color(200, 0, 200));
			
			List<String> embedReplacement = new ArrayList<>();
			embedReplacement.add("Commands available : ");

			if(member != null) helpEmbedBuilder.setThumbnail(member.getUser().getEffectiveAvatarUrl());

			for(Module module : Demi.i.getActiveModules()) {
				if(module instanceof CommandModule) {
					CommandModule command = (CommandModule) module;
					if(!command.canUseCommand(member)) continue;
					embedReplacement.add(command.getName() + " | " + command.getUsage());
					helpEmbedBuilder.addField(command.getName(), command.getDescription() + "\n" + command.getUsage(), false);
				}
			}
			COMMAND_OUTPUT.embed(helpEmbedBuilder.build(), embedReplacement);
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
	public String getDescription() {
		return "A command that shows the list of commands available to the sender";
	}
	
	@Override
	public String getUsage() {
		return "Usage : help";
	}

}
