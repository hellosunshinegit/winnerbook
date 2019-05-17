package com.winnerbook.wx.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.wx.dao.WxInfoDao;
import com.winnerbook.wx.dto.WxInfo;

@Repository("wxInfoDao")
public class WxInfoDaoImpl  extends BaseDAO implements WxInfoDao{
	private static final String WXINFO_MAPPER = "WxInfoMapper.";

	@Override
	public WxInfo findWxInfo(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(WXINFO_MAPPER+"findWxInfo",parameter);
	}

	@Override
	public int updateWxInfo(WxInfo wxInfo) {
		return this.sqlSession.update(WXINFO_MAPPER+"updateWxInfo",wxInfo);
	}

}
