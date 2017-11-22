package com.openkx.kxexam.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SubjectDto {

	@NotEmpty(message = "题目不能为空")
	private String question;
	
	@NotEmpty(message = "正确答案不能为空")
	private String rightKey;

	private String question_tag;
	
	private String analysis;		//答案分析
	
	@NotNull(message="选项不能为空")  
    @Size(min = 2, max = 4,message="选项至少填写2个")  
	private List<String> options;
	
	private String determine;		//判断选项
	
	@NotEmpty(message = "题目类型不能为空")
	private String question_type;
	
	private String question_difficulty;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRightKey() {
		return rightKey;
	}

	public void setRightKey(String rightKey) {
		this.rightKey = rightKey;
	}

	public String getQuestion_tag() {
		return question_tag;
	}

	public void setQuestion_tag(String question_tag) {
		this.question_tag = question_tag;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getDetermine() {
		return determine;
	}

	public void setDetermine(String determine) {
		this.determine = determine;
	}

	public String getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}

	public String getQuestion_difficulty() {
		return question_difficulty;
	}

	public void setQuestion_difficulty(String question_difficulty) {
		this.question_difficulty = question_difficulty;
	}
}
