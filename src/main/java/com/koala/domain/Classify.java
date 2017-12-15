package com.koala.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.koala.util.ID;
import com.koala.util.Time;

@Entity
public class Classify {

	@Id
	private String id;

	private String name;	//类别名称
	
	private String parentid;
	
	private Timestamp ctime;
	
	@OneToMany(mappedBy = "classify", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Subject> subject;
	
	@Transient
	private List<Classify> classifys = new ArrayList<>();

	public Classify(){
		this.id = ID.uuid();
		this.ctime = Time.timestamp();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public List<Classify> getClassifys() {
		return classifys;
	}

	public void setClassifys(List<Classify> classifys) {
		this.classifys = classifys;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
}
