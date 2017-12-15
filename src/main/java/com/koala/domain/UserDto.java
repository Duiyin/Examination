package com.koala.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {
	
	@NotEmpty(message = "账号不能为空")
	private String account;
	
	@NotEmpty(message = "密码不能为空")
	@Size(min = 6, max = 16, message = "密码长度有误")
	private String password;
	
	private String vcode;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
}
