package com.openkx.kxexam.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;
import com.openkx.kxexam.util.ID;
import com.openkx.kxexam.util.Time;
import com.sun.mail.imap.protocol.Item;

import net.bytebuddy.asm.Advice.This;

@Entity
public class Subject {

	@Id
	private String id;
	
	private String question;
	
	private String rightKey;		//正确答案
	
	private String tag_json;
	
	@Transient
	private String question_tag;
	
	private String analysis;		//答案分析
	
	private Timestamp newstime;		//发布时间
	
	private String options_json;
	
	@Transient
	private List<String> options;
	
	private String determine;		//判断选项
	
	private String question_type;
	
	private String question_difficulty;
	
	@ManyToOne
	private Classify classify;
	
	public Subject(){
		this.id = ID.uuid();
		this.question = question;
		this.rightKey = rightKey;
		this.tag_json = tag_json;
		this.question_tag = question_tag;
		this.analysis = analysis;
		this.newstime = Time.timestamp();
		this.options_json = options_json;
		this.options = options;
		this.determine = determine;
		this.question_type = question_type;
		this.question_difficulty = question_difficulty;
		this.classify = classify;
	}
	
	public Subject(String question, String rightKey, String tag_json, String question_tag, String analysis, String options_json,
			List<String> options, String determine, String question_type, String question_difficulty, Classify classify){
		this.id = ID.uuid();
		this.question = question;
		this.rightKey = rightKey;
		this.tag_json = tag_json;
		this.question_tag = question_tag;
		this.analysis = analysis;
		this.newstime = Time.timestamp();
		this.options_json = options_json;
		this.options = options;
		this.determine = determine;
		this.question_type = question_type;
		this.question_difficulty = question_difficulty;
		this.classify = classify;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTag_json() {
		return tag_json;
	}

	public void setTag_json(String tag_json) {
		this.tag_json = tag_json;
	}

	public List<String> getQuestion_tag() {
		List<String> list = JSON.parseArray(tag_json, String.class);
		return list;
	}

	public void setQuestion_tag(String question_tag) {
		this.question_tag = question_tag;
		String[] tag_list = question_tag.split(",| |，");
		this.tag_json = JSON.toJSONString(tag_list);
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Timestamp getNewstime() {
		return newstime;
	}

	public void setNewstime(Timestamp newstime) {
		this.newstime = newstime;
	}

	public String getOptions_json() {
		return options_json;
	}

	public void setOptions_json(String options_json) {
		this.options_json = options_json;
	}

	public List<String> getOptions() {
		return JSON.parseArray(getOptions_json(), String.class);
	}

	public void setOptions(List<String> options) {
		this.options = options;
		this.options_json = JSON.toJSONString(options);
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

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}

	//计算所有题型的数量
	public int getListCount(List<Subject> list) {
		int count = 0;
		for (Subject subject : list) {
			if (subject.getQuestion_type().equals(this.getQuestion_type())){
				count++;
			}
		}
		return count;
	}
	
	//计算某个题型的数量
	public int getCount(List<Subject> list,String str) {
		int count = 0;
		for (Subject subject : list) {
			if (subject.getQuestion_type().equals(str)){
				count++;
			}
		}
		return count;
	}
	
}
