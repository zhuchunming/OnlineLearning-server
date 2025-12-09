package com.controller;

import com.model.CommonAudit;
import com.response.Response;
import com.service.CommonAuditService;
import com.util.BusinessException;
import com.util.PageBean;
import com.util.removeHTML;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class CommonAuditController {
	
	@Resource
	private CommonAuditService auditService;
	
	//审核记录列表
	@RequestMapping(value="/list")
	@CrossOrigin
	public Response<List<CommonAudit>> list(@RequestBody CommonAudit audit,
											@RequestParam("currentPage") Integer currentPage,
											@RequestParam("pageSize") Integer pageSize) throws Exception {
		int offset = (currentPage - 1) * pageSize; //当前页开始记录
		PageBean page = new PageBean(offset, pageSize); //分页对象
		//查询记录总数
		int counts = auditService.getCount(audit);
		//获取当前页记录
		List<CommonAudit> testrecordsList = auditService.queryAuditList(audit, page);
		for (CommonAudit au : testrecordsList) {
			au.setVdescription(removeHTML.Html2Text(au.getVdescription()));
		}
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1; //总页数
		return Response.success(testrecordsList, counts, page_count);
	}

	//审核
	@ResponseBody
	@PostMapping(value = "/doAudit")
	@CrossOrigin
	public Response doAudit(@RequestBody CommonAudit audit) throws Exception {
		try {
			auditService.audit(audit);
		} catch (BusinessException e){
			return Response.error(500,e.getMessage());
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

}

