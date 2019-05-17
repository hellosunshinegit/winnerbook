package com.winnerbook.busInfo.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.busInfo.dto.BusInfo;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.system.dto.User;

public interface BusInfoDao{
	
	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	UserBusInfo findById(Map<String, Object> parameter);
	
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
	List<UserBusInfo> listByPage(Map<String, Object> parameter);

	/**
	 * 新增
	 * @param dictionary
	 */
	void insert(BusInfo busInfo);
	/**
	 * 修改
	 * @param dictionary
	 */
	void update(BusInfo busInfo);
	
	//查询企业管理员用户
	List<User> findBusUserName();
	
	int findBusInfoById(String userId);


}
