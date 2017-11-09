package com.openkx.kxexam.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openkx.kxexam.dao.ClassifyDao;
import com.openkx.kxexam.domain.Classify;
import com.openkx.kxexam.domain.ClassifyDto;

@Component
@Transactional
public class ClassifyService {
	
	@Autowired
	private ClassifyDao classifyDao;
	
	/**
	 * 查询分类信息及对应子分类*
	 * @return
	 */
	public List<Classify> findAllClassify(){
		List<Classify> list = classifyDao.findTopClassify();
		for (Classify classify : list) {
			List<Classify> subClassify = classifyDao.findSubClassify(classify.getId());
			classify.setClassifys(subClassify);
		}
		return list;
	}
	
	/**
	 * 查询最顶级分类，主分类*
	 * @return
	 */
	public List<Classify> findTopClassify(){
		return classifyDao.findTopClassify();
	}
		
	/**
	 * 查询分类详情
	 * @param classificaitonId
	 * @return
	 */
	public List<Classify> findClassifyDetails(String classifyId){
		return classifyDao.findSubClassify(classifyId);
	}
	
	/**
	 * 分类创建 *
	 * @param classifyDto
	 */
	public void create(ClassifyDto classifyDto){
		Classify classifiy = new Classify();
		BeanUtils.copyProperties(classifyDto, classifiy, Classify.class);
		classifiy.setParentid(classifyDto.getClassify());
		classifyDao.save(classifiy);
	}
	
	public Classify findClassifyById(String id){
		try {
			Classify classify = classifyDao.findClassifyById(id);
			return classify;
		} catch (Exception e) {
			throw e;
		}
	}
}
