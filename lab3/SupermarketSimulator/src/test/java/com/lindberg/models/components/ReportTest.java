package com.lindberg.models.components;

import com.lindberg.models.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
	private Report report;
	
	@BeforeEach
	private void start() {
		report = new Report();
	}
	
	
	@Test
	void itemIsAdded() {
		report.add(new Quantity<>(generateProduct(), 2, LocalTime.MIN));
		assertEquals(1, report.getSold().size());
	}
	
	@Test
	void itemsAreAdded() {
		report.add(Arrays.asList(
			new Quantity<>(generateProduct(), 2, LocalTime.MIN),
			new Quantity<>(generateProduct(), 2, LocalTime.MIN)));
		assertEquals(2, report.getSold().size());
	}
	
	@Test
	void duplicateItemsAreCollapsed() {
		IProduct product = generateProduct();
		report.add(new Quantity<>(product, 2, LocalTime.MIN));
		report.add(new Quantity<>(product, 2, LocalTime.MIN));
		assertEquals(1, report.getSold().size());
		assertEquals(4, report.getSold().get(0).getCount());
	}
	
	@Test
	void reportLogIsFormatted() {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ILogger logger = new Logger(new PrintStream(byteStream), new Time());
		report.add(new Quantity<>(generateProduct(), 2, LocalTime.MIN));
		report.log(logger);
		String output = new String(byteStream.toByteArray(), StandardCharsets.UTF_8).trim();
		assertEquals("[00:00] Sold 2 units of Product", output);
	}
	
	IProduct generateProduct() {
		return new Product("Product", BigDecimal.ONE, 10, UnitType.COUNT);
	}
}