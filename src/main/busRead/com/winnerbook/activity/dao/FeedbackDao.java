package com.winnerbook.activity.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.activity.dto.Feedback;

public interface FeedbackDao {
	
    //查询意见反馈web端
    List<Map<String, Object>> getFeedBacks(Map<String, Object> parameter);
    int getFeedBacksCount(Map<String, Object> parameter);
    
    int insertFeedBack(Feedback feedback);
    
}