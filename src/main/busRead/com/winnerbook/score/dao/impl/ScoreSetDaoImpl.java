package com.winnerbook.score.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.score.dao.ScoreSetDao;

@Repository("scoreSetDao")
public class ScoreSetDaoImpl  extends BaseDAO implements ScoreSetDao{
	private static final String SCORESET_MAPPER = "ScoreSetMapper.";
	
	@Override
	public Map<String, Object> findByKeyName(Map<String, Object> map) {
		List<Map<String, Object>> listMap = this.sqlSession.selectList(SCORESET_MAPPER+"findByKeyName",map);
		return listMap.get(0);
	}

}
