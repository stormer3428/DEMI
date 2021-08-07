package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.CommandModule;
import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.DemiCommandReceiveEvent;
import fr.stormer3428.demi.MixedOutput;

public class FileEdit extends CommandModule{

	public FileEdit() {
		super("fileedit");

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
		if(action.equalsIgnoreCase("listFiles") || action.equalsIgnoreCase("lFiles") || action.equalsIgnoreCase("listF") || action.equalsIgnoreCase("lf")) {
			showAvailableFiles(OUTPUT);
			return;
		}
		if(action.equalsIgnoreCase("param") || action.equalsIgnoreCase("prm") || action.equalsIgnoreCase("p")) {
			if(args.isEmpty()) {
				showHelpParam(OUTPUT);
				return;
			}
			String paramType = args.remove(0).toLowerCase();
			if(paramType.equalsIgnoreCase("edit") || paramType.equalsIgnoreCase("edt") || paramType.equalsIgnoreCase("ed") || paramType.equalsIgnoreCase("e")) {

				String fileName = args.remove(0).toLowerCase().replace(".cfg", "");
				File foundFile = Demi.i.findConfigFileByName(fileName);
				if(foundFile != null) {
					if(args.isEmpty()) {
						showHelpParamEdit(OUTPUT);
						return;
					}
					//TODO check if param exists and edit it if there is another argument
				}
				OUTPUT.error("No file with such name");
				showHelpParamEdit(OUTPUT);
				return;
			}else if(paramType.equalsIgnoreCase("list")) {
				if(args.isEmpty()) {
					showHelpParamList(OUTPUT);
					return;
				}
				String fileName = args.remove(0).toLowerCase().replace(".cfg", "");

				File foundFile = Demi.i.findConfigFileByName(fileName);
				if(foundFile != null) {
					listParams(OUTPUT, foundFile);
					return;
				}
				OUTPUT.error("No file with such name");
				showHelpParamEdit(OUTPUT);
				return;
			}
			OUTPUT.error("Invalid Param subcommand");
			showHelpParam(OUTPUT);
			return;
		}
	}

	private void showHelpParamList(MixedOutput OUTPUT) {
		// TODO Auto-generated method stub
		OUTPUT.info("showHelp");
	}

	private void showHelpParamEdit(MixedOutput OUTPUT) {
		// TODO Auto-generated method stub
		OUTPUT.info("showHelp");
		
	}

	private void showHelpParam(MixedOutput OUTPUT) {
		// TODO Auto-generated method stub
		OUTPUT.info("showHelp");

	}

	private void listParams(MixedOutput OUTPUT, File foundFile) {
		// TODO Auto-generated method stub
		OUTPUT.info("showHelp");

	}

	private void showAvailableFiles(MixedOutput OUTPUT) {
		// TODO Auto-generated method stub
		OUTPUT.info("showHelp");

	}

	private void showHelp(MixedOutput OUTPUT) {
		// TODO Auto-generated method stub
		OUTPUT.info("showHelp");

	}


}
