package com.lindberg.models.components;

import com.lindberg.models.data.IProduct;
import com.lindberg.models.data.Quantity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Report implements IReport {
	private final List<Quantity<IProduct>> soldProducts = new ArrayList<>();
	
	@Override
	public void add(List<Quantity<IProduct>> products) {
		for(Quantity<IProduct> quantity : products) {
			add(quantity);
		}
	}
	
	@Override
	public void add(Quantity<IProduct> quantity) {
		for(int i = 0; i < soldProducts.size(); ++i) {
			Quantity<IProduct> current = soldProducts.get(i);
			if(current.getItem() == quantity.getItem()) {
				current.setCount(current.getCount() + quantity.getCount());
				return;
			}
		}
		soldProducts.add(quantity);
	}
	
	@Override
	public List<Quantity<IProduct>> getSold() {
		return Collections.unmodifiableList(soldProducts);
	}
	
	@Override
	public void log(ILogger logger) {
		for(Quantity<IProduct> quantity : soldProducts) {
			IProduct product = quantity.getItem();
			logger.format("Sold %d %s of %s", quantity.getCount(), product.getUnitType().getName(), product.getName());
		}
	}
}
