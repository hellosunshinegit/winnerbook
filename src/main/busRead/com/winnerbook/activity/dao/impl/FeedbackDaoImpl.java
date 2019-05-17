package com.winnerbook.activity.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.winnerbook.activity.dao.FeedbackDao;
import com.winnerbook.activity.dto.Feedback;
import com.winnerbook.base.frame.dao.BaseDAO;

@Repository("feedbackDao")
public class FeedbackDaoImpl  extends BaseDAO implements FeedbackDao{
	private static final String FEEDBACK_MAPPER = "FeedbackMapper.";
	
	
	@Override
	public List<Map<String, Object>> getFeedBacks(Map<String, Object> parameter) {
		return this.sqlSession.selectList(FEEDBACK_MAPPER+"getFeedbackts",parameter);
	}

	@Override
	public int getFeedBacksCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(FEEDBACK_MAPPER + "getFeedbacktsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int insertFeedBack(Feedback feedback) {
		return this.sqlSession.insert(FEEDBACK_MAPPER+"insert",feedback);
	}

}
