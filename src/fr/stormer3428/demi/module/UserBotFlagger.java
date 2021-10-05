package fr.stormer3428.demi.module;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UserBotFlagger extends Module{

	private HashMap<Long, String> lastMessagesMap = new HashMap<>();
	private HashMap<Long, Long> lastMessagesChannelMap = new HashMap<>();
	private HashMap<Long, Integer> lastMessagesAmountMap = new HashMap<>();

	private static enum Mode{
		Ping,
		Autoban,
		Dual,
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
		super(new File("userBotFlagger.cfg"));

		CONFIG_KEYS.add(new Key("triggerthreshold", "5", 
				"//The threshold of the number of identical messages sent in different channels needed for the modul to flag a member as a bot and autoban them"));

		CONFIG_KEYS.add(new Key("flagLoggingChannelID", "CHANNEL_ID",
				"//The id of the channel the logging should happen into"));

		CONFIG_KEYS.add(new Key("blackListedRoles", "[]", 
				"//A list of role ids the module should never flag"));

		CONFIG_KEYS.add(new Key("blackListedUsers", "[]",
				"//A list of user ids the module should never flag"));

		CONFIG_KEYS.add(new Key("mode", "ping",
				"//The mode of the module : \n"
						+ "//ping : Pings the roles in rolesToPing\n"
						+ "//autoBan : Automatically bans the flagged members\n"
						+ "//dual : Automatically ban and ping roles"));

		CONFIG_KEYS.add(new Key("rolesToPing", "[]",
				"//A list of roles to ping whenever the module flags a bot"));

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
		this.OUTPUT.trace("mode : " + this.mode.toString(), this.PRINT_STACK_TRACE);

		String channelId = CONFIG.get("flagLoggingChannelID");

		TextChannel channel = Demi.jda.getGuildById(Demi.i.getServerID()).getTextChannelById(channelId);
		if(channel == null) {
			this.OUTPUT.error("Error, invalid channel id given (flagLoggingChannelID)");
			OUTPUT.error("Disabling module to prevent errors...");
			Demi.disableModule(this);
		}
		this.OUTPUT.trace("flagLoggingChannelID : " + channelId, this.PRINT_STACK_TRACE);

		LOG = new MixedOutput(channelId, true, OUTPUT.isOutputToConsole(), OUTPUT.header());

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
		List<String> blacklistedUsersId = CONFIG.getList("blackListedUsers");
		if(blacklistedUsersId.contains(member.getId())) return;
		List<String> blacklistedRolesId = CONFIG.getList("blackListedRoles");
		List<Role> roles = member.getRoles();
		for(Role role : roles) if(blacklistedRolesId.contains(role.getId())) return;

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
				LOG.action("Flagged member " + name + "(" + member.getAsMention() + ") as a user bot");
				if(mode == Mode.Autoban || mode == Mode.Dual) {
					member.ban(7).complete();
					LOG.ok("banned member " + name + " and deleted last 7 days of message history");
				}
				if(mode == Mode.Ping || mode == Mode.Dual) {
					String ping = "";
					Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
					for(String rolePingId : CONFIG.getList("rolesToPing")) {
						Role role = guild.getRoleById(rolePingId);
						if(role == null) {
							OUTPUT.error("Invalid rolePing id! ("+rolePingId+")");
							continue;
						}
						ping = role.getAsMention() + "\n";
					}
					LOG.info(ping);
				}
				LOG.info("Message that triggered the flag : \n"
						+ lastMessage);
				return;
			}
			lastMessagesAmountMap.put(id, amount);
			return;
		}
		lastMessagesMap.put(id, message);
		lastMessagesAmountMap.put(id, 1);
	}

}
