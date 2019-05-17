package com.winnerbook.activity.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.activity.dto.Activity;

public interface ActivityDao {
	
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
	List<Activity> listByPage(Map<String, Object> parameter);
	
    int insert(Activity record);
    
    Activity findById(Map<String, Object> parameter);

    int update(Activity record);
    
    List<Map<String, Object>> getActivitys(Map<String, Object> parameter);
    int getActivitysCount(Map<String, Object> parameter);
    Map<String, Object> getActivityDetail(Map<String, Object> parameter);
    
}