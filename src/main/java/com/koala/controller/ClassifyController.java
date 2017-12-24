package com.koala.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koala.domain.Classify;
import com.koala.domain.ClassifyDto;
import com.koala.service.ClassifyService;
import com.koala.util.Result;

@Controller
public class ClassifyController {

	@Autowired
	private ClassifyService classifyService;

	/**
	 * 查询分类信息及对应子分类，首页分类信息显示*
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String findAllClassify(Model model) {
		List<Classify> list = classifyService.findAllClassify();
		model.addAttribute("AllClassify", list);
		return "index";
	}

	/**
	 * 查询分类详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/classify/findDetails")
	public String findClassifyDetails(Model model) {
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
	 * 
	 * @param classifyDto
	 * @return
	 */
	@PostMapping("/classify/create")
	@ResponseBody
	public Map<String, Object> createMain(ClassifyDto classifyDto) {
		classifyService.create(classifyDto);
		return Result.success();
	}

	/**
	 * 分类修改 *
	 * 
	 * @param classifyId
	 * @param classifyDto
	 */
	@PostMapping("/classify/{classifyId}/update")
	@ResponseBody
	public Map<String, Object> update(@PathVariable String classifyId, ClassifyDto classifyDto) {
		classifyService.ContactDataUpdate(classifyId, classifyDto);
		return Result.success();
	}

	/**
	 * 分类删除 * 
	 * 删除主分类 同时所属子分类同时删除 
	 * 删除子分类 不影响其他
	 * 
	 * @param classifyId
	 */
	@PostMapping("/classify/{classifyId}/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String classifyId) {
		classifyService.delete(classifyId);
		return Result.success();
	}
}
