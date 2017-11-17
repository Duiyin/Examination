package com.openkx.kxexam.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openkx.kxexam.domain.Answer;
import com.openkx.kxexam.domain.Exam;
import com.openkx.kxexam.util.MyPage;

@Component
@Transactional
public class AnswerDao extends BaseDao<Answer>{
	
	
	/**
	 * 查询所有 答案列表 *
	 * @param page
	 * @param pagesize
	 * @param keyword
	 * @return
	 */
	public MyPage<Answer> findAllAnswer(int page, int pagesize, String keyword){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Answer.class);
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
	 * 答案保存
	 * @param answer
	 */
	public void save(Answer answer){
		getSession().save(answer);
	}

}
