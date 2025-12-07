package com.service;
import java.util.List;

import com.model.Coursemessage;
import com.util.PageBean;

public interface CoursemessageService{
	
	//查询多条记录
	public List<Coursemessage> queryCoursemessageList(Coursemessage coursemessage,PageBean page) throws Exception;
 
	//添加
	public int insertCoursemessage(Coursemessage coursemessage) throws Exception ;
	
	//根据ID删除
	public int deleteCoursemessage(int id) throws Exception ;
	
	//更新
	public int updateCoursemessage(Coursemessage coursemessage) throws Exception ;
	
	//根据ID查询单条数据
	public Coursemessage queryCoursemessageById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Coursemessage coursemessage);

}

