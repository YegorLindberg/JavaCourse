package com.lindberg.models.data;

public enum PaymentMethod {
	CASH("cash"),
	CARD("card"),
	BONUS("bonuses");
	
	private final String name;
	
	private PaymentMethod(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
