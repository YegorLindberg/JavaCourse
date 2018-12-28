package com.lindberg.spreadsheet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpreadsheetTest {
	private Spreadsheet sheet;
	
	@BeforeEach
	void createEmpty() {
		sheet = new Spreadsheet();
	}
	
	@Test
	void canSetCellExpression() {
		sheet.set(0, 0, "hello");
		assertEquals("hello", sheet.at(0, 0).getExpression());
	}
	
	@Test
	void canSetCell() {
		Cell cell = new Cell();
		cell.setExpression("hello");
		sheet.set(0, 0, cell);
		assertEquals("hello", sheet.at(0, 0).getExpression());
	}
	
	@Test
	void canSetWithPath() {
		sheet.set("a1", "hello");
		sheet.set("z8", "darkness");
		sheet.set("k4", "friend");
		assertAll("path",
			() -> assertEquals("hello", sheet.at("a1").getExpression()),
			() -> assertEquals("darkness", sheet.at("z8").getExpression()),
			() -> assertEquals("friend", sheet.at("k4").getExpression()));
	}
}