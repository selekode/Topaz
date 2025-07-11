package com.selekode.topaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.topaz.model.StatsDateRange;
import com.selekode.topaz.service.StatsService;

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
		model.addAttribute("statsDateRangeLastWeek", StatsService.getDateRangeLastWeek());
		model.addAttribute("statsDateRangeLastMonth", StatsService.getDateRangeLastMonth());
		
		model.addAttribute("statsEntryCountWeek", StatsService.getEntryCountWeek());
		model.addAttribute("statsEntryCountMonth", StatsService.getEntryCountMonth());
		model.addAttribute("statsEntryCountDateRange", StatsService.getEntryCountDateRange(statsDateRange));
		model.addAttribute("statsEntryCountAlltime", StatsService.getEntryCountAllTime());
		
		model.addAttribute("statsActivitiesPerDayOfWeekWeek", StatsService.getActivityPerDayOfWeekWeek());
		model.addAttribute("statsActivitiesPerDayOfWeekMonth", StatsService.getActivityPerDayOfWeekMonth());
		model.addAttribute("statsActivitiesPerDayOfWeekDateRange", StatsService.getActivityPerDayOfWeekDateRange(statsDateRange));
		model.addAttribute("statsActivitiesPerDayOfWeekAllTime", StatsService.getActivityPerDayOfWeekAllTime());
		
		model.addAttribute("statsEmotionFrequencyWeek", StatsService.getEmotionFrequencyWeek());
		model.addAttribute("statsEmotionFrequencyMonth", StatsService.getEmotionFrequencyMonth());
		model.addAttribute("statsEmotionFrequencyDateRange", StatsService.getEmotionFrequencyDateRange(statsDateRange));
		model.addAttribute("statsEmotionFrequencyAllTime", StatsService.getEmotionFrequencyAllTime());
		
		model.addAttribute("statsRatingsAverageWeek", StatsService.getRatingsAverageWeek());
		model.addAttribute("statsRatingsAverageMonth", StatsService.getRatingsAverageMonth());
		model.addAttribute("statsRatingsAverageDateRange", StatsService.getRatingsAverageDateRange(statsDateRange));
		model.addAttribute("statsRatingsAverageAllTime", StatsService.getRatingsAverageAllTime());
		
		model.addAttribute("statsRatingsTrendWeek", StatsService.getRatingsTrendWeek());
		model.addAttribute("statsRatingsTrendMonth", StatsService.getRatingsTrendMonth());
		model.addAttribute("statsRatingsTrendDateRange", StatsService.getRatingsTrendDateRange(statsDateRange));
		model.addAttribute("statsRatingsTrendAllTime", StatsService.getRatingsTrendAllTime());		
		
		return "stats_calculated";
	}
}
