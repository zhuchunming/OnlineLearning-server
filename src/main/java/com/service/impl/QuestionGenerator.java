package com.service.impl;

import com.mapper.QuestionsMapper;
import com.mapper.TesterrorsetMapper;
import com.mapper.TestrecordlistMapper;
import com.model.SwitchControl;
import com.model.Testerrorset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 智能组卷规则生成器
 */
@Component
public class QuestionGenerator {

    @Autowired
    private TestrecordlistMapper testrecordlistMapper;

    @Autowired
    private TesterrorsetMapper testerrorsetMapper;

    @Autowired
    private QuestionsMapper questionsMapper;

    /**
     * 生成智能组卷规则
     * @param sno 用户学号
     * @param cid 课程id
     * @param sw 出题配置比例
     * @return 试题ID列表
     */
    public List<Integer> generateQuestionPaper(String sno, int cid, SwitchControl sw) {

        // 1.1 获取用户做题历史记录
        List<Integer> testrecordQids = testrecordlistMapper.queryTestrecordlistBySno(sno);
        // 1.2 获取用户错题历史记录
        List<Integer> errorQids = testerrorsetMapper.queryErrorQIdsBySno(sno);

        // 2. 获取课程所有的试题记录
        List<Integer> allQuestionQids = questionsMapper.queryQuestionQidsByCid(cid);

        // 3. 计算各种类型的题目
        boolean allQuestionsAnswered = testrecordQids.size() >= allQuestionQids.size();

        // 需要生成的题目数量
        int targetCount = Integer.parseInt(sw.getValue2());
        if (allQuestionsAnswered) {
            // 情况1：所有试题都做过
            return generatePaperForAllAnswered(sno, targetCount, allQuestionQids, errorQids, sw);
        } else {
            // 情况2：有未做过的题目
            return generatePaperForPartialAnswered(sno, targetCount, allQuestionQids,testrecordQids, errorQids, sw);
        }
    }

    /**
     * 情况1：所有试题都做过时的组卷逻辑
     * 历史错题出sw.value1（比例70%） 所有试题出 1-sw.value1（比例30%）
     * @param sno 账号
     * @param targetCount 出题数
     * @param allQuestionQids 所有试题id
     * @param errorQids 错误试题id
     * @param sw 出题比例
     * @return
     */
    private List<Integer> generatePaperForAllAnswered(
            String sno,
            int targetCount,
            List<Integer> allQuestionQids,
            List<Integer> errorQids,
            SwitchControl sw) {
        //历史错题比例
        Double value3 = Double.valueOf(sw.getValue1());
        List<Integer> selectedQuestions = new ArrayList<>();
        Random random = new Random();

        // 1. 选择70%的历史错题
        int errorCount = (int) Math.round(targetCount * value3);
        List<Integer> selectedErrors = selectErrorQuestions(errorQids, errorCount, sno, random);

        selectedQuestions.addAll(selectedErrors);

        // 2. 从所有试题中选择30%的题目
        int allCount = targetCount - selectedErrors.size();
        if (allCount > 0) {
            List<Integer> remainingQuestions = new ArrayList<>(allQuestionQids);
            remainingQuestions.removeAll(selectedErrors); // 避免重复

            // 根据错误次数权重选择题目（错误次数越多的越容易被选中）
            List<Integer> selectedAll = selectQuestionsByErrorWeight(remainingQuestions, allCount, sno, random);
            selectedQuestions.addAll(selectedAll);
        }

        // 3. 打乱题目顺序
        Collections.shuffle(selectedQuestions);

        return selectedQuestions.subList(0, Math.min(targetCount, selectedQuestions.size()));
    }

