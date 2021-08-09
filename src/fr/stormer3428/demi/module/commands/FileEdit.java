package fr.stormer3428.demi.module.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.IO;
import fr.stormer3428.demi.MixedOutput;
import net.dv8tion.jda.api.EmbedBuilder;

public class FileEdit extends CommandModule{

	public FileEdit() {
		super("FileEdit");

		aliases.add("fedit");
		aliases.add("filed");
		aliases.add("fed");

		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);

	}

	@Override
	public String getDescription() {
		return "A module that can handle commands coming from either a discord channel or the console to directly edit config files";
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {}

	@Override
	protected void runCommand(DemiCommandReceiveEvent event) {
		ArrayList<String> args = event.getArgs();
		MixedOutput OUTPUT = event.getOutput();
		if(args.isEmpty()) {
			showHelp(OUTPUT);
			return;
		}
		String action = args.remove(0);
		if(action.equalsIgnoreCase("help") || action.equalsIgnoreCase("?")) {
			showHelp(OUTPUT);
			return;
		}
		if(action.equalsIgnoreCase("listFiles") || action.equalsIgnoreCase("lFiles") || action.equalsIgnoreCase("listF") || action.equalsIgnoreCase("lf") || action.equalsIgnoreCase("l") || action.equalsIgnoreCase("list")) {
			showAvailableFiles(OUTPUT);
			return;
		}
		if(action.equalsIgnoreCase("parameter") || action.equalsIgnoreCase("param") || action.equalsIgnoreCase("prm") || action.equalsIgnoreCase("p")) {
			if(args.isEmpty()) {
				showHelpParam(OUTPUT);
				return;
			}
			String paramType = args.remove(0).toLowerCase();
			if(paramType.equalsIgnoreCase("edit") || paramType.equalsIgnoreCase("edt") || paramType.equalsIgnoreCase("ed") || paramType.equalsIgnoreCase("e")) {
				if(args.isEmpty()) {
					showHelpParamEdit(OUTPUT);
					return;
				}
				String fileName = args.remove(0).toLowerCase().replace(".cfg", "");
				File foundFile = Demi.i.findConfigFileByName(fileName);
				if(foundFile == null) {
					OUTPUT.error("No file with such name");
					showHelpParamEdit(OUTPUT);
					return;
				}
				if(args.isEmpty()) {
					OUTPUT.error("You need to specify a parameter to edit");
					showHelpParamEdit(OUTPUT);
					return;
				}
				String paramName = args.remove(0);
				IO io = IO.findIOByFile(foundFile);
				if(io == null) io = new IO(foundFile, new ArrayList<>(), PRINT_STACK_TRACE, new ArrayList<>());
				List<String> keys = io.getKeys();
				String foundKey = "";
				for(String key : keys) if(key.equalsIgnoreCase(paramName)) {
					foundKey = key;
					break;
				}
				if(foundKey.isEmpty()) {
					OUTPUT.error("Error, no parameter with such name : " + paramName);
					listParams(OUTPUT, foundFile);
					return;
				}
				if(args.isEmpty()) {
					OUTPUT.error("You need to specifiy a new value for that parameter");
					showHelpParamEdit(OUTPUT);
					return;
				}
				String newValue = args.remove(0);
				while(!args.isEmpty()) newValue = newValue + " " + args.remove(0);
				setParam(OUTPUT, io, foundKey, newValue);
				return;
			}else if(paramType.equalsIgnoreCase("listParameters") || paramType.equalsIgnoreCase("listParams") || paramType.equalsIgnoreCase("list") || paramType.equalsIgnoreCase("lp") || paramType.equalsIgnoreCase("l")) {
				if(args.isEmpty()) {
					showHelpParamList(OUTPUT);
					return;
				}
				String fileName = args.remove(0).toLowerCase().replace(".cfg", "");

				File foundFile = Demi.i.findConfigFileByName(fileName);
				if(foundFile == null) {
					OUTPUT.error("No file with such name");
					showHelpParamEdit(OUTPUT);
					return;
				}
				listParams(OUTPUT, foundFile);
				return;
			}else if(paramType.equalsIgnoreCase("get") || paramType.equalsIgnoreCase("g")) {
				if(args.isEmpty()) {
					showHelpParamGet(OUTPUT);
					return;
				}
				String fileName = args.remove(0).toLowerCase().replace(".cfg", "");
				File foundFile = Demi.i.findConfigFileByName(fileName);
				if(foundFile == null) {
					OUTPUT.error("No file with such name");
					showHelpParamGet(OUTPUT);
					return;
				}
				if(args.isEmpty()) {
					OUTPUT.error("You need to specifiy a parameter to view the value of");
					showHelpParamGet(OUTPUT);
					return;
				}
				String paramName = args.remove(0);
				IO io = IO.findIOByFile(foundFile);
				if(io == null) io = new IO(foundFile, new ArrayList<>(), PRINT_STACK_TRACE, new ArrayList<>());
				List<String> keys = io.getKeys();
				String foundKey = "";
				for(String key : keys) if(key.equalsIgnoreCase(paramName)) {
					foundKey = key;
					break;
				}
				if(foundKey.isEmpty()) {
					OUTPUT.error("Error, no parameter with such name : " + paramName);
					listParams(OUTPUT, foundFile);
					return;
				}
				if(foundKey.equalsIgnoreCase("discordBotToken")) {
					OUTPUT.error("Sorry! i am not able to show you this...");
					return;
				}
				showParam(OUTPUT, io, foundKey);
				return;
			}
			OUTPUT.error("Invalid Param subcommand");
			showHelpParam(OUTPUT);
			return;
		}
	}

	private void setParam(MixedOutput OUTPUT, IO io, String foundKey, String newValue) {
		String oldValue = io.get(foundKey);
		io.setParameter(foundKey, newValue);
		OUTPUT.command("Successfully updated the value of " + foundKey + " from "+oldValue+" to "+io.get(foundKey));
	}

	private void showParam(MixedOutput OUTPUT, IO io, String foundKey) {
		OUTPUT.command("Value of parameter " + foundKey + " : ");
		OUTPUT.command(io.get(foundKey));
	}

	private void showHelpParamGet(MixedOutput OUTPUT) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Help for command " + getName() + " parameter get :");
		embedReplacement.add("Usage : " + getName() + " parameter get <filename> <param name>");
		embedReplacement.add("A command used to get the value of a parameter from a config file");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("Command help");
		builder.addField("Usage", getName() + " parameter get <filename> <param name>", false);
		builder.setDescription("A command used to get the value of a parameter from a config file");

		OUTPUT.embed(builder.build(), embedReplacement);
	}

	private void showHelpParamList(MixedOutput OUTPUT) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Help for command " + getName() + " parameter list :");
		embedReplacement.add("Usage : " + getName() + " parameter list <filename>");
		embedReplacement.add("A command used to list all parameters of a config file");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("Command help");
		builder.addField("Usage", getName() + " parameter list <filename>", false);
		builder.setDescription("A command used to list all parameters of a config file");

		OUTPUT.embed(builder.build(), embedReplacement);
	}

	private void showHelpParamEdit(MixedOutput OUTPUT) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Help for command " + getName() + " parameter list :");
		embedReplacement.add("Usage : " + getName() + " parameter <filename> <param name> <new value>");
		embedReplacement.add("A command used to edit parameters of a config file");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("Command help");
		builder.addField("Usage", getName() + " parameter edit <filename> <param name> <new value>", false);
		builder.setDescription("A command used to edit parameters of a config file");

		OUTPUT.embed(builder.build(), embedReplacement);
	}

	private void showHelpParam(MixedOutput OUTPUT) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Help for command " + getName() + " parameter list :");
		embedReplacement.add("Usage : " + getName() + " parameter <list;get;edit> <filename>");
		embedReplacement.add("A command used to tinker with parameters of a config file");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("Command help");
		builder.addField("Usage", getName() + " parameter <list;get;edit> <filename>", false);
		builder.setDescription("A command used to tinker with parameters of a config file");

		OUTPUT.embed(builder.build(), embedReplacement);
	}

	private void listParams(MixedOutput OUTPUT, File file) {
		IO io = IO.findIOByFile(file);
		if(io == null) io = new IO(file, new ArrayList<>(), PRINT_STACK_TRACE, new ArrayList<>());
		List<String> keys = io.getKeys();


		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("List of available parameters : ");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("List Params");

		for(String key : keys) {
			builder.addField(key, "", false);
			embedReplacement.add(key);
		}

		OUTPUT.embed(builder.build(), embedReplacement);

	}

	private void showAvailableFiles(MixedOutput OUTPUT) {
		List<File> files = Demi.i.getAllConfigFiles();

		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("List of available config files : ");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("List Files");

		for(File file : files) {
			builder.addField(file.getName(), "", false);
			embedReplacement.add(file.getName());
		}

		OUTPUT.embed(builder.build(), embedReplacement);
	}

	private void showHelp(MixedOutput OUTPUT) {
		List<String> embedReplacement = new ArrayList<>();
		embedReplacement.add("Help for command " + getName() + " :");
		embedReplacement.add("Usage : " + getName() + " <help;listfiles;parameter>");
		embedReplacement.add("A command used to directly edit config files");

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(getName());
		builder.setTitle("Command help");
		builder.addField("Usage", getName() + " <help;listfiles;parameter>", false);
		builder.setDescription("A command used to directly edit config files");

		OUTPUT.embed(builder.build(), embedReplacement);
	}

	@Override
	public String getUsage() {
		return "Usage : " + getName() + " <help;listfiles;parameter>";
	}


}
