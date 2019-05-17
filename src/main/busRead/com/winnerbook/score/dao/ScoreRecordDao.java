package com.winnerbook.score.dao;

import com.winnerbook.score.dto.ScoreRecord;

public interface ScoreRecordDao {
	
    int insertScoreRecord(ScoreRecord scoreRecord);
    
    //根据id查询
  	ScoreRecord findById(Integer id);
  	
  	//更新
  	int updateScoreRecord(ScoreRecord scoreRecord);
    
}