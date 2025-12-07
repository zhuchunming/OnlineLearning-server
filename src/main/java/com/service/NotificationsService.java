package com.service;
import java.util.List;

import com.model.Notifications;
import com.util.PageBean;

public interface NotificationsService{
	
	//查询多条记录
	public List<Notifications> queryNotificationsList(Notifications notifications,PageBean page) throws Exception;
 
	//添加
	public int insertNotifications(Notifications notifications) throws Exception ;
	
	//根据ID删除
	public int deleteNotifications(int id) throws Exception ;
	
	//更新
	public int updateNotifications(Notifications notifications) throws Exception ;
	
	//根据ID查询单条数据
	public Notifications queryNotificationsById(int id) throws Exception ;
	
	//得到记录总数
	int getCount(Notifications notifications);

}

