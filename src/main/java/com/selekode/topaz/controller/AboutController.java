package com.selekode.topaz.controller;
import com.selekode.topaz.model.AboutInfo;
import com.selekode.topaz.service.AboutService;
import com.selekode.topaz.service.DashboardService;
import com.selekode.topaz.service.JournalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	private AboutService aboutService;

	public AboutController(AboutService aboutService) {
		this.aboutService = aboutService;
	}

	@GetMapping("/about")
	  public String loadPageAbout(Model model) {
		  model.addAttribute("aboutInfo", aboutService.getDatasourceUrl());

		  return "about";
	  }
}