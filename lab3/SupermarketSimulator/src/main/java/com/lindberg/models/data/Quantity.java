package com.lindberg.models.data;

import java.time.LocalTime;

public class Quantity<T> {
	private final T item;
	private int count;
	private final LocalTime creationTime;
	
	public Quantity(T item, int count, LocalTime creationTime) {
		this.item = item;
		this.count = count;
		this.creationTime = creationTime;
	}
	
	public T getItem() {
		return item;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public LocalTime getCreationTime() {
		return creationTime;
	}
}
