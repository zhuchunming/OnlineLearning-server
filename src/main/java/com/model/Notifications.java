package com.model;
import java.util.List;

/**
* (notifications)通知公告实体类
*/
public class Notifications extends ComData{
	
	private static final long serialVersionUID = 377623435243285L;
	private Integer annid;    //公告id
	private String title;    //公告标题
	private String content;    //公告内容
	private String publishtime;    //发布时间

	public Integer getAnnid() {
		return annid;
	}

	public void setAnnid(Integer annid) {
		this.annid = annid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

}

