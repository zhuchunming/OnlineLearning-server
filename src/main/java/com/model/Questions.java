package com.model;
import java.util.List;

/**
* (questions)试题实体类
*/
public class Questions extends ComData{
	
	private static final long serialVersionUID = 543754588485852L;
	private Integer qid;    //试题id
	private Integer cid;    //所属课程
	private String cname;
	private String content;    //试题内容
	private String answer;    //试题答案
	private String analysis;    //试题解析

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

}

