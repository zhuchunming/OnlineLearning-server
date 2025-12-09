package com.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TeachersMapper;
import com.model.Teachers;
import com.util.PageBean;
@Service
public class TeachersServiceImpl implements TeachersService {
        
	@Autowired
	private TeachersMapper teachersMapper;

	//查询多条记录
	public List<Teachers> queryTeachersList(Teachers teachers,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(teachers, page);
		
		List<Teachers> getTeachers = teachersMapper.query(map);
		
		return getTeachers;
	}
	
	//得到记录总数
	@Override
	public int getCount(Teachers teachers) {
		Map<String, Object> map = getQueryMap(teachers, null);
		int count = teachersMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Teachers teachers,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(teachers!=null){
			map.put("tno", teachers.getTno());
			map.put("password", teachers.getPassword());
			map.put("tname", teachers.getTname());
			map.put("gender", teachers.getGender());
			map.put("phone", teachers.getPhone());
			map.put("email", teachers.getEmail());
			map.put("avatar", teachers.getAvatar());
			map.put("addtime", teachers.getAddtime());
			map.put("sort", teachers.getSort());
			map.put("condition", teachers.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertTeachers(Teachers teachers) throws Exception {
		return teachersMapper.insertTeachers(teachers);
	}

	//根据ID删除
	public int deleteTeachers(String id) throws Exception {
		return teachersMapper.deleteTeachers(id);
	}

	//更新
	public int updateTeachers(Teachers teachers) throws Exception {
		return teachersMapper.updateTeachers(teachers);
	}
	
	//根据ID得到对应的记录
	public Teachers queryTeachersById(String id) throws Exception {
		Teachers po =  teachersMapper.queryTeachersById(id);
		return po;
	}
}

