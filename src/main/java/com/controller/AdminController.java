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
@RequestMapping("/api/admin")
public class AdminController{
	
	@Resource
	private AdminService adminService;
	
	//管理员列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<Admin>> list(@RequestBody Admin admin, @RequestParam Integer currentPage, HttpServletRequest req) throws Exception {
		int pageSize=Integer.parseInt(req.getParameter("pageSize")); //每页显示记录数
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		int counts = 0;  //总记录数
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		counts = adminService.getCount(admin);
		//获取当前页记录
		List<Admin> adminList = adminService.queryAdminList(admin, page);
        
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(adminList, counts, page_count);
	}
        
	//添加管理员
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Admin admin, HttpServletRequest req) throws Exception {
		try {
			//判断用户名是否存在
            Admin admin1 = new Admin();
            admin1.setAname(admin.getAname());
            List<Admin> adminList = adminService.queryAdminList(admin1, null);
            if (adminList.size() > 0) {
                return Response.error(201, "账号已存在，请重新输入");
            }
            else
            {
                adminService.insertAdmin(admin); //添加
            }
   
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
    
	//删除管理员
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			adminService.deleteAdmin(id); //删除
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//修改管理员
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Admin admin, HttpServletRequest req) throws Exception {
		try {
			adminService.updateAdmin(admin); //修改
			} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}
	
	//返回管理员详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Admin admin=adminService.queryAdminById(id); //根据ID查询
			return Response.success(admin);
			} catch (Exception e) {
			return Response.error();
		}
       
	}
    
	//登录
    @ResponseBody
    @PostMapping(value = "/login")
    @CrossOrigin
    public Response login(@RequestBody Admin admin, HttpServletRequest request) throws Exception {

        try {
            List<Admin> adminList = adminService.queryAdminList(admin, null);   //查询

            //判断是否有数据
            if (adminList.size() > 0) {
                return Response.success(adminList.get(0));  //登录成功
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
    public Response updatePwd(@RequestBody Admin admin, HttpServletRequest req) throws Exception {
        try {
            Admin admin1 = adminService.queryAdminById(admin.getAid()); //根据ID查询
            //判断原密码是否正确
            if (!admin1.getLoginpassword().equals(admin.getBy1())) {
                return Response.error(201, "原密码错误");
            } else {
                admin1.setLoginpassword(admin.getBy2());  //新密码
                adminService.updateAdmin(admin1); //修改新密码
            }

        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

}

