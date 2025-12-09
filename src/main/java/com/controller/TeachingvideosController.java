package com.controller;

import com.model.Teachingvideos;
import com.response.Response;
import com.service.TeachingvideosService;
import com.util.BusinessException;
import com.util.PageBean;
import com.util.removeHTML;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/teachingvideos")
public class TeachingvideosController {

	@Resource
	private TeachingvideosService teachingvideosService;

	// 教学视频列表
	@RequestMapping(value = "/list")
	@CrossOrigin
	public Response<List<Teachingvideos>> list(@RequestBody Teachingvideos teachingvideos,
			@RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize = Integer.parseInt(req.getParameter("pageSize")); // 每页显示记录数
		int offset = (currentPage - 1) * pageSize; // 当前页开始记录
		int counts = 0; // 总记录数
		PageBean page = new PageBean(offset, pageSize); // 分页对象
		// 查询记录总数
		counts = teachingvideosService.getCount(teachingvideos);
		// 获取当前��记录
		List<Teachingvideos> teachingvideosList = teachingvideosService.queryTeachingvideosList(teachingvideos, page);
		// 遍历
		for (Teachingvideos teachingvideos2 : teachingvideosList) {
			teachingvideos2.setVdescription(removeHTML.Html2Text(teachingvideos2.getVdescription()));

		}

		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM
				: counts / PageBean.PAGE_IETM + 1; // 总页数
		return Response.success(teachingvideosList, counts, page_count);
	}

	// 添加教学视频
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Teachingvideos teachingvideos, HttpServletRequest req) throws Exception {
		try {
			return Response.success(teachingvideosService.insertTeachingvideos(teachingvideos));
		} catch (BusinessException e) {
			return Response.error(500,e.getMessage());
		} catch (Exception e) {
			return Response.error();
		}
	}

	// 删除教学视频
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			teachingvideosService.deleteTeachingvideos(id); // 删除
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 修改教学视频
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Teachingvideos teachingvideos, HttpServletRequest req) throws Exception {
		try {
			teachingvideosService.updateTeachingvideos(teachingvideos); // 修改
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 返回教学视频详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Teachingvideos teachingvideos = teachingvideosService.queryTeachingvideosById(id); // 根据ID查询
			return Response.success(teachingvideos);
		} catch (Exception e) {
			return Response.error();
		}

	}

	// 根据课程id查询视频列表
	@ResponseBody
	@PostMapping(value = "/listByCourse")
	@CrossOrigin
	public Response listByCourse(@RequestBody Teachingvideos teachingvideos) throws Exception {
		try {
			// 根据课程id查询视频列表
			List<Teachingvideos> videoList = teachingvideosService.queryTeachingvideosList(teachingvideos, null);
			return Response.success(videoList);
		} catch (Exception e) {
			return Response.error();
		}
	}

}
