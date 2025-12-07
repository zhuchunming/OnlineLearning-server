package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TeachingvideosMapper;
import com.model.Teachingvideos;
import com.util.PageBean;
@Service
public class TeachingvideosServiceImpl implements TeachingvideosService{
        
	@Autowired
	private TeachingvideosMapper teachingvideosMapper;

	//查询多条记录
	public List<Teachingvideos> queryTeachingvideosList(Teachingvideos teachingvideos,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(teachingvideos, page);
		
		List<Teachingvideos> getTeachingvideos = teachingvideosMapper.query(map);
		
		return getTeachingvideos;
	}
	
	//得到记录总数
	@Override
	public int getCount(Teachingvideos teachingvideos) {
		Map<String, Object> map = getQueryMap(teachingvideos, null);
		int count = teachingvideosMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Teachingvideos teachingvideos,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(teachingvideos!=null){
			map.put("vid", teachingvideos.getVid());
			map.put("vname", teachingvideos.getVname());
			map.put("cid", teachingvideos.getCid());
			map.put("vfile", teachingvideos.getVfile());
			map.put("vdescription", teachingvideos.getVdescription());
			map.put("uploadtime", teachingvideos.getUploadtime());
			map.put("sort", teachingvideos.getSort());
			map.put("condition", teachingvideos.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertTeachingvideos(Teachingvideos teachingvideos) throws Exception {
		return teachingvideosMapper.insertTeachingvideos(teachingvideos);
	}

	//根据ID删除
	public int deleteTeachingvideos(int id) throws Exception {
		return teachingvideosMapper.deleteTeachingvideos(id);
	}

	//更新
	public int updateTeachingvideos(Teachingvideos teachingvideos) throws Exception {
		return teachingvideosMapper.updateTeachingvideos(teachingvideos);
	}
	
	//根据ID得到对应的记录
	public Teachingvideos queryTeachingvideosById(int id) throws Exception {
		Teachingvideos po =  teachingvideosMapper.queryTeachingvideosById(id);
		return po;
	}
}

