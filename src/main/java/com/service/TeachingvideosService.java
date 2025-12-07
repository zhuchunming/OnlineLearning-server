package com.service;
import java.util.List;

import com.model.Teachingvideos;
import com.util.PageBean;

public interface TeachingvideosService{
	
	//查询多条记录
	public List<Teachingvideos> queryTeachingvideosList(Teachingvideos teachingvideos,PageBean page) throws Exception;
 
	//添加
	public int insertTeachingvideos(Teachingvideos teachingvideos) throws Exception ;
	
	//根据ID删除
	public int deleteTeachingvideos(int id) throws Exception ;
	
	//更新
	public int updateTeachingvideos(Teachingvideos teachingvideos) throws Exception ;
	
	//根据ID查询单条数据
	public Teachingvideos queryTeachingvideosById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Teachingvideos teachingvideos);

}

