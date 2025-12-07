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
@RequestMapping("/api/testrecords")
public class TestrecordsController{
	
	@Resource
	private TestrecordsService testrecordsService;
	
	//测试记录列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<Testrecords>> list(@RequestBody Testrecords testrecords, @RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize=Integer.parseInt(req.getParameter("pageSize")); //每页显示记录数
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		int counts = 0;  //总记录数
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		counts = testrecordsService.getCount(testrecords);
		//获取当前页记录
		List<Testrecords> testrecordsList = testrecordsService.queryTestrecordsList(testrecords, page);
        
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(testrecordsList, counts, page_count);
	}
        
	//添加测试记录
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Testrecords testrecords, HttpServletRequest req) throws Exception {
		try {
			testrecordsService.insertTestrecords(testrecords); //添加
   
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
    
	//删除测试记录
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			testrecordsService.deleteTestrecords(id); //删除
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//修改测试记录
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Testrecords testrecords, HttpServletRequest req) throws Exception {
		try {
			testrecordsService.updateTestrecords(testrecords); //修改
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//返回测试记录详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Testrecords testrecords=testrecordsService.queryTestrecordsById(id); //根据ID查询
			return Response.success(testrecords);
			} catch (Exception e) {
			return Response.error();
		}
       
	}
    
}

