package org.kiranmohan.lamport.clock;

import org.kiranmohan.lamport.clock.message.IMessagePOA;

public class EventHandler extends IMessagePOA {

	public String message(String text) {
		System.out.println(processName + " received message from " + text);
		return this.processName;
	}
	
	public EventHandler(String processName, LamportClock clock) {
		super();
		this.processName = processName;
		this.clock = clock;
	}
	
	private final String processName;
	private final LamportClock clock;
	
}