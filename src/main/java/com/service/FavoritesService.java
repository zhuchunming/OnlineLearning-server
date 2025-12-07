package com.service;
import java.util.List;

import com.model.Favorites;
import com.util.PageBean;

public interface FavoritesService{
	
	//查询多条记录
	public List<Favorites> queryFavoritesList(Favorites favorites,PageBean page) throws Exception;
 
	//添加
	public int insertFavorites(Favorites favorites) throws Exception ;
	
	//根据ID删除
	public int deleteFavorites(int id) throws Exception ;
	
	//更新
	public int updateFavorites(Favorites favorites) throws Exception ;
	
	//根据ID查询单条数据
	public Favorites queryFavoritesById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Favorites favorites);

}

