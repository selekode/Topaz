package com.selekode.topaz.model;

public class UnixDateRange {
	private long startDate;
	private long endDate;
	
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	
	public UnixDateRange(long dateStart, long dateEnd) {
		super();
		this.startDate = dateStart;
		this.endDate = dateEnd;
	}
}
