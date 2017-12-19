package com.koala.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;
import com.koala.util.ID;
import com.koala.util.Time;

@Entity
public class Answer {
	
	@Id
	private String id;
	
	private String userid;
	
	private String exam_id;
	
	private String exam_name;
	
	private int score;
	
	private String answer_json;
	
	@Transient
	private List<String> answers;
	
	private String result_json;
	
	@Transient
	private List<String> results;
	
	private Timestamp ctime;
	
	public Answer(){
		this.id = ID.uuid();
		this.ctime = Time.timestamp();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getExam_id() {
		return exam_id;
	}

	public void setExam_id(String exam_id) {
		this.exam_id = exam_id;
	}

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAnswer_json() {
		return answer_json;
	}

	public void setAnswer_json(String answer_json) {
		this.answer_json = answer_json;
	}

	public List<String> getAnswers() {
		answers = JSON.parseArray(this.getAnswer_json(), String.class);
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
		this.answer_json = JSON.toJSONString(answers);
	}

	public String getResult_json() {
		return result_json;
	}

	public void setResult_json(String result_json) {
		this.result_json = result_json;
	}

	public List<String> getResults() {
		results = JSON.parseArray(this.getResult_json(), String.class);
		return results;
	}

	public void setResults(List<String> results) {
		this.results = results;
		this.result_json = JSON.toJSONString(results);
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	
}
