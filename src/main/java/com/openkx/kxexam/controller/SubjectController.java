package com.openkx.kxexam.controller;

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
import com.openkx.kxexam.domain.Subject;
import com.openkx.kxexam.domain.SubjectDto;
import com.openkx.kxexam.domain.SubjectDtoList;
import com.openkx.kxexam.service.ClassifyService;
import com.openkx.kxexam.service.SubjectService;
import com.openkx.kxexam.util.Result;

@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ClassifyService classifyService;

	/**
	 * 新建一条题目信息 *
	 * @param subjectDto
	 * @return
	 */
	@PostMapping("/subject/create")
	@ResponseBody
	public Map<String, Object> create(SubjectDtoList subjectDtoList, String classify) {
		
		for(SubjectDto subjectDto : subjectDtoList.getSubjectDto()){
			subjectService.save(subjectDto,classify);
		}
		return Result.success();
	}
	
	/**
	 * 查询分类id下的所有题目
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping({"/info/{id}/papers/details","/onlineExam/{id}/start}"})
	public String getSubjectList(Model model, @PathVariable String id){
		List<Subject> list = subjectService.findSubjectDetails(id);
		model.addAttribute("subjects", list);
		return "papersDetails";
	}
	
	/**
	 * 查询分类id下的题目*
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/info/{id}/items/details/")
	public String returnOfPage(Model model ,@PathVariable String id){
		List<Subject> list = subjectService.findAppointQuestion(id);
		model.addAttribute("subjects", list);
		return "itemDetails";
	}
	
	/**
	 * 答案判断 *
	 * @param questionId
	 * @param answer
	 * @return
	 */
	@PostMapping("/info/{questionId}/{answer}/check")
	@ResponseBody
	public String checkAnswer(@PathVariable String questionId ,@PathVariable String answer){
		String JsonResult = subjectService.checkAnswer(questionId, answer);
		System.err.println(JsonResult);
		return JsonResult;
	}

	/**
	 * 表单填写 题型选择 *
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/info/{id}/random")
	public String findRandom(Model model,@PathVariable String id){
		Classify classify = classifyService.findClassifyById(id);
		model.addAttribute("classify",classify);
		return "random";
	}
	
	/**
	 * 提交一个 自主选择题目 的表单 *
	 * @param id
	 * @param questionType
	 * @param number
	 * @param papername
	 * @return
	 */
	@PostMapping("/info/{id}/random/create")
	@ResponseBody
	public Map<String, Object> createrandom(@PathVariable String id, @RequestParam("question_type[]") String[] questionType,
			@RequestParam("number[]") String[] number, String papername){
		Subject[] random = subjectService.random(id,questionType,number,papername);
		return Result.success();
	}

	/**
	 * 删除一条题目 *
	 * @param id
	 * @return
	 */
	@PostMapping("/onlineExam/subject/{id}/detele")
	public String deteleQuestion(@PathVariable String id){
		subjectService.detele(id);
		return "start";
	}
	
	@PostMapping("/onlineExam/subject/{id}/update")
	public String updateBySubject(SubjectDto subjectDto ,@PathVariable String id){
		subjectService.update(subjectDto, id);
		return "start";
	}
	
	/**
	 * 搜索 *
	 * @param model
	 * @param key
	 * @return
	 */
	@GetMapping("/start/search/{key}")
	public String keySearch(Model model ,@PathVariable String key){
		List<Subject> searchList = subjectService.keySearch(key);
		model.addAttribute("searchList", searchList);
		return "search";
	}
}
