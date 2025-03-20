package com.selekode.topaz.model;

public class UnixDates {
	private long dateStart;
	private long dateEnd;
	
	public long getDateStart() {
		return dateStart;
	}
	public void setDateStart(long dateStart) {
		this.dateStart = dateStart;
	}
	public long getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(long dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public UnixDates(long dateStart, long dateEnd) {
		super();
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
}
