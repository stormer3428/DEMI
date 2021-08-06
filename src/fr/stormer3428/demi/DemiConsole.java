package fr.stormer3428.demi;

public class DemiConsole {

	protected static final void error(String message) {
		System.err.println("\033[38;5;196m"+"[📕Error] " + message+"\033[38;5;7m");
	}

	protected static final void warning(String message) {
		System.out.println("\033[38;5;208m"+"[📙Warning] "+"\033[38;5;7m" + message);
	}

	protected static final void ok(String message) {
		System.out.println("\033[38;5;118m"+"[📗Ok] " + message + "\033[38;5;7m");
	}

	protected static final void action(String message) {
		System.out.println("\033[38;5;123m"+"[📘Action] "+"\033[38;5;7m" + message);
	}

	protected static final void cancelled(String message) {
		System.out.println("\033[38;5;245m"+"[📓Cancelled] " + message + "\033[38;5;7m");
	}
	
	protected static final void info(String message) {
		System.out.println("\033[38;5;226m"+"[Info] "+"\033[38;5;7m" + message);
	}
	
}