package com.lindberg.models.generators;

import com.lindberg.models.data.ICustomer;
import com.lindberg.models.components.IStock;

public interface ICustomerActivityGenerator {
	ICustomer get(IStock stock);
}
