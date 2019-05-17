package com.winnerbook.activity.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.activity.dao.FeedbackDao;
import com.winnerbook.activity.dto.Feedback;
import com.winnerbook.activity.service.FeedbackService;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;

@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public Map<String, Object> getFeedbacks(
			Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> courseList = feedbackDao.getFeedBacks(parameter);
		int courseCount = feedbackDao.getFeedBacksCount(parameter);
		map.put("feedbackList", courseList);
		map.put("feedbackCount", courseCount);
		return map;
	}

	@Override
	public int insertFeedback(Feedback feedback) {
		return feedbackDao.insertFeedBack(feedback);
	}
	
}
