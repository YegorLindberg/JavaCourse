package com.lindberg.models.data;

public class Customer implements ICustomer {
	private final CustomerCategory category;
	private final IBasket basket;
	private final PaymentMethod paymentMethod;
	private final String name;
	
	public Customer(IBasket basket, CustomerCategory category, PaymentMethod paymentMethod) {
		this(basket, category, paymentMethod, "");
	}
	
	public Customer(IBasket basket, CustomerCategory category, PaymentMethod paymentMethod, String name) {
		this.basket = basket;
		this.category = category;
		this.paymentMethod = paymentMethod;
		this.name = name;
	}
	
	@Override
	public IBasket getBasket() {
		return basket;
	}
	
	@Override
	public CustomerCategory getCategory() {
		return category;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
}
