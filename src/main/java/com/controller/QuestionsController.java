package com.controller;

import com.model.Questions;
import com.response.Response;
import com.service.QuestionsService;
import com.util.PageBean;
import com.util.removeHTML;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

	@Resource
	private QuestionsService questionsService;

	// 试题列表
	@RequestMapping(value = "/list")
	@CrossOrigin
	public Response<List<Questions>> list(@RequestBody Questions questions, @RequestParam Integer currentPage,
			HttpServletRequest req) throws Exception {
		int pageSize = Integer.parseInt(req.getParameter("pageSize")); // 每页显示记录数
		int offset = (currentPage - 1) * pageSize; // 当前页开始记录
		int counts = 0; // 总记录数
		PageBean page = new PageBean(offset, pageSize); // 分页对象
		// 查询记录总数
		counts = questionsService.getCount(questions);
		// 获取当前页记录
		List<Questions> questionsList = questionsService.queryQuestionsList(questions, page);
		// 遍历
		for (Questions questions2 : questionsList) {
			questions2.setContent(removeHTML.Html2Text(questions2.getContent()));

		}

		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM
				: counts / PageBean.PAGE_IETM + 1; // 总页数
		return Response.success(questionsList, counts, page_count);
	}

	// 添加试题
	@ResponseBody
	@PostMapping(value = "/add")
	@CrossOrigin
	public Response add(@RequestBody Questions questions, HttpServletRequest req) throws Exception {
		try {
			questionsService.insertQuestions(questions); // 添加

		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 删除试题
	@ResponseBody
	@PostMapping(value = "/del")
	@CrossOrigin
	public Response del(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			questionsService.deleteQuestions(id); // 删除
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 修改试题
	@ResponseBody
	@PostMapping(value = "/update")
	@CrossOrigin
	public Response update(@RequestBody Questions questions, HttpServletRequest req) throws Exception {
		try {
			questionsService.updateQuestions(questions); // 修改
		} catch (Exception e) {
			return Response.error();
		}
		return Response.success();
	}

	// 返回试题详情
	@ResponseBody
	@PostMapping(value = "/get")
	@CrossOrigin
	public Response get(HttpServletRequest req) throws Exception {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Questions questions = questionsService.queryQuestionsById(id); // 根据ID查询
			return Response.success(questions);
		} catch (Exception e) {
			return Response.error();
		}

	}

	/**
	 * 测试题列表
	 * @param questions
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping(value = "/listByCourse")
	@CrossOrigin
	public Response listByCourse(@RequestBody Questions questions) throws Exception {
		try {
			// 根据课程id查询试题列表,不分页
			List<Questions> questionList = questionsService.queryQuestionsList(questions, null);
			return Response.success(questionList);
		} catch (Exception e) {
			return Response.error();
		}
	}

	/**
	 * 错题列表
	 * @param sno 账号
	 * @param currentPage 当前页码
	 * @param pageSize 每页条数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/errorlist")
	@CrossOrigin
	public Response<List<Questions>> errorlist(@RequestParam("sno") String sno,
											   @RequestParam("currentPage") Integer currentPage,
											   @RequestParam("pageSize") Integer pageSize
	) throws Exception {
		int offset = (currentPage - 1) * pageSize; // 当前页开始记录
		int counts = 0; // 总记录数
		PageBean page = new PageBean(offset, pageSize); // 分页对象
		// 查询记录总数
		counts = questionsService.getSnoErrCount(sno);
		// 获取当前页记录
		List<Questions> questionsList = questionsService.querySnoErrQuestions(sno, page);
		int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM
				: counts / PageBean.PAGE_IETM + 1; // 总页数
		return Response.success(questionsList, counts, page_count);
	}

}
