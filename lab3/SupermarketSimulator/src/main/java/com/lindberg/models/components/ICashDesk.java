package com.lindberg.models.components;

import com.lindberg.models.data.ICustomer;
import com.lindberg.models.data.Bill;

public interface ICashDesk {
	Bill handle(ICustomer customer);
}
