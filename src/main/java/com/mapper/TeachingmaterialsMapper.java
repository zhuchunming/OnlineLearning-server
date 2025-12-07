package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Teachingmaterials;

public interface TeachingmaterialsMapper {

	//返回所有记录
	public List<Teachingmaterials> findTeachingmaterialsList();
	
	//查询多条记录
	public List<Teachingmaterials> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertTeachingmaterials(Teachingmaterials teachingmaterials);

	//根据ID删除
	public int deleteTeachingmaterials(int id);
	
	//更新
	public int updateTeachingmaterials(Teachingmaterials teachingmaterials);
	
	//根据ID得到对应的记录
	public Teachingmaterials queryTeachingmaterialsById(int id);
	
}

