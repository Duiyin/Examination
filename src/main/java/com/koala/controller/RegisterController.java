package com.koala.controller;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koala.domain.RegisterDto;
import com.koala.service.MailService;
import com.koala.service.UserService;
import com.koala.util.ServiceException;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	
	@PostMapping(value = "/register")
	public String register(HttpSession session, @Valid RegisterDto registerDto, BindingResult result, String regCode) {
		String regCode1 = (String) session.getAttribute("regCode");
		if(!regCode.equals(regCode1)){
			result.rejectValue("regCode","user.verifycode.error","激活码错误");
			return "register";
		}

		if (result.hasErrors()) {
			return "register";
		}
		try{
			userService.register(session, registerDto);
		}catch(ServiceException e){
			result.reject(e.getMessage());
			return "register";
		}
		return "success";
	}
	
	@ResponseBody
	@PostMapping("/email/verify")
	public String verifyEmail(HttpSession session, String email){
		System.err.println(email);
		if(!isEmail(email)){
			return "邮箱格式有误";
		}
		String regCode = getFourRandom();
		session.setAttribute("regCode", regCode);
		String fromName = "在线考试";
		String subject = "激活码邮件";
		String htmlbody = "您的激活码是：" + regCode;
		mailService.send(email, email, fromName, subject, htmlbody);
		return "激活码发送成功";
		
	}
	
	@ResponseBody
	@PostMapping("/email/verify/confirm")
	public String confirmRegCode(HttpSession session, String email, String regCode){
		String regCode1 = (String) session.getAttribute("regCode");
		if(regCode.equals(regCode1)){
			return "true";
		}
		return "false";
	}
	
	private boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	private static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
          for(int i=1; i<=4-randLength; i++)
              fourRandom = "0" + fourRandom  ;
      }
        return fourRandom;
    }


}
