package com.openkx.kxexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openkx.kxexam.dao.UserDao;
import com.openkx.kxexam.domain.RegisterDto;
import com.openkx.kxexam.domain.User;
import com.openkx.kxexam.domain.UserDto;

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
}
