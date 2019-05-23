package com.winnerbook.wx.service;
import com.winnerbook.wx.dto.WxInfo;

public interface WxInfoService {

	WxInfo getWxInfo(String id);//获取微信信息
	
	int updateWxInfo(WxInfo wxInfo);
	
	//更新 access_token
	void updateAccessToken(String accessToken);
	
	//更新jsapi_ticket
	void updateJsapiTicket(String accessToken);
		
	String getWbAccessToken(String code);
	
	//设置微博的access_token
	String setWbAccess_token(String code);
	
	String sendWbInfo(String idInfo,String accessToken);
	
}
