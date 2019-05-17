package com.winnerbook.wx.service;
import com.winnerbook.wx.dto.WxInfo;

public interface WxInfoService {

	WxInfo getWxInfo(String id);//获取微信信息
	
	int updateWxInfo(WxInfo wxInfo);
	
	//更新 access_token
	void updateAccessToken(String accessToken);
	
	//更新jsapi_ticket
	void updateJsapiTicket(String accessToken);
	
}
