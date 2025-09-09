package com.selekode.topaz.model;

public class EntryCountDTO {
	private int totalEntries;
	private int totalEntriesJournal;
	private int totalEntriesRevision;
	private int totalEntriesInnerWork;

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

	public int getTotalEntriesInnerWork() {
		return totalEntriesInnerWork;
	}

	public void setTotalEntriesInnerWork(int totalEntriesInnerWork) {
		this.totalEntriesInnerWork = totalEntriesInnerWork;
	}

	public EntryCountDTO(int totalEntries, int totalEntriesJournal, int totalEntriesRevision,
			int totalEntriesInnerWork) {
		super();
		this.totalEntries = totalEntries;
		this.totalEntriesJournal = totalEntriesJournal;
		this.totalEntriesRevision = totalEntriesRevision;
		this.totalEntriesInnerWork = totalEntriesInnerWork;
	}

	@Override
	public String toString() {
		return "StatsEntryCount [totalEntries=" + totalEntries + ", totalEntriesJournal=" + totalEntriesJournal
				+ ", totalEntriesRevision=" + totalEntriesRevision + ", totalEntriesInnerWork=" + totalEntriesInnerWork
				+ "]";
	}
	
	

}
