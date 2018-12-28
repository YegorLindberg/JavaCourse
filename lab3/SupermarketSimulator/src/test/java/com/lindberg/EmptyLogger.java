package com.lindberg;

import com.lindberg.models.components.ILogger;

public class EmptyLogger implements ILogger {
	@Override
	public void log(String message) {
	
	}
	
	@Override
	public void format(String pattern, Object... args) {
	
	}
}
