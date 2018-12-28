package com.lindberg.models.data;

public enum UnitType {
	COUNT("units"),
	WEIGHT("grams");
	
	private final String name;
	
	private UnitType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return  name;
	}
}
