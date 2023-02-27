package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import fr.stormer3428.demi.module.LevelCalculator;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class SetLevel extends CommandModule{

	private LevelCalculator LEVEL_CALCULATOR;

	public SetLevel() {
		super("setlevel");

		this.aliases.add("slvl");
		this.aliases.add("setlvl");
		this.aliases.add("slevel");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "The setlevel command allows you to set an user's level";
	}

	@Override
	public List<String> getDependencies() {
		List<String> dependencies = new ArrayList<>();
		dependencies.add("LevelCalculator");
		return dependencies;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		this.OUTPUT.ok("Successfully loaded all config parameters");

		try {
			for(Module module : Demi.i.getActiveModules()) if(module.getName().equals("LevelCalculator")) {
				this.LEVEL_CALCULATOR = (LevelCalculator) module;
				break;
			}
		}catch (Exception e) {
			this.OUTPUT.error("Error while hooking to dependency LevelCalculator");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		ArrayList<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();
		if(args.size() == 0) {
			OUTPUT.command("okay you wanna set someone's level, but to what?");
			return;
		}
		String givenID = args.remove(0);
		long targetId;
		try {
			targetId = Long.parseLong(givenID);
		} catch (Exception e) {
			OUTPUT.command("I expected to receive a member id but you gave me this shit : " + givenID);
			return;
		}
		User user = Demi.jda.retrieveUserById(targetId).complete();
		if(user == null) {
			OUTPUT.command("I never heard of this guy nor has discord");
			return;
		}
		String name;
		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		Member member = guild.retrieveMember(user).complete();
		if(member == null) {
			name = user.getName();
		}else {
			name = member.getEffectiveName();
		}
		
		if(args.size() == 0) {
			OUTPUT.command("Okay cool, you want me to change " + name + "'s level, but you forgot to tell me what to change it to");
			return;
		}
		String givenLevel = args.remove(0);
		int newLevel;
		
		try {
			newLevel = Integer.parseInt(givenLevel);
		} catch (NumberFormatException e) {
			handleTrace(e);
			OUTPUT.command("This "+givenLevel+" thing aint a level... ");
			return;
		}
		
		if(newLevel < 0) {
			OUTPUT.command("As fun as setting " + name + "'s level to " + newLevel + " may be, i am not doing that");
			return;
		}

		setLevel(event.getOutput(), targetId, newLevel, name);
	}

	private void setLevel(MixedOutput OUTPUT, long targetId, int newLevel, String name) {
		LEVEL_CALCULATOR.setUserLevel(targetId + "", newLevel);
		OUTPUT.command("Done! "+name+"'s level was set to " + newLevel + "! ("+LEVEL_CALCULATOR.getExpForLevel(newLevel)+"exp)");
	}

	@Override
	public String getUsage() {
		return "Usage : setlevel <user ID> <new level>";
	}

}
