package com.controller;

import com.model.*;
import com.response.Response;
import com.service.*;
import com.util.PageBean;
import com.util.removeHTML;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController{
	
	@Resource
	private NotificationsService notificationsService;
	
	//通知公告列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<Notifications>> list(@RequestBody Notifications notifications, @RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize=Integer.parseInt(req.getParameter("pageSize")); //每页显示记录数
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		int counts = 0;  //总记录数
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		counts = notificationsService.getCount(notifications);
		//获取当前页记录
		List<Notifications> notificationsList = notificationsService.queryNotificationsList(notifications, page);
		//遍历
		for (Notifications notifications2 : notificationsList) {
			notifications2.setContent(removeHTML.Html2Text(notifications2.getContent()));

		}

		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(notificationsList, counts, page_count);
	}
        
	//添加通知公告
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Notifications notifications, HttpServletRequest req) throws Exception {
		try {
			notificationsService.insertNotifications(notifications); //添加
   
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
    
	//删除通知公告
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			notificationsService.deleteNotifications(id); //删除
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//修改通知公告
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Notifications notifications, HttpServletRequest req) throws Exception {
		try {
			notificationsService.updateNotifications(notifications); //修改
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//返回通知公告详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Notifications notifications=notificationsService.queryNotificationsById(id); //根据ID查询
			return Response.success(notifications);
			} catch (Exception e) {
			return Response.error();
		}
       
	}
    
}

