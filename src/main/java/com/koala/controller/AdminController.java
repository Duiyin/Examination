package com.koala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.koala.domain.Classify;
import com.koala.domain.Exam;
import com.koala.domain.Subject;
import com.koala.service.ClassifyService;
import com.koala.service.ExamService;
import com.koala.service.SubjectService;
import com.koala.util.MyPage;

@Controller
public class AdminController {

	@Autowired
	private ClassifyService classifyService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ExamService examService;
	
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

	@GetMapping("/classify/{classifyid}/edit")
	public String classify_edit(@PathVariable String classifyid, Model model) {
		Classify classify = classifyService.findClassifyById(classifyid);
		List<Classify> allclassify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		model.addAttribute("allclassify", allclassify);
		return "admin/classify_edit";
	}
	
	@GetMapping("/subject")
	public String subject(Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "5", required = false) int pagesize,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		MyPage<Subject> subject = subjectService.findAll(page, pagesize, keyword);
		model.addAttribute("subject", subject);
		return "admin/subject";
	}
	
	@GetMapping("/subject/add")
	public String subject_add(Model model) {
		List<Classify> classify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		return "admin/subject_add";
	}
	
	@GetMapping("/subject/{classifyId}/{subjectId}/edit")
	public String subject_edit(@PathVariable String subjectId, @PathVariable String classifyId, Model model) {
		Subject subject = subjectService.findQuestionById(subjectId);
		List<Classify> classify = classifyService.findAllClassify();
		model.addAttribute("subject",subject);
		model.addAttribute("classify",classify);
		return "admin/subject_edit";
	}
	
	@GetMapping("/paper")
	public String paper(Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "5", required = false) int pagesize,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		MyPage<Exam> exam = examService.findExam(page, pagesize, keyword);
		model.addAttribute("exam", exam);
		return "admin/paper";
	}
	
	@GetMapping("/paper/add")
	public String paper_add(Model model) {
		List<Classify> classify = classifyService.findAllClassify();
		model.addAttribute("classify", classify);
		return "admin/paper_add";
	}
	
	@GetMapping("/abc")
	public String abc(Model model) {
		return "random";
	}
}
