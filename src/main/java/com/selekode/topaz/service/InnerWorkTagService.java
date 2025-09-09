package com.selekode.topaz.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.repository.InnerWorkTagRepository;

@Service
public class InnerWorkTagService {
	private final InnerWorkTagRepository innerWorkTagRepository;
	
	public InnerWorkTagService(InnerWorkTagRepository innerWorkTagRepository) {
		this.innerWorkTagRepository = innerWorkTagRepository;
	}
	
	public List<InnerWorkTag> getAll(){
		return innerWorkTagRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	public InnerWorkTag getById(Long id) {
		return innerWorkTagRepository.findById(id).orElse(null);
	}
	
	public InnerWorkTag save(InnerWorkTag innerWorkTag) {
		return innerWorkTagRepository.save(innerWorkTag);
	}
	
	public InnerWorkTag update(Long id, InnerWorkTag updatedInnerWorkTag) {
		return innerWorkTagRepository.findById(id).map(existing -> {
			existing.setName(updatedInnerWorkTag.getName());
			return innerWorkTagRepository.save(existing);
			
		}).orElseThrow(() -> new IllegalArgumentException("Entry not found: " + id));
	}
	
	public void delete(Long id) {
		innerWorkTagRepository.deleteById(id);
	}
}
