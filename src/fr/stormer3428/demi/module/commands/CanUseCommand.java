package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class CanUseCommand extends CommandModule{

	public CanUseCommand() {
		super("canusecommand");

		this.aliases.add("cuc");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		ArrayList<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();
		if(args.size() == 0) {
			OUTPUT.command("you need to tell me what command to check the permissions of");
			return;
		}
		String command = args.remove(0);
		CommandModule commandModule = null;
		for(Module module : Demi.i.getActiveModules()) {
			if(!(module instanceof CommandModule)) continue;
			if(!module.getName().equalsIgnoreCase(command) 
					/*&& !commandModule.getAliases().contains(command.toLowerCase())*/) continue;
			commandModule = (CommandModule) module;
		}
		if(commandModule == null) {
			OUTPUT.command("I've never heard of the command " + command.toLowerCase());
			return;
		}

		if(args.size() == 0) {
			OUTPUT.command("okay you wanna if someone can run " + commandModule.getName() + " but who");
			return;
		}
		String givenID = args.remove(0);
		long targetId;
		try {
			targetId = Long.parseLong(givenID);
		} catch (Exception e) {
			OUTPUT.command("this aint an id");
			return;
		}
		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		Member member = guild.retrieveMemberById(targetId).complete();
		if(member == null) {
			OUTPUT.command(givenID + "?..." + givenID + "?.... welp, i guess no one has this id");
			return;
		}

		OUTPUT.command(member.getEffectiveName() + (commandModule.canUseCommand(member) ? " can use " : " cant use ") + commandModule.getName());
	}

	@Override
	public String getDescription() {
		return "A command that shows whether a member can use a specific command or not";
	}

	@Override
	public String getUsage() {
		return "Usage : " + getName() + " <command> userid";
	}

}
