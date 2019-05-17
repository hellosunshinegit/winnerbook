package com.winnerbook.activity.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.activity.dto.ActivitySignup;

public interface ActivitySignupDao {
	
	/**
	 * 查询总条数
	 * @param parameter
	 * @return
	 */
	int  selectCount(Map<String, Object> parameter);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	List<ActivitySignup> listByPage(Map<String, Object> parameter);
	
	//web
    int insert(ActivitySignup record);
    List<Map<String, Object>> getActivitySignups(Map<String, Object> parameter);
    int getActivitySignupCount(Map<String, Object> parameter);

    List<Map<String, Object>> isRepeatSingup(Map<String, Object> paramter);
    
}