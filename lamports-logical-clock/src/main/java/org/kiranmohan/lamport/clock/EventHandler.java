package org.kiranmohan.lamport.clock;

import org.kiranmohan.lamport.clock.message.IMessagePOA;

public class EventHandler extends IMessagePOA {

	/**
	 * process the received message
	 */
	public String message(String text, int clockValue) {

		// ignore local events
		if (text == processName) {
			return this.processName;
		}

		// process message received from external process
		synchronized (myClock) {
			int currentClockValue = myClock.compareAndUpdateValue(clockValue);
			System.out.println(processName + " : " + currentClockValue + " : receive");
		}
		return this.processName;
	}

	public EventHandler(String processName, LamportClock clock) {
		super();
		this.processName = processName;
		this.myClock = clock;
	}

	private final String processName;
	private final LamportClock myClock;

}