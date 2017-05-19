package com.tpg;


import java.time.Duration;
import java.time.LocalDateTime;

public class TimingMeasureTool {
	public static String measure(String className, String methodName, Class<?>[] classes, Object ...parameters) throws Exception {
		LocalDateTime start = LocalDateTime.now();
		Class.forName(className).getMethod(methodName, classes).invoke(Class.forName(className), parameters);
		LocalDateTime end = LocalDateTime.now();
		
		long duration = Duration.between(start, end).getSeconds();
		if (duration == 0) {
			return Duration.between(start, end).toMillis() + " milliseconds";
		} else {
			return duration + " seconds";
		}
	}
}
