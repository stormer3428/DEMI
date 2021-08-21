package fr.stormer3428.demi;

public class Key {
	
	private String name;
	private String defaultValue;
	
	public Key(String name, String defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public String name() {
		return this.name;
	}

	public String defaultValue() {
		return this.defaultValue;
	}
	
}