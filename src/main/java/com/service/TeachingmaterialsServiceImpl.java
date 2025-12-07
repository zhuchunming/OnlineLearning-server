package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TeachingmaterialsMapper;
import com.model.Teachingmaterials;
import com.util.PageBean;
@Service
public class TeachingmaterialsServiceImpl implements TeachingmaterialsService{
        
	@Autowired
	private TeachingmaterialsMapper teachingmaterialsMapper;

	//查询多条记录
	public List<Teachingmaterials> queryTeachingmaterialsList(Teachingmaterials teachingmaterials,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(teachingmaterials, page);
		
		List<Teachingmaterials> getTeachingmaterials = teachingmaterialsMapper.query(map);
		
		return getTeachingmaterials;
	}
	
	//得到记录总数
	@Override
	public int getCount(Teachingmaterials teachingmaterials) {
		Map<String, Object> map = getQueryMap(teachingmaterials, null);
		int count = teachingmaterialsMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Teachingmaterials teachingmaterials,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(teachingmaterials!=null){
			map.put("cmid", teachingmaterials.getCmid());
			map.put("cmname", teachingmaterials.getCmname());
			map.put("cid", teachingmaterials.getCid());
			map.put("filepath", teachingmaterials.getFilepath());
			map.put("description", teachingmaterials.getDescription());
			map.put("uploadtime", teachingmaterials.getUploadtime());
			map.put("sort", teachingmaterials.getSort());
			map.put("condition", teachingmaterials.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertTeachingmaterials(Teachingmaterials teachingmaterials) throws Exception {
		return teachingmaterialsMapper.insertTeachingmaterials(teachingmaterials);
	}

	//根据ID删除
	public int deleteTeachingmaterials(int id) throws Exception {
		return teachingmaterialsMapper.deleteTeachingmaterials(id);
	}

	//更新
	public int updateTeachingmaterials(Teachingmaterials teachingmaterials) throws Exception {
		return teachingmaterialsMapper.updateTeachingmaterials(teachingmaterials);
	}
	
	//根据ID得到对应的记录
	public Teachingmaterials queryTeachingmaterialsById(int id) throws Exception {
		Teachingmaterials po =  teachingmaterialsMapper.queryTeachingmaterialsById(id);
		return po;
	}
}

