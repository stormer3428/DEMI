package fr.stormer3428.demi;

public interface Module {
	
	public String getName();
	public String getDescription();
	public boolean enabled();

	public void onDisable();
	public void onEnable();
}
