package com.openkx.kxexam.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openkx.kxexam.domain.CFormDto;
import com.openkx.kxexam.domain.PFormDto;
import com.openkx.kxexam.domain.User;
import com.openkx.kxexam.domain.UserDto;
import com.openkx.kxexam.service.UserService;
import com.openkx.kxexam.util.Result;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String register(HttpSession session, UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.register(session, userDto);
		return "success";
	}

	@PostMapping("/login")
	public String auth(HttpSession session,UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		}
		userService.login(session, userDto.getAccount(), userDto.getPassword());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userid");
		return "redirect:/";
	}
	
	@PostMapping("/personal/{userid}/update")
	@ResponseBody
	public Map<String, Object> pUpdate(@PathVariable String userid, PFormDto pFormDto, HttpSession session){
		User user = userService.personalDataUpdate(userid, pFormDto);
		session.setAttribute("userid", user);
		return Result.success();
	}
	
	@PostMapping("/contacts/{userid}/update")
	@ResponseBody
	public Map<String, Object> cUpdate(@PathVariable String userid, CFormDto cFormDto, HttpSession session){
		User user = userService.ContactDataUpdate(userid, cFormDto);
		session.setAttribute("userid", user);
		return Result.success();
	}
}
