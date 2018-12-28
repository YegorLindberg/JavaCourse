package com.lindberg.models.components;

import com.lindberg.models.data.IProduct;
import java.util.*;
import java.util.stream.Collectors;

public class Stock implements IStock {
	private List<IProduct> products = new ArrayList<>();
	
	@Override
	public void add(IProduct product) {
		products.add(product);
	}
	
	@Override
	public void add(Collection<IProduct> range) {
		for(IProduct product : range) {
			products.add(product);
		}
	}
	
	@Override
	public List<IProduct> getAll() {
		return Collections.unmodifiableList(products);
	}
	
	@Override
	public List<IProduct> getStocked() {
		return products.stream()
			.filter(product -> product.getUnits() > 0)
			.collect(Collectors.toList());
	}
}
