package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Coursecategories;

public interface CoursecategoriesMapper {

	//返回所有记录
	public List<Coursecategories> findCoursecategoriesList();
	
	//查询多条记录
	public List<Coursecategories> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertCoursecategories(Coursecategories coursecategories);

	//根据ID删除
	public int deleteCoursecategories(int id);
	
	//更新
	public int updateCoursecategories(Coursecategories coursecategories);
	
	//根据ID得到对应的记录
	public Coursecategories queryCoursecategoriesById(int id);
	
}

