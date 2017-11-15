package com.openkx.kxexam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.openkx.kxexam.dao.ExamDao;
import com.openkx.kxexam.dao.SubjectDao;
import com.openkx.kxexam.domain.Classify;
import com.openkx.kxexam.domain.Exam;
import com.openkx.kxexam.domain.Subject;
import com.openkx.kxexam.domain.SubjectDto;


@Component
@Transactional
public class SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private ExamDao examDao;
	
	/**
	 * 题目添加
	 * @param subjectDto
	 * @param classify
	 */
	public void save(SubjectDto subjectDto,String classify){
		Subject subject = new Subject();
		
		List<String> list = subjectDto.getOptions();
		List<String> newGroup = new ArrayList<String>();
		String[] arr = {"A", "B", "C", "D"};
		
		for(int i = 0; i < list.size(); i++) {
			if(! list .get(i).isEmpty()){
			newGroup.add(arr[i] + " : " + list.get(i));
			}else{
				break;
			}
		}
		
		if(subjectDto.getQuestion_tag().isEmpty()){
			subjectDto.setQuestion_tag("无标签信息!");
		}
		subjectDto.setOptions(newGroup);
		
		if (subjectDto.getOptions().isEmpty() && !subjectDto.getDetermine().isEmpty()) {
			
			if(subjectDto.getDetermine().equals("answerNull")){
				subjectDto.setRightKey("暂无此题答案...");
			}else{
				subjectDto.setRightKey(subjectDto.getDetermine());
			}
			newGroup.add("正确");
			newGroup.add("错误");
			subjectDto.setOptions(newGroup);
			subjectDto.setDetermine(null);
			
		} else {
			if(!subjectDto.getDetermine().isEmpty()){
				subjectDto.setDetermine(null);
			}
		}
		
		BeanUtils.copyProperties(subjectDto, subject, Subject.class);
		Classify Classify = subjectDao.findClassifyById(classify);
		subject.setClassify(Classify);
		subjectDao.save(subject);
	}
	
	public Subject[] random(String classifyId, String[] questionType, String[] number, String papername){
		Subject[] list=subjectDao.random(classifyId,questionType,number,papername);
		Exam exam = new Exam();
		Classify classify = subjectDao.findClassifyById(classifyId);
		List<String> paperids = new ArrayList<String>();
		for(Subject subject : list){
			String paperid = subject.getId();
			paperids.add(paperid);
			System.err.println("--------"+subject.getQuestion());
			System.err.println("--------"+subject.getQuestion_type());
		}
		exam.setPapername(papername);
		exam.setPapers(paperids);
		exam.setClassify(classify);
		examDao.save(exam);
		System.err.println("------"+papername);
		return list;
	}
	
	public List<Subject> findSubjectDetails(String classifyId){
		List<Subject> list = subjectDao.findAppointQuestions(classifyId);
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getDetermine() != null){
				list.get(i).setRightKey(list.get(i).getDetermine());
			}
		}
		return list;
	}
	
	public List<Subject> findOneDetails(String classifyId){
		List<Subject> list = subjectDao.findAppointQuestions(classifyId);
		ArrayList lis2 = new ArrayList();
		for(int i = 0;i< list.size();i++){
			lis2.add(i);
			System.err.println(list.get(0));
		}
		return lis2;
	}
	
	/**
	 * 题目删除 *
	 * @param subjectId
	 */
	public void detele(String subjectId){
		subjectDao.detele(subjectId);
	}
	
	
	public void update(SubjectDto subjectDto,String id){
		Subject subject = subjectDao.findQuestionById(id);
		BeanUtils.copyProperties(subjectDto, subject, Subject.class);
		subjectDao.update(subject);
	}
	
	/**
	 * 搜索 *
	 * @param key
	 * @return
	 */
	public List<Subject> keySearch(String key){
		List<Subject> searchList = subjectDao.search(key);
		return searchList;
	}
	
	public List<Subject> findAppointQuestion(String classifyId){
		return subjectDao.findAppointQuestions(classifyId);
	}
	
	/**答案比对 正确返回ture 错误返回正确答案 和 解析*
	 * @param questionId
	 * @param answer
	 * @return
	 */
	public String checkAnswer(String questionId, String answer){
		Subject subject = subjectDao.findQuestionById(questionId);
		String JsonResult = null;
		if (answer.equals(subject.getRightKey())) {
			JsonResult = JSON.toJSONString(true);
		}else{
			ArrayList<String> list = new ArrayList<String>();
			list.add(subject.getRightKey());
			list.add(subject.getAnalysis());
			JsonResult = JSON.toJSONString(list);
		}
		return JsonResult;
	}
	
	public Subject findQuestionById(String id){
		try {
			Subject subject = subjectDao.findQuestionById(id);
			return subject;
		} catch (Exception e) {
			throw e;
		}
	}
}
