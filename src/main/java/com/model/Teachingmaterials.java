package com.model;
import java.util.List;

/**
* (teachingmaterials)教学课件实体类
*/
public class Teachingmaterials extends ComData{
	
	private static final long serialVersionUID = 577646874164281L;
	private Integer cmid;    //课件id
	private String cmname;    //课件名称
	private Integer cid;    //所属课程
	private String cname;
	private String filepath;    //课件文件
	private String description;    //课件介绍
	private String uploadtime;    //上传时间

	public Integer getCmid() {
		return cmid;
	}

	public void setCmid(Integer cmid) {
		this.cmid = cmid;
	}

	public String getCmname() {
		return cmname;
	}

	public void setCmname(String cmname) {
		this.cmname = cmname;
	}

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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

}

