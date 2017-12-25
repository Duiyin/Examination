package com.koala.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koala.dao.ClassifyDao;
import com.koala.domain.Classify;
import com.koala.domain.ClassifyDto;

@Component
@Transactional
public class ClassifyService {

	@Autowired
	private ClassifyDao classifyDao;

	/**
	 * 查询分类信息及对应子分类*
	 * 
	 * @return
	 */
	public List<Classify> findAllClassify() {
		List<Classify> list = classifyDao.findTopClassify();
		for (Classify classify : list) {
			List<Classify> subClassify = classifyDao.findSubClassify(classify.getId());
			classify.setClassifys(subClassify);
		}
		return list;
	}

	/**
	 * 查询最顶级分类，主分类*
	 * 
	 * @return
	 */
	public List<Classify> findTopClassify() {
		return classifyDao.findTopClassify();
	}

	/**
	 * 查询分类详情
	 * 
	 * @param classificaitonId
	 * @return
	 */
	public List<Classify> findClassifyDetails(String classifyId) {
		return classifyDao.findSubClassify(classifyId);
	}

	/**
	 * 分类创建 *
	 * 
	 * @param classifyDto
	 */
	public void create(ClassifyDto classifyDto) {
		Classify classifiy = new Classify();
		BeanUtils.copyProperties(classifyDto, classifiy, Classify.class);
		classifiy.setParentid(classifyDto.getClassify());
		classifyDao.save(classifiy);
	}

	public Classify findClassifyById(String id) {
		try {
			Classify classify = classifyDao.findClassifyById(id);
			return classify;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 分类信息修改
	 * 
	 * @param classifyId
	 * @param classifyDto
	 * @return
	 */
	public Classify ContactDataUpdate(String classifyId, ClassifyDto classifyDto) {
		Classify classify = classifyDao.findClassifyById(classifyId);
		BeanUtils.copyProperties(classifyDto, classify, Classify.class);
		classify.setParentid(classifyDto.getClassify());
		classifyDao.update(classify);
		return classify;
	}

	/**
	 * 删除 * 判断删除的是否为主分类 如果是 同时查询所属子分类 全部删除 如果否 不影响
	 * 
	 * @param classifyId
	 * @return
	 */
	public Classify delete(String classifyId) {
		Classify classify = classifyDao.findClassifyById(classifyId);
		if (StringUtils.isNotEmpty(classify.getParentid())) {
			classifyDao.delete(classify);
		} else {
			List<Classify> list = classifyDao.findSubClassify(classify.getId());
			for (int i = 0; i < list.size(); i++) {
				classifyDao.delete(list.get(i));
			}
			classifyDao.delete(classify);
		}
		return classify;
	}

	/**
	 * 总数
	 * 
	 * @return
	 */
	public int count() {
		return classifyDao.findAll().size();
	}
}
