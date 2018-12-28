package com.lindberg.models.components;

import com.lindberg.models.data.IProduct;
import java.util.Collection;
import java.util.List;

public interface IStock {
	void add(IProduct product);
	void add(Collection<IProduct> range);
	List<IProduct> getAll();
	List<IProduct> getStocked();
}
