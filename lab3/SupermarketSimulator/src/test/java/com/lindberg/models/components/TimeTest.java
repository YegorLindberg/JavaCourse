package com.lindberg.models.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
	private Time time;
	
	@BeforeEach
	void start() {
		time = new Time();
	}
	
	@Test
	void timeIsSet() {
		time.set(LocalTime.of(6, 0));
		assertEquals(LocalTime.of(6, 0), time.now());
	}
	
	@Test
	void timeIsIncremented() {
		time.plusMinutes(20);
		assertEquals(LocalTime.of(0, 20), time.now());
	}
}