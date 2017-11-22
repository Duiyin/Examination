package com.openkx.kxexam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openkx.kxexam.domain.Classify;
import com.openkx.kxexam.domain.ClassifyDto;
import com.openkx.kxexam.service.ClassifyService;

@Controller
public class ClassifyController {
	
	@Autowired
	private ClassifyService classifyService;

	/**
	 * 查询分类信息及对应子分类，首页分类信息显示*
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String findAllClassify(Model model) {
		List<Classify> list = classifyService.findAllClassify();
		model.addAttribute("AllClassify", list);
		return "onlineExam";
	}

	/**
	 * 查询分类详情
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/classify/{id}/findDetails")
	public String findClassifyDetails(Model model, @PathVariable String id) {
		List<Classify> clist = classifyService.findClassifyDetails(id);
		model.addAttribute("clist", clist);
		return "details";
	}
	
	@GetMapping("/classify/{id}/findClassifySub")
	@ResponseBody
	public List<Classify> findClassifySub(Model model, @PathVariable String id) {
		List<Classify> clist = classifyService.findClassifyDetails(id);
		return clist;
	}
	
	/**
	 * 分类创建 *
	 * @param classifyDto
	 * @return
	 */
	@PostMapping("/classify/create")
	public String createMain(ClassifyDto classifyDto){
		classifyService.create(classifyDto);
		return "redirect:/";
	}
}
