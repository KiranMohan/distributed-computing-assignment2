package org.kiranmohan.lamport.clock;

import org.kiranmohan.lamport.clock.message.IMessagePOA;

public class EventHandler extends IMessagePOA {

	public String message(String text) {
		System.out.println("message received : " + text);
		return "OK";
	}
	
}