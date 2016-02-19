package org.kiranmohan.lamport.clock;

public final class LamportClock {
	private int value = 0;

	public synchronized int getValue() {
		return value;
	}
	
	public synchronized void incrementValue() {
		++value;
	}
	
	public synchronized void compareAndUpdateValue(int value) {
		this.value = Math.max(this.value, value)+1;
	}

}
