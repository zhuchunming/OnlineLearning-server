package com.service;
import com.model.Testrecords;
import com.util.PageBean;

import java.util.List;

public interface TestrecordsService{
	
	//查询多条记录
	public List<Testrecords> queryTestrecordsList(Testrecords testrecords,PageBean page) throws Exception;
 
	//添加
	public void insertTestrecords(Testrecords testrecords) throws Exception ;
	
	//根据ID删除
	public void deleteTestrecords(int id) throws Exception ;
	
	//更新
	public int updateTestrecords(Testrecords testrecords) throws Exception ;
	
	//根据ID查询单条数据
	public Testrecords queryTestrecordsById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Testrecords testrecords);

}

