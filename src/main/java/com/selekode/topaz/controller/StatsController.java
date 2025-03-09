package com.selekode.topaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.service.StatsService;
import com.selekode.topaz.model.StatsDateRange;


import org.springframework.ui.Model;

@RequestMapping("/stats")
@Controller
public class StatsController {

	@GetMapping("/load")
	public String loadPage(Model model) {
		model.addAttribute("statsDateRange", new StatsDateRange("", ""));
		return "stats";
	}

	@PostMapping("/calculateStats")
	public String calculateStats(@ModelAttribute StatsDateRange statsDateRange, Model model) {
		
		model.addAttribute("statsDateRange", statsDateRange);
		model.addAttribute("statsEntryCountAlltime", StatsService.getEntryCountAllTime());
		model.addAttribute("statsEntryCountDateRange", StatsService.getEntryCountDateRange(statsDateRange));
		model.addAttribute("statsActivityPerDayOfWeekAllTime", StatsService.getActivityPerDayOfWeekAllTime());
		model.addAttribute("statsActivityPerDayOfWeekDateRange", StatsService.getActivityPerDayOfWeekDateRange(statsDateRange));
		model.addAttribute("statsEmotionFrequencyAllTime", StatsService.getEmotionFrequencyAllTime());
		model.addAttribute("statsEmotionFrequencyDateRange", StatsService.getEmotionFrequencyDateRange(statsDateRange));
		
		return "stats_calculated";
	}
}
