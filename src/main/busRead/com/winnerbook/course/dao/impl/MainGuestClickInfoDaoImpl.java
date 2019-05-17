package com.winnerbook.course.dao.impl;

import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.MainGuestClickInfoDao;
import com.winnerbook.course.dto.MainGuestClickInfo;

@Repository("mainGuestClickInfoDao")
public class MainGuestClickInfoDaoImpl  extends BaseDAO implements MainGuestClickInfoDao{
	private static final String MAINGUESTCLICKINFO_MAPPER = "MainGuestClickInfoMapper.";
	
	@Override
	public int insert(MainGuestClickInfo mainGuestClickInfo) {
		return this.sqlSession.insert(MAINGUESTCLICKINFO_MAPPER+"insert",mainGuestClickInfo);
	}

}
