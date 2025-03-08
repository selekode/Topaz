package com.selekode.topaz.model;

public class StatsActivityPerDayOfWeek {
	private int journalMondayEntryCount;
	private int journalTuesdayEntryCount;
	private int journalWednesdayEntryCount;
	private int journalThursdayEntryCount;
	private int journalFridayEntryCount;
	private int journalSaturdayEntryCount;
	private int journalSundayEntryCount;

	private int revisionMondayEntryCount;
	private int revisionTuesdayEntryCount;
	private int revisionWednesdayEntryCount;
	private int revisionThursdayEntryCount;
	private int revisionFridayEntryCount;
	private int revisionSaturdayEntryCount;
	private int revisionSundayEntryCount;

	private String journalMostActiveDay;
	private String revisionMostActiveDay;
	
	public StatsActivityPerDayOfWeek(int journalMondayEntryCount, int journalTuesdayEntryCount,
			int journalWednesdayEntryCount, int journalThursdayEntryCount, int journalFridayEntryCount,
			int journalSaturdayEntryCount, int journalSundayEntryCount, int revisionMondayEntryCount,
			int revisionTuesdayEntryCount, int revisionWednesdayEntryCount, int revisionThursdayEntryCount,
			int revisionFridayEntryCount, int revisionSaturdayEntryCount, int revisionSundayEntryCount,
			String journalMostActiveDay, String revisionMostActiveDay) {
		super();
		this.journalMondayEntryCount = journalMondayEntryCount;
		this.journalTuesdayEntryCount = journalTuesdayEntryCount;
		this.journalWednesdayEntryCount = journalWednesdayEntryCount;
		this.journalThursdayEntryCount = journalThursdayEntryCount;
		this.journalFridayEntryCount = journalFridayEntryCount;
		this.journalSaturdayEntryCount = journalSaturdayEntryCount;
		this.journalSundayEntryCount = journalSundayEntryCount;
		this.revisionMondayEntryCount = revisionMondayEntryCount;
		this.revisionTuesdayEntryCount = revisionTuesdayEntryCount;
		this.revisionWednesdayEntryCount = revisionWednesdayEntryCount;
		this.revisionThursdayEntryCount = revisionThursdayEntryCount;
		this.revisionFridayEntryCount = revisionFridayEntryCount;
		this.revisionSaturdayEntryCount = revisionSaturdayEntryCount;
		this.revisionSundayEntryCount = revisionSundayEntryCount;
		this.journalMostActiveDay = journalMostActiveDay;
		this.revisionMostActiveDay = revisionMostActiveDay;
	}
	
