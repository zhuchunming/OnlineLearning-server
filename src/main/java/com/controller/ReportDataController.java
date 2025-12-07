package com.controller;

import com.model.ReportData;
import com.response.Response;
import com.service.ReportDataService;
import com.util.PageBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/ReportData")
public class ReportDataController {

	@Resource
	private ReportDataService reportDataService;

	//查询报表
	@RequestMapping(value="/queryReport")
	@CrossOrigin
	public Response<List<ReportData>> queryReport(@RequestBody ReportData reportdata,HttpServletRequest req) throws Exception {

		reportdata.setSql("select date_format(ltime, '%Y-%m-%d') as name, count(*) as num \n" +
		"from leaves \n" +
		"where flag = '审核通过' \n" +
		"group by date_format(ltime, '%Y-%m-%d') order by name desc");
		List<ReportData> getReportData = reportDataService.report(reportdata);

		req.setAttribute("ReportDataList", getReportData);

		return Response.success(getReportData);
	}

}

