package com.openkx.kxexam.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.openkx.kxexam.util.ID;
import com.openkx.kxexam.util.Time;

@Entity
public class User {

	@Id
	private String id;
	
	private String account;
	
	private String password;
	
	private Timestamp createtime;	//创建时间
	
	//以下为个人信息↓
	private String nickname;	//昵称
	
	private String autograph;	//个性签名
	
	private String sex;			//性别
	
	private String birthday;	//破蛋日
	
	private String email;
	
	private String mobile;
	
	private String education;
	
	private String location;	//所在地
	
	private String occupation;	//职业
	
	private String introduce;	//个人介绍
	
	private String identifier;	//标识符-编号
	
	public enum ROLE{
		VISITOR, USER;
	}
	
	public User(){
		this.id = ID.uuid();
		this.identifier = ID.Intercept();
		this.createtime = Time.timestamp();
		this.nickname = identifier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
