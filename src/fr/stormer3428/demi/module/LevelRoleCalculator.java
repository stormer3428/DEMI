package fr.stormer3428.demi.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.Demi;
import fr.stormer3428.demi.Module;
import net.dv8tion.jda.api.entities.Member;

public class LevelRoleCalculator extends Module{

	public LevelRoleCalculator() {
		super(new File("level/levelRoleCalculator"));

		if(initialConfigIOCreation()) return;
		OUTPUT.warning("Disabling module to prevent errors");
		Demi.disableModule(this);	
	}

	@Override
	public List<String> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public String getName() {
		return "LevelRoleCalculator";
	}

	@Override
	public String getDescription() {
		return "A module that handle the computation needed to know what role should be given for what level";
	}

	@Override
	public void onDisable() {}

	public int retrieveLevelFromRoles(String UID) {
		Member member = Demi.jda.getGuildById(Demi.i.getServerID()).getMemberById(UID);
		if(member == null) {
			OUTPUT.warning("Attempted te retrieve level of non guild member, returning 0");
			return 0;
		}
		return retrieveLevelFromRoles(member);
	}

	public int retrieveLevelFromRoles(Member member) {
		// TODO return 0 on fail
		return 0;
	}

}
