package com.service.impl;
import com.mapper.QuestionsMapper;
import com.model.Questions;
import com.service.QuestionsService;
import com.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class QuestionsServiceImpl implements QuestionsService {
        
	@Autowired
	private QuestionsMapper questionsMapper;

	//查询多条记录
	public List<Questions> queryQuestionsList(Questions questions,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(questions, page);
		
		List<Questions> getQuestions = questionsMapper.query(map);
		
		return getQuestions;
	}
	
	//得到记录总数
	@Override
	public int getCount(Questions questions) {
		Map<String, Object> map = getQueryMap(questions, null);
		int count = questionsMapper.getCount(map);
		return count;
	}

	//得到用户错误记录总数
	@Override
	public int getSnoErrCount(String sno) {
		return questionsMapper.getSnoErrCount(sno);
	}

	@Override
	public List<Questions> querySnoErrQuestions(String sno, PageBean page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		PageBean.setPageMap(map, page);
		return questionsMapper.querySnoErrQuestions(map);
	}

	private Map<String, Object> getQueryMap(Questions questions,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		PageBean.setPageMap(map, page);
		if(questions!=null){
			map.put("qid", questions.getQid());
			map.put("cid", questions.getCid());
			map.put("content", questions.getContent());
			map.put("answer", questions.getAnswer());
			map.put("analysis", questions.getAnalysis());
			map.put("sort", questions.getSort());
			map.put("condition", questions.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertQuestions(Questions questions) throws Exception {
		return questionsMapper.insertQuestions(questions);
	}

	//根据ID删除
	public int deleteQuestions(int id) throws Exception {
		return questionsMapper.deleteQuestions(id);
	}

	//更新
	public int updateQuestions(Questions questions) throws Exception {
		return questionsMapper.updateQuestions(questions);
	}
	
	//根据ID得到对应的记录
	public Questions queryQuestionsById(int id) throws Exception {
		Questions po =  questionsMapper.queryQuestionsById(id);
		return po;
	}
}

