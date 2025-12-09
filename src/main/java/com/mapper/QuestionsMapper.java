package com.mapper;

import com.model.Questions;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionsMapper {

	//返回所有记录
	public List<Questions> findQuestionsList();
	
	//查询多条记录
	public List<Questions> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);

	//得到用户错误记录总数
	int getSnoErrCount(String sno);
	
	//添加
	public int insertQuestions(Questions questions);

	//根据ID删除
	public int deleteQuestions(int id);
	
	//更新
	public int updateQuestions(Questions questions);
	
	//根据ID得到对应的记录
	public Questions queryQuestionsById(int id);

	//根据in(ID)得到对应的记录
	public List<Questions> queryQuestionsByIds(@Param("ids") List<Integer> ids);

	//获取用户错误记录
	List<Questions> querySnoErrQuestions(Map<String, Object> map);
}

