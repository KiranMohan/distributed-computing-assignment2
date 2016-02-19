package org.kiranmohan.lamport.clock;

import org.kiranmohan.lamport.clock.message.IMessagePOA;

public class EventHandler extends IMessagePOA {

	public String message(String text, int clockValue) {
		if (text == processName) {
			// ignore local events
			return this.processName;
		}
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