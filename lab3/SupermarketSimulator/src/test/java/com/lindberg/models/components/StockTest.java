package com.lindberg.models.components;

import com.lindberg.models.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
	private Stock stock;
	
	@BeforeEach
	void start() {
		stock = new Stock();
	}
	
	@Test
	void singleProductIsAdded() {
		IProduct product = generateProduct();
		stock.add(product);
		assertEquals(1, stock.getAll().size());
		assertEquals(product, stock.getAll().get(0));
	}
	
	@Test
	void multipleItemsAreAdded() {
		stock.add(Arrays.asList(generateProduct(), generateProduct()));
		assertEquals(2, stock.getAll().size());
	}
	
	@Test
	void stockedProductsAreSelected() {
		stock.add(generateProduct());
		assertEquals(1, stock.getStocked().size());
		stock.getStocked().get(0).setUnits(0);
		assertEquals(0, stock.getStocked().size());
		assertEquals(1, stock.getAll().size());
	}
	
	IProduct generateProduct() {
		return new Product("Product", BigDecimal.ONE, 10, UnitType.COUNT);
	}
}