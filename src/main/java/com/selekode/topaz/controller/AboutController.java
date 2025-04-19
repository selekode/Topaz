package com.selekode.topaz.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.selekode.topaz.service.SettingsService;

@Controller
public class AboutController {
	@GetMapping("/about")
	public String loadPageAbout(Model model) throws IOException {
		model.addAttribute("currentDatabasePath", SettingsService.getCurrentDatabasePath());
		return "about";
	}

	@PostMapping("/updateDatabasePath")
	public String updateDatabasePath(@RequestParam("newPath") MultipartFile file) {
		SettingsService.updateDatabasePath(file);
		return "redirect_settings";
	}

}