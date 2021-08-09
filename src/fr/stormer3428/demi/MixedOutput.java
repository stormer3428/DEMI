package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class MixedOutput {

	private static IO io;

	private static boolean enableBuffer;
	private static int bufferTimeoutMS;
	private static String buffer = "";
	private static Thread bufferThread;

	public boolean PRINT_STACK_TRACE;

	private String outputChannelID;
	private boolean outputToChannel;
	private boolean outputToConsole;
	private TextChannel textChannel;
	private String mixedOutputHead;

	public MixedOutput(String channelID, boolean channel, boolean console, String header) {
		if(io == null) {
			ArrayList<Key> keys = new ArrayList<>();
			keys.add(new Key("printStackTrace", "true"));
			keys.add(new Key("enableBuffer", "true"));
			keys.add(new Key("bufferTimeoutMS", "2000"));
			io = new IO(new File("MixedOutput.cfg"), keys, true);

			enableBuffer = io.get("enableBuffer").equalsIgnoreCase("true");

			try {
				bufferTimeoutMS = Integer.parseInt(io.get("bufferTimeoutMS"));
			}catch (NumberFormatException e) {
				bufferTimeoutMS = 2000;
				Demi.i.OUTPUT.error("Unable to retrieve buffer timeout of MixedOutput, using default value (2000)");
				handleTrace(e);
			}
			if(enableBuffer) startBufferThread();
		}
		outputChannelID = channelID;
		outputToChannel = channel;
		outputToConsole = console;
		mixedOutputHead = header;
		if(!outputToChannel) return;
	}

	public MixedOutput(String channelID, boolean console, String header) {
		this(channelID, true, console, header);
	}

	public MixedOutput() {
		this(null, false, true, "");
	}

	protected void handleTrace(Exception e) {
		if(PRINT_STACK_TRACE) {
			DemiConsole.info("printing stack trace");
			e.printStackTrace();
		}else DemiConsole.cancelled("Mixed Output set to not print stack trace");
	}

	private void startBufferThread() {
		if(bufferThread != null) return;
		DemiConsole.info("Starting up buffer discord console sender");
		bufferThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					if(!buffer.isEmpty()) {
						textChannel.sendMessage(buffer).complete();
						buffer = "";
					}
					try {
						Thread.sleep(bufferTimeoutMS);
					} catch (InterruptedException e) {
						handleTrace(e);
					}
				}
			}
		});
		bufferThread.start();
	}

	private void textChannelInit() {
		if(textChannel == null) {
			if(Demi.jda == null) return;
			textChannel = Demi.i.getGuild().getTextChannelById(outputChannelID);
		}
	}

	public final void error(String message) {
		if(outputToConsole) System.out.println("\033[38;5;196m" + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + "[📕Error] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " **📕ERROR " + message + ".**" + "\n");
		else textChannel.sendMessage("" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " **📕ERROR " + message + ".**" + "\n").queue();
	}

	public final void warning(String message) {
		if(outputToConsole) System.out.println("\033[38;5;208m" + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + "[📙Warning] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " ***📙WARNING " + message + ".***" + "\n");
		else textChannel.sendMessage("" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " ***📙WARNING " + message + ".***" + "\n").queue();
	}

	public final void ok(String message) {
		if(outputToConsole) System.out.println("\033[38;5;118m" + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + "[📗Ok] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " 📗Ok " + message + "." + "\n");
		else textChannel.sendMessage("" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " 📗Ok " + message + "." + "\n").queue();
	}

	public final void action(String message) {
		if(outputToConsole) System.out.println("\033[38;5;123m"+"" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + "[📘Action] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " 📘Action " + message + "." + "\n");
		else textChannel.sendMessage("" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " 📘Action " + message + "." + "\n").queue();
	}

	public final void cancelled(String message) {
		if(outputToConsole) System.out.println("\033[38;5;245m"+"" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + "[📓Cancelled] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " *📓Cancelled " + message + ".*" + "\n");
		else textChannel.sendMessage("" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " *📓Cancelled " + message + ".*" + "\n").queue();
	}

	public final void info(String message) {
		if(outputToConsole) System.out.println("\033[38;5;226m"+"" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + "[Info] " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " Info " + message + "." + "\n");
		else textChannel.sendMessage("" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " Info " + message + "." + "\n").queue();
	}

	public void command(String message) {
		if(outputToConsole)  System.out.println("\033[38;5;226m"+"" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " " + message + "\033[38;5;7m");
		if(!outputToChannel) return;
		textChannelInit();
		if(enableBuffer) buffer = (buffer + "```" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " " + message + "```" + "\n");
		else textChannel.sendMessage("```" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " " + message + "```" + "\n").queue();
	}

	public void embed(MessageEmbed embed, List<String> embedReplacement) {
		if(outputToChannel) {
			textChannelInit();
			textChannel.sendMessage(embed).queue();
		}
		if(!outputToConsole) return;
		for(String line : embedReplacement)
			System.out.println("\033[38;5;226m"+"" + (mixedOutputHead.isEmpty() ? "" : "[" + mixedOutputHead + "]") + " " + line);
	}
}
