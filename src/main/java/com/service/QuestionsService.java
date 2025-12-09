package com.service;
import com.model.Questions;
import com.util.PageBean;

import java.util.List;

public interface QuestionsService{
	
	//查询多条记录
	public List<Questions> queryQuestionsList(Questions questions,PageBean page) throws Exception;
 
	//添加
	public int insertQuestions(Questions questions) throws Exception ;
	
	//根据ID删除
	public int deleteQuestions(int id) throws Exception ;
	
	//更新
	public int updateQuestions(Questions questions) throws Exception ;
	
	//根据ID查询单条数据
	public Questions queryQuestionsById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Questions questions);

	//获取用户错误记录总数
	int getSnoErrCount(String sno);

	//获取用户错误记录
	List<Questions> querySnoErrQuestions(String sno, PageBean page);
}

