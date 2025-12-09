package com.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StudentsMapper;
import com.model.Students;
import com.util.PageBean;
@Service
public class StudentsServiceImpl implements StudentsService {
        
	@Autowired
	private StudentsMapper studentsMapper;

	//查询多条记录
	public List<Students> queryStudentsList(Students students,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(students, page);
		
		List<Students> getStudents = studentsMapper.query(map);
		
		return getStudents;
	}
	
	//得到记录总数
	@Override
	public int getCount(Students students) {
		Map<String, Object> map = getQueryMap(students, null);
		int count = studentsMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Students students,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(students!=null){
			map.put("sno", students.getSno());
			map.put("password", students.getPassword());
			map.put("stname", students.getStname());
			map.put("gender", students.getGender());
			map.put("phone", students.getPhone());
			map.put("email", students.getEmail());
			map.put("major", students.getMajor());
			map.put("classes", students.getClasses());
			map.put("avatar", students.getAvatar());
			map.put("registertime", students.getRegistertime());
			map.put("sort", students.getSort());
			map.put("condition", students.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertStudents(Students students) throws Exception {
		return studentsMapper.insertStudents(students);
	}

	//根据ID删除
	public int deleteStudents(String id) throws Exception {
		return studentsMapper.deleteStudents(id);
	}

	//更新
	public int updateStudents(Students students) throws Exception {
		return studentsMapper.updateStudents(students);
	}
	
	//根据ID得到对应的记录
	public Students queryStudentsById(String id) throws Exception {
		Students po =  studentsMapper.queryStudentsById(id);
		return po;
	}
}

