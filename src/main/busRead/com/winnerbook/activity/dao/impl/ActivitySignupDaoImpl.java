package com.winnerbook.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.activity.dao.ActivitySignupDao;
import com.winnerbook.activity.dto.ActivitySignup;
import com.winnerbook.base.frame.dao.BaseDAO;

@Repository("activitySignupDao")
public class ActivitySignupDaoImpl  extends BaseDAO implements ActivitySignupDao{
	private static final String ACTIVITYSIGNUP_MAPPER = "ActivitySignupMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ACTIVITYSIGNUP_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<ActivitySignup> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ACTIVITYSIGNUP_MAPPER + "listByPage", parameter);
	}

	@Override
	public int insert(ActivitySignup record) {
		return this.sqlSession.insert(ACTIVITYSIGNUP_MAPPER+"insert",record);
	}

	@Override
	public List<Map<String, Object>> getActivitySignups(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(ACTIVITYSIGNUP_MAPPER+"getActivitySignups",parameter);
	}

	@Override
	public int getActivitySignupCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ACTIVITYSIGNUP_MAPPER + "getActivitySignupCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Map<String, Object>> isRepeatSingup(Map<String, Object> paramter) {
		return this.sqlSession.selectList(ACTIVITYSIGNUP_MAPPER+"isRepeatSingup",paramter);
	}

	@Override
	public List<ActivitySignup> getActivitySignupByUserId(String userId) {
		return this.sqlSession.selectList(ACTIVITYSIGNUP_MAPPER+"getActivitySignupByUserId",userId);
	}

	@Override
	public int updateActivitySignupById(Map<String, Object> parameter) {
		return this.sqlSession.update(ACTIVITYSIGNUP_MAPPER+"updateActivitySignupById",parameter);
	}
}
