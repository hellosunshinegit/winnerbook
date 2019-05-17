package com.winnerbook.activity.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.winnerbook.activity.dao.ActivityDao;
import com.winnerbook.activity.dto.Activity;
import com.winnerbook.base.frame.dao.BaseDAO;

@Repository("activityDao")
public class ActivityDaoImpl  extends BaseDAO implements ActivityDao{
	private static final String ACTIVITY_MAPPER = "ActivityMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ACTIVITY_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Activity> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ACTIVITY_MAPPER + "listByPage", parameter);
	}

	@Override
	public Activity findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(ACTIVITY_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Activity record) {
		return this.sqlSession.insert(ACTIVITY_MAPPER+"insert",record);
	}

	@Override
	public int update(Activity record) {
		return this.sqlSession.insert(ACTIVITY_MAPPER+"update",record);
	}

	@Override
	public List<Map<String, Object>> getActivitys(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ACTIVITY_MAPPER+"getActivitys", parameter);
	}

	@Override
	public int getActivitysCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ACTIVITY_MAPPER + "getActivitysCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getActivityDetail(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(ACTIVITY_MAPPER+"getActivityDetail",parameter);
	}
}
