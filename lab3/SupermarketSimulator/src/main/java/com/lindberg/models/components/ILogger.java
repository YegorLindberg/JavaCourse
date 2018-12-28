package com.lindberg.models.components;

public interface ILogger {
	void log(String message);
	void format(String pattern, Object... args);
}
