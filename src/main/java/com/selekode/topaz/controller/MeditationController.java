package com.selekode.topaz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/meditacion")
@Controller
public class MeditationController {
	  @GetMapping("/load")
	  public String loadPageMeditation(Model model) {

		  return "meditation";
	  }
}