package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Coursemessage;

public interface CoursemessageMapper {

	//返回所有记录
	public List<Coursemessage> findCoursemessageList();
	
	//查询多条记录
	public List<Coursemessage> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertCoursemessage(Coursemessage coursemessage);

	//根据ID删除
	public int deleteCoursemessage(int id);
	
	//更新
	public int updateCoursemessage(Coursemessage coursemessage);
	
	//根据ID得到对应的记录
	public Coursemessage queryCoursemessageById(int id);
	
}

