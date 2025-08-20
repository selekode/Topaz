package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.InnerWorkEntry;
import com.selekode.topaz.repository.InnerWorkEntryRepository;

@Service
public class InnerWorkEntryService {

	private final InnerWorkEntryRepository innerWorkEntryRepository;

	public InnerWorkEntryService(InnerWorkEntryRepository innerWorkEntryRepository) {
		this.innerWorkEntryRepository = innerWorkEntryRepository;
	}

	public List<InnerWorkEntry> getAll() {
		return innerWorkEntryRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
	}

	public InnerWorkEntry getById(Long id) {
		return innerWorkEntryRepository.findById(id).orElse(null);
	}

	public void save(InnerWorkEntry innerWorkEntry) {
		if (innerWorkEntry.getDate() == null) {
			innerWorkEntry.setDate(LocalDate.now());
		}
		innerWorkEntryRepository.save(innerWorkEntry);
	}

	public InnerWorkEntry update(Long id, InnerWorkEntry updatedInnerWorkEntry) {
		return innerWorkEntryRepository.findById(id).map(existing -> {
            existing.setDate(updatedInnerWorkEntry.getDate());
            existing.setTitle(updatedInnerWorkEntry.getTitle());
            existing.setContent(updatedInnerWorkEntry.getContent());
            existing.setTagID(updatedInnerWorkEntry.getTagID());
            return innerWorkEntryRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Entry not found: " + id));
	}

	public void delete(Long id) {
		innerWorkEntryRepository.deleteById(id);
	}
}
