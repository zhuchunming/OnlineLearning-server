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
@RequestMapping("/api/teachers")
public class TeachersController{
	
	@Resource
	private TeachersService teachersService;
	
	//教师列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<Teachers>> list(@RequestBody Teachers teachers, @RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize=Integer.parseInt(req.getParameter("pageSize")); //每页显示记录数
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		int counts = 0;  //总记录数
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		counts = teachersService.getCount(teachers);
		//获取当前页记录
		List<Teachers> teachersList = teachersService.queryTeachersList(teachers, page);
        
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(teachersList, counts, page_count);
	}
        
	//添加教师
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Teachers teachers, HttpServletRequest req) throws Exception {
		try {
			//判断用户名是否存在
            Teachers teachers1 = new Teachers();
            teachers1.setTno(teachers.getTno());
            List<Teachers> teachersList = teachersService.queryTeachersList(teachers1, null);
            if (teachersList.size() > 0) {
                return Response.error(201, "教师编号已存在，请重新输入");
            }
            else
            {
                teachersService.insertTeachers(teachers); //添加
            }
   
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
    
	//删除教师
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			String id = req.getParameter("id");
			teachersService.deleteTeachers(id); //删除
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//修改教师
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Teachers teachers, HttpServletRequest req) throws Exception {
		try {
			teachersService.updateTeachers(teachers); //修改
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//返回教师详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			String id = req.getParameter("id");
			Teachers teachers=teachersService.queryTeachersById(id); //根据ID查询
			return Response.success(teachers);
			} catch (Exception e) {
			return Response.error();
		}
       
	}
    
	//登录
    @ResponseBody
    @PostMapping(value = "/login")
    @CrossOrigin
    public Response login(@RequestBody Teachers teachers, HttpServletRequest request) throws Exception {

        try {
            List<Teachers> teachersList = teachersService.queryTeachersList(teachers, null);   //查询

            //判断是否有数据
            if (teachersList.size() > 0) {
                return Response.success(teachersList.get(0));  //登录成功
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error();
        }
        return Response.error(201, "用户名或密码错误");
    }
	//修改密码
    @ResponseBody
    @PostMapping(value = "/updatePwd")
    @CrossOrigin
    public Response updatePwd(@RequestBody Teachers teachers, HttpServletRequest req) throws Exception {
        try {
            Teachers teachers1 = teachersService.queryTeachersById(teachers.getTno()); //根据ID查询
            //判断原密码是否正确
            if (!teachers1.getPassword().equals(teachers.getBy1())) {
                return Response.error(201, "原密码错误");
            } else {
                teachers1.setPassword(teachers.getBy2());  //新密码
                teachersService.updateTeachers(teachers1); //修改新密码
            }

        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

}

