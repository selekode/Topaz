package com.selekode.topaz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.repository.InnerWorkTagRepository;

@Service
public class InnerWorkTagService {
	public static List<InnerWorkTag> selectAllTags() {
		List<InnerWorkTag> innerWorkTags = InnerWorkTagRepository.selectAllInnerWorkTags();
		
		return innerWorkTags;
	}
	
	public static InnerWorkTag selectTag(int id) {
		InnerWorkTag innerWorkTag = InnerWorkTagRepository.selectInnerWorkTag(id);
		
		return innerWorkTag;
	}
	
	public static void insertTag(InnerWorkTag tag) {
		InnerWorkTagRepository.insertInnerWorkTag(tag);
	}
	public static void updateTag(int id, InnerWorkTag tag) {
		InnerWorkTagRepository.updateInnerWorkTag(id, tag);
	}
	
	public static void deleteTag(int id) {
		InnerWorkTagRepository.deleteInnerWorkTag(id);
	}

}
