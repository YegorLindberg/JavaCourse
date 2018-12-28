package com.lindberg.service;


import com.lindberg.models.generators.ICustomerActivityGenerator;
import com.lindberg.models.generators.IStockingStrategy;
import com.lindberg.models.components.*;
import com.lindberg.models.data.Bill;
import com.lindberg.models.data.ICustomer;
import com.lindberg.models.data.IProduct;

import java.time.LocalTime;
import java.util.List;

public class Supermarket implements ISupermarket {
	private final ILogger logger;
	private final IStock stock;
	private final IStockingStrategy stockingStrategy;
	private final ICustomerActivityGenerator activityGenerator;
	private final ICashDesk cashDesk;
	private final LocalTime openingTime;
	private final LocalTime closingTime;
	private final Time time;
	private final long step;
	
	public Supermarket(ILogger logger, IStock stock, IStockingStrategy stockingStrategy,
					   ICustomerActivityGenerator activityGenerator, ICashDesk cashDesk,
					   Time time, LocalTime openingTime, LocalTime closingTime, long step) {
		if(!openingTime.isBefore(closingTime)) {
			throw new IllegalArgumentException("openingTime doesn't precede closingTime");
		}
		this.logger = logger;
		this.stock = stock;
		this.stockingStrategy = stockingStrategy;
		this.activityGenerator = activityGenerator;
		this.cashDesk = cashDesk;
		this.time = time;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.step = step;
		time.set(openingTime);
	}
	
	@Override
	public void run(IReport report) {
		time.set(openingTime);
		stockingStrategy.stock(stock);
		logger.log("Supermarket is opened");
		
		while(time.now().isBefore(closingTime)) {
			List<IProduct> stocked = stock.getStocked();
			if(stocked.isEmpty()) {
				break;
			}
			ICustomer customer = activityGenerator.get(stock);
			Bill bill = cashDesk.handle(customer);
			report.add(bill.getProducts());
			if(time.now().plusMinutes(step).isBefore(closingTime)) {
				time.plusMinutes(step);
			}
		}
		
		logger.log("Supermarket is closed");
		report.log(logger);
	}
}
