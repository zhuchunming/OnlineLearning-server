package com.model;
import java.util.List;

/**
* (testrecords)测试记录实体类
*/
public class Testrecords extends ComData{
	
	private static final long serialVersionUID = 873234254254687L;
	private Integer id;    //测试id
	private Integer cid;    //课程
	private String cname;
	private String sno;    //账号
	private Integer questioncount;    //做题数量
	private Integer correctcount;    //正确数量
	private Integer errorcount;    //错误数量
	private Object score;    //成绩
	private String testtime;    //测试时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public Integer getQuestioncount() {
		return questioncount;
	}

	public void setQuestioncount(Integer questioncount) {
		this.questioncount = questioncount;
	}

	public Integer getCorrectcount() {
		return correctcount;
	}

	public void setCorrectcount(Integer correctcount) {
		this.correctcount = correctcount;
	}

	public Integer getErrorcount() {
		return errorcount;
	}

	public void setErrorcount(Integer errorcount) {
		this.errorcount = errorcount;
	}

	public Object getScore() {
		return score;
	}

	public void setScore(Object score) {
		this.score = score;
	}

	public String getTesttime() {
		return testtime;
	}

	public void setTesttime(String testtime) {
		this.testtime = testtime;
	}

}

