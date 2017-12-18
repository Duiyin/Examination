package com.koala.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koala.domain.Answer;
import com.koala.domain.Classify;
import com.koala.domain.Exam;
import com.koala.domain.Subject;
import com.koala.domain.User;
import com.koala.util.MyPage;



public class BaseDao<T> {
	protected final Logger log = LoggerFactory.getLogger( getClass() );

	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, MyPage.PAGESIZE, 0);
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria, int page) {
		return findPageByCriteria(detachedCriteria, MyPage.PAGESIZE, page);
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize,
			int page) {
		//final int startIndex = (page - 1) * pageSize;
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = 0;
		try {
			totalCount = (Long) object;
		} catch (Exception e) {
		}
		MyPage<T> ps = new MyPage<T>((int) totalCount, pageSize, page);
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pageSize).list();
		//MyPage<T> ps = new MyPage<T>(items, (int) totalCount, pageSize, startIndex);
		ps.setItems(items);
		return ps;
	}

	public MyPage<T> findPageByCriteria(Session session, final DetachedCriteria detachedCriteria,
			final int pageSize, int page) {
		//final int startIndex = (page - 1) * pageSize;
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = 0;
		try {
			totalCount = (Long) object;
		} catch (Exception e) {
		}
		MyPage<T> ps = new MyPage<T>((int) totalCount, pageSize, page);
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pageSize).list();
		//MyPage<T> ps = new MyPage<T>(items, (int) totalCount, pageSize, startIndex);
		ps.setItems(items);
		return ps;
	}

	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}

	public List<T> findAllByCriteria(Session session, final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		return criteria.list();
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = 0;
		try {
			totalCount = (Long) object;
		} catch (Exception e) {
		}
		return (int) totalCount;
	}
	
	/**
	 * 查询一条分类记录 对象 *
	 * @param id
	 * @return
	 */
	public Classify findClassifyById(String id){
		try {
			Classify classify = (Classify) getSession().get("com.openkx.kxexam.domain.Classify", id);
			return classify;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询一条题目记录 对象 *
	 * @param id
	 * @return
	 */
	public Subject findQuestionById(String id){
		try {
			Subject subject = (Subject) getSession().get("com.openkx.kxexam.domain.Subject", id);
			return subject;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 查询一条试卷记录 对象 *
	 * @param id
	 * @return
	 */
	public Exam findExamById(String id){
		try {
			Exam exam = (Exam) getSession().get("com.openkx.kxexam.domain.Exam", id);
			return exam;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 查询一条答案记录 对象 *
	 * @param id
	 * @return
	 */
	public Answer findAnswerById(String id){
		try {
			Answer answer = (Answer) getSession().get("com.openkx.kxexam.domain.Answer", id);
			return answer;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 查询一个用户对象*
	 * @param id
	 * @return
	 */
	public User findUserById(String id){
		try {
			User user = (User) getSession().get("com.openkx.kxexam.domain.User", id);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
}