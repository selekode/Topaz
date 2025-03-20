package com.selekode.topaz.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.service.JournalService;

@RequestMapping("/journal")
@Controller
public class JournalController {
	// LOAD JOURNAL FEATURE
	@GetMapping("/load")
	public String loadPageJournal(Model model) {
		model.addAttribute("journalEntries", JournalService.selectAllJournalEntries()); // Add journalEntries to the model

		return "journal";
	}

	// ADD ENTRY FEATURE
	@GetMapping("/addEntry")
	public String loadPageAddEntry(Model model) {
		JournalEntry journalEntry = new JournalEntry(0, "", "", "");
		model.addAttribute("journalEntry", journalEntry);

		return "journal_addEntry";
	}

	// When the save button on the form is clicked, it will send the data here
	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute JournalEntry journalEntry) {
		JournalService.insertJournalEntry(journalEntry);

		return "redirect_journal";
	}

	// EDIT ENTRY
	@GetMapping("/editEntry/{id}")
	public String loadPageEditEntry(@PathVariable("id") Long id, Model model) {
		model.addAttribute("journalEntry", JournalService.selectJournalEntry(id));

		return "journal_editEntry";
	}

	// When the edit button on the form is clicked, it will send the data here
	@PostMapping("/updateEntry/{id}")
	public String updateEntry(@PathVariable("id") Long id, @ModelAttribute JournalEntry journalEntry) {
		JournalService.updateJournalEntry(id, journalEntry);

		return "redirect_journal";
	}

	// DELETE ENTRY FEATURE
	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") Long id) {
		JournalService.deleteJournalEntry(id);

		return "redirect_journal";
	}
}
