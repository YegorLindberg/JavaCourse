package com.lindberg.models.data;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
	
	@Test
	void itemsAreAdded() {
		Basket basket = new Basket();
		Product product = new Product("Some", BigDecimal.ONE, 5, UnitType.COUNT);
		Quantity<IProduct> quantity = new Quantity<>(product, 2, LocalTime.MIN);
		basket.add(quantity);
		List<Quantity<IProduct>> products = basket.getItems();
		assertEquals(1, products.size());
		assertEquals(quantity, products.get(0));
	}
}