package com.winnerbook.score.dao.impl;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.score.dao.ScoreRecordDao;
import com.winnerbook.score.dto.ScoreRecord;

@Repository("scoreRecordDao")
public class ScoreRecordDaoImpl  extends BaseDAO implements ScoreRecordDao{
	private static final String SCORERECORD_MAPPER = "ScoreRecordMapper.";
	
	@Override
	public int insertScoreRecord(ScoreRecord scoreRecord) {
		return this.sqlSession.insert(SCORERECORD_MAPPER+"insert",scoreRecord);
	}

	@Override
	public ScoreRecord findById(Integer id) {
		return this.sqlSession.selectOne(SCORERECORD_MAPPER+"findById",id);
	}

	@Override
	public int updateScoreRecord(ScoreRecord scoreRecord) {
		return this.sqlSession.update(SCORERECORD_MAPPER+"update",scoreRecord);
	}

}
