package com.mapper;

import com.model.CommonAudit;

import java.util.List;
import java.util.Map;

public interface CommonAuditMapper {

	//添加
	public int insertAudit(CommonAudit audit);

	//分页查询
	public List<CommonAudit> query(Map<String, Object> map);

	//分页总条数查询
	public int getCount(Map<String, Object> map);

	public CommonAudit queryById(int id);

	//更新
	public void updateAudit(CommonAudit audit);
}

