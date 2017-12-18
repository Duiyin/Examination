package com.koala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.koala.dao.UserDao;
import com.koala.domain.RegisterDto;
import com.koala.domain.User;
import com.koala.domain.UserDto;

@Controller
public class IndexController {
	
	@Autowired
	private UserDao userDao;

	@GetMapping("/register")
	public String register(RegisterDto registerDto) {
		return "register";
	}

	@GetMapping("/login")
	public String login(UserDto userDto) {
		return "login";
	}
	
	@GetMapping("/personal")
	public String personal(){
		return "personal";
	}
	
	@GetMapping("/pform/{id}")
	public String pform(@PathVariable String id, Model model){
		User user = userDao.findUserById(id);
		model.addAttribute("pform", user);
		return "pform";
	}
	
	@GetMapping("/cform/{id}")
	public String cform(@PathVariable String id, Model model){
		User user = userDao.findUserById(id);
		model.addAttribute("cform", user);
		return "cform";
	}
	
	@GetMapping("/backstage")
	public String backstage(){
		return "backstage";
	}
}
