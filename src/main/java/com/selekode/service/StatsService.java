package com.selekode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.repository.StatsRepository;
import com.selekode.topaz.model.StatsEntryCount;

@Service
public class StatsService {
	public static StatsEntryCount getStatsEntryCount() {
		StatsEntryCount statsEntryCount = null;
		// Get date of every JournalEntry
		// Calculate how many JournalEntries all time
		int journalEntryCount = StatsRepository.getJournalEntryCount();

		// Calculate how many JournalEntries in each week
		// Calculate how many JournalEntries in each month

		// Get date of every RevisionEntries
		// Calculate how many RevisionEntries all time
		int revisionEntryCount = StatsRepository.getRevisionEntryCount();

		// Calculate how many RevisionEntries in each week
		// Calculate how many RevisionEntries in each month

		// Calculate total number of entries
		int allEntryCount = journalEntryCount + revisionEntryCount;
		
		statsEntryCount = new StatsEntryCount(allEntryCount,journalEntryCount,revisionEntryCount);
		
		return statsEntryCount;

	}
}