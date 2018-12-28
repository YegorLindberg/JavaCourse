package com.lindberg.models.components;

import java.time.LocalTime;

public class Time {
	private LocalTime currentTime = LocalTime.MIN;
	
	public LocalTime now() {
		return currentTime;
	}
	
	public void set(LocalTime time) {
		currentTime = time;
	}
	
	public void plusMinutes(long minutes) {
		currentTime = currentTime.plusMinutes(minutes);
	}
}
