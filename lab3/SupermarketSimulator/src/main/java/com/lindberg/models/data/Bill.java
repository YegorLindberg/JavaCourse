package com.lindberg.models.data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Bill {
	private final List<Quantity<IProduct>> products;
	private final BigDecimal totalPrice;
	private final PaymentMethod paymentMethod;
	
	public Bill(List<Quantity<IProduct>> products, BigDecimal totalPrice, PaymentMethod paymentMethod) {
		this.products = Collections.unmodifiableList(products);
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
	}
	
	public List<Quantity<IProduct>> getProducts() {
		return products;
	}
	
	public BigDecimal getTotal() {
		return totalPrice;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
}
