package com.selekode.topaz.controller;
import com.selekode.topaz.service.AboutService;
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
		  model.addAttribute("databasePath", aboutService.getDatabasePath());

		  return "about";
	  }
}