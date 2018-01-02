package com.koala.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koala.domain.Exam;
import com.koala.domain.Subject;
import com.koala.util.MyPage;


@Component
@Transactional
public class ExamDao extends BaseDao<Exam> {
	
	/**
	 * 查询所有 试卷列表 *
	 * @param classifyId
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	public MyPage<Exam> findAll(String classifyId, int page, int pagesize, String keyword){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Exam.class);
			dc.add(Property.forName("classify.id").eq(classifyId));
			if(StringUtils.isNoneBlank(keyword)){
				Disjunction dis = Restrictions.disjunction();
				dis.add(Property.forName("papername").like(keyword, MatchMode.ANYWHERE));
				dc.add(dis);
			}
			dc.addOrder(Order.desc("ctime"));
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
	public MyPage<Exam> findExam(int page, int pagesize, String keyword){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Exam.class);
			if(StringUtils.isNoneBlank(keyword)){
				Disjunction dis = Restrictions.disjunction();
				//对试题名字进行模糊查询
				dis.add(Property.forName("papername").like(keyword, MatchMode.ANYWHERE));
				dc.add(dis);
			}
			dc.addOrder(Order.desc("ctime"));
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
	 * 保存exam
	 * @param exam
	 */
	public void save(Exam exam) {
		getSession().save(exam);
	}
	
	/**
	 * 删除 *
	 * @param id
	 */
	public void delete(String id) {
		try {
			Exam exam = findExamById(id);
			getSession().delete(exam);
		} catch (Exception e) {
		}
	}
}
