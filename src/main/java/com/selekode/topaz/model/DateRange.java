package com.selekode.topaz.model;

public class DateRange {
	private String startDate;
	private String endDate;

	public DateRange(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "StatsDateRange [startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
