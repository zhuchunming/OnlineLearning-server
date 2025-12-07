package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.CoursemessageMapper;
import com.model.Coursemessage;
import com.util.PageBean;
@Service
public class CoursemessageServiceImpl implements CoursemessageService{
        
	@Autowired
	private CoursemessageMapper coursemessageMapper;

	//查询多条记录
	public List<Coursemessage> queryCoursemessageList(Coursemessage coursemessage,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(coursemessage, page);
		
		List<Coursemessage> getCoursemessage = coursemessageMapper.query(map);
		
		return getCoursemessage;
	}
	
	//得到记录总数
	@Override
	public int getCount(Coursemessage coursemessage) {
		Map<String, Object> map = getQueryMap(coursemessage, null);
		int count = coursemessageMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Coursemessage coursemessage,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(coursemessage!=null){
			map.put("id", coursemessage.getId());
			map.put("cid", coursemessage.getCid());
			map.put("mtitle", coursemessage.getMtitle());
			map.put("content", coursemessage.getContent());
			map.put("sno", coursemessage.getSno());
			map.put("addtime", coursemessage.getAddtime());
			map.put("replyteacher", coursemessage.getReplyteacher());
			map.put("sort", coursemessage.getSort());
			map.put("condition", coursemessage.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertCoursemessage(Coursemessage coursemessage) throws Exception {
		return coursemessageMapper.insertCoursemessage(coursemessage);
	}

	//根据ID删除
	public int deleteCoursemessage(int id) throws Exception {
		return coursemessageMapper.deleteCoursemessage(id);
	}

	//更新
	public int updateCoursemessage(Coursemessage coursemessage) throws Exception {
		return coursemessageMapper.updateCoursemessage(coursemessage);
	}
	
	//根据ID得到对应的记录
	public Coursemessage queryCoursemessageById(int id) throws Exception {
		Coursemessage po =  coursemessageMapper.queryCoursemessageById(id);
		return po;
	}
}

