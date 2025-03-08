package com.selekode.topaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.selekode.service.RevisionService;
import com.selekode.topaz.model.RevisionEntry;

@RequestMapping("/revision")
@Controller
public class RevisionController {
	// LOAD REVISION FEATURE
	@GetMapping("/load")
	public String loadPageRevision(Model model) {
		model.addAttribute("revisionEntries", RevisionService.selectAllRevisionEntries());
		
		return "revision";
	}

	// ADD ENTRY FEATURE
	@GetMapping("/addEntry")
	public String loadPageAddEntry(Model model) {
		RevisionEntry revisionEntry = new RevisionEntry(0, "", "", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "", false,
				false, false, false, false, false, false, false, false, false, false, false, false, false, false);
		model.addAttribute("revisionEntry", revisionEntry);

		return "revision_addEntry";
	}

	// When the save button on the form is clicked, it will send the data here
	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute RevisionEntry revisionEntry) {
		RevisionService.insertRevisionEntry(revisionEntry);

		return "redirect_revision";
	}

	// EDIT ENTRY
	@GetMapping("/editEntry/{id}")
	public String loadPageEditEntry(@PathVariable("id") Long id, Model model) {
		RevisionEntry revisionEntry = RevisionService.selectRevisionEntry(id);
		model.addAttribute("revisionEntry", revisionEntry);

		return "revision_editEntry";
	}

	// When the edit button on the form is clicked, it will send the data here
	@PostMapping("/updateEntry/{id}")
	public String updateEntry(@PathVariable("id") Long id, @ModelAttribute RevisionEntry revisionEntry) {
		RevisionService.updateRevisionEntry(id, revisionEntry);

		return "redirect_revision";
	}

	// DELETE ENTRY FEATURE
	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") Long id) {
		RevisionService.deleteRevisionEntry(id);

		return "redirect_revision";
	}
}
