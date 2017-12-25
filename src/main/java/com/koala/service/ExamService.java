package com.koala.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.koala.dao.AnswerDao;
import com.koala.dao.ExamDao;
import com.koala.dao.SubjectDao;
import com.koala.domain.Answer;
import com.koala.domain.Exam;
import com.koala.domain.Subject;
import com.koala.util.MyPage;

@Component
@Transactional
public class ExamService {

	@Autowired
	private ExamDao examDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private AnswerDao answerDao;

	/**
	 * 试卷列表 *
	 * 
	 * @param classifyId
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	public MyPage<Exam> findAll(String classifyId, int page, int pagesize, String keyword) {
		try {
			return examDao.findAll(classifyId, page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}
	public MyPage<Exam> findExam(int page, int pagesize, String keyword) {
		try {
			return examDao.findExam(page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 用戶完成試卷答案列表 *
	 * 
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	public MyPage<Answer> findUserAnswer(String userid, int page, int pagesize, String keyword) {
		try {
			return answerDao.findUserAnswer(userid, page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 试卷答案比对 正确返回ture 错误返回正确答案 和 解析
	 * 
	 * @param questionId
	 * @param answer
	 * @return
	 */
	public String checkExam(String userid,String[] questionId, String[] answer, String examid, String examname) {
		String JsonResult = null;
		int score = 0;
		Answer answerdto = new Answer();
		List<String> answers = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < answer.length; i++) {
			Subject subject = subjectDao.findQuestionById(questionId[i]);
			answers.add(answer[i]);
			if (answer[i].equals(subject.getRightKey())) {
				JsonResult = JSON.toJSONString(true);
				if (subject.getQuestion_type().equals("选择题")) {
					score++;
				}
				if (subject.getQuestion_type().equals("判断题")) {
					score += 2;
				}
			} else {
				JsonResult = JSON.toJSONString(false);
			}
			results.add(JsonResult);
		}
		answerdto.setAnswers(answers);
		answerdto.setExam_id(examid);
		answerdto.setExam_name(examname);
		answerdto.setResults(results);
		answerdto.setScore(score);
		answerdto.setUserid(userid);
		answerDao.save(answerdto);
		return JsonResult;
	}

	/**
	 * 删除 *
	 * 
	 * @param id
	 */
	public void delete(String id) {
		try {
			examDao.delete(id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询试卷id
	 * 
	 * @param id
	 * @return
	 */
	public Exam findExamById(String id) {
		try {
			Exam exam = examDao.findExamById(id);
			return exam;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询试卷id
	 * 
	 * @param id
	 * @return
	 */
	public Answer findAnswerById(String id) {
		try {
			Answer answer = answerDao.findAnswerById(id);
			return answer;
		} catch (Exception e) {
			throw e;
		}
	}

}
