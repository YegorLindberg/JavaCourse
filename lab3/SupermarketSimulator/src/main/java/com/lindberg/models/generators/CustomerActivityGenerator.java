package com.lindberg.models.generators;

import com.lindberg.models.components.ILogger;
import com.lindberg.models.components.IStock;
import com.lindberg.models.components.Time;
import com.lindberg.models.data.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomerActivityGenerator implements ICustomerActivityGenerator {
	private int nextId = 1;
	private final ILogger logger;
	private final Time time;
	private final Random random = new Random();
	private final int maxProductCount;
	private final int maxQuantity;
	private  final int maxWeight;
	private final  long delay;
	
	public CustomerActivityGenerator(ILogger logger, Time time, int maxProductCount, int maxQuantity, int maxWeight, long delay) {
		this.logger = logger;
		this.time = time;
		this.maxProductCount = maxProductCount;
		this.maxQuantity = maxQuantity;
		this.maxWeight = maxWeight;
		this.delay = delay;
	}
	
	@Override
	public ICustomer get(IStock stock) {
		List<IProduct> stockedProducts = stock.getStocked();
		if(stockedProducts.isEmpty()) {
			throw new IllegalArgumentException("stockedProducts is empty");
		}
		
		Customer customer = new Customer(new Basket(), getRandomCategory(),
			getRandomPaymentMethod(), "customer#" + nextId++);
		logger.log("New customer " + customer.getName() + " arrived");
		List<Integer> range = IntStream
			.range(0, Math.min(stockedProducts.size(), maxProductCount))
			.boxed().collect(Collectors.toList());
		if(customer.getCategory() == CustomerCategory.CHILD) {
			range.removeIf(index -> stockedProducts.get(index).getCategory() == ProductCategory.ADULT);
		}
		int productCount = range.size() == 1 ? 1 : random.nextInt(range.size()) + 1;
		for(int i = 0; i < productCount; ++i) {
			time.plusMinutes(delay);
			int productIndex = random.nextInt(range.size());
			IProduct product = stockedProducts.get(range.get(productIndex));
			range.remove(productIndex);
			int count = random.nextInt(Math.min(product.getUnits(), product.getUnitType() == UnitType.COUNT ? maxQuantity : maxWeight)) + 1;
			product.setUnits(product.getUnits() - count);
			customer.getBasket().add(new Quantity<>(product, count, time.now()));
		}
		for(Quantity<IProduct> quantity : customer.getBasket().getItems()) {
			IProduct product = quantity.getItem();
			logger.format("Customer %s picked up %d %s of %s",
				customer.getName(), quantity.getCount(), product.getUnitType().getName(), product.getName());
		}
		return customer;
	}
	
	private CustomerCategory getRandomCategory() {
		CustomerCategory[] values = CustomerCategory.values();
		return values[random.nextInt(values.length)];
	}
	
	private PaymentMethod getRandomPaymentMethod() {
		PaymentMethod[] values = PaymentMethod.values();
		return values[random.nextInt((values.length))];
	}
}
