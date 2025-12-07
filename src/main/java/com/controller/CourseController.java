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
@RequestMapping("/api/course")
public class CourseController {

	@Resource
	private CourseService courseService;

	// 课程列表
	@RequestMapping(value = "/list")
	@CrossOrigin
	public Response<List<Course>> list(@RequestBody Course course, @RequestParam Integer currentPage,
			HttpServletRequest req) throws Exception {
		int pageSize = Integer.parseInt(req.getParameter("pageSize")); // 每页显示记录数
		int offset = (currentPage - 1) * pageSize; // 当前页开始记录
		int counts = 0; // 总记录数
		PageBean page = new PageBean(offset, pageSize); // 分页对象
		// 查询记录总数
		counts = courseService.getCount(course);
		// 获取当前页记录
		List<Course> courseList = courseService.queryCourseList(course, page);
		// 遍历
		for (Course course2 : courseList) {
			course2.setCdesc(removeHTML.Html2Text(course2.getCdesc()));

		}

		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM
				: counts / PageBean.PAGE_IETM + 1; // 总页数
		return Response.success(courseList, counts, page_count);
	}

	// 添加课程
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Course course, HttpServletRequest req) throws Exception {
		try {
			courseService.insertCourse(course); // 添加

		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 删除课程
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			courseService.deleteCourse(id); // 删除
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 修改课程
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Course course, HttpServletRequest req) throws Exception {
		try {
			courseService.updateCourse(course); // 修改
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 返回课程详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Course course = courseService.queryCourseById(id); // 根据ID查询
			// +1
			course.setCtotal(course.getCtotal() + 1);
			courseService.updateCourse(course);

			return Response.success(course);
		} catch (Exception e) {
			return Response.error();
		}

	}

}
