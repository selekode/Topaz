package com.selekode.topaz.model;

public class ActivityPerDayOfWeekDTO {
	private int mondayEntryCount;
	private int tuesdayEntryCount;
	private int wednesdayEntryCount;
	private int thursdayEntryCount;
	private int fridayEntryCount;
	private int saturdayEntryCount;
	private int sundayEntryCount;
	private String mostActiveDay;
	private int mostActiveDayN;

	public int getMondayEntryCount() {
		return mondayEntryCount;
	}

	public void setMondayEntryCount(int mondayEntryCount) {
		this.mondayEntryCount = mondayEntryCount;
	}

	public int getTuesdayEntryCount() {
		return tuesdayEntryCount;
	}

	public void setTuesdayEntryCount(int tuesdayEntryCount) {
		this.tuesdayEntryCount = tuesdayEntryCount;
	}

	public int getWednesdayEntryCount() {
		return wednesdayEntryCount;
	}

	public void setWednesdayEntryCount(int wednesdayEntryCount) {
		this.wednesdayEntryCount = wednesdayEntryCount;
	}

	public int getThursdayEntryCount() {
		return thursdayEntryCount;
	}

	public void setThursdayEntryCount(int thursdayEntryCount) {
		this.thursdayEntryCount = thursdayEntryCount;
	}

	public int getFridayEntryCount() {
		return fridayEntryCount;
	}

	public void setFridayEntryCount(int fridayEntryCount) {
		this.fridayEntryCount = fridayEntryCount;
	}

	public int getSaturdayEntryCount() {
		return saturdayEntryCount;
	}

	public void setSaturdayEntryCount(int saturdayEntryCount) {
		this.saturdayEntryCount = saturdayEntryCount;
	}

	public int getSundayEntryCount() {
		return sundayEntryCount;
	}

	public void setSundayEntryCount(int sundayEntryCount) {
		this.sundayEntryCount = sundayEntryCount;
	}

	public String getMostActiveDay() {
		return mostActiveDay;
	}

	public void setMostActiveDay(String mostActiveDay) {
		this.mostActiveDay = mostActiveDay;
	}

	public int getMostActiveDayN() {
		return mostActiveDayN;
	}

	public void setMostActiveDayN(int mostActiveDayN) {
		this.mostActiveDayN = mostActiveDayN;
	}

	public ActivityPerDayOfWeekDTO(int mondayEntryCount, int tuesdayEntryCount, int wednesdayEntryCount,
			int thursdayEntryCount, int fridayEntryCount, int saturdayEntryCount, int sundayEntryCount,
			String mostActiveDay, int mostActiveDayN) {
		super();
		this.mondayEntryCount = mondayEntryCount;
		this.tuesdayEntryCount = tuesdayEntryCount;
		this.wednesdayEntryCount = wednesdayEntryCount;
		this.thursdayEntryCount = thursdayEntryCount;
		this.fridayEntryCount = fridayEntryCount;
		this.saturdayEntryCount = saturdayEntryCount;
		this.sundayEntryCount = sundayEntryCount;
		this.mostActiveDay = mostActiveDay;
		this.mostActiveDayN = mostActiveDayN;
	}

	// SETTERS TO CALCULATE MOST ACTIVE DAY
	public void setEntryCounts(int mon, int tue, int wed, int thu, int fri, int sat, int sun) {
		this.mondayEntryCount = mon;
		this.tuesdayEntryCount = tue;
		this.wednesdayEntryCount = wed;
		this.thursdayEntryCount = thu;
		this.fridayEntryCount = fri;
		this.saturdayEntryCount = sat;
		this.sundayEntryCount = sun;
	}

	@Override
	public String toString() {
		return "StatsActivityPerDayOfWeek [mondayEntryCount=" + mondayEntryCount + ", tuesdayEntryCount="
				+ tuesdayEntryCount + ", wednesdayEntryCount=" + wednesdayEntryCount + ", thursdayEntryCount="
				+ thursdayEntryCount + ", fridayEntryCount=" + fridayEntryCount + ", saturdayEntryCount="
				+ saturdayEntryCount + ", sundayEntryCount=" + sundayEntryCount + ", mostActiveDay=" + mostActiveDay
				+ ", mostActiveDayN=" + mostActiveDayN + "]";
	}

	
}
