package com.selekode.topaz.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.RevisionEntry;
import com.selekode.topaz.repository.RevisionRepository;
import com.selekode.topaz.utils.DatesHelper;

@Service
public class RevisionService {
	
	public static List<RevisionEntry> selectAllRevisionEntries() {
		List<RevisionEntry> revisionEntries = RevisionRepository.selectAllRevisionEntries();
		
		return revisionEntries;
	}
	
	public static RevisionEntry selectRevisionEntry(Long id) {
		RevisionEntry revisionEntry = RevisionRepository.selectRevisionEntry(id);
		
		return revisionEntry;
	}
	
	public static void insertRevisionEntry(RevisionEntry revisionEntry) {
		Long unixTime = DatesHelper.generateUnixDate();
		RevisionRepository.insertRevisionEntry(revisionEntry, unixTime);
	}
	
	public static void updateRevisionEntry(Long id, RevisionEntry revisionEntry) {
		RevisionRepository.updateRevisionEntry(id, revisionEntry);		
	}
	
	public static void deleteRevisionEntry(Long id) {
		RevisionRepository.deleteRevisionEntry(id);
	}
}
