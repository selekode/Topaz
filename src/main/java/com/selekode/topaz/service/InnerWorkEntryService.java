package com.selekode.topaz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.InnerWorkEntry;
import com.selekode.topaz.repository.InnerWorkEntryRepository;
import com.selekode.topaz.utils.DatesHelper;


@Service
public class InnerWorkEntryService {

	public static List<InnerWorkEntry> selectAllInnerWorkEntries() {
		List<InnerWorkEntry> journalWorkEntries = InnerWorkEntryRepository.selectAllInnerWorkEntries();
		return journalWorkEntries;
	}
	
	public static InnerWorkEntry selectInnerWorkEntry(int id) {
		InnerWorkEntry innerWorkEntry = InnerWorkEntryRepository.selectInnerWorkEntry(id);
		return innerWorkEntry;
	}
	
	public static void insertInnerWorkEntry(InnerWorkEntry entry) {
		Long unixTime = DatesHelper.generateUnixDate();
		InnerWorkEntryRepository.insertInnerWorkEntry(entry, unixTime);
	}
	
	public static void updateInnerWorkEntry(int id, InnerWorkEntry entry) {
		InnerWorkEntryRepository.updateInnerWorkEntry(id, entry);
	}
	
	public static void deleteInnerWorkEntry(int id) {
		InnerWorkEntryRepository.deleteInnerWorkEntry(id);
	}
}
