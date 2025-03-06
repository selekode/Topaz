package com.selekode.topaz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@RequestMapping("/stats")
@Controller
public class StatsController {

	@GetMapping("/load")
	public String loadPage(Model model) {
		// model.addAttribute("journalEntries", journalEntries); // Add journalEntries
		// to the model

		return "stats"; // Load journal.html
	}
}
