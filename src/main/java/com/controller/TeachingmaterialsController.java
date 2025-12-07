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
@RequestMapping("/api/teachingmaterials")
public class TeachingmaterialsController {

	@Resource
	private TeachingmaterialsService teachingmaterialsService;

	// 教学课件列表
	@RequestMapping(value = "/list")
	@CrossOrigin
	public Response<List<Teachingmaterials>> list(@RequestBody Teachingmaterials teachingmaterials,
			@RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize = Integer.parseInt(req.getParameter("pageSize")); // 每页显示记录数
		int offset = (currentPage - 1) * pageSize; // 当前页开始记录
		int counts = 0; // 总记录数
		PageBean page = new PageBean(offset, pageSize); // 分页对象
		// 查询记录总数
		counts = teachingmaterialsService.getCount(teachingmaterials);
		// 获取当前页记录
		List<Teachingmaterials> teachingmaterialsList = teachingmaterialsService
				.queryTeachingmaterialsList(teachingmaterials, page);
		// 遍历
		for (Teachingmaterials teachingmaterials2 : teachingmaterialsList) {
			teachingmaterials2.setDescription(removeHTML.Html2Text(teachingmaterials2.getDescription()));

		}

		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM
				: counts / PageBean.PAGE_IETM + 1; // 总页数
		return Response.success(teachingmaterialsList, counts, page_count);
	}

	// 添加教学课件
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Teachingmaterials teachingmaterials, HttpServletRequest req) throws Exception {
		try {
			teachingmaterialsService.insertTeachingmaterials(teachingmaterials); // 添加

		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 删除教学课件
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			teachingmaterialsService.deleteTeachingmaterials(id); // 删除
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 修改教学课件
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Teachingmaterials teachingmaterials, HttpServletRequest req) throws Exception {
		try {
			teachingmaterialsService.updateTeachingmaterials(teachingmaterials); // 修改
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 返回教学课件详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Teachingmaterials teachingmaterials = teachingmaterialsService.queryTeachingmaterialsById(id); // 根据ID查询
			return Response.success(teachingmaterials);
		} catch (Exception e) {
			return Response.error();
		}

	}

	@ResponseBody
	@PostMapping(value = "/listByCourse")
	@CrossOrigin
	public Response listByCourse(@RequestBody Teachingmaterials teachingmaterials) throws Exception {
		try {
			// 根据课程id查询课件列表
			List<Teachingmaterials> materialList = teachingmaterialsService
					.queryTeachingmaterialsList(teachingmaterials, null);
			return Response.success(materialList);
		} catch (Exception e) {
			return Response.error();
		}
	}

}