	public int getJournalMondayEntryCount() {
		return journalMondayEntryCount;
	}
	public void setJournalMondayEntryCount(int journalMondayEntryCount) {
		this.journalMondayEntryCount = journalMondayEntryCount;
	}
	public int getJournalTuesdayEntryCount() {
		return journalTuesdayEntryCount;
	}
	public void setJournalTuesdayEntryCount(int journalTuesdayEntryCount) {
		this.journalTuesdayEntryCount = journalTuesdayEntryCount;
	}
	public int getJournalWednesdayEntryCount() {
		return journalWednesdayEntryCount;
	}
	public void setJournalWednesdayEntryCount(int journalWednesdayEntryCount) {
		this.journalWednesdayEntryCount = journalWednesdayEntryCount;
	}
	public int getJournalThursdayEntryCount() {
		return journalThursdayEntryCount;
	}
	public void setJournalThursdayEntryCount(int journalThursdayEntryCount) {
		this.journalThursdayEntryCount = journalThursdayEntryCount;
	}
	public int getJournalFridayEntryCount() {
		return journalFridayEntryCount;
	}
	public void setJournalFridayEntryCount(int journalFridayEntryCount) {
		this.journalFridayEntryCount = journalFridayEntryCount;
	}
	public int getJournalSaturdayEntryCount() {
		return journalSaturdayEntryCount;
	}
	public void setJournalSaturdayEntryCount(int journalSaturdayEntryCount) {
		this.journalSaturdayEntryCount = journalSaturdayEntryCount;
	}
	public int getJournalSundayEntryCount() {
		return journalSundayEntryCount;
	}
	public void setJournalSundayEntryCount(int journalSundayEntryCount) {
		this.journalSundayEntryCount = journalSundayEntryCount;
	}
	public int getRevisionMondayEntryCount() {
		return revisionMondayEntryCount;
	}
	public void setRevisionMondayEntryCount(int revisionMondayEntryCount) {
		this.revisionMondayEntryCount = revisionMondayEntryCount;
	}
	public int getRevisionTuesdayEntryCount() {
		return revisionTuesdayEntryCount;
	}
	public void setRevisionTuesdayEntryCount(int revisionTuesdayEntryCount) {
		this.revisionTuesdayEntryCount = revisionTuesdayEntryCount;
	}
	public int getRevisionWednesdayEntryCount() {
		return revisionWednesdayEntryCount;
	}
	public void setRevisionWednesdayEntryCount(int revisionWednesdayEntryCount) {
		this.revisionWednesdayEntryCount = revisionWednesdayEntryCount;
	}
	public int getRevisionThursdayEntryCount() {
		return revisionThursdayEntryCount;
	}
	public void setRevisionThursdayEntryCount(int revisionThursdayEntryCount) {
		this.revisionThursdayEntryCount = revisionThursdayEntryCount;
	}
	public int getRevisionFridayEntryCount() {
		return revisionFridayEntryCount;
	}
	public void setRevisionFridayEntryCount(int revisionFridayEntryCount) {
		this.revisionFridayEntryCount = revisionFridayEntryCount;
	}
	public int getRevisionSaturdayEntryCount() {
		return revisionSaturdayEntryCount;
	}
	public void setRevisionSaturdayEntryCount(int revisionSaturdayEntryCount) {
		this.revisionSaturdayEntryCount = revisionSaturdayEntryCount;
	}
	public int getRevisionSundayEntryCount() {
		return revisionSundayEntryCount;
	}
	public void setRevisionSundayEntryCount(int revisionSundayEntryCount) {
		this.revisionSundayEntryCount = revisionSundayEntryCount;
	}
	public String getJournalMostActiveDay() {
		return journalMostActiveDay;
	}
	public void setJournalMostActiveDay(String journalMostActiveDay) {
		this.journalMostActiveDay = journalMostActiveDay;
	}
	public String getRevisionMostActiveDay() {
		return revisionMostActiveDay;
	}
	public void setRevisionMostActiveDay(String revisionMostActiveDay) {
		this.revisionMostActiveDay = revisionMostActiveDay;
	}
	
	public String getDayWithHighestJournalCount() {
        int[] journalCounts = {
            journalMondayEntryCount, journalTuesdayEntryCount, journalWednesdayEntryCount,
            journalThursdayEntryCount, journalFridayEntryCount, journalSaturdayEntryCount, journalSundayEntryCount
        };

        String[] days = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        return getMaxDay(journalCounts, days);
    }

    public String getDayWithHighestRevisionCount() {
        int[] revisionCounts = {
            revisionMondayEntryCount, revisionTuesdayEntryCount, revisionWednesdayEntryCount,
            revisionThursdayEntryCount, revisionFridayEntryCount, revisionSaturdayEntryCount, revisionSundayEntryCount
        };

        String[] days = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        return getMaxDay(revisionCounts, days);
    }

    private String getMaxDay(int[] counts, String[] days) {
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return days[maxIndex]; // Returns the day with the highest count
    }

    // SETTERS TO CALCULATE MOST ACTIVE DAY
    public void setJournalEntryCounts(int mon, int tue, int wed, int thu, int fri, int sat, int sun) {
        this.journalMondayEntryCount = mon;
        this.journalTuesdayEntryCount = tue;
        this.journalWednesdayEntryCount = wed;
        this.journalThursdayEntryCount = thu;
        this.journalFridayEntryCount = fri;
        this.journalSaturdayEntryCount = sat;
        this.journalSundayEntryCount = sun;
    }
    // SETTERS TO CALCULATE MOST ACTIVE DAY
    public void setRevisionEntryCounts(int mon, int tue, int wed, int thu, int fri, int sat, int sun) {
        this.revisionMondayEntryCount = mon;
        this.revisionTuesdayEntryCount = tue;
        this.revisionWednesdayEntryCount = wed;
        this.revisionThursdayEntryCount = thu;
        this.revisionFridayEntryCount = fri;
        this.revisionSaturdayEntryCount = sat;
        this.revisionSundayEntryCount = sun;
    }
	
}
