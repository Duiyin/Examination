package com.openkx.kxexam.domain;

import java.util.List;

public class SubjectDto {

	private String question;
	
	private String rightKey;

	private String question_tag;
	
	private String analysis;		//答案分析
	
	private List<String> options;
	
	private String determine;		//判断选项
	
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
