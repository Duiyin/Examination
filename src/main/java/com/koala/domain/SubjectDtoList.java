package com.koala.domain;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class SubjectDtoList {
	
	@Valid
	@NotEmpty
	private List<SubjectDto> subjectDto;

	public List<SubjectDto> getSubjectDto() {
		return subjectDto;
	}

	public void setSubjectDto(List<SubjectDto> subjectDto) {
		this.subjectDto = subjectDto;
	}
}
