package com.model;
/**
* (teachingvideos)教学视频实体类
*/
public class Teachingvideos extends ComData{
	
	private static final long serialVersionUID = 667188436734324L;
	private Integer vid;    //视频id
	private String vname;    //视频名称
	private Integer cid;    //所属课程
	private String cname;
	private String vfile;    //视频文件
	private String vdescription;    //视频介绍
	private String uploadtime;    //上传时间
	private String opstatus;	//审核状态 0审核中 1审核驳回 2审核通过
	private String opcode;	//申请用户


	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
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

	public String getVfile() {
		return vfile;
	}

	public void setVfile(String vfile) {
		this.vfile = vfile;
	}

	public String getVdescription() {
		return vdescription;
	}

	public void setVdescription(String vdescription) {
		this.vdescription = vdescription;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getOpstatus() {
		return opstatus;
	}

	public void setOpstatus(String opstatus) {
		this.opstatus = opstatus;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
}

