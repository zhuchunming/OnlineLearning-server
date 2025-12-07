package com.model;
import java.util.List;

/**
* (course)课程实体类
*/
public class Course extends ComData{
	
	private static final long serialVersionUID = 483647385516251L;
	private Integer cid;    //课程id
	private String cname;    //课程名称
	private Integer catid;    //课程分类
	private String catname;
	private String cimage;    //课程图片
	private Integer thours;    //总课时
	private String cdesc;    //课程介绍
	private String tno;    //授课教师
	private String tname;
	private Integer ctotal;    //浏览量
	private String addtime;    //创建时间

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCatid() {
		return catid;
	}

	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getCimage() {
		return cimage;
	}

	public void setCimage(String cimage) {
		this.cimage = cimage;
	}

	public Integer getThours() {
		return thours;
	}

	public void setThours(Integer thours) {
		this.thours = thours;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getCtotal() {
		return ctotal;
	}

	public void setCtotal(Integer ctotal) {
		this.ctotal = ctotal;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}

