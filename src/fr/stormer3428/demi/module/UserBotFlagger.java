package fr.stormer3428.demi.module;

import java.io.File;
import java.util.HashMap;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UserBotFlagger extends Module{

	private HashMap<Long, String> lastMessagesMap = new HashMap<>();
	private HashMap<Long, Long> lastMessagesChannelMap = new HashMap<>();
	private HashMap<Long, Integer> lastMessagesAmountMap = new HashMap<>();
	private int triggerthreshold;

	public UserBotFlagger() {
		super(new File("userBotFlagger.cfg"));

		CONFIG_KEYS.add(new Key("triggerthreshold", "5"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		triggerthreshold = triggerthreshold();
		if(this.triggerthreshold == -1) return;
		this.OUTPUT.trace("triggerthreshold : " + this.triggerthreshold, this.PRINT_STACK_TRACE);
		
		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	private int triggerthreshold() {
		int i = -1;
		try{
			i = Integer.parseInt(this.CONFIG.get("triggerthreshold"));
		}catch (NumberFormatException e) {
			handleTrace(e);
		}
		if(i > 0) return i;
		this.OUTPUT.error("Failed to retrieve parameter (triggerthreshold) in config file " + getName());
		this.OUTPUT.warning("Retrieved value : " + this.CONFIG.get("triggerthreshold"));
		this.OUTPUT.warning("Expected a strictly positive number");
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
		return -1;
	}

	@Override
	public String getName() {
		return "UserBotFlagger";
	}

	@Override
	public String getDescription() {
		return "A module that automatically flags bots that sends the exact same message in multiple channels and bans them automatically";
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		Member member = event.getMember();
		if(member.getUser() == null) return;
		if(member.getUser().isBot()) return;
		Long id = member.getIdLong();
		String message = event.getMessage().getContentRaw();
		if(message == null) return;
		
		if(!lastMessagesMap.containsKey(id)) {
			lastMessagesMap.put(id, message);
			lastMessagesChannelMap.put(id, event.getChannel().getIdLong());
			lastMessagesAmountMap.put(id, 1);
		}
		
		Long lastChannelId = lastMessagesChannelMap.get(id);
		lastMessagesChannelMap.put(id, event.getChannel().getIdLong());

		String lastMessage = lastMessagesMap.get(id);
		if(lastMessage.equals(message) && event.getChannel().getIdLong() != lastChannelId) {
			int amount = lastMessagesAmountMap.get(id);
			amount ++;
			if(amount >= triggerthreshold) {
				String name = member.getEffectiveName();
				OUTPUT.action("Flagged member " + name + "(" + member.getAsMention() + ") as a user bot");
				member.ban(7).complete();
				OUTPUT.ok("banned member " + name + " and deleted last 7 days of message history");
				return;
			}
			lastMessagesAmountMap.put(id, amount);
			return;
		}
		lastMessagesMap.put(id, message);
		lastMessagesAmountMap.put(id, 1);
	}

}
