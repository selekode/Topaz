package com.selekode.topaz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	  @GetMapping("/error")
	  public String loadPageError(Model model) {

		  return "error";
	  }
}