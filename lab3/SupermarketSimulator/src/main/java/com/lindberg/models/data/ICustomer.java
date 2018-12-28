package com.lindberg.models.data;

public interface ICustomer {
	IBasket getBasket();
	CustomerCategory getCategory();
	PaymentMethod getPaymentMethod();
	String getName();
}
