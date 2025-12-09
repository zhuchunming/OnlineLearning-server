package com.model;

import java.util.Date;

/**
 * (testerrorset)错题集实体类
 */
public class SwitchControl {
	private static final long serialVersionUID = -3833313456535293110L;
	private String name;    	//开关控制name
	private String value;     	//value
	private String value1;		//value1
	private String value2;    	//value2
	private String value3;  	//value3
	private String ifvalid;		//是否生效 0时效 1生效
	private Date createtime;    //创建时间
	private String modifdec;	//修改记录

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getIfvalid() {
		return ifvalid;
	}

	public void setIfvalid(String ifvalid) {
		this.ifvalid = ifvalid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getModifdec() {
		return modifdec;
	}

	public void setModifdec(String modifdec) {
		this.modifdec = modifdec;
	}
}