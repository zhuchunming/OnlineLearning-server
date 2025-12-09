package com.model;

import java.util.Date;

/**
 * (testerrorset)错题集实体类
 */
public class Testerrorset extends ComData{
	private static final long serialVersionUID = -5069714846901593754L;
	private Integer id;    		//测试明细id
	private String sno;     	//账号
	private Integer num;		//错误次数
	private Integer qid;    		//测试明细id
	private String useranswer;  //用户答案
	private Date createtime;    //创建时间
	private Date lastopdate;	//最后修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String getUseranswer() {
		return useranswer;
	}

	public void setUseranswer(String useranswer) {
		this.useranswer = useranswer;
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
}