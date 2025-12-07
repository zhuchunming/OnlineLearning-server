package com.model;
import java.util.List;

/**
* (coursemessage)互动讨论实体类
*/
public class Coursemessage extends ComData{
	
	private static final long serialVersionUID = 683872712484553L;
	private Integer id;    //课程留言id
	private Integer cid;    //课程
	private String cname;
	private String mtitle;    //留言标题
	private String content;    //留言内容
	private String sno;    //账号
	private String addtime;    //留言时间
	private String replyteacher;    //教师回复

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

	public String getMtitle() {
		return mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getReplyteacher() {
		return replyteacher;
	}

	public void setReplyteacher(String replyteacher) {
		this.replyteacher = replyteacher;
	}

}