    /**
     *  情况2：有未做过的题目时的组卷逻辑
     *  历史错题出sw.value（比例40%） 未答题出=1-sw.value（比例60%）
     * @param sno 账号
     * @param targetCount 出题数
     * @param allQuestionIds 所有试题id
     * @param answeredQuestionIds
     * @param errorQuestionIds 错误试题id
     * @param sw 出题比例
     * @return
     */
    private List<Integer> generatePaperForPartialAnswered(
            String sno,
            int targetCount,
            List<Integer> allQuestionIds,
            List<Integer> answeredQuestionIds,
            List<Integer> errorQuestionIds,
            SwitchControl sw) {

        //历史错题比例
        Double value = Double.valueOf(sw.getValue());

        List<Integer> selectedQuestions = new ArrayList<>();
        Random random = new Random();

        // 1. 获取未做过的题目
        Set<Integer> unansweredSet = new HashSet<>(allQuestionIds);
        unansweredSet.removeAll(answeredQuestionIds);
        List<Integer> unansweredQuestions = new ArrayList<>(unansweredSet);

        // 2. 选择40%的历史错题
        int errorCount = (int) Math.round(targetCount * value);
        List<Integer> selectedErrors = selectErrorQuestions(errorQuestionIds, errorCount,sno, random);

        selectedQuestions.addAll(selectedErrors);

        // 3. 选择60%的未做过的题目
        int unansweredCount = targetCount - selectedErrors.size();
        if (unansweredCount > 0 && !unansweredQuestions.isEmpty()) {
            // 从未做过的题目中随机选择
            Collections.shuffle(unansweredQuestions);
            int actualUnansweredCount = Math.min(unansweredCount, unansweredQuestions.size());
            List<Integer> selectedUnanswered = unansweredQuestions.subList(0, actualUnansweredCount);
            selectedQuestions.addAll(selectedUnanswered);

            // 如果未做过的题目不够，用随机题目补全
            if (selectedQuestions.size() < targetCount) {
                int remainingCount = targetCount - selectedQuestions.size();
                List<Integer> remainingQuestions = new ArrayList<>(allQuestionIds);
                remainingQuestions.removeAll(selectedQuestions);

                Collections.shuffle(remainingQuestions);
                int actualRemainingCount = Math.min(remainingCount, remainingQuestions.size());
                selectedQuestions.addAll(remainingQuestions.subList(0, actualRemainingCount));
            }
        }

        // 4. 打乱题目顺序
        Collections.shuffle(selectedQuestions);

        return selectedQuestions.subList(0, Math.min(targetCount, selectedQuestions.size()));
    }

    /**
     * 根据错误次数权重选择错题
     */
    private List<Integer> selectErrorQuestions(
            List<Integer> errorQids,
            int count,
            String sno,
            Random random) {

        if (errorQids.isEmpty() || count <= 0) {
            return new ArrayList<>();
        }

        // 获取错题的错误次数
        Map<Integer, Integer> errorCountMap = new HashMap<>();
        for (Integer qid : errorQids) {
            Testerrorset error = testerrorsetMapper.querytErrorRecordBySnoAndQid(sno, qid);
            if (error != null) {
                errorCountMap.put(qid, error.getNum());
            }
        }

        // 如果没有错误次数信息，随机选择
        if (errorCountMap.isEmpty()) {
            return randomSelect(errorQids, count, random);
        }

        // 根据错误次数进行加权随机选择
        return weightedRandomSelect(errorCountMap, count, random);
    }

    /**
     * 根据错误次数权重选择题目
     */
    private List<Integer> selectQuestionsByErrorWeight(
            List<Integer> questionIds,
            int count,
            String sno,
            Random random) {

        // 获取题目的错误次数
        Map<Integer, Integer> weightMap = new HashMap<>();
        for (Integer qid : questionIds) {
            Testerrorset error = testerrorsetMapper.querytErrorRecordBySnoAndQid(sno, qid);
            int weight = 1; // 默认权重为1
            if (error != null && error.getNum() > 0) {
                weight = error.getNum() + 1; // 错误次数越多，权重越高
            }
            weightMap.put(qid, weight);
        }

        return weightedRandomSelect(weightMap, count, random);
    }

    /**
     * 加权随机选择
     */
    private List<Integer> weightedRandomSelect(
            Map<Integer, Integer> weightMap,
            int count,
            Random random) {

        if (weightMap.isEmpty() || count <= 0) {
            return new ArrayList<>();
        }

        List<Integer> selected = new ArrayList<>();
        List<Integer> candidates = new ArrayList<>(weightMap.keySet());

        // 计算总权重
        int totalWeight = weightMap.values().stream().mapToInt(Integer::intValue).sum();

        while (selected.size() < count && !candidates.isEmpty()) {
            int randomValue = random.nextInt(totalWeight);
            int cumulativeWeight = 0;

            for (Integer qid : candidates) {
                cumulativeWeight += weightMap.get(qid);
                if (randomValue < cumulativeWeight) {
                    selected.add(qid);
                    candidates.remove(qid);
                    totalWeight -= weightMap.get(qid);
                    break;
                }
            }
        }

        return selected;
    }

    /**
     * 随机选择
     */
    private List<Integer> randomSelect(List<Integer> list, int count, Random random) {
        if (list.size() <= count) {
            return new ArrayList<>(list);
        }
        Collections.shuffle(list, random);
        return new ArrayList<>(list.subList(0, count));
    }
}