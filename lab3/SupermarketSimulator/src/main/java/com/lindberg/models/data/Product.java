package com.lindberg.models.data;

import java.math.BigDecimal;

public class Product implements IProduct {
	private String name;
	private BigDecimal price;
	private int units;
	private UnitType unitType;
	private ProductCategory category;
	private Discount discount = null;
	
	public Product(String name, BigDecimal price, int units, UnitType unitType) {
		this(name, price, units, unitType, ProductCategory.REGULAR);
	}
	
	public Product(String name, BigDecimal price, int units, UnitType unitType, ProductCategory category) {
		this.name = name;
		this.price = price;
		this.units = units;
		this.unitType = unitType;
		this.category = category;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) { this.name = name; }
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public int getUnits() {
		return units;
	}
	
	@Override
	public void setUnits(int units) {
		this.units = units;
	}
	
	@Override
	public UnitType getUnitType() {
		return unitType;
	}
	
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	
	@Override
	public ProductCategory getCategory() {
		return category;
	}
	
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	@Override
	public Discount getDiscount() {
		return discount;
	}
	
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	
	@Override
	public boolean hasDiscount() {
		return discount != null;
	}
}
