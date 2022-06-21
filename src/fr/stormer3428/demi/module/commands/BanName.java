package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class BanName extends CommandModule{

	private HashMap<Long, List<Long>> query = new HashMap<>();
	
	public BanName() {
		super("banname");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);

	}

	@Override
	public String getDescription() {
		return "A module that can ban multiple people based on the name prompted";
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		long senderId = -1;
		if(event.getMessageReceivedEvent() != null) {
			senderId = event.getMessageReceivedEvent().getMember().getIdLong();
		}
		List<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();
		if(args.size() == 0) {
			OUTPUT.command("I need a command type... like -query or -confirm");
			return;
		}
		String type = args.remove(0);
		if(type.equalsIgnoreCase("-query") || type.equalsIgnoreCase("-q")) {
			if(args.size() == 0) {
				OUTPUT.command("i need a name to search members with");
				return;
			}
			String match = "";
			for(String s : args) match = " " + s;
			match = match.replaceFirst(" ", "");
			
			Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
			OUTPUT.command("Loading members for process...");
			guild.loadMembers().onSuccess(successfullLoadConsumer(OUTPUT, match, senderId)).onError(failedLoadConsumer(OUTPUT));
			return;
		}

		if(type.equalsIgnoreCase("-confirm") || type.equalsIgnoreCase("-c")) {
			banList(OUTPUT, senderId);
			return;
		}
		OUTPUT.command("there's only two valid argument, -query and -confirm, and " + type + " isn't one of them");
	}

	private void banList(MixedOutput OUTPUT, long senderId) {
		if(!query.containsKey(senderId)) {
			OUTPUT.command("ERROR : you do not have any stored queries");
			return;
		}
		List<Long> list = query.remove(senderId);
		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		for(Long id : list) {
			Member member = guild.retrieveMemberById(id).complete();
			OUTPUT.command("Banned " + member.getEffectiveName());
			member.ban(7).complete();
		}
	}

	private Consumer<? super Throwable> failedLoadConsumer(MixedOutput OUTPUT) {
		return new Consumer<Throwable>() {@Override public void accept(Throwable threw) {
			OUTPUT.command("An error was thrown during loading, likely a timeout");
		}};
	}

	@Override
	public String getUsage() {
		return "Usage : " + getName() + " <-query; -confirm>";
	}

	private Consumer<? super List<Member>> successfullLoadConsumer(MixedOutput OUTPUT, String match, Long senderId) {return new Consumer<List<Member>>() {@Override public void accept(List<Member> members) {
		OUTPUT.command("Finished loading members, processing...");
		OUTPUT.command("Checking for names matching regex :```\n```"+match);
		List<Member> matchs = new ArrayList<>();
		List<Long> matchsId = new ArrayList<>();
		for(Member member : members) {try {
			if(!member.getEffectiveName().matches(match) && !member.getUser().getName().matches(match)) continue;
			matchs.add(member);
		}catch (Exception e) {}}
		OUTPUT.command("Found " + matchs.size() + " matches.");
		String message = "";
		for(Member member : matchs) {
			matchsId.add(member.getIdLong());
			if(message.length() + member.getEffectiveName().length() + member.getUser().getName().length() + 2 > 1000) {
				OUTPUT.command(message);
				message = "";
			}
			message = message + member.getEffectiveName() + "(" + member.getUser().getAsTag() +")\n";
		}
		if(!message.isEmpty()) {
			OUTPUT.command(message);
		}
		query.put(senderId, matchsId);
	}};}
}







