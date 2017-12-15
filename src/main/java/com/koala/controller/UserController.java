package com.koala.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.koala.domain.CFormDto;
import com.koala.domain.PFormDto;
import com.koala.domain.User;
import com.koala.domain.UserDto;
import com.koala.service.UserService;
import com.koala.util.Result;
import com.koala.util.ServiceException;

@Controller
public class UserController extends KaptchaExtend{

	@Autowired
	private UserService userService;
	
	@GetMapping("/vericode.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		super.captcha(request, response);
	}
 
	@PostMapping("/login")
	public String auth(HttpSession session,@Valid UserDto userDto, BindingResult result, HttpServletRequest request) {
		String vcode = request.getParameter("vcode");
		if(!StringUtils.equals(vcode, getGeneratedKey(request))){
			result.rejectValue("vcode", "user.vcode.error", "验证码错误");
			return "login";
		}
		if (result.hasErrors()) {
			return "login";
		}
		try {
			userService.login(session, userDto.getAccount(), userDto.getPassword(), userDto.getVcode());
		} catch (ServiceException e) {
			result.rejectValue("account", e.getMessage(), "账号或密码错误");
			return "login";
		}
		
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
