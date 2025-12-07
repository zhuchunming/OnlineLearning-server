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
@RequestMapping("/api/coursemessage")
public class CoursemessageController {

	@Resource
	private CoursemessageService coursemessageService;

	// 互动讨论列表
	@RequestMapping(value = "/list")
	@CrossOrigin
	public Response<List<Coursemessage>> list(@RequestBody Coursemessage coursemessage,
			@RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize = Integer.parseInt(req.getParameter("pageSize")); // 每页显示记录数
		int offset = (currentPage - 1) * pageSize; // 当前页开始记录
		int counts = 0; // 总记录数
		PageBean page = new PageBean(offset, pageSize); // 分页对象
		// 查询记录总数
		counts = coursemessageService.getCount(coursemessage);
		// 获取当前页记录
		List<Coursemessage> coursemessageList = coursemessageService.queryCoursemessageList(coursemessage, page);

		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM
				: counts / PageBean.PAGE_IETM + 1; // 总页数
		return Response.success(coursemessageList, counts, page_count);
	}

	// 添加互动讨论
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Coursemessage coursemessage, HttpServletRequest req) throws Exception {
		try {
			coursemessageService.insertCoursemessage(coursemessage); // 添加

		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 删除互动讨论
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			coursemessageService.deleteCoursemessage(id); // 删除
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 修改互动讨论
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Coursemessage coursemessage, HttpServletRequest req) throws Exception {
		try {
			coursemessageService.updateCoursemessage(coursemessage); // 修改
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 返回互动讨论详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Coursemessage coursemessage = coursemessageService.queryCoursemessageById(id); // 根据ID查询
			return Response.success(coursemessage);
		} catch (Exception e) {
			return Response.error();
		}

	}

	// 根据课程id查询讨论列表
	@ResponseBody
	@PostMapping(value = "/listByCourse")
	@CrossOrigin
	public Response listByCourse(@RequestBody Coursemessage coursemessage) throws Exception {
		try {
			// 根据课程id查询讨论列表
			List<Coursemessage> messageList = coursemessageService.queryCoursemessageList(coursemessage, null);
			return Response.success(messageList);
		} catch (Exception e) {
			return Response.error();
		}
	}

}
