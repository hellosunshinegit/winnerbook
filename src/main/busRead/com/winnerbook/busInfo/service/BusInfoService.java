package com.winnerbook.busInfo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.busInfo.dto.BusInfo;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.system.dto.User;

public interface BusInfoService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	UserBusInfo findById(String userId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<UserBusInfo> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(BusInfo busInfo,HttpServletRequest request);
	//查询企业管理员用户
	List<User> findBusUserName();
	
	int findBusInfoById(String userId);
	
	String customCourseTypeSubmit(String courseTypeIds);
}
