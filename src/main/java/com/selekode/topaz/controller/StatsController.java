package com.selekode.topaz.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.service.StatsService;
import com.selekode.topaz.model.StatsEntryCount;

import org.springframework.ui.Model;


@RequestMapping("/stats")
@Controller
public class StatsController {

	@GetMapping("/load")
	public String loadPage(Model model) {
		StatsEntryCount statsEntryCount = StatsService.getStatsEntryCount();
		model.addAttribute("statsEntryCount", statsEntryCount);

		return "stats";
	}
}
