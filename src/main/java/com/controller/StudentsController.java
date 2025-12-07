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
@RequestMapping("/api/students")
public class StudentsController{
	
	@Resource
	private StudentsService studentsService;
	
	//学生列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<Students>> list(@RequestBody Students students, @RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize=Integer.parseInt(req.getParameter("pageSize")); //每页显示记录数
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		int counts = 0;  //总记录数
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		counts = studentsService.getCount(students);
		//获取当前页记录
		List<Students> studentsList = studentsService.queryStudentsList(students, page);
        
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(studentsList, counts, page_count);
	}
        
	//添加学生
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Students students, HttpServletRequest req) throws Exception {
		try {
			//判断用户名是否存在
            Students students1 = new Students();
            students1.setSno(students.getSno());
            List<Students> studentsList = studentsService.queryStudentsList(students1, null);
            if (studentsList.size() > 0) {
                return Response.error(201, "账号已存在，请重新输入");
            }
            else
            {
                studentsService.insertStudents(students); //添加
            }
   
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
    
	//删除学生
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			String id = req.getParameter("id");
			studentsService.deleteStudents(id); //删除
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//修改学生
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Students students, HttpServletRequest req) throws Exception {
		try {
			studentsService.updateStudents(students); //修改
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//返回学生详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			String id = req.getParameter("id");
			Students students=studentsService.queryStudentsById(id); //根据ID查询
			return Response.success(students);
			} catch (Exception e) {
			return Response.error();
		}
       
	}
    
	//登录
    @ResponseBody
    @PostMapping(value = "/login")
    @CrossOrigin
    public Response login(@RequestBody Students students, HttpServletRequest request) throws Exception {

        try {
            List<Students> studentsList = studentsService.queryStudentsList(students, null);   //查询

            //判断是否有数据
            if (studentsList.size() > 0) {
                return Response.success(studentsList.get(0));  //登录成功
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
    public Response updatePwd(@RequestBody Students students, HttpServletRequest req) throws Exception {
        try {
            Students students1 = studentsService.queryStudentsById(students.getSno()); //根据ID查询
            //判断原密码是否正确
            if (!students1.getPassword().equals(students.getBy1())) {
                return Response.error(201, "原密码错误");
            } else {
                students1.setPassword(students.getBy2());  //新密码
                studentsService.updateStudents(students1); //修改新密码
            }

        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

}

