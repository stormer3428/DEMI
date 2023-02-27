package fr.stormer3428.demi;

public class Key {
	
	private String name;
	private String defaultValue;
	private String defaultComment = "";
	
	public Key(String name, String defaultValue, String defaultComment) {
		this(name, defaultValue);
		this.defaultComment = defaultComment;
	}
	
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

	public String defaultComment() {
		return defaultComment;
	}
	
}