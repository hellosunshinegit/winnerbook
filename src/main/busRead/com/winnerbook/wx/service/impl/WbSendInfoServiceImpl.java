package com.winnerbook.wx.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo4j.model.Status;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.wx.dao.WbSendInfoDao;
import com.winnerbook.wx.dto.WbSendInfo;
import com.winnerbook.wx.service.WbSendInfoService;

@Service("wbSendInfoService")
public class WbSendInfoServiceImpl extends BaseServiceImpl implements WbSendInfoService{
	
	@Autowired
	private WbSendInfoDao wbSendInfoDao;

	@Override
	public int insertWbSendInfo(String mainId,String mainType,String title,Status status) {
		//记录发送微博信息
		WbSendInfo wbSendInfo = new WbSendInfo();
		wbSendInfo.setMainId(Integer.parseInt(mainId));
		wbSendInfo.setMainType(mainType);
		wbSendInfo.setMainTitle(title);
		wbSendInfo.setWbId(status.getId());
		wbSendInfo.setWbIdstr(status.getIdstr()+""); 
		wbSendInfo.setWbMid(status.getMid());
		wbSendInfo.setWbCreatedAt(status.getCreatedAt());
		wbSendInfo.setCreateTime(new Date());
		return wbSendInfoDao.insert(wbSendInfo);
	}


}
