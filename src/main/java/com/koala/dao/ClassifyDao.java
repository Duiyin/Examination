package com.koala.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koala.domain.Classify;

@Component
@Transactional
public class ClassifyDao extends BaseDao<Classify> {

	/**
	 * 查询最顶级分类，主分类*
	 * 
	 * @return
	 */
	public List<Classify> findTopClassify() {
		DetachedCriteria dc = DetachedCriteria.forClass(Classify.class);
		dc.add(Restrictions.or(Restrictions.isNull("parentid"), Restrictions.eq("parentid", "")));
		dc.addOrder(Order.asc("ctime"));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		return criteria.list();
	}
	
	/**
	 * 查询子级分类*
	 * 
	 * @param classifyId
	 * @return
	 */
	public List<Classify> findSubClassify(String classifyId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Classify.class);
		dc.add(Restrictions.eq("parentid", classifyId));
		dc.addOrder(Order.asc("ctime"));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		return criteria.list();
	}

	/**
	 * 分类创建 *
	 * 
	 * @param classify
	 */
	public void save(Classify classify) {
		getSession().save(classify);
	}

	public void update(Classify classify) {
		getSession().update(classify);
	}

	public void detele(Classify classify) {
		getSession().delete(classify);
	}
}
