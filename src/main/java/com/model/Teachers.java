package com.model;
import java.util.List;

/**
* (teachers)教师实体类
*/
public class Teachers extends ComData{
	
	private static final long serialVersionUID = 117468173473151L;
	private String tno;    //教师编号
	private String password;    //密码
	private String tname;    //姓名
	private String gender;    //性别
	private String phone;    //手机号码
	private String email;    //电子邮箱
	private String avatar;    //头像
	private String addtime;    //添加时间

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}

