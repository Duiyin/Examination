package com.openkx.kxexam.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.openkx.kxexam.dao.ExamDao;
import com.openkx.kxexam.dao.SubjectDao;
import com.openkx.kxexam.domain.Exam;
import com.openkx.kxexam.domain.Subject;
import com.openkx.kxexam.util.MyPage;

@Component
@Transactional
public class ExamService {

	@Autowired
	private ExamDao examDao;
	@Autowired
	private SubjectDao subjectDao;
	
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
	 * 试卷答案比对 正确返回ture 错误返回正确答案 和 解析
	 * @param questionId
	 * @param answer
	 * @return
	 */
	public String checkExam(String[] questionId, String[] answer){
		String JsonResult = null;
		for(int i=0; i < answer.length; i++){
			Subject subject = subjectDao.findQuestionById(questionId[i]);
			if(answer[i].equals(subject.getRightKey())){ 
				JsonResult = JSON.toJSONString(true);
				System.err.println(JsonResult);
			}else{
				ArrayList<String> list = new ArrayList<String>();
				list.add(subject.getRightKey());
				list.add(subject.getAnalysis());
				JsonResult = JSON.toJSONString(list);
				System.err.println(JsonResult);
			}
		}
		return JsonResult;
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
	
	/**
	 * 查询试卷id
	 * @param id
	 * @return
	 */
	public Exam findExamById(String id){
		try {
			Exam exam = examDao.findExamById(id);
			return exam;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
