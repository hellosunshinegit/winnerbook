package com.winnerbook.activity.service;

import java.util.Map;
import com.winnerbook.activity.dto.Feedback;

public interface FeedbackService {

	//查询意见反馈web端
    Map<String, Object> getFeedbacks(Map<String, Object> parameter);
    
    int insertFeedback(Feedback feedback);

	
}
