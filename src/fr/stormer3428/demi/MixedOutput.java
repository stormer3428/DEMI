package fr.stormer3428.demi;

import net.dv8tion.jda.api.entities.TextChannel;

public class MixedOutput {

	private String outputChannelID;
	private boolean outputToChannel;
	private boolean outputToConsole;
	private TextChannel textChannel;
	private String mixedOutputHead;

	public MixedOutput(String channelID, boolean channel, boolean console, String header) {
		outputChannelID = channelID;
		outputToChannel = channel;
		outputToConsole = console;
		mixedOutputHead = header;
		if(!outputToChannel) return;
		this.textChannel = Demi.i.getGuild().getTextChannelById(outputChannelID);
		if(textChannel != null) return; 
		DemiConsole.error("Error while creating mixed output with channel id, id given is invalid ("+outputChannelID+")");	
	}
	
	public MixedOutput(String channelID, boolean console, String header) {
		this(channelID, true, console, header);
	}

	public MixedOutput() {
		this(null, false, true, "");
	}

	public final void error(String message) {
		if(outputToConsole)
			System.err.println("\033[38;5;196m"+"[" + mixedOutputHead + "][📕Error] " + message+"\033[38;5;7m");
		if(!outputToChannel) return;
		textChannel.sendMessage("[" + mixedOutputHead + "] **📕ERROR " + message + ".**");
	}

	public final void warning(String message) {
		if(outputToConsole)
			System.out.println("\033[38;5;208m"+"[" + mixedOutputHead + "][📙Warning] "+"\033[38;5;7m" + message);
		if(!outputToChannel) return;
		textChannel.sendMessage("[\" + mixedOutputHead + \"][" + mixedOutputHead + "] ***📙WARNING " + message + ".***");
	}

	public final void ok(String message) {
		if(outputToConsole)
			System.out.println("\033[38;5;118m"+"[" + mixedOutputHead + "][📗Ok] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannel.sendMessage("[" + mixedOutputHead + "] 📗Ok " + message + ".");
	}

	public final void action(String message) {
		if(outputToConsole)
			System.out.println("\033[38;5;123m"+"[" + mixedOutputHead + "][📘Action] "+"\033[38;5;7m" + message);
		if(!outputToChannel) return;
		textChannel.sendMessage("[" + mixedOutputHead + "] 📘Action " + message + ".");
	}

	public final void cancelled(String message) {
		if(outputToConsole)
			System.out.println("\033[38;5;245m"+"[" + mixedOutputHead + "][📓Cancelled] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannel.sendMessage("[" + mixedOutputHead + "] *📓Cancelled " + message + ".*");
	}

	public final void info(String message) {
		if(outputToConsole)
			System.out.println("\033[38;5;226m"+"[" + mixedOutputHead + "][Info] "+"\033[38;5;7m" + message);
		if(!outputToChannel) return;
		textChannel.sendMessage("[" + mixedOutputHead + "] Info " + message + ".");
	}


}
