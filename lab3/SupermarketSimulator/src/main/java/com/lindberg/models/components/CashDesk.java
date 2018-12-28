package com.lindberg.models.components;

import com.lindberg.models.data.*;
import java.math.BigDecimal;
import java.util.List;

public class CashDesk implements ICashDesk {
	private final ILogger logger;
	private final Time time;
	private final long delay;
	
	public CashDesk(ILogger logger, Time time, long delay) {
		this.logger = logger;
		this.time = time;
		this.delay = delay;
	}
	
	public Bill handle(ICustomer customer) {
		time.plusMinutes(delay);
		List<Quantity<IProduct>> items = customer.getBasket().getItems();
		if(items.isEmpty()) {
			throw new IllegalArgumentException("customer has an empty basket");
		}
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(Quantity<IProduct> quantity : items) {
			totalPrice = totalPrice.add(calculatePrice(customer.getCategory(), quantity));
		}
		Bill bill = new Bill(items, totalPrice, customer.getPaymentMethod());
		log(customer, bill);
		return bill;
	}
	
	private BigDecimal calculatePrice(CustomerCategory customerCategory, Quantity<IProduct> quantity) {
		IProduct product = quantity.getItem();
		BigDecimal price = product.getPrice();
		if((customerCategory == CustomerCategory.SENIOR) && product.hasDiscount()) {
			price = price.subtract(price.multiply(product.getDiscount().get()));
		}
		return price.multiply(BigDecimal.valueOf(quantity.getCount()));
	}
	
	private  void log(ICustomer customer, Bill bill) {
		logger.format("Customer %s at the cash desk, amount to pay: %s",
			customer.getName(), bill.getTotal());
		logger.format("Customer paid %s by %s",
			bill.getTotal(), bill.getPaymentMethod().getName());
	}
}
