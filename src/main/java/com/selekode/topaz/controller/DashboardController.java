package com.selekode.topaz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.topaz.service.DashboardService;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {
	  @GetMapping("/load")
	  public String loadPageDashboard(Model model) {
			model.addAttribute("dashboardData", DashboardService.getDashboardData()); // Add journalEntries to the model

		  return "dashboard";
	  }
}