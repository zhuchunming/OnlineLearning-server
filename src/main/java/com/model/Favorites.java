package com.model;
import java.util.List;

/**
* (favorites)收藏实体类
*/
public class Favorites extends ComData{
	
	private static final long serialVersionUID = 456656811847822L;
	private Integer id;    //收藏id
	private Integer cid;    //课程id
	private String sno;    //账号
	private String ctime;    //收藏时间

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

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

}

