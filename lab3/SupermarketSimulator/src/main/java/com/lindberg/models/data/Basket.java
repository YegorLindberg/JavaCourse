package com.lindberg.models.data;

import java.util.ArrayList;
import java.util.List;

public class Basket implements IBasket {
	private List<Quantity<IProduct>> items = new ArrayList<>();
	
	@Override
	public List<Quantity<IProduct>> getItems() {
		return items;
	}
	
	@Override
	public void add(Quantity<IProduct> quantity) {
		items.add(quantity);
	}
}
