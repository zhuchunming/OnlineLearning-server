package com.model;

import java.util.Date;

/**
 * (commonaudit)审核表实体类
 */
public class CommonAudit implements java.io.Serializable{
	private static final long serialVersionUID = 6809256970880041699L;
	private Integer id;    			//审核id
	private Integer applydataid;    //审核来源id
	private String applyno;			//审核申请号
	private String applytype;		//审核类型编码
	private String applyname;		//审核类型
	private String opcode;   	//申请用户
	private String opstatus;  		//审核状态 0审核中 1审核驳回 2审核通过
	private Date createtime;    	//创建时间
	private Date lastopdate;		//最后修改时间
	private String modifdec;  		//修改记录

	private String vname;    //视频名称
	private String vfile;    //视频文件
	private String vdescription;    //视频介绍

	public CommonAudit(){}
	public CommonAudit(Integer applydataid, String applyno, String applytype, String applyname, String opcode) {
		this.applydataid = applydataid;
		this.applyno = applyno;
		this.applytype = applytype;
		this.applyname = applyname;
		this.opcode = opcode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplydataid() {
		return applydataid;
	}

	public void setApplydataid(Integer applydataid) {
		this.applydataid = applydataid;
	}

	public String getApplyno() {
		return applyno;
	}

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}

	public String getApplytype() {
		return applytype;
	}

	public void setApplytype(String applytype) {
		this.applytype = applytype;
	}

	public String getApplyname() {
		return applyname;
	}

	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getOpstatus() {
		return opstatus;
	}

	public void setOpstatus(String opstatus) {
		this.opstatus = opstatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastopdate() {
		return lastopdate;
	}

	public void setLastopdate(Date lastopdate) {
		this.lastopdate = lastopdate;
	}

	public String getModifdec() {
		return modifdec;
	}

	public void setModifdec(String modifdec) {
		this.modifdec = modifdec;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
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
}