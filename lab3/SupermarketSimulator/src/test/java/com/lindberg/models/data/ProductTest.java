package com.lindberg.models.data;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
	
	@Test
	void productPropertiesAreSaved() {
		Product product = new Product("Me", BigDecimal.ONE, 10, UnitType.WEIGHT);
		assertEquals("Me", product.getName());
		assertEquals(BigDecimal.ONE, product.getPrice());
		assertEquals(10, product.getUnits());
		assertEquals(UnitType.WEIGHT, product.getUnitType());
		assertNull(product.getDiscount());
		assertFalse(product.hasDiscount());
		assertEquals(ProductCategory.REGULAR, product.getCategory());
	}
	
	@Test
	void productPropertiesAreChanged() {
		Product product = new Product("Me", BigDecimal.ONE, 10, UnitType.WEIGHT);
		product.setName("You");
		product.setPrice(BigDecimal.ZERO);
		product.setUnits(2);
		product.setUnitType(UnitType.COUNT);
		product.setDiscount(new Discount(BigDecimal.valueOf(0.5)));
		product.setCategory(ProductCategory.ADULT);
		
		assertEquals("You", product.getName());
		assertEquals(BigDecimal.ZERO, product.getPrice());
		assertEquals(2, product.getUnits());
		assertEquals(UnitType.COUNT, product.getUnitType());
		assertEquals(BigDecimal.valueOf(0.5), product.getDiscount().get());
		assertTrue(product.hasDiscount());
		assertEquals(ProductCategory.ADULT, product.getCategory());
	}
}