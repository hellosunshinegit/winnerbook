package com.winnerbook.activity.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.activity.dto.ActivitySignup;
import com.winnerbook.base.common.PageDTO;

public interface ActivitySignupService {
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<ActivitySignup> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param record
	 */
	int insert(ActivitySignup record);

    Map<String, Object> getActivitySignups(Map<String, Object> parameter);
    
    List<Map<String, Object>> isRepeatSingup(String busId,String userId,String activityId);
	
}
