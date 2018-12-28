package com.lindberg.spreadsheet;

public class Cell {
	private String expression = "";
	private Object value;
	private ValueType valueType = ValueType.STRING;
	
	public String getExpression() {
		return expression;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public ValueType getValueType() {
		return valueType;
	}
	
	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
}
