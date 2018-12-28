package com.lindberg.models.components;

import java.io.PrintStream;

public class Logger implements ILogger {
	private final PrintStream stream;
	private final Time time;
	
	public Logger(PrintStream stream, Time time) {
		this.stream = stream;
		this.time = time;
	}
	
	@Override
	public void log(String message) {
		stream.println(formatTime() + message);
	}
	
	@Override
	public void format(String pattern, Object... args) {
		stream.print(formatTime());
		stream.printf(pattern, args);
		stream.println();
	}
	
	private String formatTime() {
		return "[" + time.now() + "] ";
	}
}
