package com.openkx.kxexam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openkx.kxexam.domain.Classify;
import com.openkx.kxexam.domain.Exam;
import com.openkx.kxexam.domain.Subject;
import com.openkx.kxexam.service.ClassifyService;
import com.openkx.kxexam.service.ExamService;
import com.openkx.kxexam.service.SubjectService;
import com.openkx.kxexam.util.MyPage;
import com.openkx.kxexam.util.Result;

@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ClassifyService classifyService;
	
	@Autowired
	private SubjectService subjectService;
	
	/**
	 * 试卷列表 *
	 * @param model
	 * @param id
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	@GetMapping("/info/{id}/exam")
	public String findAll(Model model,@PathVariable String id,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "10", required = false) int pagesize,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword){
		Classify classify = classifyService.findClassifyById(id);
		model.addAttribute("classify",classify);
		MyPage<Exam> exam = examService.findAll(id, page, pagesize, keyword);
		model.addAttribute("exam", exam);
		return "exam";
	}
	
	/**
	 * 读取选中试卷的内容 *
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/info/{classifyid}/exam/{id}")
	public String get(Model model, @PathVariable String id) {
		Exam exam = examService.findExamById(id);
		Subject subject = new Subject();
		List<Subject> sublist = new ArrayList<>();
		for(String paper : exam.getPapers()){
			subject = subjectService.findQuestionById(paper);
			System.err.println(subject.getQuestion());
			sublist.add(subject);
		}
		model.addAttribute("sublist", sublist);
		return "paper";
	}
	
	/**
	 * 试卷删除 *
	 * @param id
	 * @return
	 */
	@PostMapping("/info/{classifyid}/exam/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id) {
		//Map<String, Object> map = new HashMap<String, Object>();
		examService.delete(id);
		return Result.success();
	}

}
