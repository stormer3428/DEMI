package fr.stormer3428.demi;

public class DemiConsole {

	public static final void error(String message) {
		System.err.println("\033[38;5;196m"+"[📕Error] " + message+"\033[38;5;7m");
	}

	public static final void warning(String message) {
		System.out.println("\033[38;5;208m"+"[📙Warning] "+"\033[38;5;7m" + message);
	}

	public static final void ok(String message) {
		System.out.println("\033[38;5;118m"+"[📗Ok] " + message + "\033[38;5;7m");
	}

	public static final void action(String message) {
		System.out.println("\033[38;5;123m"+"[📘Action] "+"\033[38;5;7m" + message);
	}

	public static final void cancelled(String message) {
		System.out.println("\033[38;5;245m"+"[📓Cancelled] " + message + "\033[38;5;7m");
	}
	
	public static final void info(String message) {
		System.out.println("\033[38;5;226m"+"[Info] "+"\033[38;5;7m" + message);
	}
	
}
