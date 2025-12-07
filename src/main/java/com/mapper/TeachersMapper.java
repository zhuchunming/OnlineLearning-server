package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Teachers;

public interface TeachersMapper {

	//返回所有记录
	public List<Teachers> findTeachersList();
	
	//查询多条记录
	public List<Teachers> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertTeachers(Teachers teachers);

	//根据ID删除
	public int deleteTeachers(String id);
	
	//更新
	public int updateTeachers(Teachers teachers);
	
	//根据ID得到对应的记录
	public Teachers queryTeachersById(String id);
	
}

