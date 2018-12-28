package com.lindberg.models.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
	private Time time;
	private ByteArrayOutputStream byteStream;
	private Logger logger;
	
	@BeforeEach
	private void start() {
		time = new Time();
		byteStream = new ByteArrayOutputStream();
		logger = new Logger(new PrintStream(byteStream), time);
	}
	
	
	@Test
	void logPrintsMessage() {
		logger.log("hello");
		assertEquals("[00:00] hello", getLog());
	}
	
	@Test
	void formatAppliesPatternCorrectly() {
		logger.format("hello %s", "man");
		assertEquals("[00:00] hello man", getLog());
	}
	
	String getLog() {
		return new String(byteStream.toByteArray(), StandardCharsets.UTF_8).trim();
	}
}