package fr.stormer3428.demi.module.commands;

import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;

public class ListChannels extends CommandModule{

	public ListChannels() {
		super("listchannels");

		this.aliases.add("lc");

		if(initialConfigIOCreation()) return;
		this.OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);
	}

	@Override
	public String getDescription() {
		return "A command that allows you to list all of the id's of a specific category";
	}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		ArrayList<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();

		if(args.size() == 0) {
			OUTPUT.command("i need a category id");
			return;
		}

		String idString = args.remove(0);
		long id;
		try {
			id = Long.parseLong(idString);
		}catch (NumberFormatException e) {
			OUTPUT.command("for the last time" + (event.getMessageReceivedEvent() == null ? "," : " " + event.getMessageReceivedEvent().getMember().getEffectiveName() + ",") + "\"" + idString + "\" isn't even a number");
			return;
		}

		Guild guild = Demi.jda.getGuildById(Demi.i.getServerID());
		Category category = guild.getCategoryById(id);
		if(category == null) {
			OUTPUT.command("there isnt even any category with that id");
			return;
		}

		List<GuildChannel> channels = category.getChannels();
		for(ChannelType type : ChannelType.values()) {
			boolean hasThisType = false;
			for(GuildChannel channel : channels) if(channel.getType() == type) {
				hasThisType = true;
				break;
			}
			if(!hasThisType) continue;
			String msg = "Channels of type : " + type.toString();
			for(GuildChannel channel : channels) {
				if(msg.length() > 1000) {
					OUTPUT.command(msg);
					msg = "";
				}
				if(channel.getType() != type) continue;
				msg = msg + "\n" + channel.getId();
			}
			OUTPUT.command(msg);
		}

	}

	@Override
	public String getUsage() {
		return "Usage : ListChannels <category ID>";
	}

}
