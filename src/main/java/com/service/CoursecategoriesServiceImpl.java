package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.CoursecategoriesMapper;
import com.model.Coursecategories;
import com.util.PageBean;
@Service
public class CoursecategoriesServiceImpl implements CoursecategoriesService{
        
	@Autowired
	private CoursecategoriesMapper coursecategoriesMapper;

	//查询多条记录
	public List<Coursecategories> queryCoursecategoriesList(Coursecategories coursecategories,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(coursecategories, page);
		
		List<Coursecategories> getCoursecategories = coursecategoriesMapper.query(map);
		
		return getCoursecategories;
	}
	
	//得到记录总数
	@Override
	public int getCount(Coursecategories coursecategories) {
		Map<String, Object> map = getQueryMap(coursecategories, null);
		int count = coursecategoriesMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Coursecategories coursecategories,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(coursecategories!=null){
			map.put("catid", coursecategories.getCatid());
			map.put("catname", coursecategories.getCatname());
			map.put("sort", coursecategories.getSort());
			map.put("condition", coursecategories.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertCoursecategories(Coursecategories coursecategories) throws Exception {
		return coursecategoriesMapper.insertCoursecategories(coursecategories);
	}

	//根据ID删除
	public int deleteCoursecategories(int id) throws Exception {
		return coursecategoriesMapper.deleteCoursecategories(id);
	}

	//更新
	public int updateCoursecategories(Coursecategories coursecategories) throws Exception {
		return coursecategoriesMapper.updateCoursecategories(coursecategories);
	}
	
	//根据ID得到对应的记录
	public Coursecategories queryCoursecategoriesById(int id) throws Exception {
		Coursecategories po =  coursecategoriesMapper.queryCoursecategoriesById(id);
		return po;
	}
}

