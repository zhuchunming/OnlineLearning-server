package com.service.impl;

import com.mapper.QuestionsMapper;
import com.mapper.TesterrorsetMapper;
import com.mapper.TestrecordlistMapper;
import com.mapper.TestrecordsMapper;
import com.model.Questions;
import com.model.Testerrorset;
import com.model.Testrecordlist;
import com.model.Testrecords;
import com.service.TestrecordsService;
import com.util.BusinessException;
import com.util.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TestrecordsServiceImpl implements TestrecordsService {

	private final static Logger log = LoggerFactory.getLogger(TestrecordsServiceImpl.class);

	@Autowired
	private TestrecordsMapper testrecordsMapper;

	@Autowired
	private TestrecordlistMapper testrecordlistMapper;

	@Autowired
	private QuestionsMapper questionsMapper;

	@Autowired
	private TesterrorsetMapper errorsetMapper;

	//查询多条记录
	public List<Testrecords> queryTestrecordsList(Testrecords testrecords,PageBean page) throws Exception {
		Map<String, Object> map =getQueryMap(testrecords, page);
		
		List<Testrecords> getTestrecords = testrecordsMapper.query(map);
		
		return getTestrecords;
	}
	
	//得到记录总数
	@Override
	public int getCount(Testrecords testrecords) {
		Map<String, Object> map = getQueryMap(testrecords, null);
		int count = testrecordsMapper.getCount(map);
		return count;
	}
	
	private Map<String, Object> getQueryMap(Testrecords testrecords,PageBean page){
		Map<String, Object> map = new HashMap<String, Object>();
		if(testrecords!=null){
			map.put("id", testrecords.getId());
			map.put("cid", testrecords.getCid());
			map.put("sno", testrecords.getSno());
			map.put("questioncount", testrecords.getQuestioncount());
			map.put("correctcount", testrecords.getCorrectcount());
			map.put("errorcount", testrecords.getErrorcount());
			map.put("score", testrecords.getScore());
			map.put("testtime", testrecords.getTesttime());
			map.put("sort", testrecords.getSort());
			map.put("condition", testrecords.getCondition());

		}
		PageBean.setPageMap(map, page);
		return map;
	}

	//添加
	@Transactional(rollbackFor = Exception.class)
	public void insertTestrecords(Testrecords testrecords) throws Exception {
		try{
			//新增记录主表
			int result = testrecordsMapper.insertTestrecords(testrecords);
			if(result > 0){
				//问题与答案转换成集合
				List<Testrecordlist> testrecordlist = tranTestrecordList(testrecords.getId(),testrecords.getSno(),testrecords.getAnswers(), testrecords.getQuestionids());
				//新增记录明细
				if(!CollectionUtils.isEmpty(testrecordlist)){
					testrecordlistMapper.insertTestrecordlist(testrecordlist);
				}
				// 将字符串转为 List
				List<Integer> ids = Arrays.stream(testrecords.getQuestionids().split(","))
						.map(String::trim)  // 去除空格
						.map(Integer::parseInt)  // 转为整数
						.collect(Collectors.toList());
				//获取用户试题列表
				List<Questions> questions = questionsMapper.queryQuestionsByIds(ids);
				//获取用户答错的题目ID列表
				List<Integer> qidList = getWrongQuestionIds(testrecordlist,questions);
				//用户是否错过的试题
				List<Integer> existErrors = errorsetMapper.queryErrorsetBySnoAndIds(testrecords.getSno(),qidList);
				//新增的错题集
				List<Integer> resultqidList = new ArrayList<>();
				if(!CollectionUtils.isEmpty(existErrors)){
					//错过的试题 num+1
					errorsetMapper.updateErrorsetNumBySnoAndIds(testrecords.getSno(),existErrors);
					for (Integer qid : qidList) {
						if (!existErrors.contains(qid)) {
							resultqidList.add(qid);
						}
					}
				}else{
					resultqidList.addAll(qidList);
				}
				//新增错题集
				List<Testerrorset> errorsets = new ArrayList<>();
				for(Integer id : resultqidList){
					Testerrorset errorset = new Testerrorset();
					errorset.setQid(id);
					errorset.setNum(1);
					errorset.setSno(testrecords.getSno());
					errorsets.add(errorset);
				}
				errorsetMapper.insertTesterrorset(errorsets);
			}
		}catch (Exception e) {
			// 记录错误日志
			log.info("在线测试保存测试记录失败: {}", e.getMessage());
			// 抛出自定义异常，确保事务回滚
			throw new BusinessException("保存测试记录失败: " + e.getMessage(), e);
		}
	}

	/**
	 * 获取用户答错的题目ID列表
	 * @param userAnswers 用户答案列表
	 * @param correctAnswers 正确答案列表
	 * @return 答错的题目ID列表
	 */
	public static List<Integer> getWrongQuestionIds(
			List<Testrecordlist> userAnswers,
			List<Questions> correctAnswers) {

		// 1. 参数校验
		if (userAnswers == null || userAnswers.isEmpty() ||
				correctAnswers == null || correctAnswers.isEmpty()) {
			return new ArrayList<>();
		}

		// 2. 构建正确答案映射表（使用Map提高查询效率）
		Map<Integer, String> correctAnswerMap = correctAnswers.stream()
				.filter(Objects::nonNull)  // 过滤空对象
				.filter(ca -> ca.getQid() != null)  // 过滤qid为null的
				.collect(Collectors.toMap(
						Questions::getQid,  // key: 题目ID
						Questions::getAnswer,  // value: 正确答案
						(existing, replacement) -> existing,  // 如果有重复qid，保留第一个
						HashMap::new
				));

		// 3. 遍历用户答案，找出答错的题目ID
		return userAnswers.stream()
				.filter(Objects::nonNull)  // 过滤空对象
				.filter(ua -> ua.getQid() != null)  // 过滤qid为null的
				.filter(ua -> {
					Integer qid = ua.getQid();
					String userAnswer = ua.getUseranswer();
					String correctAnswer = correctAnswerMap.get(qid);

					// 判断是否答错的条件：
					// 1. 题目不存在于正确答案中
					// 2. 用户答案为null
					// 3. 用户答案与正确答案不匹配
					return correctAnswer == null ||
							userAnswer == null ||
							!correctAnswer.equals(userAnswer);
				})
				.map(Testrecordlist::getQid)  // 提取题目ID
				.distinct()  // 去重
				.collect(Collectors.toList());
	}

	/**
	 * 问题与答案转换成集合
	 * @param mainid 主表id
	 * @param sno 账号
	 * @param answers 用户答案合集
	 * @param questionIds 试题id合集
	 * @return
	 */
	public static List<Testrecordlist> tranTestrecordList(int mainid, String sno, String answers, String questionIds) {
		String[] answerArray = answers.split(",");
		String[] idArray = questionIds.split(",");
		// 验证长度
		if (answerArray.length != idArray.length) {
			throw new BusinessException(
					String.format("答案数量(%d)与问题ID数量(%d)不匹配",
							answerArray.length, idArray.length)
			);
		}
		return IntStream.range(0, answerArray.length)
				.mapToObj(i -> {
					try {
						int qid = Integer.parseInt(idArray[i].trim());
						String answer = answerArray[i].trim();
						return new Testrecordlist(mainid, sno, qid, answer);
					} catch (NumberFormatException e) {
						throw new BusinessException("问题ID格式错误: " + idArray[i], e);
					}
				})
				.collect(Collectors.toList());
	}

	//根据ID删除
	public void deleteTestrecords(int id) throws Exception {
		//清理记录明细
		testrecordlistMapper.deleteTestrecordlist(id);
		//清理记录主表
		testrecordsMapper.deleteTestrecords(id);
	}

	//更新
	public int updateTestrecords(Testrecords testrecords) throws Exception {
		return testrecordsMapper.updateTestrecords(testrecords);
	}
	
	//根据ID得到对应的记录
	public Testrecords queryTestrecordsById(int id) throws Exception {
		Testrecords po =  testrecordsMapper.queryTestrecordsById(id);
		return po;
	}
}

