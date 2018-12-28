package com.lindberg.models.data;

import java.math.BigDecimal;

public class Discount {
	private final BigDecimal percentage;
	
	public Discount(BigDecimal percentage) {
		if((percentage.compareTo(BigDecimal.ZERO) < 0) || (percentage.compareTo(BigDecimal.ONE) > 0)) {
			throw new IllegalArgumentException("percentage not in [0, 1] range");
		}
		this.percentage = percentage;
	}
	
	public BigDecimal get() {
		return percentage;
	}
}
