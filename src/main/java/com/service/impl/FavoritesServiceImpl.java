package com.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.FavoritesMapper;
import com.model.Favorites;
import com.util.PageBean;
@Service
public class FavoritesServiceImpl implements FavoritesService {
        
	@Autowired
	private FavoritesMapper favoritesMapper;

	//查询多条记录
	public List<Favorites> queryFavoritesList(Favorites favorites,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(favorites, page);
		
		List<Favorites> getFavorites = favoritesMapper.query(map);
		
		return getFavorites;
	}
	
	//得到记录总数
	@Override
	public int getCount(Favorites favorites) {
		Map<String, Object> map = getQueryMap(favorites, null);
		int count = favoritesMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Favorites favorites,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(favorites!=null){
			map.put("id", favorites.getId());
			map.put("cid", favorites.getCid());
			map.put("sno", favorites.getSno());
			map.put("ctime", favorites.getCtime());
			map.put("sort", favorites.getSort());
			map.put("condition", favorites.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertFavorites(Favorites favorites) throws Exception {
		return favoritesMapper.insertFavorites(favorites);
	}

	//根据ID删除
	public int deleteFavorites(int id) throws Exception {
		return favoritesMapper.deleteFavorites(id);
	}

	//更新
	public int updateFavorites(Favorites favorites) throws Exception {
		return favoritesMapper.updateFavorites(favorites);
	}
	
	//根据ID得到对应的记录
	public Favorites queryFavoritesById(int id) throws Exception {
		Favorites po =  favoritesMapper.queryFavoritesById(id);
		return po;
	}
}

