package com.winnerbook.busInfo.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.busInfo.dao.UserBusCourseTypeDao;
import com.winnerbook.busInfo.dto.UserBusCourseType;

@Repository("userBusCourseTypeDao")
public class UserBusCourseTypeDaoImpl  extends BaseDAO implements UserBusCourseTypeDao{
private static final String USERBUSCOURSETYPE_MAPPER = "UserBusCourseTypeMapper.";
	
	@Override
	public List<UserBusCourseType> findByUserId(String userId) {
		return this.sqlSession.selectList(USERBUSCOURSETYPE_MAPPER+"findByUserId",userId);
	}

	@Override
	public void insert(List<UserBusCourseType> records) {
		this.sqlSession.insert(USERBUSCOURSETYPE_MAPPER+"insert",records);
	}

	@Override
	public void delete(String userId) {
		this.sqlSession.delete(USERBUSCOURSETYPE_MAPPER+"delete",userId);
	}
	
}
