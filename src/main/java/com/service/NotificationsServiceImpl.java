package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.NotificationsMapper;
import com.model.Notifications;
import com.util.PageBean;
@Service
public class NotificationsServiceImpl implements NotificationsService{
        
	@Autowired
	private NotificationsMapper notificationsMapper;

	//查询多条记录
	public List<Notifications> queryNotificationsList(Notifications notifications,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(notifications, page);
		
		List<Notifications> getNotifications = notificationsMapper.query(map);
		
		return getNotifications;
	}
	
	//得到记录总数
	@Override
	public int getCount(Notifications notifications) {
		Map<String, Object> map = getQueryMap(notifications, null);
		int count = notificationsMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Notifications notifications,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(notifications!=null){
			map.put("annid", notifications.getAnnid());
			map.put("title", notifications.getTitle());
			map.put("content", notifications.getContent());
			map.put("publishtime", notifications.getPublishtime());
			map.put("sort", notifications.getSort());
			map.put("condition", notifications.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}
		
	//添加
	public int insertNotifications(Notifications notifications) throws Exception {
		return notificationsMapper.insertNotifications(notifications);
	}

	//根据ID删除
	public int deleteNotifications(int id) throws Exception {
		return notificationsMapper.deleteNotifications(id);
	}

	//更新
	public int updateNotifications(Notifications notifications) throws Exception {
		return notificationsMapper.updateNotifications(notifications);
	}
	
	//根据ID得到对应的记录
	public Notifications queryNotificationsById(int id) throws Exception {
		Notifications po =  notificationsMapper.queryNotificationsById(id);
		return po;
	}
}

