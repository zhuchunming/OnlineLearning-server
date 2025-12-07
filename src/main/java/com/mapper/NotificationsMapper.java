package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Notifications;

public interface NotificationsMapper {

	//返回所有记录
	public List<Notifications> findNotificationsList();
	
	//查询多条记录
	public List<Notifications> query(Map<String,Object> inputParam);
	
	//得到记录总数
	int getCount(Map<String,Object> inputParam);
	
	//添加
	public int insertNotifications(Notifications notifications);

	//根据ID删除
	public int deleteNotifications(int id);
	
	//更新
	public int updateNotifications(Notifications notifications);
	
	//根据ID得到对应的记录
	public Notifications queryNotificationsById(int id);
	
}

