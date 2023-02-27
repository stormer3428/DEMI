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
		super(new File("conf/discordcommanddispatcher.conf"));

		this.CONFIG_KEYS.add(new Key("prefix", "?"));
		this.CONFIG_KEYS.add(new Key("acceptCommandsFromDiscordBots", "false"));
		
		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "A primary module that handle the detection and dispatching of commands coming from discord to all other modules";
	}
	@Override
	public void onEnable() {
		super.onEnable();
		this.acceptFromDiscordBots = this.CONFIG.get("acceptCommandsFromDiscordBots").equalsIgnoreCase("true");
		this.OUTPUT.trace("acceptCommandsFromDiscordBots : " + (this.acceptFromDiscordBots ? "true" : "false"), this.PRINT_STACK_TRACE);
		this.prefix = this.CONFIG.get("prefix");
		this.OUTPUT.trace("prefix : " + this.prefix, this.PRINT_STACK_TRACE);

		this.OUTPUT.ok("Successfully loaded all config parameters");
	}


	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!this.acceptFromDiscordBots && event.getAuthor().isBot()) return;

		Message message = event.getMessage();
		Member member = event.getMember();
		if(member == null) return;

		if(this.prefix != null && !this.prefix.isEmpty() && message.getContentRaw() != null && message.getContentRaw().startsWith(this.prefix)) {

			String raw = message.getContentRaw().replace(this.prefix, "");
			String cmd = raw.split(" ", 2)[0];
			String[] argsArray = message.getContentRaw().replace(this.prefix + cmd, "").split(" ");
			ArrayList<String> args = new ArrayList<>();

			this.OUTPUT.info("Command received from discord : ");
			this.OUTPUT.info(cmd);
			for(String s : argsArray) if(!s.isEmpty()) args.add(s);
			for(String s : args) this.OUTPUT.info("- " + s);

			List<Thread> threads = new ArrayList<>();
			for(Module module : Demi.i.getActiveModules()) {
				threads.add(new Thread(new Runnable() {
					@Override public void run() {
						module.onCommand(new DemiCommandReceiveEvent(event, cmd.toLowerCase(), args, new MixedOutput(event.getChannel().getId(), false, "")));
					}
				}));
			}
			for(Thread th : threads) th.start();
		}
	}
}
