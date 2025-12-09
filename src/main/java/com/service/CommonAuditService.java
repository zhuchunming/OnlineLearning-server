package com.service;

import com.model.CommonAudit;
import com.util.PageBean;

import java.util.List;

public interface CommonAuditService {
	
	//查询审核数据
	public List<CommonAudit> queryAuditList(CommonAudit audit, PageBean page) throws Exception;

	//查询审核总条数
	public int getCount(CommonAudit audit);

	/**
	 * 审核数据新增
	 * @param applydataid 审核来源id
	 * @param applytype 审核类型
	 * @param opcode 申请用户
	 * @return
	 * @throws Exception
	 */
	public String insertAudit(int applydataid ,String applytype ,String applyname,String opcode) throws Exception ;

	//审核
	public void audit(CommonAudit audit);
}

