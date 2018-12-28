package com.lindberg.models.data;

import java.math.BigDecimal;

public interface IProduct {
	String getName();
	BigDecimal getPrice();
	int getUnits();
	void setUnits(int units);
	UnitType getUnitType();
	ProductCategory getCategory();
	Discount getDiscount();
	boolean hasDiscount();
}
