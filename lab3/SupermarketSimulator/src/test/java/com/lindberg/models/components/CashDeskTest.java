package com.lindberg.models.components;

import com.lindberg.EmptyLogger;
import com.lindberg.models.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CashDeskTest {
	private Time time;
	private ILogger logger = new EmptyLogger();
	private CashDesk cashDesk;
	
	@BeforeEach
	void start() {
		time = new Time();
	}
	
	@Test
	void customerWithEmptyBasketIsError() {
		CashDesk desk = new CashDesk(logger, time, 0);
		Customer customer = new Customer(new Basket(), CustomerCategory.ADULT, PaymentMethod.CARD);
		assertThrows(IllegalArgumentException.class, () -> desk.handle(customer));
	}
	
	@Test
	void totalIsCalculated() {
		CashDesk desk = new CashDesk(logger, time, 0);
		IBasket basket = new Basket();
		basket.add(new Quantity<>(new Product("Product", BigDecimal.ONE, 10, UnitType.COUNT), 2, LocalTime.MIN));
		Customer customer = new Customer(basket, CustomerCategory.ADULT, PaymentMethod.CARD);
		Bill bill = desk.handle(customer);
		assertEquals(BigDecimal.valueOf(2), bill.getTotal());
		assertEquals(PaymentMethod.CARD, bill.getPaymentMethod());
		assertEquals(basket.getItems(), bill.getProducts());
	}
	
	@Test
	void discountAppliedForSeniors() {
		CashDesk desk = new CashDesk(logger, time, 0);
		IBasket basket = new Basket();
		Product product = new Product("Product", BigDecimal.ONE, 10, UnitType.COUNT);
		product.setDiscount(new Discount(BigDecimal.valueOf(0.5)));
		basket.add(new Quantity<>(product, 2, LocalTime.MIN));
		Customer customer = new Customer(basket, CustomerCategory.SENIOR, PaymentMethod.CARD);
		Bill bill = desk.handle(customer);
		assertEquals(BigDecimal.ONE.subtract(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(2)), bill.getTotal());
		
		customer = new Customer(basket, CustomerCategory.ADULT, PaymentMethod.CARD);
		bill = desk.handle(customer);
		assertEquals(BigDecimal.ONE.multiply(BigDecimal.valueOf(2)), bill.getTotal());
	}
	
	@Test
	void delayIsApplied() {
		CashDesk desk = new CashDesk(logger, time, 2);
		IBasket basket = new Basket();
		basket.add(new Quantity<>(new Product("Product", BigDecimal.ONE, 10, UnitType.COUNT), 2, LocalTime.MIN));
		Customer customer = new Customer(basket, CustomerCategory.ADULT, PaymentMethod.CARD);
		Bill bill = desk.handle(customer);
		assertEquals(LocalTime.of(0, 2), time.now());
	}
}