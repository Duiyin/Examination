package com.koala.controller;

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

import com.koala.domain.Answer;
import com.koala.domain.Classify;
import com.koala.domain.Exam;
import com.koala.domain.Subject;
import com.koala.service.ClassifyService;
import com.koala.service.ExamService;
import com.koala.service.SubjectService;
import com.koala.util.MyPage;
import com.koala.util.Result;

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
	 * 
	 * @param model
	 * @param id
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	@GetMapping("/info/{id}/exam")
	public String findAll(Model model, @PathVariable String id,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "10", required = false) int pagesize,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		Classify classify = classifyService.findClassifyById(id);
		model.addAttribute("classify", classify);
		MyPage<Exam> exam = examService.findAll(id, page, pagesize, keyword);
		model.addAttribute("exam", exam);
		return "exam";
	}

	/**
	 * 答案列表 *
	 * 
	 * @param model
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	@GetMapping("/finish/{userid}/answer/")
	public String findAllAnswer(@PathVariable String userid, Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "10", required = false) int pagesize,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		MyPage<Answer> answer = examService.findUserAnswer(userid, page, pagesize, keyword);
		model.addAttribute("answer", answer);
		return "answer";
	}

	/**
	 * 读取选中试卷的内容 *
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/info/{classifyid}/exam/{id}")
	public String get(Model model, @PathVariable String classifyid, @PathVariable String id) {
		Exam exam = examService.findExamById(id);
		Classify classify = classifyService.findClassifyById(classifyid);
		Subject subject = new Subject();
		List<Subject> sublist = new ArrayList<>();
		for (String paper : exam.getPapers()) {
			subject = subjectService.findQuestionById(paper);
			System.err.println(subject.getQuestion());
			sublist.add(subject);
		}
		model.addAttribute("sublist", sublist);
		model.addAttribute("classify", classify);
		model.addAttribute("exam", exam);
		return "paper";
	}

	/**
	 * 读取选中答案的内容 *
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/info/{examid}/answer/{id}")
	public String getanswer(Model model, @PathVariable String examid, @PathVariable String id) {
		Exam exam = examService.findExamById(examid);
		Answer answer = examService.findAnswerById(id);
		Answer answerall = examService.findAnswerById(id);
		Answer answerresult = examService.findAnswerById(id);
		Subject subject = new Subject();
		List<Subject> sublist = new ArrayList<>();
		for (String paper : exam.getPapers()) {
			subject = subjectService.findQuestionById(paper);
			sublist.add(subject);
		}
		model.addAttribute("sublist", sublist);
		model.addAttribute("answerall", answerall);
		model.addAttribute("answer", answer.getAnswers());
		model.addAttribute("answerresult", answerresult.getResults());
		return "answerdatail";
	}

	/**
	 * 试卷答案判断
	 * 
	 * @param id
	 * @param answer
	 * @return
	 */
	@PostMapping("/info/{classifyid}/exam/{id}/answer")
	@ResponseBody
	public Map<String, Object> checkexam(@RequestParam("id[]") String[] id, @RequestParam("answer[]") String[] answer,
			String examid, String examname) {
		String JsonResult = examService.checkExam(id, answer, examid, examname);
		return Result.success();
	}

	/**
	 * 试卷删除 *
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/exam/{id}/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id) {
		examService.delete(id);
		return Result.success();
	}
}
