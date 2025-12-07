package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Questions;

public interface QuestionsMapper {

	//返回所有记录
	public List<Questions> findQuestionsList();
	
	//查询多条记录
	public List<Questions> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertQuestions(Questions questions);

	//根据ID删除
	public int deleteQuestions(int id);
	
	//更新
	public int updateQuestions(Questions questions);
	
	//根据ID得到对应的记录
	public Questions queryQuestionsById(int id);
	
}

