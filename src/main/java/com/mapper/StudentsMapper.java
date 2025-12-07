package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Students;

public interface StudentsMapper {

	//返回所有记录
	public List<Students> findStudentsList();
	
	//查询多条记录
	public List<Students> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertStudents(Students students);

	//根据ID删除
	public int deleteStudents(String id);
	
	//更新
	public int updateStudents(Students students);
	
	//根据ID得到对应的记录
	public Students queryStudentsById(String id);
	
}

