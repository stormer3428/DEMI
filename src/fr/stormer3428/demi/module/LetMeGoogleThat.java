package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LetMeGoogleThat extends Module{

	private boolean whitelistEnabled;
	private List<Long> whitelist = new ArrayList<>();
	
	public LetMeGoogleThat() {
		super(new File("LetMeGoogleThat.cfg"));

		CONFIG_KEYS.add(new Key("whitelistEnabled", "true", 
				"//Whether i should reply to a limited amount of user or not"));
		
		CONFIG_KEYS.add(new Key("whitelist", "[]", 
				"//The list of user i will reply to when the whitelist is enabled"));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.whitelistEnabled = this.CONFIG.get("whitelistEnabled").equalsIgnoreCase("true");
		this.OUTPUT.trace("whitelistEnabled : " + (this.whitelistEnabled ? "true" : false), this.PRINT_STACK_TRACE);
		this.whitelist = whitelist();
		if(this.whitelist == null) return;
		if(this.whitelist.isEmpty()) {
			if(this.whitelistEnabled) {
				this.OUTPUT.warning("whitelistEnabled was set to true but no whitelist was set");
				this.OUTPUT.cancelled("disabming module");
				this.whitelistEnabled = false;
				Demi.disableModule(this);
				return;
			}
		}else {
			this.OUTPUT.trace("whitelist : ", this.PRINT_STACK_TRACE);
			for(Long member : this.whitelist) {
				this.OUTPUT.trace("- " + member, this.PRINT_STACK_TRACE);
			}
		}
	}
	
	private List<Long> whitelist() {
		List<String> whitelistString = this.CONFIG.getList("whitelist");
		List<Long> returnedWhitelist = new ArrayList<>();
		try {
			for(String str : whitelistString) returnedWhitelist.add(Long.parseLong(str));
			return returnedWhitelist;
		} catch (NumberFormatException e) {
			this.OUTPUT.error("Error while retrieving whitelist");
			this.OUTPUT.error("Expected an array of user ids but got ("+this.CONFIG.get("whitelist")+")");
			handleTrace(e);
			if(this.whitelistEnabled) {
				this.OUTPUT.warning("Disabling module to prevent errors");
				Demi.disableModule(this);
			}else {
				this.OUTPUT.cancelled("whitelistEnabled is set to false, skipping");
			}
			return null;
		}

	}
	
	@Override
	public String getName() {
		return "LetMeGoogleThat";
	}

	@Override
	public String getDescription() {
		return "A module that will automatically search stuff when prompted";
	}

	static final List<String> searchWords = new ArrayList<>();
	static final List<String> replaceSearchWords = new ArrayList<>();
	static final List<String> askWords = new ArrayList<>();
	
	static final List<String> uselessEnders = new ArrayList<>();
	
	static final List<String> replies = new ArrayList<>();
	
	static {
		replaceSearchWords.add("look up ");
		replaceSearchWords.add("search ");
		replaceSearchWords.add("search for ");
		replaceSearchWords.add("find info about ");
		replaceSearchWords.add("find info ");
		replaceSearchWords.add("tell me about ");
		
		askWords.add("can you ");
		askWords.add("could you ");
		askWords.add("would you ");
		askWords.add("hey ");
		
		searchWords.add("what's");
		searchWords.add("whats");
		searchWords.add("what is");
		searchWords.add("who is");
		searchWords.add("why");
		searchWords.add("does");
		searchWords.add("is");

		uselessEnders.add("for me");
		uselessEnders.add("please");

		replies.add("Sure thing");
		replies.add("Right away");
		replies.add("Alright %u");
		replies.add("Seems simple enough");
		replies.add("You really wanna look that up?...");
		replies.add("Yup!");
		replies.add("Yes sir");
		replies.add("Yes %u");
		replies.add("That's a weird thing to ask for but alright");
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel channel = event.getChannel();
		if(channel == null) return;
		if(whitelistEnabled && !whitelist.contains(event.getMember().getIdLong())) return;
		
		String raw = event.getMessage().getContentRaw().toLowerCase();

		if(!raw.contains("demi")) return;
		raw = raw.replace("demi ", "");
		
		for(String word : askWords) if(raw.startsWith(word)) {
			raw = raw.replaceFirst(word, "");
			break;
		}
		
		boolean Return = true;

		for(String word : replaceSearchWords) if(raw.startsWith(word)) {
			Return = false;
			raw = raw.replaceFirst(word, "");
			break;
		}
		if(Return) for(String word : searchWords) if(raw.startsWith(word)) {
			Return = false;
			break;
		}
		if(Return) return;

		String reply = replies.get(new Random().nextInt(replies.size()));
		reply = reply.replace("%u", event.getMember().getEffectiveName());
		channel.sendMessage(reply).complete();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {handleTrace(e);}
		
		for(String word : uselessEnders) if(raw.endsWith(word)) {
			raw = raw.replace(word, "");
			break;
		}
		
		String link = "https://letmegooglethat.com/?q=";
		
		String[] argsArray = raw.split(" ");
		for(String s : argsArray) if(!s.isEmpty()) link = link + "+" + s;
		
		channel.sendMessage(link).complete();
		


	}

}
