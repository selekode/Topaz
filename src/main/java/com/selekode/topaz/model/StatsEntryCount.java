package com.selekode.topaz.model;

public class StatsEntryCount {
	private int totalEntries;
	private int totalEntriesJournal;
	private int totalEntriesRevision;
	
	public StatsEntryCount(int totalEntries, int totalEntriesJournal, int totalEntriesRevision) {
		super();
		this.totalEntries = totalEntries;
		this.totalEntriesJournal = totalEntriesJournal;
		this.totalEntriesRevision = totalEntriesRevision;
	}
	
	public int getTotalEntries() {
		return totalEntries;
	}
	public void setTotalEntries(int totalEntries) {
		this.totalEntries = totalEntries;
	}
	public int getTotalEntriesJournal() {
		return totalEntriesJournal;
	}
	public void setTotalEntriesJournal(int totalEntriesJournal) {
		this.totalEntriesJournal = totalEntriesJournal;
	}
	public int getTotalEntriesRevision() {
		return totalEntriesRevision;
	}
	public void setTotalEntriesRevision(int totalEntriesRevision) {
		this.totalEntriesRevision = totalEntriesRevision;
	}
	
	
}
