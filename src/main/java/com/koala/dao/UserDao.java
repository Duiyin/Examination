package com.koala.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koala.domain.User;
import com.koala.util.MyPage;

@Component
@Transactional
public class UserDao extends BaseDao<User>{

	public void save(User user) {
		getSession().save(user);
	}
	
	/**
	 * 判断账号是否重复*
	 * @param account
	 * @return
	 */
	public User findByAccount(String account){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(User.class);
			Disjunction dis = Restrictions.disjunction();
			dis.add(Property.forName("account").eq(account));
			dis.add(Property.forName("identifier").eq(account));
			dis.add(Property.forName("email").eq(account));
			dis.add(Property.forName("mobile").eq(account));
			dc.add(dis);
			List<User> list = findAllByCriteria(dc);
			try {
				return list.get(0);
			} catch (Exception e) {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void update(User user){
		getSession().update(user);
	}
	
	public MyPage<User> findAllUser(int page, int pagesize, String keyword) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		Disjunction dis = Restrictions.disjunction();
		dis.add(Property.forName("rolename").eq("管理员"));
		dis.add(Property.forName("rolename").eq("特级用户"));
		dis.add(Property.forName("rolename").eq("普通用户"));
		dc.add(dis);
		dc.addOrder(Order.desc("createtime"));
		try {
			if(pagesize <=0){
				pagesize = 20;
			}
		} catch (Exception e) {
		}
		return findPageByCriteria(dc, pagesize, page);
	}
}
