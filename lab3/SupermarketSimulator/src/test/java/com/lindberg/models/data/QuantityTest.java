package com.lindberg.models.data;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
	@Test
	void quantityPropertiesAreSet() {
		Quantity<Integer> quantity = new Quantity<>(2, 5, LocalTime.of(4, 0));
		assertEquals(2, (int)quantity.getItem());
		assertEquals(5, quantity.getCount());
		assertEquals(LocalTime.of(4, 0), quantity.getCreationTime());
	}
	
	@Test
	void countIsChanged() {
		Quantity<Integer> quantity = new Quantity<>(2, 5, LocalTime.of(4, 0));
		quantity.setCount(2);
		assertEquals(2, quantity.getCount());
	}
}