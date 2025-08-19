package com.selekode.topaz.controller;

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
	private final JournalService journalService;

	public JournalController(JournalService journalService) {
		this.journalService = journalService;
	}

	@GetMapping("/load")
	public String loadPageJournal(Model model) {
		model.addAttribute("journalEntries", journalService.getAll());

		return "journal";
	}

	@GetMapping("/addEntry")
	public String loadPageAddEntry(Model model) {
		JournalEntry journalEntry = new JournalEntry();
		model.addAttribute("journalEntry", journalEntry);

		return "journal_addEntry";
	}

	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute JournalEntry journalEntry) {
		journalService.save(journalEntry);

		return "redirect_journal";
	}

	@GetMapping("/editEntry/{id}")
	public String loadPageEditEntry(@PathVariable("id") Long id, Model model) {
		model.addAttribute("journalEntry", journalService.getById(id));

		return "journal_editEntry";
	}

	@PostMapping("/updateEntry/{id}")
	public String saveUpdatedEntry(@PathVariable("id") Long id, @ModelAttribute JournalEntry journalEntry) {	
		journalService.update(id, journalEntry);

		return "redirect_journal";
	}

	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") Long id) {
		journalService.delete(id);

		return "redirect_journal";
	}
}
