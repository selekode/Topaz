package com.selekode.topaz.model;

public class DashboardData {
	public int journalStreak;
	public int revisionStreak;
	public int longestJournalStreak;
	public int longestRevisionStreak;
	public boolean journalToday;
	public boolean revisionToday;

	public String strJournalStreak;
	public String strRevisionStreak;
	public String strLongestJournalStreak;
	public String strLongestRevisionStreak;
	public String strJournalToday;
	public String strRevisionToday;

	public int getJournalStreak() {
		return journalStreak;
	}

	public void setJournalStreak(int journalStreak) {
		this.journalStreak = journalStreak;
	}

	public int getRevisionStreak() {
		return revisionStreak;
	}

	public void setRevisionStreak(int revisionStreak) {
		this.revisionStreak = revisionStreak;
	}

	public int getLongestJournalStreak() {
		return longestJournalStreak;
	}

	public void setLongestJournalStreak(int longestJournalStreak) {
		this.longestJournalStreak = longestJournalStreak;
	}

	public int getLongestRevisionStreak() {
		return longestRevisionStreak;
	}

	public void setLongestRevisionStreak(int longestRevisionStreak) {
		this.longestRevisionStreak = longestRevisionStreak;
	}

	public boolean isJournalToday() {
		return journalToday;
	}

	public void setJournalToday(boolean journalToday) {
		this.journalToday = journalToday;
	}

	public boolean isRevisionToday() {
		return revisionToday;
	}

	public void setRevisionToday(boolean revisionToday) {
		this.revisionToday = revisionToday;
	}

	public String getStrJournalStreak() {
		return strJournalStreak;
	}

	public void setStrJournalStreak(String strJournalStreak) {
		this.strJournalStreak = strJournalStreak;
	}

	public String getStrRevisionStreak() {
		return strRevisionStreak;
	}

	public void setStrRevisionStreak(String strRevisionStreak) {
		this.strRevisionStreak = strRevisionStreak;
	}

	public String getStrLongestJournalStreak() {
		return strLongestJournalStreak;
	}

	public void setStrLongestJournalStreak(String strLongestJournalStreak) {
		this.strLongestJournalStreak = strLongestJournalStreak;
	}

	public String getStrLongestRevisionStreak() {
		return strLongestRevisionStreak;
	}

	public void setStrLongestRevisionStreak(String strLongestRevisionStreak) {
		this.strLongestRevisionStreak = strLongestRevisionStreak;
	}

	public String getStrJournalToday() {
		return strJournalToday;
	}

	public void setStrJournalToday(String strJournalToday) {
		this.strJournalToday = strJournalToday;
	}

	public String getStrRevisionToday() {
		return strRevisionToday;
	}

	public void setStrRevisionToday(String strRevisionToday) {
		this.strRevisionToday = strRevisionToday;
	}

	public DashboardData(int journalStreak, int revisionStreak, int longestJournalStreak, int longestRevisionStreak,
			boolean journalToday, boolean revisionToday, String strJournalStreak, String strRevisionStreak,
			String strLongestJournalStreak, String strLongestRevisionStreak, String strJournalToday,
			String strRevisionToday) {
		super();
		this.journalStreak = journalStreak;
		this.revisionStreak = revisionStreak;
		this.longestJournalStreak = longestJournalStreak;
		this.longestRevisionStreak = longestRevisionStreak;
		this.journalToday = journalToday;
		this.revisionToday = revisionToday;
		this.strJournalStreak = strJournalStreak;
		this.strRevisionStreak = strRevisionStreak;
		this.strLongestJournalStreak = strLongestJournalStreak;
		this.strLongestRevisionStreak = strLongestRevisionStreak;
		this.strJournalToday = strJournalToday;
		this.strRevisionToday = strRevisionToday;
	}

}
