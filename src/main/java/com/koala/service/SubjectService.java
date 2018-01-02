package com.koala.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.koala.dao.ExamDao;
import com.koala.dao.SubjectDao;
import com.koala.domain.Classify;
import com.koala.domain.Exam;
import com.koala.domain.Subject;
import com.koala.domain.SubjectDto;
import com.koala.util.MyPage;

@Component
@Transactional
public class SubjectService {

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private ExamDao examDao;

	public MyPage<Subject> findAll( int page, int pagesize, String keyword) {
		try {
			return subjectDao.findAll(page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 题目添加*
	 * 
	 * @param subjectDto
	 * @param classify
	 */
	public void save(SubjectDto subjectDto, String classify) {
		Subject subject = new Subject();
		List<String> list = subjectDto.getOptions();
		List<String> newGroup = new ArrayList<String>();
		String[] arr = { "A", "B", "C", "D" };
		// 如果为选择题给options添加ABCD
		if (subjectDto.getQuestion_type().equals("选择题")) {
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).isEmpty()) {
					newGroup.add(arr[i] + " : " + list.get(i));
				} else {
					break;
				}
			}
			subjectDto.setOptions(newGroup);
		}
		// 如果为判断题直接把list添加如options
		if (subjectDto.getQuestion_type().equals("判断题")) {
			subjectDto.setOptions(list);
		}
		// 标签为空保存无标签信息
		if (null == subjectDto.getQuestion_tag()) {
			subjectDto.setQuestion_tag("无标签信息!");
		}

		BeanUtils.copyProperties(subjectDto, subject, Subject.class);
		Classify Classify = subjectDao.findClassifyById(classify);
		subject.setClassify(Classify);
		subjectDao.save(subject);
	}
	
	/**
	 * 对随机抽取完的内容进行序列化并保存到exam表里*
	 * @param userid
	 * @param classifyId
	 * @param questionType
	 * @param number
	 * @param papername
	 * @return
	 */
	public Subject[] random(String userid, String classifyId, String[] questionType,String[] number,String papername) {
		Subject[] list = subjectDao.random(classifyId, questionType, number,papername);
		Exam exam = new Exam();
		Classify classify = subjectDao.findClassifyById(classifyId);
		List<String> paperids = new ArrayList<String>();
		for (Subject subject : list) {
			String paperid = subject.getId();
			paperids.add(paperid);
		}
		exam.setPapername(papername);
		exam.setPapers(paperids);
		exam.setUserid(userid);
		exam.setClassify(classify);
		examDao.save(exam);
		return list;
	}

	public List<Subject> findSubjectDetails(String classifyId) {
		List<Subject> list = subjectDao.findAppointQuestions(classifyId);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDetermine() != null) {
				list.get(i).setRightKey(list.get(i).getDetermine());
			}
		}
		return list;
	}

	public List<Subject> findOneDetails(String classifyId) {
		List<Subject> list = subjectDao.findAppointQuestions(classifyId);
		ArrayList lis2 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			lis2.add(i);
			System.err.println(list.get(0));
		}
		return lis2;
	}

	/**
	 * 题目删除 *
	 * 
	 * @param subjectId
	 */
	public void delete(String subjectId) {
		subjectDao.delete(subjectId);
	}
	
	/**
	 * 题目修改*
	 * @param subjectDto
	 * @param id
	 * @param classify
	 */
	public void update(SubjectDto subjectDto, String id,String classify) {
		Subject subject = subjectDao.findQuestionById(id);
		List<String> list = subjectDto.getOptions();
		List<String> newGroup = new ArrayList<String>();
		String[] arr = { "A", "B", "C", "D" };
		// 如果为选择题给options添加ABCD
		if (subjectDto.getQuestion_type().equals("选择题")) {
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).isEmpty()) {
					newGroup.add(arr[i] + " : " + list.get(i));
				} else {
					break;
				}
			}
			subjectDto.setOptions(newGroup);
		}
		// 标签为空保存无标签信息
		if (null == subjectDto.getQuestion_tag()) {
			subjectDto.setQuestion_tag("无标签信息!");
		}
		BeanUtils.copyProperties(subjectDto, subject, Subject.class);
		Classify Classify = subjectDao.findClassifyById(classify);
		subject.setClassify(Classify);
		subjectDao.update(subject);
	}

	public List<Subject> findAppointQuestion(String classifyId) {
		return subjectDao.findAppointQuestions(classifyId);
	}

	/**
	 * 答案比对 正确返回ture 错误返回正确答案 和 解析*
	 * 
	 * @param questionId
	 * @param answer
	 * @return
	 */
	public String checkAnswer(String questionId, String answer) {
		Subject subject = subjectDao.findQuestionById(questionId);
		String JsonResult = null;
		if (answer.equals(subject.getRightKey())) {
			JsonResult = JSON.toJSONString(true);
		} else {
			ArrayList<String> list = new ArrayList<String>();
			list.add(subject.getRightKey());
			list.add(subject.getAnalysis());
			JsonResult = JSON.toJSONString(list);
		}
		return JsonResult;
	}

	public Subject findQuestionById(String id) {
		try {
			Subject subject = subjectDao.findQuestionById(id);
			return subject;
		} catch (Exception e) {
			throw e;
		}
	}
}
