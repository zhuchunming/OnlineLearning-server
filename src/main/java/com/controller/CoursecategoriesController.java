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
@RequestMapping("/api/coursecategories")
public class CoursecategoriesController{
	
	@Resource
	private CoursecategoriesService coursecategoriesService;
	
	//课程分类列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<Coursecategories>> list(@RequestBody Coursecategories coursecategories, @RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize=Integer.parseInt(req.getParameter("pageSize")); //每页显示记录数
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		int counts = 0;  //总记录数
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		counts = coursecategoriesService.getCount(coursecategories);
		//获取当前页记录
		List<Coursecategories> coursecategoriesList = coursecategoriesService.queryCoursecategoriesList(coursecategories, page);
        
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(coursecategoriesList, counts, page_count);
	}
        
	//添加课程分类
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Coursecategories coursecategories, HttpServletRequest req) throws Exception {
		try {
			//判断用户名是否存在
            Coursecategories coursecategories1 = new Coursecategories();
            coursecategories1.setCatname(coursecategories.getCatname());
            List<Coursecategories> coursecategoriesList = coursecategoriesService.queryCoursecategoriesList(coursecategories1, null);
            if (coursecategoriesList.size() > 0) {
                return Response.error(201, "分类名称已存在，请重新输入");
            }
            else
            {
                coursecategoriesService.insertCoursecategories(coursecategories); //添加
            }
   
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
    
	//删除课程分类
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			coursecategoriesService.deleteCoursecategories(id); //删除
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//修改课程分类
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Coursecategories coursecategories, HttpServletRequest req) throws Exception {
		try {
			coursecategoriesService.updateCoursecategories(coursecategories); //修改
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//返回课程分类详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Coursecategories coursecategories=coursecategoriesService.queryCoursecategoriesById(id); //根据ID查询
			return Response.success(coursecategories);
			} catch (Exception e) {
			return Response.error();
		}
       
	}
    
}

