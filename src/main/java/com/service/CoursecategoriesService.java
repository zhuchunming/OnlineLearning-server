package com.service;
import java.util.List;

import com.model.Coursecategories;
import com.util.PageBean;

public interface CoursecategoriesService{
	
	//查询多条记录
	public List<Coursecategories> queryCoursecategoriesList(Coursecategories coursecategories,PageBean page) throws Exception;
 
	//添加
	public int insertCoursecategories(Coursecategories coursecategories) throws Exception ;
	
	//根据ID删除
	public int deleteCoursecategories(int id) throws Exception ;
	
	//更新
	public int updateCoursecategories(Coursecategories coursecategories) throws Exception ;
	
	//根据ID查询单条数据
	public Coursecategories queryCoursecategoriesById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Coursecategories coursecategories);

}

