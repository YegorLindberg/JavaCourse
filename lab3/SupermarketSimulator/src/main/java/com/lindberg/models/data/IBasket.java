package com.lindberg.models.data;

import java.util.List;

public interface IBasket {
	List<Quantity<IProduct>> getItems();
	void add(Quantity<IProduct> quantity);
}
