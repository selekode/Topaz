package com.selekode.topaz.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.selekode.topaz.model.InnerWork;
import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.service.InnerWorkService;
import com.selekode.topaz.service.InnerWorkTagService;

@Controller
@RequestMapping("/innerwork")
public class InnerWorkController {
	private final InnerWorkService innerWorkEntryService;
	private final InnerWorkTagService innerWorkTagService;
	
	public InnerWorkController(InnerWorkService innerWorkEntryService, InnerWorkTagService innerWorkTagService) {
		this.innerWorkEntryService = innerWorkEntryService;
		this.innerWorkTagService = innerWorkTagService;
	}
	
	
	@GetMapping("/load")
	public String loadPageInnerWork(Model model) {
	    List<InnerWork> innerWorkEntries = innerWorkEntryService.getAll();
	    List<InnerWorkTag> tags = innerWorkTagService.getAll();

	    Map<Long, String> tagMap = tags.stream()
	                                     .collect(Collectors.toMap(InnerWorkTag::getId, InnerWorkTag::getName));
	    
	    model.addAttribute("innerWorkEntries", innerWorkEntries);
	    model.addAttribute("tags", tags);
	    model.addAttribute("tagMap", tagMap);
	    System.out.println(tagMap);

	    return "innerWork";
	}

	@GetMapping("/addEntry")
	public String loadPageAddEntry(Model model) {
		InnerWork innerWorkEntry = new InnerWork();
		model.addAttribute("innerWorkEntry", innerWorkEntry);
		List<InnerWorkTag> tags = innerWorkTagService.getAll();
		model.addAttribute("tags", tags);
		return "innerWork_addEntry";
	}

	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute InnerWork innerWorkEntry) {
		innerWorkEntryService.save(innerWorkEntry);

		return "redirect_innerWork";
	}

	@GetMapping("/editEntry/{id}")
	public String loadPageEditEntry(@PathVariable("id") Long id, Model model) {
		model.addAttribute("innerWorkEntry", innerWorkEntryService.getById(id));
		List<InnerWorkTag> tags = innerWorkTagService.getAll();
		model.addAttribute("tags", tags);
		return "innerWork_editEntry";
	}

	@PostMapping("/updateEntry/{id}")
	public String updateEntry(@PathVariable("id") Long id, @ModelAttribute InnerWork innerWorkEntry) {
		System.out.println("Received from frontend: entry with: ID: " + id + ", Title: " + innerWorkEntry.getTitle()
				+ ", TagID: " + innerWorkEntry.getTagID() + ", Content: " + innerWorkEntry.getContent());

		innerWorkEntryService.update(id, innerWorkEntry);

		return "redirect_innerWork";
	}

	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") Long id) {
		innerWorkEntryService.delete(id);

		return "redirect_innerWork";
	}
}
