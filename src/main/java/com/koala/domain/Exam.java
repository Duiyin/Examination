package com.koala.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;
import com.koala.util.ID;
import com.koala.util.Time;

@Entity
public class Exam {

	@Id
	private String id;
	
	private String papername;
	
	private String paper_json;
	
	@Transient
	private List<String> papers;
	
	private String classifyId;
	
	private Timestamp ctime;
	
	public Exam(){
		this.id = ID.uuid();
		this.ctime = Time.timestamp();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPapername() {
		return papername;
	}

	public void setPapername(String papername) {
		this.papername = papername;
	}

	public String getPaper_json() {
		return paper_json;
	}

	public void setPaper_json(String paper_json) {
		this.paper_json = paper_json;
	}

	public List<String> getPapers() {
		papers = JSON.parseArray(this.getPaper_json(), String.class);
		return papers;
	}

	public void setPapers(List<String> papers) {
		this.papers = papers;
		this.paper_json = JSON.toJSONString(papers);
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
