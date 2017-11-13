package com.openkx.kxexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/register")
	public String login(){
		return "register";
	}
}
