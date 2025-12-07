package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Teachingvideos;

public interface TeachingvideosMapper {

	//返回所有记录
	public List<Teachingvideos> findTeachingvideosList();
	
	//查询多条记录
	public List<Teachingvideos> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertTeachingvideos(Teachingvideos teachingvideos);

	//根据ID删除
	public int deleteTeachingvideos(int id);
	
	//更新
	public int updateTeachingvideos(Teachingvideos teachingvideos);
	
	//根据ID得到对应的记录
	public Teachingvideos queryTeachingvideosById(int id);
	
}

