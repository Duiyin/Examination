package com.openkx.kxexam.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openkx.kxexam.dao.UserDao;
import com.openkx.kxexam.domain.User;
import com.openkx.kxexam.domain.UserDto;

@Component
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User register(HttpSession httpsession, UserDto userDto){
		
		return null;
	}
}
