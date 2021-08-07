package fr.stormer3428.demi.module.commands;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.module.CommandModule;
import net.dv8tion.jda.api.entities.Member;

public class Reload extends CommandModule{


	public Reload() {
		super("Reload");

		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "The reload command reloads all modules within demi";
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		OUTPUT.ok("Successfully loaded all config parameters");
	}
	
	@Override
	public boolean onCommand(DemiCommandReceiveEvent event) {
		if(!event.getCommand().equalsIgnoreCase(getName())) return false;
		if(event.getMessageReceivedEvent() == null) {
			Demi.i.reloadModules();
			return true;
		}
		Member member = event.getMessageReceivedEvent().getMember();
		if(canUseCommand(member)) {
			Demi.i.reloadModules();
		}
		return true;

	}

}
