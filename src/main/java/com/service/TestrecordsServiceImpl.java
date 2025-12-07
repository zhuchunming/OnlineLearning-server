package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TestrecordsMapper;
import com.model.Testrecords;
import com.util.PageBean;
@Service
public class TestrecordsServiceImpl implements TestrecordsService{
        
	@Autowired
	private TestrecordsMapper testrecordsMapper;

	//查询多条记录
	public List<Testrecords> queryTestrecordsList(Testrecords testrecords,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(testrecords, page);
		
		List<Testrecords> getTestrecords = testrecordsMapper.query(map);
		
		return getTestrecords;
	}
	
	//得到记录总数
	@Override
	public int getCount(Testrecords testrecords) {
		Map<String, Object> map = getQueryMap(testrecords, null);
		int count = testrecordsMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Testrecords testrecords,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(testrecords!=null){
			map.put("id", testrecords.getId());
			map.put("cid", testrecords.getCid());
			map.put("sno", testrecords.getSno());
			map.put("questioncount", testrecords.getQuestioncount());
			map.put("correctcount", testrecords.getCorrectcount());
			map.put("errorcount", testrecords.getErrorcount());
			map.put("score", testrecords.getScore());
			map.put("testtime", testrecords.getTesttime());
			map.put("sort", testrecords.getSort());
			map.put("condition", testrecords.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertTestrecords(Testrecords testrecords) throws Exception {
		return testrecordsMapper.insertTestrecords(testrecords);
	}

	//根据ID删除
	public int deleteTestrecords(int id) throws Exception {
		return testrecordsMapper.deleteTestrecords(id);
	}

	//更新
	public int updateTestrecords(Testrecords testrecords) throws Exception {
		return testrecordsMapper.updateTestrecords(testrecords);
	}
	
	//根据ID得到对应的记录
	public Testrecords queryTestrecordsById(int id) throws Exception {
		Testrecords po =  testrecordsMapper.queryTestrecordsById(id);
		return po;
	}
}

