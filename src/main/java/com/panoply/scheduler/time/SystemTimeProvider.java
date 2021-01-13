package com.panoply.scheduler.time;

public class SystemTimeProvider implements TimeProvider {

	@Override
	public long currentTime() {
		return System.currentTimeMillis();
	}

}
