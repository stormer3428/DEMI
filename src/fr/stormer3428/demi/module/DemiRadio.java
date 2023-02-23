package fr.stormer3428.demi.module;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.JDA;

public class DemiRadio extends Module{

	public DemiRadio() {
		super(new File("conf/DemiRadio.conf"));
	}

	@Override
	public String getDescription() {
		return "Haha ptdr chuis une description";
	}

	@Override
	public void onEnable() {
		super.onEnable();
		try {
			startRadio();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startRadio() throws UnsupportedAudioFileException, IOException {
		URL url = new URL("http://www.howjsay.com/index.php?word=car");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
		
		JDA jda = Demi.jda;
	
		

	}


}
