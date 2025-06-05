package com.selekode.topaz.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.selekode.topaz.model.InnerWorkEntryTag;
import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.service.InnerWorkEntryService;
import com.selekode.topaz.service.InnerWorkTagService;
import com.selekode.topaz.service.JournalService;

@Controller
@RequestMapping("/innerwork/tags")
public class InnerWorkTagsController {

	@GetMapping("/load")
	public String loadPageInnerWorkTags(Model model) {
		model.addAttribute("innerWorkTags", InnerWorkTagService.selectAllTags());

		return "innerWorkTags";
	}

	@GetMapping("/addTag")
	public String loadPageInnerWorkAddTags(Model model) {
		InnerWorkTag innerWorkTag = new InnerWorkTag(0, "");
		model.addAttribute("innerWorkTag", innerWorkTag);

		return "innerWorkTags_addTag";
	}

	@PostMapping("/addTag")
	public String saveNewTag(@ModelAttribute InnerWorkTag innerWorkTag) {
		InnerWorkTagService.insertTag(innerWorkTag);

		return "redirect_innerWorkTags";
	}

	@GetMapping("/editTag/{id}")
	public String loadPageEditTag(@PathVariable("id") int id, Model model) {
		model.addAttribute("innerWorkTag", InnerWorkTagService.selectTag(id));

		return "innerWorkTags_editTag";
	}

	@PostMapping("/updateTag/{id}")
	public String updateTag(@PathVariable("id") int id, @ModelAttribute InnerWorkTag innerWorkTag) {
		InnerWorkTagService.updateTag(id, innerWorkTag);

		return "redirect_innerWorkTags";
	}

	@PostMapping("/deleteTag")
	public String deleteTag(@RequestParam("id") int id) {
		InnerWorkTagService.deleteTag(id);

		return "redirect_innerWorkTags";
	}
}
