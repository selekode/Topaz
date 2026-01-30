package com.selekode.topaz.controller;

import com.selekode.topaz.repository.StatsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.topaz.model.DateRange;
import com.selekode.topaz.service.StatsService;
import com.selekode.topaz.utils.DatesUtils;
import com.selekode.topaz.utils.StatsUtils;

import org.springframework.ui.Model;

@RequestMapping("/stats")
@Controller
public class StatsController {
	StatsService statsService;

	public StatsController(StatsService statsService){
		this.statsService = statsService;
	}

    @GetMapping("/load")
	public String loadStats(Model model) {
		// Default date range
		DateRange statsDateRange = new DateRange("2000-01-01", "2100-01-01");

		model.addAttribute("statsDateRange", DatesUtils.convertYYYYmmDDtoDDmmmYYYY(statsDateRange));
		model.addAttribute("statsDateRangeLastWeek", StatsUtils.calculateDateRangeLastWeek());
		model.addAttribute("statsDateRangeLastMonth", StatsUtils.calculateDateRangeLastMonth());

		model.addAttribute("statsEntryCountWeek", statsService.getEntryCountWeek());
		model.addAttribute("statsEntryCountMonth", statsService.getEntryCountMonth());
		model.addAttribute("statsEntryCountDateRange", statsService.getEntryCountDateRange(statsDateRange));
		model.addAttribute("statsEntryCountAlltime", statsService.getEntryCountAllTime());

		model.addAttribute("statsActivitiesPerDayOfWeekWeek", statsService.getActivityPerDayOfWeekWeek());
		model.addAttribute("statsActivitiesPerDayOfWeekMonth", statsService.getActivityPerDayOfWeekMonth());
		model.addAttribute("statsActivitiesPerDayOfWeekDateRange",
				statsService.getActivityPerDayOfWeekDateRange(statsDateRange));
		model.addAttribute("statsActivitiesPerDayOfWeekAllTime", statsService.getActivityPerDayOfWeekAllTime());

		model.addAttribute("statsEmotionFrequencyWeek", statsService.getEmotionFrequencyWeek());
		model.addAttribute("statsEmotionFrequencyMonth", statsService.getEmotionFrequencyMonth());
		model.addAttribute("statsEmotionFrequencyDateRange", statsService.getEmotionFrequencyDateRange(statsDateRange));
		model.addAttribute("statsEmotionFrequencyAllTime", statsService.getEmotionFrequencyAllTime());

		model.addAttribute("statsRatingsAverageWeek", statsService.getRatingsAverageWeek());
		model.addAttribute("statsRatingsAverageMonth", statsService.getRatingsAverageMonth());
		model.addAttribute("statsRatingsAverageDateRange", statsService.getRatingsAverageDateRange(statsDateRange));
		model.addAttribute("statsRatingsAverageAllTime", statsService.getRatingsAverageAllTime());

		model.addAttribute("statsRatingsTrendWeek", statsService.getRatingsTrendWeek());
		model.addAttribute("statsRatingsTrendMonth", statsService.getRatingsTrendMonth());
		model.addAttribute("statsRatingsTrendDateRange", statsService.getRatingsTrendDateRange(statsDateRange));
		model.addAttribute("statsRatingsTrendAllTime", statsService.getRatingsTrendAllTime());

		return "stats";
	}

	@GetMapping("/search")
	public String loadSearchPage(Model model) {
		model.addAttribute("statsDateRange", new DateRange("", ""));
		return "stats_search";
	}
	
	@PostMapping("/calculate")
	public String calculateStats(@ModelAttribute DateRange statsDateRange, Model model) {
		model.addAttribute("statsDateRange", statsDateRange);
		model.addAttribute("statsDateRangeLastWeek", StatsUtils.calculateDateRangeLastWeek());
		model.addAttribute("statsDateRangeLastMonth", StatsUtils.calculateDateRangeLastMonth());

		model.addAttribute("statsEntryCountWeek", statsService.getEntryCountWeek());
		model.addAttribute("statsEntryCountMonth", statsService.getEntryCountMonth());
		model.addAttribute("statsEntryCountDateRange", statsService.getEntryCountDateRange(statsDateRange));
		model.addAttribute("statsEntryCountAlltime", statsService.getEntryCountAllTime());

		model.addAttribute("statsActivitiesPerDayOfWeekWeek", statsService.getActivityPerDayOfWeekWeek());
		model.addAttribute("statsActivitiesPerDayOfWeekMonth", statsService.getActivityPerDayOfWeekMonth());
		model.addAttribute("statsActivitiesPerDayOfWeekDateRange",
				statsService.getActivityPerDayOfWeekDateRange(statsDateRange));
		model.addAttribute("statsActivitiesPerDayOfWeekAllTime", statsService.getActivityPerDayOfWeekAllTime());

		model.addAttribute("statsEmotionFrequencyWeek", statsService.getEmotionFrequencyWeek());
		model.addAttribute("statsEmotionFrequencyMonth", statsService.getEmotionFrequencyMonth());
		model.addAttribute("statsEmotionFrequencyDateRange", statsService.getEmotionFrequencyDateRange(statsDateRange));
		model.addAttribute("statsEmotionFrequencyAllTime", statsService.getEmotionFrequencyAllTime());

		model.addAttribute("statsRatingsAverageWeek", statsService.getRatingsAverageWeek());
		model.addAttribute("statsRatingsAverageMonth", statsService.getRatingsAverageMonth());
		model.addAttribute("statsRatingsAverageDateRange", statsService.getRatingsAverageDateRange(statsDateRange));
		model.addAttribute("statsRatingsAverageAllTime", statsService.getRatingsAverageAllTime());

		model.addAttribute("statsRatingsTrendWeek", statsService.getRatingsTrendWeek());
		model.addAttribute("statsRatingsTrendMonth", statsService.getRatingsTrendMonth());
		model.addAttribute("statsRatingsTrendDateRange", statsService.getRatingsTrendDateRange(statsDateRange));
		model.addAttribute("statsRatingsTrendAllTime", statsService.getRatingsTrendAllTime());

		
		System.out.println("Going to load stats between selected dates");
		return "stats";
	}
}
