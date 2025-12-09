package com.mapper;

import com.model.Testrecordlist;

import java.util.List;

public interface TestrecordlistMapper {

	//添加
	public int insertTestrecordlist(List<Testrecordlist> testrecordlist);

	//根据mainid删除
	public int deleteTestrecordlist(int mainid);
	
}

