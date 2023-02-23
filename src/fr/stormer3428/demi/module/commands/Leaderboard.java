package fr.stormer3428.demi.module.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.Key;
import fr.stormer3428.demi.MixedOutput;
import fr.stormer3428.demi.Module;
import fr.stormer3428.demi.module.LevelCalculator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Leaderboard extends CommandModule{

	private long lastRefreshed = System.currentTimeMillis();
	private long cacheRefreshRateMS;
	private boolean useCache;

	private int rowsDisplayed;

	private HashMap<Integer, List<Long>> expToMemberCachedLeaderboard;
	private LevelCalculator LEVEL_CALCULATOR;

	public Leaderboard() {
		super("leaderboard");

		this.aliases.add("ldb");

		CONFIG_KEYS.add(new Key("rowsDisplayed", "25"));

		CONFIG_KEYS.add(new Key("useCache", "true"));
		CONFIG_KEYS.add(new Key("cacheRefreshRateMS", (60 * 1000 * 10) + ""));

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "The level show the top leveled members on the server";
	}

	@Override
	public List<String> getDependencies() {
		List<String> dependencies = new ArrayList<>();
		dependencies.add("LevelCalculator");
		return dependencies;
	}

	@Override
	public void onEnable() {
		super.onEnable();
		try {
			for(Module module : Demi.i.getActiveModules()) if(module.getName().equals("LevelCalculator")) {
				this.LEVEL_CALCULATOR = (LevelCalculator) module;
				break;
			}
		}catch (Exception e) {
			this.OUTPUT.error("Error while hooking to dependency LevelCalculator");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		try {
			rowsDisplayed = Integer.parseInt(CONFIG.get("rowsDisplayed"));
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Error while parsing rowsDisplayed");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}

		useCache = CONFIG.get("useCache").equalsIgnoreCase("true");
		try {
			cacheRefreshRateMS = Long.parseLong(CONFIG.get("cacheRefreshRateMS"));
		}catch (NumberFormatException e) {
			this.OUTPUT.error("Error while parsing cacheRefreshRateMS");
			handleTrace(e);
			this.OUTPUT.warning("Disabling module to prevent errors");
			Demi.disableModule(this);
			return;
		}
		this.OUTPUT.ok("Successfully loaded all config parameters");
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		if(useCache && expToMemberCachedLeaderboard != null && (System.currentTimeMillis() - lastRefreshed) < cacheRefreshRateMS) {
			showLeaderBoard(event.getOutput(), expToMemberCachedLeaderboard);
			return;
		}

		HashMap<Integer, List<Long>> leaderboard = new HashMap<Integer, List<Long>>();

		HashMap<String, String> all = LEVEL_CALCULATOR.getLEVEL_DATABASE().getAll();
		for(String idString : all.keySet()) {
			String levelString = all.get(idString);
			long id;
			int level;
			try {
				id = Long.parseLong(idString);
				level = Integer.parseInt(levelString);
			} catch (NumberFormatException e) {
				continue;
			}

			if(!leaderboard.containsKey(level)){
				leaderboard.put(level, new ArrayList<>());
			}
			leaderboard.get(level).add(id);
		}
		if(useCache) {
			expToMemberCachedLeaderboard = leaderboard;
			lastRefreshed = System.currentTimeMillis();
			showLeaderBoard(event.getOutput(), expToMemberCachedLeaderboard);
		}else showLeaderBoard(event.getOutput(), leaderboard);
	}

	private void showLeaderBoard(MixedOutput OUTPUT, HashMap<Integer, List<Long>> leaderboard) {
		if(OUTPUT.isOutputToChannel()) leaderboardMessageDiscord(OUTPUT, leaderboard);
		else leaderboardMessageConsole(OUTPUT, leaderboard);
	}
	
	private void leaderboardMessageConsole(MixedOutput OUTPUT, HashMap<Integer, List<Long>> leaderboard) {
		OUTPUT.command("Computing leaderboard \n"
				+ "Computing the top \" + rowsDisplayed + \" of the server...\", \"This usually takes about 15 seconds...\n");
		
		String ldbString = "";
		int count = 1;
		SortedSet<Integer> sortedLdbSet = new TreeSet<>(leaderboard.keySet());
		ArrayList<Integer> sorted = new ArrayList<>();
		if(true) {
			int i = sortedLdbSet.size();
			for(int y = 0; y < i; y++) sorted.add(0);
			for(int key : sortedLdbSet) {
				i --;
				sorted.set(i, key);
			}
		}

		for(int key : sorted) {
			if(count > rowsDisplayed) break;
			for(Long userId : leaderboard.get(key)) {
				if(count > rowsDisplayed) break;
				String name = "";
				User user = Demi.jda.retrieveUserById(userId).complete();
				if(user == null) {
					name = userId + "";
				}else {
					Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
					try {
						Member member = guild.retrieveMember(user).complete();
						name = member.getEffectiveName();
					}catch (Exception e) {
						name = user.getName();
					}
				}
				int level = LEVEL_CALCULATOR.getUserLevel(userId + "");
				long exp = LEVEL_CALCULATOR.getUserExp(userId + "");
				String newRow = count + ". " + name + " **Level" + level + "** " + exp + " exp";


				ldbString = ldbString + newRow + "\n";
				count ++;
			}
		}
		OUTPUT.command(ldbString);
	}

	
	private void leaderboardMessageDiscord(MixedOutput OUTPUT, HashMap<Integer, List<Long>> leaderboard) {
		EmbedBuilder loadingBuilder = new EmbedBuilder()
				.setTitle("Computing leaderboard")
				.addField("Computing the top " + rowsDisplayed + " of the server...", "This usually takes about 15 seconds...", false)
				.setThumbnail("https://mir-s3-cdn-cf.behance.net/project_modules/disp/c3c4d331234507.564a1d23db8f9.gif")
				.setColor(new Color(150, 0, 200));
		TextChannel channel = OUTPUT.getTextChannel();
		Message loading = channel.sendMessageEmbeds(loadingBuilder.build()).complete();
		
		EmbedBuilder ldbBuilder = new EmbedBuilder();
		int count = 1;
		SortedSet<Integer> sortedLdbSet = new TreeSet<>(leaderboard.keySet());
		ArrayList<Integer> sorted = new ArrayList<>();
		if(true) {
			int i = sortedLdbSet.size();
			for(int y = 0; y < i; y++) sorted.add(0);
			for(int key : sortedLdbSet) {
				i --;
				sorted.set(i, key);
			}
		}

		String ldbString = "";
		for(int key : sorted) {
			if(count > rowsDisplayed) break;
			for(Long userId : leaderboard.get(key)) {
				if(count > rowsDisplayed) break;
				String name = "";
				User user = Demi.jda.retrieveUserById(userId).complete();
				if(user == null) {
					name = userId + "";
				}else {
					Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
					try {
						Member member = guild.retrieveMember(user).complete();
						name = member.getEffectiveName();
					}catch (Exception e) {
						name = user.getName();
					}
				}
				int level = LEVEL_CALCULATOR.getUserLevel(userId + "");
				long exp = LEVEL_CALCULATOR.getUserExp(userId + "");
				String newRow = "#" + count + " | **" + name + "** | Level : " + level + " | Exp : " + exp + " XP";


				ldbString = ldbString + newRow + "\n";
				count ++;
			}
		}
		
		ldbBuilder.setDescription(ldbString);
		
		ldbBuilder.setAuthor("Leaderboard");
		ldbBuilder.setThumbnail(Demi.jda.getGuildById(Demi.i.getServerID()).getIconUrl());
		ldbBuilder.setColor(new Color(200, 0, 200));
		
		channel.sendMessageEmbeds(ldbBuilder.build()).queue(deleteOnFinish(loading));
	}

	static final Consumer<Message> deleteOnFinish(Message message){
		return new Consumer<Message>() {
			@Override
			public void accept(Message t) {
				message.delete().queue();
			}
		};
	}

	@Override
	public String getUsage() {
		return "Usage : leaderboard";
	}

}
