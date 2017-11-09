package com.openkx.kxexam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openkx.kxexam.dao.ExamDao;
import com.openkx.kxexam.domain.Exam;
import com.openkx.kxexam.util.MyPage;

@Component
@Transactional
public class ExamService {

	@Autowired
	private ExamDao examDao;
	
	/**
	 * 试卷列表 *
	 * @param classifyId
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	public MyPage<Exam> findAll(String classifyId, int page, int pagesize, String keyword){
		try {
			return examDao.findAll(classifyId, page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 删除 *
	 * @param id
	 */
	public void delete(String id){
		try {
			examDao.delete(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Exam findExamById(String id){
		try {
			Exam exam = examDao.findExamById(id);
			return exam;
		} catch (Exception e) {
			throw e;
		}
	}
}
