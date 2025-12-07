package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Favorites;

public interface FavoritesMapper {

	//返回所有记录
	public List<Favorites> findFavoritesList();
	
	//查询多条记录
	public List<Favorites> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertFavorites(Favorites favorites);

	//根据ID删除
	public int deleteFavorites(int id);
	
	//更新
	public int updateFavorites(Favorites favorites);
	
	//根据ID得到对应的记录
	public Favorites queryFavoritesById(int id);
	
}

