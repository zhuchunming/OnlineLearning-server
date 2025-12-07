package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Testrecords;

public interface TestrecordsMapper {

	//返回所有记录
	public List<Testrecords> findTestrecordsList();
	
	//查询多条记录
	public List<Testrecords> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertTestrecords(Testrecords testrecords);

	//根据ID删除
	public int deleteTestrecords(int id);
	
	//更新
	public int updateTestrecords(Testrecords testrecords);
	
	//根据ID得到对应的记录
	public Testrecords queryTestrecordsById(int id);
	
}

