package com.lindberg.spreadsheet;

import java.util.HashMap;
import java.util.regex.*;

public class Spreadsheet {
	private final Pattern pathPattern = Pattern.compile("([a-z])(\\d+)");
	private final HashMap<Integer, HashMap<Integer, Cell>> cells = new HashMap<>();
	
	public Cell at(int row, int column) {
		if(!cells.containsKey(row) || !cells.get(row).containsKey(column)) {
			return null;
		}
		return cells.get(row).get(column);
	}
	
	public Cell at(String path) {
		Vector2 position = pathToPosition(path);
		return at(position.X, position.Y);
	}
	
	public void set(int row, int column, Cell cell) {
		HashMap<Integer, Cell> columnValues = null;
		if(cells.containsKey(row)) {
			columnValues = cells.get(row);
		}
		else {
			columnValues = new HashMap<>();
			cells.put(row, columnValues);
		}
		if(columnValues.containsKey(column)) {
			columnValues.replace(column, cell);
		}
		else {
			columnValues.put(column, cell);
		}
	}
	
	public void set(int row, int column, String expression) {
		Cell cell = new Cell();
		cell.setExpression(expression);
		set(row, column, cell);
	}
	
	public void set(String path, String expression) {
		Cell cell = new Cell();
		cell.setExpression(expression);
		Vector2 position = pathToPosition(path);
		set(position.X, position.Y, cell);
	}
	
	public boolean isPath(String path) {
		return pathPattern.matcher(path).matches();
	}
	
	private Vector2 pathToPosition(String path) {
		Matcher matcher = pathPattern.matcher(path);
		if(!matcher.matches()) {
			throw new IllegalArgumentException("path");
		}
		char letter = Character.toLowerCase(matcher.group(1).charAt(0));
		int column = Integer.parseInt(matcher.group(2));
		return new Vector2(letter - 'a', column - 1);
	}
}
