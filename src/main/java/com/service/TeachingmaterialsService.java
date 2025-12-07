package com.service;
import java.util.List;

import com.model.Teachingmaterials;
import com.util.PageBean;

public interface TeachingmaterialsService{
	
	//查询多条记录
	public List<Teachingmaterials> queryTeachingmaterialsList(Teachingmaterials teachingmaterials,PageBean page) throws Exception;
 
	//添加
	public int insertTeachingmaterials(Teachingmaterials teachingmaterials) throws Exception ;
	
	//根据ID删除
	public int deleteTeachingmaterials(int id) throws Exception ;
	
	//更新
	public int updateTeachingmaterials(Teachingmaterials teachingmaterials) throws Exception ;
	
	//根据ID查询单条数据
	public Teachingmaterials queryTeachingmaterialsById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Teachingmaterials teachingmaterials);

}

