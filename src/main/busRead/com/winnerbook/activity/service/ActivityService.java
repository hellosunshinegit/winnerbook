package com.winnerbook.activity.service;

import java.util.Map;
import com.winnerbook.activity.dto.Activity;
import com.winnerbook.base.common.PageDTO;

public interface ActivityService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Activity findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<Activity> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param record
	 */
	void insert(Activity record);

	/**
	 * 修改
	 * @param record
	 */
	void update(Activity record);
	
    Map<String, Object> getActivitys(Map<String, Object> parameter);
    Map<String, Object> getActivityDetail(Map<String, Object> parameter);
	
}
