package com.lindberg;

import com.lindberg.models.components.*;
import com.lindberg.service.*;
import java.time.LocalTime;

public class Main {
	public static void main(String[] args) {
		Time time = new Time();
		ILogger logger = new Logger(System.out, time);
		ISupermarket market = new SupermarketFactory()
			.withLogger(logger)
			.withTime(time)
			.withOpening(LocalTime.of(8, 0))
			.withClosing(LocalTime.of(20, 0))
			.withCustomerDelay(2)
			.withStep(30)
			.create();
		market.run(new Report());
	}
}
