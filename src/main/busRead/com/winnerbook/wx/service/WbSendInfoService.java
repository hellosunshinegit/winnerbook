package com.winnerbook.wx.service;
import weibo4j.model.Status;

public interface WbSendInfoService {

	int insertWbSendInfo(String mainId,String mainType,String title,Status status);
}
