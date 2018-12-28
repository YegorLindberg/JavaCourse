package com.lindberg.service;

import com.lindberg.models.generators.CustomerActivityGenerator;
import com.lindberg.models.generators.RandomStocking;
import com.lindberg.models.components.CashDesk;
import com.lindberg.models.components.Stock;
import com.lindberg.models.components.Time;
import com.lindberg.models.components.ILogger;

import java.time.LocalTime;

public class SupermarketFactory {
	private ILogger logger;
	private LocalTime opening;
	private LocalTime closing;
	private Time time;
	private long delay;
	private long step;
	
	public SupermarketFactory withTime(Time time) {
		this.time = time;
		return  this;
	}
	
	public SupermarketFactory withLogger(ILogger logger) {
		this.logger = logger;
		return this;
	}
	
	public SupermarketFactory withOpening(LocalTime time) {
		opening = time;
		return this;
	}
	
	public SupermarketFactory withClosing(LocalTime time) {
		closing = time;
		return this;
	}
	
	public SupermarketFactory withCustomerDelay(long delay) {
		this.delay = delay;
		return this;
	}
	
	public SupermarketFactory withStep(long step) {
		this.step = step;
		return  this;
	}
	
	public ISupermarket create() {
		if((logger == null) || (opening == null) || (closing == null) || (time == null)) {
			throw new IllegalArgumentException("Not all factory parameters set");
		}
		return new Supermarket(logger, new Stock(), new RandomStocking(logger),
			new CustomerActivityGenerator(logger, time, 2, 3, 100, delay),
			new CashDesk(logger, time, delay), time, opening, closing, step);
	}
}
