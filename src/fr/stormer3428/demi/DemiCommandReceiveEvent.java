package fr.stormer3428.demi;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DemiCommandReceiveEvent {

	private MessageReceivedEvent messageReceivedEvent;
	private String command;
	private ArrayList<String> args = new ArrayList<>();
	private MixedOutput output;
	
	public DemiCommandReceiveEvent(MessageReceivedEvent event, String command, ArrayList<String> args, MixedOutput output) {
		this.messageReceivedEvent = event;
		this.command = command;
		this.args = args;
		this.output = output;
	}

	public MessageReceivedEvent getMessageReceivedEvent() {
		return messageReceivedEvent;
	}

	public String getCommand() {
		return command;
	}

	public ArrayList<String> getArgs() {
		return args;
	}

	public MixedOutput getOutput() {
		return output;
	}
	
}
