package com.service.impl;

import com.enums.AuditStatus;
import com.mapper.CommonAuditMapper;
import com.mapper.TeachingvideosMapper;
import com.model.CommonAudit;
import com.model.Teachingvideos;
import com.service.CommonAuditService;
import com.util.BusinessException;
import com.util.PageBean;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class CommonAuditServiceImpl implements CommonAuditService {

	@Autowired
	private CommonAuditMapper auditMapper;

	@Autowired
	private TeachingvideosMapper teachingvideosMapper;

	/**
	 * 审核数据查询
	 * @param audit
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CommonAudit> queryAuditList(CommonAudit audit, PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(audit, page);
		return auditMapper.query(map);
	}

	@Override
	public int getCount(CommonAudit audit) {
		Map<String, Object> map =getQueryMap(audit,null);
		return auditMapper.getCount(map);
	}

	private Map<String, Object> getQueryMap(CommonAudit audit, PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(audit!=null){
			map.put("applyno", audit.getApplyno());
			map.put("applytype", audit.getApplytype());
			map.put("opcode", audit.getOpcode());
			if(ObjectUtils.isEmpty(audit.getOpstatus())){
				map.put("opstatus", "0");
			}else{
				map.put("opstatus", audit.getOpstatus());
			}
		}
		PageBean.setPageMap(map, page);
		return map;
	}

	/**
	 * 审核数据新增
	 * @param applydataid 审核来源id
	 * @param applytype 审核类型
	 * @param applyuser 申请用户
	 * @return
	 * @throws Exception
	 */
	@Override
	public String insertAudit(int applydataid, String applytype, String applyname, String applyuser) throws Exception {
		if(ObjectUtils.isEmpty(applydataid)){
			throw new BusinessException("审核来源Id不能为空applydataid");
		}
		if(ObjectUtils.isEmpty(applytype)){
			throw new BusinessException("审核类型不能为空applytype");
		}
		if(ObjectUtils.isEmpty(applyuser)){
			throw new BusinessException("申请用户不能为空applyuser");
		}
		//审核申请号
		String applyno = getApplyno(applytype);
		CommonAudit audit = new CommonAudit(applydataid,applyno,applytype,applyname,applyuser);
		auditMapper.insertAudit(audit);
		return applyno;
	}

	@Override
	public void audit(CommonAudit audit) {
		if(AuditStatus.PASS.getCode().equals(audit.getOpstatus()) ||
				AuditStatus.REJECT.getCode().equals(audit.getOpstatus())){
			//更新审核表
			auditMapper.updateAudit(audit);
			//更新视频表
			Teachingvideos video = new Teachingvideos();
			video.setVid(audit.getApplydataid());
			video.setOpstatus(audit.getOpstatus());
			teachingvideosMapper.updateTeachingvideos(video);
		}else{
			throw new BuilderException("审核状态异常：" + audit.getOpstatus());
		}
	}

	/**
	 * 生成申请号
	 * @return 申请号
	 */
	public String getApplyno(String applytype) {
		// 生成当前时间戳（年月日时分秒）
		String timestamp = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		Random random = new Random();
		int randomNum = random.nextInt(10000);
		// 生成4位随机数
		String randomNumStr = String.format("%04d", randomNum);
		// 拼接申请号
		return timestamp + applytype + randomNum;
	}
}

