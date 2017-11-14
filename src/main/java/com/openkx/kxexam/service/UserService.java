package com.openkx.kxexam.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openkx.kxexam.dao.UserDao;
import com.openkx.kxexam.domain.User;
import com.openkx.kxexam.domain.UserDto;
import com.openkx.kxexam.util.ServiceException;

@Component
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public User register(HttpSession session, UserDto userDto) {
		try {
			User checkRepeat = userDao.findByAccount(userDto.getAccount());
			User user = new User();
			if (null != checkRepeat) { // 查账号是否有重复
				throw new ServiceException("register", "account_exist");
			} else {
				BeanUtils.copyProperties(userDto, user, User.class);
				userDao.save(user);
			}
			String sessionid = session.getId().toLowerCase();

			session.setAttribute("user", user.getId());
			// user.setSessionid(session.getId());

			System.out.println("suser：update_login_sessionuser:" + user.getNickname() + sessionid);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
}
