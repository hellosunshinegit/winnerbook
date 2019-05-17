package com.winnerbook.busInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.busInfo.dao.UserBusCourseTypeDao;
import com.winnerbook.busInfo.dto.UserBusCourseType;
import com.winnerbook.busInfo.service.UserBusCourseTypeService;

@Service("userBusCourseTypeService")
public class UserBusCourseTypeServiceImpl extends BaseServiceImpl implements UserBusCourseTypeService{
	
	@Autowired
	private UserBusCourseTypeDao userBusCourseTypeDao;

	@Override
	public List<UserBusCourseType> findByUserId(String userId) {
		return userBusCourseTypeDao.findByUserId(userId);
	}


}
