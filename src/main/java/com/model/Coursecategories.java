package com.model;
import java.util.List;

/**
* (coursecategories)课程分类实体类
*/
public class Coursecategories extends ComData{
	
	private static final long serialVersionUID = 827226631723836L;
	private Integer catid;    //分类编号
	private String catname;    //分类名称

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

}

