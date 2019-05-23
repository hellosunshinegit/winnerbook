package com.winnerbook.wx.dao.impl;

import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.wx.dao.WbSendInfoDao;
import com.winnerbook.wx.dto.WbSendInfo;

@Repository("wbSendInfoDao")
public class WbSendInfoDaoImpl  extends BaseDAO implements WbSendInfoDao{
	private static final String WBSENDINFO_MAPPER = "WbSendInfoMapper.";

	@Override
	public int insert(WbSendInfo wbSendInfo) {
		return this.sqlSession.insert(WBSENDINFO_MAPPER+"insert",wbSendInfo);
	}

}
