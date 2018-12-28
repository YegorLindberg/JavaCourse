package com.lindberg.models.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
	
	@Test
	void customerInfoIsSaved() {
		IBasket basket = new Basket();
		Customer customer = new Customer(basket, CustomerCategory.ADULT, PaymentMethod.CARD, "somebody");
		assertEquals(basket, customer.getBasket());
		assertEquals(CustomerCategory.ADULT, customer.getCategory());
		assertEquals(PaymentMethod.CARD, customer.getPaymentMethod());
		assertEquals("somebody", customer.getName());
	}
}