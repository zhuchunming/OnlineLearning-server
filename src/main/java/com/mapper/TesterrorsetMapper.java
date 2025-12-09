package com.mapper;

import com.model.Testerrorset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TesterrorsetMapper {

	//添加
	public int insertTesterrorset(List<Testerrorset> errorsets);

	//用户是否错过的试题
	public List<Integer> queryErrorsetBySnoAndIds(@Param("sno") String sno, @Param("qids") List<Integer> qids);

	//用户是否错过的试题 num+1
	void updateErrorsetNumBySnoAndIds(@Param("sno") String sno, @Param("qids") List<Integer> qids);
}

