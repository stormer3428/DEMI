package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandDispatcher extends Module{

	private String prefix;
	private boolean acceptFromConsole;
	private boolean acceptFromDiscord;
	private boolean acceptFromDiscordBots;
	private boolean active = false;
	private Thread consoleThread;

	Scanner console;

	public CommandDispatcher() {
		super(new File("commanddispatcher.cfg"));

		CONFIG_KEYS.add(new Key("prefix", "?"));
		CONFIG_KEYS.add(new Key("acceptCommandsFromConsole", "true"));
		CONFIG_KEYS.add(new Key("acceptCommandsFromDiscord", "true"));
		CONFIG_KEYS.add(new Key("acceptCommandsFromDiscordBots", "false"));
		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public String getName() {
		return "CommandDispatcher";
	}

	@Override
	public String getDescription() {
		return "A primary module that handle the detection and dispatching of commands to all other modules";
	}

	@Override
	public void onDisable() {
		active = false;
		if(acceptFromConsole) {
			OUTPUT.action("Waiting for console thread to close...");
			try {
				while(consoleThread.isAlive()) {
					Thread.sleep(100);
				}
				OUTPUT.ok("Thread ended successfully");
			} catch (InterruptedException e) {
				OUTPUT.error("Caught an error while waiting for the console thread");
				handleTrace(e);
			}
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();
		acceptFromConsole = CONFIG.get("acceptCommandsFromConsole").equalsIgnoreCase("true");
		OUTPUT.info("acceptCommandsFromConsole : " + (acceptFromConsole ? "true" : "false"));
		acceptFromDiscord = CONFIG.get("acceptCommandsFromDiscord").equalsIgnoreCase("true");
		OUTPUT.info("acceptCommandsFromDiscord : " + (acceptFromDiscord ? "true" : "false"));
		acceptFromDiscordBots = CONFIG.get("acceptCommandsFromDiscordBots").equalsIgnoreCase("true");
		OUTPUT.info("acceptCommandsFromDiscordBots : " + (acceptFromDiscord ? "true" : "false"));
		prefix = CONFIG.get("prefix");
		OUTPUT.info("prefix : " + prefix);

		OUTPUT.ok("Successfully loaded all config parameters");
		active = true;

		if(!acceptFromConsole) return;
		startConsoleInputReader();
	}

	private void startConsoleInputReader() {
		OUTPUT.action("Starting console input stream reader");
		consoleThread = new Thread(new Runnable() {

			@Override
			public void run() {
				OUTPUT.ok("Thread started");
				console = new Scanner(Demi.i.SystemIn);
				while (active) {
					while(active && !console.hasNext()) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							handleTrace(e);
						}
					}

					if(!active) break;
					String line = console.next();

					if(line.isEmpty()) continue;

					String raw = line;
					String cmd = raw.split(" ", 2)[0].toLowerCase();
					String[] argsArray = raw.replaceFirst(cmd, "").split(" ");
					ArrayList<String> args = new ArrayList<String>();

					OUTPUT.info("Command received from console : ");
					OUTPUT.info(cmd);
					for(String s : argsArray) if(!s.isEmpty()) args.add(s);
					for(String s : args) OUTPUT.info("- " + s);
					for(Module module : Demi.i.getActiveModules()) {
						new Thread(new Runnable() {
							@Override public void run() {
								module.onCommand(new DemiCommandReceiveEvent(null, cmd, args, OUTPUT));
							}
						}).start();
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						handleTrace(e);
					}
				}
				OUTPUT.info("Console reader thread stopped");
				console.close();
			}
		});
		consoleThread.start();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!acceptFromDiscord) return;
		if(!acceptFromDiscordBots && event.getAuthor().isBot()) return;

		Message message = event.getMessage();
		Member member = event.getMember();
		if(member == null) return;

		if(prefix != null && !prefix.isEmpty() && message.getContentRaw() != null && message.getContentRaw().startsWith(prefix)) {

			String raw = message.getContentRaw().replace(prefix, "");
			String cmd = raw.split(" ", 2)[0].toLowerCase();
			String[] argsArray = raw.replaceFirst(cmd, "").split(" ");
			ArrayList<String> args = new ArrayList<String>();

			OUTPUT.info("Command received from discord : ");
			OUTPUT.info(cmd);
			for(String s : argsArray) if(!s.isEmpty()) args.add(s);
			for(String s : args) OUTPUT.info("- " + s);


			for(Module module : Demi.i.getActiveModules()) {
				new Thread(new Runnable() {
					@Override public void run() {
						module.onCommand(new DemiCommandReceiveEvent(event, cmd, args, new MixedOutput(event.getTextChannel().getId(), false, "")));
					}
				}).start();
			}
		}
	}

}
