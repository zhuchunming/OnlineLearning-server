package com.model;
import java.util.List;

/**
* (admin)管理员实体类
*/
public class Admin extends ComData{
	
	private static final long serialVersionUID = 672882647366374L;
	private Integer aid;    //管理员id
	private String aname;    //账号
	private String loginpassword;    //登录密码
	private String arole;    //身份

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String getArole() {
		return arole;
	}

	public void setArole(String arole) {
		this.arole = arole;
	}

}

