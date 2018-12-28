package com.lindberg.service;

import com.lindberg.EmptyLogger;
import com.lindberg.models.components.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class SupermarketTest {
	ILogger logger = new EmptyLogger();
	IStock stock;
	ICashDesk cashDesk;
	Time time;
	
	@BeforeEach
	void start() {
		time = new Time();
		stock = new Stock();
		cashDesk = new CashDesk(logger, time, 0);
	}
	
	@Test
	void marketClosesWithinDelay() {
		getDefault().create().run(new Report());
		LocalTime end = LocalTime.of(18, 0).plusMinutes(20);
		assertTrue(time.now().isBefore(end) || time.equals(end));
	}
	
	SupermarketFactory getDefault() {
		return new SupermarketFactory()
			.withTime(time)
			.withLogger(logger)
			.withOpening(LocalTime.of(8, 0))
			.withClosing(LocalTime.of(18, 0))
			.withStep(20)
			.withCustomerDelay(0);
	}
}