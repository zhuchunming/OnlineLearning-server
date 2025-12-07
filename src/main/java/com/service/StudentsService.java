package com.service;
import java.util.List;

import com.model.Students;
import com.util.PageBean;

public interface StudentsService{
	
	//查询多条记录
	public List<Students> queryStudentsList(Students students,PageBean page) throws Exception;
 
	//添加
	public int insertStudents(Students students) throws Exception ;
	
	//根据ID删除
	public int deleteStudents(String id) throws Exception ;
	
	//更新
	public int updateStudents(Students students) throws Exception ;
	
	//根据ID查询单条数据
	public Students queryStudentsById(String id) throws Exception ;
	
	//得到记录总数
	int getCount(Students students);

}

