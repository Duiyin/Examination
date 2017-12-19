package com.koala.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koala.domain.Answer;
import com.koala.util.MyPage;

@Component
@Transactional
public class AnswerDao extends BaseDao<Answer> {

	/**
	 * 查询用户做过的试卷记录 *
	 * 
	 * @return
	 */
	public MyPage<Answer> findUserAnswer(String userid, int page, int pagesize, String keyword) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Answer.class);
			dc.add(Restrictions.eq("userid", userid));
			dc.addOrder(Order.desc("ctime"));
			try {
				if (pagesize <= 0) {
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
	 * 答案保存
	 * 
	 * @param answer
	 */
	public void save(Answer answer) {
		getSession().save(answer);
	}

}
