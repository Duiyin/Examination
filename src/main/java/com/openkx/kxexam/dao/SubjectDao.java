package com.openkx.kxexam.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openkx.kxexam.domain.Classify;
import com.openkx.kxexam.domain.RandomDto;
import com.openkx.kxexam.domain.Subject;


@Component
@Transactional
public class SubjectDao extends BaseDao<Subject>{

	/**
	 * 查一条分类表里的记录  对象 *
	 * @param classifyId
	 * @return
	 */
	public Classify findClassifyById(String classifyId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Classify.class);
		dc.add(Restrictions.eq("id", classifyId));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		List<Classify> list = criteria.list();
		Classify classify = new Classify();
			for(Classify item : list){
				classify = item;
			}
		return classify;
	}
	
	/**
	 * 信息创建 *
	 * @param classify
	 */
	public void save(Subject subject) {
		getSession().save(subject);
	}
	
	public Subject[] random(String classifyId, String[] questionType, RandomDto randomDto){
		log.debug("rendom subject");
		try {
			List<Subject> list = new ArrayList<>();
		
			for(int i=0; i < questionType.length; i++){
				List<Subject> singal_list = new ArrayList<>();
				Criteria criteria = getSession().createCriteria(Subject.class);
				criteria.add(Property.forName("classify.id").eq(classifyId));
				criteria.add(Property.forName("question_type").eq(questionType[i]));
				criteria.setMaxResults(Integer.parseInt(randomDto.getNumber()[i]));
				criteria.add(Restrictions.sqlRestriction("1 = 1  order by rand()"));
				singal_list = criteria.list();
				list.addAll(singal_list);
			}
			Subject[] personArray = list.toArray((Subject[])Array.newInstance(Subject.class, list .size()));
			return personArray;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 通过分类id查询指定分类下的题目*
	 * @param classifyId
	 * @return
	 */
	public List<Subject> findAppointQuestions(String classifyId){
		DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
		dc.add(Restrictions.eq("classify.id", classifyId));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		return criteria.list();
	}
	
	/**
	 * 题目删除 *
	 * @param subjectId
	 */
	public void detele(String subjectId){
		Subject subject = findQuestionById(subjectId);
		getSession().delete(subject);
	}
	
	public void update(Subject subject){
		getSession().update(subject);
	}
	
	public List<Subject> findByRodaom(String classifyId){
		DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
		dc.add(Restrictions.eq("id", classifyId));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		return criteria.list();
	}

	/**
	 * 搜索 *
	 * @param key
	 * @return
	 */
	public List<Subject> search(String key){
		DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
		dc.add(Property.forName("subject_json").like(key,MatchMode.ANYWHERE));
		dc.addOrder(Order.desc("newstime"));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		return criteria.list();
	}
}
