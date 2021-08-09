package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DiscordCommandDispatcher extends Module{

	private String prefix;
	private boolean acceptFromDiscordBots;

	public DiscordCommandDispatcher() {
		super(new File("discordcommanddispatcher.cfg"));

		CONFIG_KEYS.add(new Key("prefix", "?"));
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
		return "DiscordCommandDispatcher";
	}

	@Override
	public String getDescription() {
		return "A primary module that handle the detection and dispatching of commands coming from discord to all other modules";
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		super.onEnable();
		acceptFromDiscordBots = CONFIG.get("acceptCommandsFromDiscordBots").equalsIgnoreCase("true");
		OUTPUT.trace("acceptCommandsFromDiscordBots : " + (acceptFromDiscordBots ? "true" : "false"));
		prefix = CONFIG.get("prefix");
		OUTPUT.trace("prefix : " + prefix);

		OUTPUT.ok("Successfully loaded all config parameters");
	}


	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!acceptFromDiscordBots && event.getAuthor().isBot()) return;

		Message message = event.getMessage();
		Member member = event.getMember();
		if(member == null) return;

		if(prefix != null && !prefix.isEmpty() && message.getContentRaw() != null && message.getContentRaw().startsWith(prefix)) {

			String raw = message.getContentRaw().replace(prefix, "");
			String cmd = raw.split(" ", 2)[0].toLowerCase();
			String[] argsArray = message.getContentRaw().replace(prefix + cmd, "").split(" ");
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
