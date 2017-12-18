package com.koala.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class RandomDto {
	
	@NotEmpty(message = "试卷名字不能为空")
	@NotBlank(message = "试卷名字不能为空")
	private String papername;
	private String[] number;
	
	public String getPapername() {
		return papername;
	} 

	public void setPapername(String papername) {
		this.papername = papername;
	}
	
	public String[] getNumber() {
		return number;
	}

	public void setNumber(String[] number) {
		this.number = number;
	}
	
}
