package com.lindberg.models.generators;

import com.lindberg.models.components.IStock;
import com.lindberg.models.data.IProduct;
import com.lindberg.models.data.Product;
import com.lindberg.models.data.UnitType;
import com.lindberg.models.components.ILogger;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RandomStocking implements IStockingStrategy {
	private final ILogger logger;
	
	public RandomStocking(ILogger logger) {
		this.logger = logger;
	}
	
	@Override
	public void stock(IStock stock) {
		List<IProduct> items = Arrays.asList(
			new Product("Milk", BigDecimal.valueOf(35), 20, UnitType.COUNT),
			new Product("White bread", BigDecimal.valueOf(14), 40, UnitType.COUNT),
			new Product("Eggs 8-pack", BigDecimal.valueOf(60), 10, UnitType.COUNT),
			new Product("Juice", BigDecimal.valueOf(80), 30, UnitType.COUNT),
			new Product("Beer", BigDecimal.valueOf(120), 5, UnitType.COUNT),
			new Product("Chicken", BigDecimal.valueOf(200 / 1000), 400, UnitType.WEIGHT)
		);
		stock.add(items);
		log(stock);
	}
	
	private void log(IStock stock) {
		logger.log("Supermarket products have been formed:");
		for (IProduct product : stock.getAll()) {
			logger.format("%s price: %s count: %d",
				product.getName(), product.getPrice(), product.getUnits());
		}
	}
}
