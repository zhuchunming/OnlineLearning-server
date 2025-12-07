package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Course;

public interface CourseMapper {

	//返回所有记录
	public List<Course> findCourseList();
	
	//查询多条记录
	public List<Course> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertCourse(Course course);

	//根据ID删除
	public int deleteCourse(int id);
	
	//更新
	public int updateCourse(Course course);
	
	//根据ID得到对应的记录
	public Course queryCourseById(int id);
	
}

