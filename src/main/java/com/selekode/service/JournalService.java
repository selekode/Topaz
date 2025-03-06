package com.selekode.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.repository.JournalRepository;
import com.selekode.topaz.model.JournalEntry;

@Service
public class JournalService {
	
	public static List<JournalEntry> selectAllJournalEntries() {
		List<JournalEntry> journalEntries = JournalRepository.selectAllJournalEntries();
		
		return journalEntries;
	}
	
	public static JournalEntry selectJournalEntry(Long id) {
		JournalEntry journalEntry = JournalRepository.selectJournalEntry(id);
		
		return journalEntry;
	}
	
	public static void insertJournalEntry(JournalEntry journalEntry) {
		Long unixTime = generateUnixDate();
		JournalRepository.insertJournalEntry(journalEntry, unixTime);
	}
	
	public static void updateJournalEntry(Long id, JournalEntry journalEntry) {
		JournalRepository.updateJournalEntry(id, journalEntry);		
	}
	
	public static void deleteJournalEntry(Long id) {
    	JournalRepository.deleteJournalEntry(id);
	}

	public static long generateUnixDate() {
		Instant now = Instant.now();
		long unixTime = now.getEpochSecond();

		return unixTime;
	}	

}
