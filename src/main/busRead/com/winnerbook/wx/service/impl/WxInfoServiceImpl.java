package com.winnerbook.wx.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.wx.dao.WxInfoDao;
import com.winnerbook.wx.dto.WxInfo;
import com.winnerbook.wx.service.WxInfoService;

@Service("wxInfoService")
public class WxInfoServiceImpl extends BaseServiceImpl implements WxInfoService{
	
	@Autowired
	private WxInfoDao wxInfoDao;

	@Override
	public WxInfo getWxInfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", Integer.parseInt(id));
		return wxInfoDao.findWxInfo(map);
	}

	@Override
	public int updateWxInfo(WxInfo wxInfo) {
		return wxInfoDao.updateWxInfo(wxInfo);
	}

	@Override
	public void updateAccessToken(String accessToken) {
		WxInfo wxInfo = getWxInfo("1");
		wxInfo.setAccesstoken(accessToken);
		wxInfo.setAccesstokendate(new Date());
		updateWxInfo(wxInfo);
	}

	@Override
	public void updateJsapiTicket(String jsapiticket) {
		WxInfo wxInfo = getWxInfo("1");
		wxInfo.setJsapiticket(jsapiticket);
		wxInfo.setJsapiticketdate(new Date());
		updateWxInfo(wxInfo);
	}
	
}
