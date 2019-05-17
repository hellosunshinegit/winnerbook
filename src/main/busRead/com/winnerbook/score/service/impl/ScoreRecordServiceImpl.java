package com.winnerbook.score.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.score.dao.ScoreRecordDao;
import com.winnerbook.score.dao.ScoreSetDao;
import com.winnerbook.score.dto.ScoreRecord;
import com.winnerbook.score.service.ScoreRecordService;
import com.winnerbook.score.service.ScoreSetService;
import com.winnerbook.system.dto.User;

@Service("scoreRecordService")
public class ScoreRecordServiceImpl extends BaseServiceImpl implements ScoreRecordService{
	
	@Autowired
	private ScoreRecordDao scoreRecordDao;
	
	@Autowired
	private ScoreSetService scoreSetService;

	@Override
	public Map<String, Object> getScoreRecords(
			Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		
		return map;
	}

	@Override
	public int insertScoreRecord(ScoreRecord scoreRecord) {
		return scoreRecordDao.insertScoreRecord(scoreRecord);
	}

	@Override
	public ScoreRecord findById(Integer id) {
		return scoreRecordDao.findById(id);
	}

	@Override
	public int updateScoreRecord(ScoreRecord scoreRecord) {
		return scoreRecordDao.updateScoreRecord(scoreRecord);
	}
	
	@Override
	public int insertScoreRecord_source(User user,Integer mainId,String remarks,Integer minute_second,String souceType,String scoreKey){
		
		Map<String, Object> scoreKey_map = scoreSetService.findByKeyName(scoreKey);
		
		//记录评论得分
		ScoreRecord scoreRecord = new ScoreRecord();
		scoreRecord.setBusId(StringUtils.isNotBlank(user.getBelongBusUserId())?Integer.parseInt(user.getBelongBusUserId()):0);
		scoreRecord.setUserId(Integer.parseInt(user.getUserId()+""));
		scoreRecord.setMainId(mainId);//学习记录的id
		scoreRecord.setScore((null!=scoreKey_map.get("score")?Integer.parseInt(scoreKey_map.get("score")+""):0)*minute_second);
		scoreRecord.setRemarks(remarks);
		scoreRecord.setSourse(souceType);//分值来源 1：学习记录（1分钟1分），2：读后感1个5分，3：评论 1个1分  4：小视频学习时常
		scoreRecord.setCreateDate(new Date());
		scoreRecord.setCreateUserId(Integer.parseInt(user.getUserId()+""));
		scoreRecord.setCreateUserName(user.getUserUnitName());
		
		scoreRecordDao.insertScoreRecord(scoreRecord);
		return scoreRecord.getId();
	}
	
}
