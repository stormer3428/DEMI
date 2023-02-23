package fr.stormer3428.demi.module.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import fr.stormer3428.demi.module.LevelCalculator;
import fr.stormer3428.demi.module.music.DemiRadio;

public class RadioLink extends CommandModule{

	private DemiRadio demiRadio;
	
	public RadioLink() {
		super("radiolink");
		
		this.aliases.add("rdlk");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public List<String> getDependencies() {
		List<String> dependencies = new ArrayList<>();
		dependencies.add("DemiRadio");
		return dependencies;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		

		try {
			for(Module module : Demi.i.getActiveModules()) if(module.getName().equals("DemiRadio")) {
				this.demiRadio = (DemiRadio) module;
				break;
			}
		}catch (Exception e) {
			this.OUTPUT.error("Error while hooking to dependency DemiRadio");
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
			OUTPUT.command("great, you want to update the radio to a new link but if you don't give it to me we're gonna have a problem here bud");
			return;
		}
		String newUrl = args.remove(0);
		try {
			demiRadio.startRadio(newUrl);
			OUTPUT.command("Link updated to " + newUrl);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			OUTPUT.error("Idk what the fuck you gave me but the radio module ain't liking it");
		} catch (IOException e) {
			e.printStackTrace();
			OUTPUT.error("Idk what the fuck you gave me but the radio module ain't taking it");
		}
	}

	@Override
	public String getUsage() {
		return commandName + " <link>";
	}

	@Override
	public String getDescription() {
		return "A command that updates the radio link";
	}

}
