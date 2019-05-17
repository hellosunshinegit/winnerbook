package com.winnerbook.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.share.dao.QrcodeDao;
import com.winnerbook.share.dao.QrcodeRecordDao;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.dto.QrcodeRecord;
import com.winnerbook.share.service.QrcodeService;

@Service("qrcodeServiceWeb")
public class QrcodeServiceWeb extends WebBaseServiceImpl{
	
	
	@Autowired
	private QrcodeDao qrcodeDao;
	
	@Autowired
	private QrcodeRecordDao qrcodeRecordDao;
	
	public void addSacnRecord(String id,HttpServletRequest request){
		
		//扫描次数+1
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		qrcodeDao.updateScanCount(map);
		
		//记录扫描时间等信息
		String agent = request.getHeader("User-Agent");
		StringTokenizer st = new StringTokenizer(agent,";");
		st.nextToken();
		String userbrowser = st.nextToken();//浏览器名称
		
		QrcodeRecord qrcodeRecord = new QrcodeRecord();
		qrcodeRecord.setQrcodeId(Integer.parseInt(id));
		qrcodeRecord.setIp(request.getRemoteAddr());
		qrcodeRecord.setBrowserType(userbrowser);
		qrcodeRecord.setMobileDevice(getMobileDevice(request));
		qrcodeRecord.setCreateDate(new Date());
		qrcodeRecordDao.insert(qrcodeRecord);
	}
	
	
	//获取手机型号
	public String getMobileDevice(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		String deviceStr="";
		try {
			if(StringUtils.isNotBlank(userAgent)){
				int startIndex=userAgent.indexOf("(");
				int endIndex=userAgent.indexOf(")");
				deviceStr = userAgent.substring(startIndex+1, endIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceStr;
	}
	

}
