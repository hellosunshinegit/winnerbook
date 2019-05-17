package com.winnerbook.score.service;

import java.util.Map;

import com.winnerbook.score.dto.ScoreRecord;
import com.winnerbook.system.dto.User;

public interface ScoreRecordService {

	//查询分值排名
    Map<String, Object> getScoreRecords(Map<String, Object> parameter);
    
    int insertScoreRecord(ScoreRecord scoreRecord);
    
    //根据id查询
  	ScoreRecord findById(Integer id);
  	
  	//更新
  	int updateScoreRecord(ScoreRecord scoreRecord);
  	
  	//插入学习的分值
  	public int insertScoreRecord_source(User user,Integer mainId,String remarks,Integer minute_second,String souceType,String scoreKey);

	
}
