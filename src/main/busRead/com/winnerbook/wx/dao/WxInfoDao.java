package com.winnerbook.wx.dao;

import java.util.Map;
import com.winnerbook.wx.dto.WxInfo;

public interface WxInfoDao {
	
    WxInfo findWxInfo(Map<String, Object> parameter);
    
    int updateWxInfo(WxInfo wxInfo);
    
}