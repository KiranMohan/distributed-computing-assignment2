package org.kiranmohan.lamport.clock;

import org.kiranmohan.lamport.clock.message.IMessagePOA;

public class EventHandler extends IMessagePOA {

	public String message(String text) {
		System.out.println(processName + " received message from " + text);
		return "OK";
	}
	
	public EventHandler(String processName) {
		super();
		this.processName = processName;
	}
	
	private final String processName;
	
}