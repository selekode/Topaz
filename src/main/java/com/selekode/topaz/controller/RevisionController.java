package com.selekode.topaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.selekode.topaz.model.RevisionEntry;
import com.selekode.topaz.service.RevisionService;

@RequestMapping("/revision")
@Controller
public class RevisionController {
	RevisionService revisionService;
	
	public RevisionController(RevisionService revisionService) {
		this.revisionService = revisionService;
	}
	
	@GetMapping("/load")
	public String loadPageRevision(Model model) {
		model.addAttribute("revisionEntries", revisionService.getAll());
		
		return "revision";
	}

	@GetMapping("/addEntry")
	public String loadPageAddEntry(Model model) {
		RevisionEntry revisionEntry = new RevisionEntry();
		model.addAttribute("revisionEntry", revisionEntry);

		return "revision_addEntry";
	}

	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute RevisionEntry revisionEntry) {
		revisionService.save(revisionEntry);

		return "redirect_revision";
	}

	@GetMapping("/editEntry/{id}")
	public String loadPageEditEntry(@PathVariable("id") Long id, Model model) {
		RevisionEntry revisionEntry = revisionService.getById(id);
		model.addAttribute("revisionEntry", revisionEntry);

		return "revision_editEntry";
	}

	@PostMapping("/updateEntry/{id}")
	public String updateEntry(@PathVariable("id") Long id, @ModelAttribute RevisionEntry revisionEntry) {
		revisionService.update(id, revisionEntry);

		return "redirect_revision";
	}

	// DELETE ENTRY FEATURE
	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") Long id) {
		revisionService.delete(id);

		return "redirect_revision";
	}
}
