package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.InnerWork;
import com.selekode.topaz.repository.InnerWorkRepository;

@Service
public class InnerWorkService {

	private final InnerWorkRepository innerWorkRepository;
	private final EntryEncryptionService entryEncryptionService;

	public InnerWorkService(InnerWorkRepository innerWorkRepository, EntryEncryptionService entryEncryptionService) {
		this.innerWorkRepository = innerWorkRepository;
		this.entryEncryptionService = entryEncryptionService;
	}
	
	// Encrypts the fields of an InnerWork object
    public InnerWork encryptInnerWork(InnerWork innerWork) {
        if (innerWork == null) return null;

        try {
			innerWork.setTitle(entryEncryptionService.encryptText(innerWork.getTitle()));
	        innerWork.setContent(entryEncryptionService.encryptText(innerWork.getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return innerWork;
    }

    // Decrypts the fields of an InnerWork object
    public InnerWork decryptInnerWork(InnerWork innerWork) {
        if (innerWork == null) return null;

        try {
			innerWork.setTitle(entryEncryptionService.decryptText(innerWork.getTitle()));
	        innerWork.setContent(entryEncryptionService.decryptText(innerWork.getContent()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return innerWork;
    }

    /*
	public List<InnerWork> getAll() {
	    return innerWorkRepository.findAllByOrderByDateDesc();
	}*/
    
	public List<InnerWork> getAll() {
		return innerWorkRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream().map(this::decryptInnerWork)
				.collect(Collectors.toList());
	}

	public InnerWork getById(Long id) {
		return innerWorkRepository.findById(id).map(this::decryptInnerWork).orElse(null);
	}

	public void save(InnerWork innerWork) {
		if (innerWork.getDate() == null) {
			innerWork.setDate(LocalDate.now());
		}
		innerWorkRepository.save(encryptInnerWork(innerWork));
	}

	public InnerWork update(Long id, InnerWork updatedInnerWork) {
		return innerWorkRepository.findById(id).map(existing -> {

			existing.setDate(updatedInnerWork.getDate());
			existing.setTitle(updatedInnerWork.getTitle());
			existing.setContent(updatedInnerWork.getContent());
			existing.setTagID(updatedInnerWork.getTagID());

	    return innerWorkRepository.save(encryptInnerWork(existing));
		}).orElse(null);
	}

	public void delete(Long id) {
		innerWorkRepository.deleteById(id);
	}
}
