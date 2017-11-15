package com.openkx.kxexam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.openkx.kxexam.domain.UserDto;
import com.openkx.kxexam.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String register(HttpSession session, UserDto userDto) {
		userService.register(session, userDto);
		return "success";
	}

	@PostMapping("/login")
	public String auth(HttpSession session,UserDto userDto) {
		userService.login(session, userDto.getAccount(), userDto.getPassword());
		return "redirect:/";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userid");
		return "redirect:/";
	}
}
