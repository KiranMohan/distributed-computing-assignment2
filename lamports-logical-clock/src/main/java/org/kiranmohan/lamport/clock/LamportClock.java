package org.kiranmohan.lamport.clock;

public final class LamportClock {
	private int value = 0;

	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int incrementValue() {
		++value;
		return value;
	}
	
	public synchronized int compareAndUpdateValue(int value) {
		this.value = Math.max(this.value, value)+1;
		return value;
	}

}
