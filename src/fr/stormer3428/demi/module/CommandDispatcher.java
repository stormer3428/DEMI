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

	private CommandDispatcher i;
	private String prefix;
	private boolean acceptFromConsole;
	private boolean acceptFromDiscord;
	private boolean acceptFromDiscordBots;

	Scanner console;

	public CommandDispatcher() {
		super(new File("commanddispatcher.cfg"));
		i = this;

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
		console.close();
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

		if(!acceptFromConsole) return;
		console = new Scanner(System.in);
		startConsoleInputReader();
	}

	private void startConsoleInputReader() {
		OUTPUT.action("Starting console input stream reader");
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (Demi.i.getActiveModules().contains(i)) {
					String line = console.nextLine();

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
								boolean success = module.onCommand(new DemiCommandReceiveEvent(null, cmd, args, OUTPUT));
								if(!success) {
									OUTPUT.warning("Unknow command " + cmd);
								}
							}
						}).start();
					}
				}
			}
		}).start();
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
