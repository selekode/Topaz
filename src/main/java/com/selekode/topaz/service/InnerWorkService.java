package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.InnerWork;
import com.selekode.topaz.repository.InnerWorkRepository;

@Service
public class InnerWorkService {

	private final InnerWorkRepository innerWorkRepository;

	public InnerWorkService(InnerWorkRepository innerWorkRepository) {
		this.innerWorkRepository = innerWorkRepository;
	}

	public List<InnerWork> getAll() {
	    return innerWorkRepository.findAllByOrderByDateDesc();
	}

	public InnerWork getById(Long id) {
		return innerWorkRepository.findById(id).orElse(null);
	}

	public void save(InnerWork innerWork) {
		if (innerWork.getDate() == null) {
			innerWork.setDate(LocalDate.now());
		}
		innerWorkRepository.save(innerWork);
	}

	public InnerWork update(Long id, InnerWork updatedInnerWork) {
	    InnerWork existingInnerWork = innerWorkRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Entry not found: " + id));

	    existingInnerWork.setDate(updatedInnerWork.getDate());
	    existingInnerWork.setTitle(updatedInnerWork.getTitle());
	    existingInnerWork.setContent(updatedInnerWork.getContent());
	    existingInnerWork.setTagID(updatedInnerWork.getTagID());

	    return innerWorkRepository.save(existingInnerWork);
	}

	public void delete(Long id) {
		innerWorkRepository.deleteById(id);
	}
}
