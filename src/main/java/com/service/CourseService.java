package com.service;
import java.util.List;

import com.model.Course;
import com.util.PageBean;

public interface CourseService{
	
	//查询多条记录
	public List<Course> queryCourseList(Course course,PageBean page) throws Exception;
 
	//添加
	public int insertCourse(Course course) throws Exception ;
	
	//根据ID删除
	public int deleteCourse(int id) throws Exception ;
	
	//更新
	public int updateCourse(Course course) throws Exception ;
	
	//根据ID查询单条数据
	public Course queryCourseById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Course course);

}

