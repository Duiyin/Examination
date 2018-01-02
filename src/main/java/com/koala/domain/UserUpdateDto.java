package com.koala.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class UserUpdateDto {

	@NotEmpty(message = "不能为空")
	private String nickname;
	
	@NotEmpty(message = "不能为空")
	private String rolename;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
