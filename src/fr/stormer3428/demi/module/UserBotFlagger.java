package fr.stormer3428.demi.module;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserBotFlagger extends Module{

	private HashMap<Long, Long> lastMessagesChannelMap = new HashMap<>();

	private HashMap<Long, String> lastMessagesMap = new HashMap<>();
	private HashMap<Long, Integer> lastMessagesAmountMap = new HashMap<>();
	private HashMap<Long, Message> loggingMessageMap = new HashMap<>();
	private HashMap<Long, List<Message>> lastMessagesIDsMap = new HashMap<>();

	private ArrayList<Pattern> triggerRegexes = new ArrayList<>();
	private IO TRIGGER_REGEXES_DATABASE;

	private static enum Mode{
		Ping,
		Autoban,
		AutobanTriggerWord,
		Dual,
		DualTriggerWord,
		Invalid;

		public static Mode getMode(String name) {
			for(Mode mode : Mode.values()) if(mode.toString().equalsIgnoreCase(name)) return mode;
			return Invalid;
		}
	}

	private Mode mode;
	private int triggerthreshold;

	private MixedOutput LOG;

	public UserBotFlagger() {
		super(new File("conf/userbotflagger.conf"));

		CONFIG_KEYS.add(new Key("triggerthreshold", "5", 
				"The threshold of the number of identical messages sent in different channels needed for the modul to flag a member as a bot and autoban them"));

		CONFIG_KEYS.add(new Key("flagLoggingChannelID", "CHANNEL_ID",
				"The id of the channel the logging should happen into"));

		CONFIG_KEYS.add(new Key("blackListedRoles", "[]", 
				"A list of role ids the module should never flag"));

		CONFIG_KEYS.add(new Key("blackListedUsers", "[]",
				"A list of user ids the module should never flag"));

		CONFIG_KEYS.add(new Key("mode", "autobanTriggerWord",
				"The mode of the module : \n"
						+ "//ping : Pings the roles in rolesToPing\n"
						+ "//autoBan : Automatically bans the flagged members\n"
						+ "//autobanTriggerWord : Automatically bans the flagged members if the message matches a trigger regex"
						+ "//dual : Automatically ban and ping roles"
						+ "//dualTriggerWords : Automatically ping roles and bans if the message matches a trigger regex"));

		CONFIG_KEYS.add(new Key("rolesToPing", "[]",
				"A list of roles to ping whenever the module flags a bot"));

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

		mode = Mode.getMode(CONFIG.get("mode"));
		if(mode == Mode.Invalid) {
			OUTPUT.error("Error, invalid mode selected, allowed modes are : ");
			for(Mode mode : Mode.values()) OUTPUT.error("- " + mode.toString());
			OUTPUT.error("Disabling module to prevent errors...");
			Demi.disableModule(this);
		}
		triggerRegexes.clear();
		if(mode == Mode.AutobanTriggerWord || mode == Mode.DualTriggerWord) {
			TRIGGER_REGEXES_DATABASE = new IO(new File("conf/userbotflaggertriggerregexes.conf"), new ArrayList<>(), true, IO.defaultHeaders, ""
					+ "// This file is meant to host all trigger regexes for the userbotflagger module\n"
					+ "\n// You have to put here a regex per line"
					+ "\n// /!\\ IMPORTANT /!\\"
					+ "\n// The regex has to  match the ENTIRE message! not just one part"
					+ "\n// For example, you wat @everyone to be a trigger word, \"@everyone\" will not work unless the message is composed strictly and only of @everyone"
					+ "\n// for it to match any message CONTAINING @everyone, it would look like this \"/.*@everyone.*/s\""
					+ "\n// And remember, ONE regex per line, if you want a multiline regex, just use \\n"
					+ "\n// Here's a nice bewsite to test out regexes on : 'https://regex101.com'"
					+ "\n"
					+ "\n.*@everyone.*"
					+ "\n.*discord\\.gg.*"
					+ "\n.*discord\\.com.*");

			for(String s : TRIGGER_REGEXES_DATABASE.getAllRaw(true)) {
				if(s.isBlank() || s.isEmpty()) continue;
				Pattern pattern = Pattern.compile(s, Pattern.DOTALL);
				triggerRegexes.add(pattern);
			}
			
			OUTPUT.info("Regex registered");
			for(Pattern s : triggerRegexes) OUTPUT.info(s.pattern());

		}
		this.OUTPUT.trace("mode : " + this.mode.toString(), this.PRINT_STACK_TRACE);

		String channelId = CONFIG.get("flagLoggingChannelID");

		TextChannel channel = Demi.jda.getGuildById(Demi.i.getServerID()).getTextChannelById(channelId);
		if(channel == null) {
			this.OUTPUT.error("Error, invalid channel id given (flagLoggingChannelID)");
			OUTPUT.error("Disabling module to prevent errors...");
			Demi.disableModule(this);
		}
		this.OUTPUT.trace("flagLoggingChannelID : " + channelId, this.PRINT_STACK_TRACE);

		LOG = new MixedOutput(channelId, true, OUTPUT.isOutputToConsole(), "");

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
	public String getDescription() {
		return "A module that automatically flags bots that sends the exact same message in multiple channels and bans them automatically";
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!event.isFromGuild()) return;
		Member member = event.getMember();
		if(member == null) return;
		if(member.getUser() == null) return;
		if(member.getUser().isBot()) return;
		List<String> blacklistedUsersId = CONFIG.getList("blackListedUsers");
		if(blacklistedUsersId.contains(member.getId())) return;
		List<String> blacklistedRolesId = CONFIG.getList("blackListedRoles");
		List<Role> roles = member.getRoles();
		for(Role role : roles) if(blacklistedRolesId.contains(role.getId())) return;
		Long id = member.getIdLong();
		String message = event.getMessage().getContentRaw();
		if(message == null) return;
		if(message.isEmpty()) return;
		if(!lastMessagesMap.containsKey(id)) {
			lastMessagesMap.put(id, message);
			lastMessagesChannelMap.put(id, event.getChannel().getIdLong());
			lastMessagesIDsMap.put(id, new ArrayList<>());
		}
		if(!lastMessagesAmountMap.containsKey(id)) lastMessagesAmountMap.put(id, 0);

		//available variables : member roles id message

		lastMessagesChannelMap.put(id, event.getChannel().getIdLong());


		String lastMessage = lastMessagesMap.get(id);
		if(lastMessage.equals(message)) {
			lastMessagesIDsMap.get(id).add(event.getMessage());

			lastMessagesAmountMap.put(id, lastMessagesAmountMap.get(id) + 1);
			int amount = lastMessagesAmountMap.get(id);
			if(amount >= triggerthreshold) flag(member);
			return;
		}

		lastMessagesMap.put(id, message);
		lastMessagesAmountMap.put(id, 0);
		lastMessagesIDsMap.put(id, new ArrayList<>());
		loggingMessageMap.remove(id);
	}

	private void flag(Member member) {
		long id = member.getIdLong();
		String name = member.getEffectiveName();
		String lastMessage = lastMessagesMap.get(id);

		EmbedBuilder builder = new EmbedBuilder();
		List<String> embedReplacement = new ArrayList<>();

		builder.setAuthor("User bot flagger");
		builder.setTitle("Flagged member " + member.getEffectiveName());
		builder.setThumbnail(member.getUser().getEffectiveAvatarUrl());
		builder.setColor(new Color(200, 0, 0));
		builder.addField("Flagged member " + name + " as a user bot", member.getAsMention(), false);
		//LOG.action("Flagged member " + name + "(" + member.getAsMention() + ") as a user bot");
		embedReplacement.add("Flagged member " + member.getEffectiveName() + "("+member.getId()+") as an user bot");

		boolean containstTriggerWord = false;
		if(mode == Mode.AutobanTriggerWord || mode == Mode.DualTriggerWord) {
			for(Pattern regex : triggerRegexes) {
				OUTPUT.info("checking match for : \"" + regex + "\"");
				if(regex.matcher(lastMessage).matches()) {
					OUTPUT.info("Match found!");
					containstTriggerWord = true;
					break;
				}
			}
		}

		String status = "Waiting";
		if(mode == Mode.Autoban ||	mode == Mode.Dual || ((mode == Mode.AutobanTriggerWord || mode == Mode.DualTriggerWord) && containstTriggerWord)) {
			//LOG.ok("Banned member <@" + member.getIdLong() + "> (" + name + ")");
			status = "Autobanned";
			embedReplacement.add("User was autobanned");
			member.ban(7, TimeUnit.DAYS).complete();
		}

		if(mode == Mode.Ping || mode == Mode.Dual) {
			String ping = "";
			for(String rolePingId : CONFIG.getList("rolesToPing")) ping = rolePingId + "\n";
			//LOG.info(ping);
			status = "Pinging Moderators for manual decision";
			builder.addField("AutoPing: ", ping, false);	
		}

		builder.addField("Status: ", status, false);
		builder.setDescription(lastMessage);
		//LOG.info("Message that triggered the flag : \n" + lastMessage);

		int size = lastMessagesAmountMap.get(id);

		while(!lastMessagesIDsMap.get(id).isEmpty()) {
			Message messagetdlt = lastMessagesIDsMap.get(id).remove(0);
			messagetdlt.delete().queue();
		}
		//LOG.info("deleted " + size + " message(s)");
		builder.addField("deleted " + lastMessagesAmountMap.get(id) + " message" + (size > 1 ? "s" : ""), "", false);

		Message m = null;
		if(loggingMessageMap.containsKey(id)) {
			m = loggingMessageMap.get(id).editMessageEmbeds(builder.build()).complete();
		}else m = LOG.embed(builder.build(), embedReplacement);
		if(m != null) loggingMessageMap.put(id, m);
	}

}
