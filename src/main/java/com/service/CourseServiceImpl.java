package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.CourseMapper;
import com.model.Course;
import com.util.PageBean;
@Service
public class CourseServiceImpl implements CourseService{
        
	@Autowired
	private CourseMapper courseMapper;

	//查询多条记录
	public List<Course> queryCourseList(Course course,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(course, page);
		
		List<Course> getCourse = courseMapper.query(map);
		
		return getCourse;
	}
	
	//得到记录总数
	@Override
	public int getCount(Course course) {
		Map<String, Object> map = getQueryMap(course, null);
		int count = courseMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Course course,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(course!=null){
			map.put("cid", course.getCid());
			map.put("cname", course.getCname());
			map.put("catid", course.getCatid());
			map.put("cimage", course.getCimage());
			map.put("thours", course.getThours());
			map.put("cdesc", course.getCdesc());
			map.put("tno", course.getTno());
			map.put("ctotal", course.getCtotal());
			map.put("addtime", course.getAddtime());
			map.put("sort", course.getSort());
			map.put("condition", course.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertCourse(Course course) throws Exception {
		return courseMapper.insertCourse(course);
	}

	//根据ID删除
	public int deleteCourse(int id) throws Exception {
		return courseMapper.deleteCourse(id);
	}

	//更新
	public int updateCourse(Course course) throws Exception {
		return courseMapper.updateCourse(course);
	}
	
	//根据ID得到对应的记录
	public Course queryCourseById(int id) throws Exception {
		Course po =  courseMapper.queryCourseById(id);
		return po;
	}
}

