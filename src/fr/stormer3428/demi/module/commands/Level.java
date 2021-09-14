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

public class Level extends CommandModule{

	private LevelCalculator LEVEL_CALCULATOR;
	
	public Level() {
		super("Level");

		this.aliases.add("lvl");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "The level command allows you to know your level or another user's level";
	}

	@Override
	public List<String> getDependencies() {
		List<String> dependencies = new ArrayList<>();
		dependencies.add("LevelCalculator");
		return dependencies;
	}

	@Override
	public void onDisable() {}

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
		long targetId = -1;
		if(event.getMessageReceivedEvent() == null && args.size() == 0) {
			OUTPUT.command("Missing argument ID, only discord members may not specify a target id");
			return;
		}

		if(args.size() == 0) {
			targetId = event.getMessageReceivedEvent().getAuthor().getIdLong();
		}else {
			String arg1 = args.remove(0);
			try {
				targetId = Long.parseLong(arg1);
			} catch (Exception e) {
				OUTPUT.command("I expected to receive a member id but you gave me this shit : " + arg1);
				return;
			}
		}

		showLevel(event.getOutput(), targetId);
	}

	private void showLevel(MixedOutput OUTPUT, long targetId) {
		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		Member member = guild.retrieveMemberById(targetId).complete();
		if(member == null) {
			OUTPUT.command("idk what id you gave me but it sure ain't someone i know of");
			return;
		}
		long exp = LEVEL_CALCULATOR.getUserExp(member.getId());
		int level = LEVEL_CALCULATOR.getLevelForExp(exp);
		long expNextLevel = LEVEL_CALCULATOR.getExpToNextLevel(level + 1);
		
		OUTPUT.command(member.getEffectiveName() + " is currently level " + level + " with " + exp + " xp" + "\n" + "Exp until next level : " + expNextLevel + " xp");
		
		
	}

	@Override
	public String getUsage() {
		return "Usage : level <optional ID>";
	}

}
