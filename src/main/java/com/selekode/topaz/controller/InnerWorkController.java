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

import com.selekode.topaz.model.InnerWorkEntry;
import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.service.InnerWorkEntryService;
import com.selekode.topaz.service.InnerWorkTagService;

@Controller
@RequestMapping("/innerwork")
public class InnerWorkController {
	private final InnerWorkEntryService innerWorkEntryService;
	private final InnerWorkTagService innerWorkTagService;
	
	public InnerWorkController(InnerWorkEntryService innerWorkEntryService, InnerWorkTagService innerWorkTagService) {
		this.innerWorkEntryService = innerWorkEntryService;
		this.innerWorkTagService = innerWorkTagService;
	}
	
	
	@GetMapping("/load")
	public String loadPageInnerWork(Model model) {
	    List<InnerWorkEntry> innerWorkEntries = InnerWorkEntryService.selectAllInnerWorkEntries();
	    List<InnerWorkTag> tags = innerWorkTagService.getAll();

	    Map<Long, String> tagMap = tags.stream()
	                                     .collect(Collectors.toMap(InnerWorkTag::getId, InnerWorkTag::getName));
	    
	    model.addAttribute("innerWorkEntries", innerWorkEntries);
	    model.addAttribute("tags", tags);
	    model.addAttribute("tagMap", tagMap);

	    return "innerWork";
	}



	@GetMapping("/addEntry")
	public String loadPageAddEntry(Model model) {
		InnerWorkEntry innerWorkEntry = new InnerWorkEntry(0, "", 0, "", "");
		model.addAttribute("innerWorkEntry", innerWorkEntry);
		List<InnerWorkTag> tags = innerWorkTagService.getAll();
		model.addAttribute("tags", tags);
		return "innerWork_addEntry";
	}

	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute InnerWorkEntry innerWorkEntry) {
		InnerWorkEntryService.insertInnerWorkEntry(innerWorkEntry);

		return "redirect_innerWork";
	}

	// EDIT ENTRY
	@GetMapping("/editEntry/{id}")
	public String loadPageEditEntry(@PathVariable("id") int id, Model model) {
		model.addAttribute("innerWorkEntry", InnerWorkEntryService.selectInnerWorkEntry(id));
		List<InnerWorkTag> tags = innerWorkTagService.getAll();
		model.addAttribute("tags", tags);
		return "innerWork_editEntry";
	}

	// When the edit button on the form is clicked, it will send the data here
	@PostMapping("/updateEntry/{id}")
	public String updateEntry(@PathVariable("id") int id, @ModelAttribute InnerWorkEntry innerWorkEntry) {
		System.out.println("Received from frontend: entry with: ID: " + id + ", Title: " + innerWorkEntry.getTitle()
				+ ", TagID: " + innerWorkEntry.getTagID() + ", Content: " + innerWorkEntry.getContent());

		InnerWorkEntryService.updateInnerWorkEntry(id, innerWorkEntry);

		return "redirect_innerWork";
	}

	// DELETE ENTRY FEATURE
	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") int id) {
		InnerWorkEntryService.deleteInnerWorkEntry(id);

		return "redirect_innerWork";
	}
}
