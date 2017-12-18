package com.koala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koala.domain.Classify;
import com.koala.service.ClassifyService;

@Controller
public class AdminController {
	
	@Autowired
	private ClassifyService classifyService;
	
	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/classify")
	public String classify(Model model) {
		List<Classify> classify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		return "admin/classify";
	}
	
	@GetMapping("/classify/add")
	public String classify_add(Model model) {
		List<Classify> classify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		return "admin/classify_add";
	}
}
