package com.koala.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koala.domain.Classify;
import com.koala.domain.RandomDto;
import com.koala.domain.Subject;
import com.koala.util.MyPage;


@Component
@Transactional
public class SubjectDao extends BaseDao<Subject>{
	
	/**
	 * 试题列表显示 
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	public MyPage<Subject> findAll(int page, int pagesize, String keyword){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
			if(StringUtils.isNoneBlank(keyword)){
				Disjunction dis = Restrictions.disjunction();
				//对试题名字进行模糊查询
				dis.add(Property.forName("question").like(keyword, MatchMode.ANYWHERE));
				dc.add(dis);
			}
			dc.addOrder(Order.desc("newstime"));
			try {
				if(pagesize <=0){
					pagesize = 20;
				}
			} catch (Exception e) {
			}
			return findPageByCriteria(dc, pagesize, page);
		} catch (Exception e) {
			throw e;
		}
		
	}

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
	
	/**
	 * 对提交过来的值进行随机抽取*
	 * @param classifyId
	 * @param questionType
	 * @param number
	 * @param papername
	 * @return
	 */
	public Subject[] random(String classifyId, String[] questionType, String[] number,String papername){
		log.debug("rendom subject");
		try {
			List<Subject> list = new ArrayList<>();
		
			for(int i=0; i < questionType.length; i++){				
				DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
				dc.add(Restrictions.eq("classify.id", classifyId));
				dc.add(Restrictions.eq("question_type", questionType[i]));
				Criteria criteria = dc.getExecutableCriteria(getSession());
				criteria.setMaxResults(Integer.parseInt(number[i]));
				criteria.add(Restrictions.sqlRestriction("1 = 1  order by rand()"));
				list.addAll(criteria.list());
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
	public void delete(String subjectId){
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

	
}
