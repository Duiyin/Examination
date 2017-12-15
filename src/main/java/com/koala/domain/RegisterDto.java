package com.koala.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.koala.util.FieldMatch;

@FieldMatch(first = "npassword", second = "cpassword", message = "密码不一致")
public class RegisterDto {
	@NotEmpty(message = "用户名不能为空")
	private String account;

	@NotEmpty(message = "密码不能为空")
	@Size(min = 6, max = 16, message = "密码长度有误")
	private String npassword;

	@NotEmpty(message = "密码不能为空")
	@Size(min = 6, max = 16, message = "密码长度有误")
	private String cpassword;

	@NotEmpty(message = "邮箱不能为空")
	@Email(message = "请正确书写Email格式")
	private String email;

	private String regCode;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNpassword() {
		return npassword;
	}

	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

}
