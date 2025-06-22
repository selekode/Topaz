package com.selekode.topaz.model;

public class DashboardData {
	public int currentStreak;
	public int longestStreak;
	public boolean isWrittenToday;
	
	public String strCurrentStreak;
	public String strLongestStreak;
	public String strIsWrittenToday;
	
	public int getCurrentStreak() {
		return currentStreak;
	}
	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}
	public int getLongestStreak() {
		return longestStreak;
	}
	public void setLongestStreak(int longestStreak) {
		this.longestStreak = longestStreak;
	}
	public boolean isWrittenToday() {
		return isWrittenToday;
	}
	public void setWrittenToday(boolean isWrittenToday) {
		this.isWrittenToday = isWrittenToday;
	}
	public String getStrCurrentStreak() {
		return strCurrentStreak;
	}
	public void setStrCurrentStreak(String strCurrentStreak) {
		this.strCurrentStreak = strCurrentStreak;
	}
	public String getStrLongestStreak() {
		return strLongestStreak;
	}
	public void setStrLongestStreak(String strLongestStreak) {
		this.strLongestStreak = strLongestStreak;
	}
	public String getStrIsWrittenToday() {
		return strIsWrittenToday;
	}
	public void setStrIsWrittenToday(String strIsWrittenToday) {
		this.strIsWrittenToday = strIsWrittenToday;
	}
	
	public DashboardData(int currentStreak, int longestStreak, boolean isWrittenToday, String strCurrentStreak,
			String strLongestStreak, String strIsWrittenToday) {
		super();
		this.currentStreak = currentStreak;
		this.longestStreak = longestStreak;
		this.isWrittenToday = isWrittenToday;
		this.strCurrentStreak = strCurrentStreak;
		this.strLongestStreak = strLongestStreak;
		this.strIsWrittenToday = strIsWrittenToday;
	}
	@Override
	public String toString() {
		return "DashboardData [currentStreak=" + currentStreak + ", longestStreak=" + longestStreak
				+ ", isWrittenToday=" + isWrittenToday + ", strCurrentStreak=" + strCurrentStreak
				+ ", strLongestStreak=" + strLongestStreak + ", strIsWrittenToday=" + strIsWrittenToday + "]";
	}
	
	

	
}
