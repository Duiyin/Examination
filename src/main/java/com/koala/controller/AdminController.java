package com.koala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.koala.domain.Classify;
import com.koala.service.ClassifyService;

@Controller
public class AdminController {

	@Autowired
	private ClassifyService classifyService;

	@GetMapping("/backstage")
	public String admin(Model model) {
		return "admin/index";
	}

	@GetMapping("/classify")
	public String classify(Model model) {
		List<Classify> classify = classifyService.findAllClassify();
		int count = classifyService.count();
		model.addAttribute("classify", classify);
		model.addAttribute("count", count); // 总数
		return "admin/classify";
	}

	@GetMapping("/classify/add")
	public String classify_add(Model model) {
		List<Classify> classify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		return "admin/classify_add";
	}

	@GetMapping("/classify/{id}/edit")
	public String classify_edit(@PathVariable String id, Model model) {
		Classify classify = classifyService.findClassifyById(id);
		List<Classify> allclassify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		model.addAttribute("allclassify", allclassify);
		return "admin/classify_edit";
	}
	
	@GetMapping("/subject")
	public String subject() {
		return "admin/subject";
	}
	
	@GetMapping("/paper")
	public String paper() {
		return "admin/paper";
	}
}
