package com.service;
import java.util.List;

import com.model.Teachers;
import com.util.PageBean;

public interface TeachersService{
	
	//查询多条记录
	public List<Teachers> queryTeachersList(Teachers teachers,PageBean page) throws Exception;
 
	//添加
	public int insertTeachers(Teachers teachers) throws Exception ;
	
	//根据ID删除
	public int deleteTeachers(String id) throws Exception ;
	
	//更新
	public int updateTeachers(Teachers teachers) throws Exception ;
	
	//根据ID查询单条数据
	public Teachers queryTeachersById(String id) throws Exception ;
	
	//得到记录总数
	int getCount(Teachers teachers);

}

