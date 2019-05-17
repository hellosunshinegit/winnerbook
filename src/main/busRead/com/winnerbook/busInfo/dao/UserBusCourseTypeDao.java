package com.winnerbook.busInfo.dao;

import java.util.List;

import com.winnerbook.busInfo.dto.UserBusCourseType;

public interface UserBusCourseTypeDao{

	//根据用户id查询
	List<UserBusCourseType> findByUserId(String userId);

	//新增
	void insert(List<UserBusCourseType> records);
	
	//删除
	void delete(String userId);
	
}
