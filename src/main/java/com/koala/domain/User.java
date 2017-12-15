package com.koala.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.koala.util.ID;
import com.koala.util.PasswordUtil;
import com.koala.util.Time;

@Entity
public class User {

	@Id
	private String id;
	
	private String account;
	
	private String password;
	
	private Timestamp createtime;	//创建时间
	
	private String role;
	
	//以下为个人信息↓
	private String headimgs;	//头像
	
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
		STUDENTS, TEACHER, ADMIN, FOREVER;
	}
	
	public User(){
		this.id = ID.uuid();
		this.identifier = ID.Intercept();
		this.createtime = Time.timestamp();
		this.role = "STUDENTS";
		this.headimgs = "/images/default_head.png";
		this.nickname = identifier;
		this.autograph = "此人较懒，暂无信息";
		this.sex = "保密";
		this.birthday = "1990-01-01";
		this.email = "保密";
		this.mobile = "保密";
		this.education = "保密";
		this.location = "保密";
		this.occupation = "保密";
		this.introduce = "保密";
	}
		
	//初始化账户
	public void init(){
		this.setId("4oBGXsU9b@%zlxbUK1y5MsTtXpYq6H9g");
		this.setAccount("l1278945971@163.com");
		this.setPassword("koala123456");
		this.setRole("FOREVER");
		this.setHeadimgs("/images/default_koala.jpg");
		this.setNickname("大鱼");
		this.setIdentifier("4oBGXsU9bz");
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
		this.password = PasswordUtil.createPassword(password);
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getHeadimgs() {
		return headimgs;
	}

	public void setHeadimgs(String headimgs) {
		this.headimgs = headimgs;
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
