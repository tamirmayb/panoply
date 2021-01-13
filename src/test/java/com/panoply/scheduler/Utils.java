package com.panoply.scheduler;

import com.panoply.scheduler.schedule.Schedule;
import com.panoply.scheduler.schedule.Schedules;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Utils {

	public static final Runnable TASK_THAT_SLEEPS_FOR_200MS = () -> {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			throw new RuntimeException("Should not be interrupted", e);
		}
	};

	public static class SingleJob implements Runnable {
		AtomicInteger countExecuted = new AtomicInteger(0);

		@Override
		public void run() {
			countExecuted.incrementAndGet();
			synchronized (this) {
				notifyAll();
			}
		}
	}

	public static void waitForJob(Object lockOn, Supplier<Boolean> condition, long maxWait) {
		long currentTime = System.currentTimeMillis();
		long waitUntil = currentTime + maxWait;
		while(!condition.get() && waitUntil > currentTime) {
			synchronized (lockOn) {
				try {
					lockOn.wait(5);
				} catch (InterruptedException e) {
				}
			}
			currentTime = System.currentTimeMillis();
		}
	}

	// a do nothing runnable
	private static Runnable doNothing = () -> {};
	public static Runnable doNothing() {
		return doNothing;
	}

	public static Schedule oneMillsDelay() {
		return Schedules.fixedDelaySchedule(Duration.ofMillis(1));
	}
	public static Schedule fiveMillsDelay() {
		return Schedules.fixedDelaySchedule(Duration.ofMillis(5));
	}

}
