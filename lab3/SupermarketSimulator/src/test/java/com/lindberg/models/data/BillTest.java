package com.lindberg.models.data;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {
	
	@Test
	void billInfoIsSaved() {
		List<Quantity<IProduct>> products = Arrays.asList(new Quantity<>(new Product("Some", BigDecimal.ONE, 5, UnitType.COUNT), 2, LocalTime.MIN));
		Bill bill = new Bill(products, BigDecimal.ONE, PaymentMethod.CARD);
		assertEquals(products, bill.getProducts());
		assertEquals(BigDecimal.ONE, bill.getTotal());
		assertEquals(PaymentMethod.CARD, bill.getPaymentMethod());
	}
}