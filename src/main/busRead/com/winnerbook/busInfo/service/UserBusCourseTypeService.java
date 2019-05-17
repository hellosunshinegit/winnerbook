package com.winnerbook.busInfo.service;

import java.util.List;

import com.winnerbook.busInfo.dto.UserBusCourseType;

public interface UserBusCourseTypeService {

	//根据用户id查询
	List<UserBusCourseType> findByUserId(String userId);

}
