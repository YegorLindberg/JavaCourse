package com.lindberg.models.components;

import com.lindberg.models.data.IProduct;
import com.lindberg.models.data.Quantity;
import java.util.List;

public interface IReport {
	void add(List<Quantity<IProduct>> products);
	void add(Quantity<IProduct> quantity);
	List<Quantity<IProduct>> getSold();
	void log(ILogger logger);
}
