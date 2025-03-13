package com.selekode.topaz.model;

public class DataPoint {
	private String date; // X-Axis (e.g., "2025-03-06")
	private double value; // Y-Axis (e.g., emotional rating)

	public DataPoint(String date, double value) {
		this.date = date;
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public double getValue() {
		return value;
	}
}
