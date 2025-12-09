package com.model;

import java.util.Date;

/**
* (testrecordlist)测试记录明细实体类
*/
public class Testrecordlist {
	private static final long serialVersionUID = 7649634863681390566L;
	private Integer id;    		//测试明细id
	private Integer mainid;     //测试id
	private String sno;  		//账号
	private Integer qid;		//试题id
	private String useranswer;  //用户答案
	private Date testtime;      //测试时间

	public Testrecordlist(){}
	public Testrecordlist(Integer mainid, String sno, Integer qid, String useranswer) {
		this.mainid = mainid;
		this.sno = sno;
		this.qid = qid;
		this.useranswer = useranswer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMainid() {
		return mainid;
	}

	public void setMainid(Integer mainid) {
		this.mainid = mainid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
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

	public Date getTesttime() {
		return testtime;
	}

	public void setTesttime(Date testtime) {
		this.testtime = testtime;
	}
}

