package com.service;
import java.util.List;

import com.model.Testrecords;
import com.util.PageBean;

public interface TestrecordsService{
	
	//查询多条记录
	public List<Testrecords> queryTestrecordsList(Testrecords testrecords,PageBean page) throws Exception;
 
	//添加
	public int insertTestrecords(Testrecords testrecords) throws Exception ;
	
	//根据ID删除
	public int deleteTestrecords(int id) throws Exception ;
	
	//更新
	public int updateTestrecords(Testrecords testrecords) throws Exception ;
	
	//根据ID查询单条数据
	public Testrecords queryTestrecordsById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Testrecords testrecords);

}

