package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class MixedOutput {

	private static IO io;

	private static boolean enableBuffer;
	private static int bufferTimeoutMS;
	private static Thread bufferThread;
	private static HashMap<String, String> buffer = new HashMap<>();


	public boolean PRINT_STACK_TRACE;

	private String outputChannelID = "";
	private boolean outputToChannel;
	private boolean outputToConsole;
	private String mixedOutputHead;

	public TextChannel getTextChannel() {
		return Demi.jda.getGuildById(Demi.i.getServerID()).getTextChannelById(outputChannelID);
	}

	public String header() {
		return mixedOutputHead;
	}

	public boolean isOutputToConsole() {
		return outputToConsole;
	}

	public boolean isOutputToChannel() {
		return outputToChannel;
	}

	public MixedOutput(String channelID, boolean channel, boolean console, String header) {
		if(io == null) {
			ArrayList<Key> keys = new ArrayList<>();
			keys.add(new Key("printStackTrace", "true"));
			keys.add(new Key("enableBuffer", "true"));
			keys.add(new Key("bufferTimeoutMS", "2000"));
			io = new IO(new File("mixedoutput.conf"), keys, true);

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
		this.outputChannelID = channelID;
		this.outputToChannel = channel;
		this.outputToConsole = console;
		this.mixedOutputHead = header;
		buffer.put(outputChannelID, "");
		if(!this.outputToChannel) return;
	}

	public MixedOutput(String channelID, boolean console, String header) {
		this(channelID, true, console, header);
	}

	public MixedOutput() {
		this(null, false, true, "");
	}

	protected void handleTrace(Exception e) {
		if(this.PRINT_STACK_TRACE) {
			DemiConsole.info("printing stack trace");
			e.printStackTrace();
		}else DemiConsole.cancelled("Mixed Output set to not print stack trace");
	}

	private void startBufferThread() {
		if(bufferThread != null) return;
		DemiConsole.info("Starting up buffer discord console sender (" + outputChannelID + ", " + outputToChannel + ", " + outputToConsole + ")");
		bufferThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					if(!buffer.isEmpty()) {
						if(Demi.jda != null) {
							Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());

							for(String id : buffer.keySet()) {
								if(buffer.get(id).isEmpty()) continue;
								final String[] listRaw = buffer.get(id).split("\n");
								List<String> list = new ArrayList<>();
								for(String s : listRaw) list.add(s);

								TextChannel channel = guild.getTextChannelById(id);
								if(channel == null) continue;

								while(!list.isEmpty()) {
									String tempBuffer = "";
									while(!list.isEmpty() && tempBuffer.length() + list.get(0).length() + 2 < 2000) tempBuffer = tempBuffer + "\n" +list.remove(0);
									channel.sendMessage(tempBuffer).complete();
									tempBuffer = "";
								}
								buffer.put(id, "");
							}
						}
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

	public final void error(String message) {
		if(this.outputToConsole) System.out.println("\033[38;5;196m" + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + "[📕Error] " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " **ERROR 📕 " + message + ".**" + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " **ERROR 📕 " + message + ".**" + "\n").queue();
	}

	public final void warning(String message) {
		if(this.outputToConsole) System.out.println("\033[38;5;208m" + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + "[📙Warning] " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " ***WARNING 📙 " + message + ".***" + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " ***WARNING 📙 " + message + ".***" + "\n").queue();
	}

	public final void ok(String message) {
		if(this.outputToConsole) System.out.println("\033[38;5;118m" + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + "[📗Ok] " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " Ok 📗 " + message + "." + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " Ok 📗 " + message + "." + "\n").queue();
	}

	public final void action(String message) {
		if(this.outputToConsole) System.out.println("\033[38;5;123m"+"" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + "[📘Action] " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " Action 📘 " + message + "." + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " Action 📘 " + message + "." + "\n").queue();
	}

	public final void cancelled(String message) {
		if(this.outputToConsole) System.out.println("\033[38;5;245m"+"" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + "[📓Cancelled] " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " *Cancelled 📓 " + message + ".*" + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " *Cancelled 📓 " + message + ".*" + "\n").queue();
	}

	public final void info(String message) {
		if(this.outputToConsole) System.out.println("\033[38;5;226m"+"" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + "[Info] " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " Info " + message + "." + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " Info " + message + "." + "\n").queue();
	}

	public final void trace(String message, boolean print) {
		if(!print && !this.PRINT_STACK_TRACE) return;
		if(this.outputToConsole) System.out.println("\033[38;5;226m"+"" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		if(enableBuffer) buffer.put(outputChannelID, buffer.get(outputChannelID) + "" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " " + message + "." + "\n");
		else getTextChannel().sendMessage("" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " " + message + "." + "\n").queue();
	}

	public void command(String message) {
		if(this.outputToConsole)  System.out.println("\033[38;5;226m"+"" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " " + message + "\033[38;5;7m");
		if(!this.outputToChannel) return;

		getTextChannel().sendMessage("```" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + message + "```" + "\n").queue();
	}

	public Message embed(MessageEmbed embed, List<String> embedReplacement) {
		Message r = null;
		if(this.outputToChannel) {
			r = getTextChannel().sendMessage(embed).complete();
		}
		if(this.outputToConsole) for(String line : embedReplacement) System.out.println("\033[38;5;226m"+"" + (this.mixedOutputHead.isEmpty() ? "" : "[" + this.mixedOutputHead + "]") + " " + line);
		return r;
	}
}
