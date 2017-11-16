package com.openkx.kxexam.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openkx.kxexam.domain.Answer;

@Component
@Transactional
public class AnswerDao extends BaseDao<Answer>{
	
	/**
	 * 答案保存
	 * @param answer
	 */
	public void save(Answer answer){
		getSession().save(answer);
	}

}
