package com.winnerbook.web.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.common.util.HttpRequestUtil;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.wx.dto.WxInfo;
import com.winnerbook.wx.service.WxInfoService;

@Service("wxInfoServiceWeb")
public class WxInfoServiceWeb extends WebBaseServiceImpl{
	
	@Autowired
	private WxInfoService wxInfoService;
	
	//获取accessToken
	public String getAccessTokenOfJssdk(WxInfo wxInfo){
		
		String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wxInfo.getAppid()+"&secret="+wxInfo.getAppsecret();
		//查询数据库，是否存在access_token
		String accessToken = wxInfo.getAccesstoken();
		if(StringUtils.isNotBlank(accessToken)){//不为空判断下时间是否过期，不过期直接返回，过期重新获取
			//获取上一次的更新时间
			Date validdate = wxInfo.getAccesstokendate();
			//比较与当前时间差
			Long times = DateTimeUtils.getDiffMinute(validdate);
            if(times > 90) {//token>90分钟，则重新获取
            	return getAccessToken(getTokenUrl);
            }else{
            	return wxInfo.getAccesstoken();
            }
		}else{//为空直接获取
			return getAccessToken(getTokenUrl);
		}
	}
	
	public String getAccessToken(String getTokenUrl){
		String newToken = "";
		String newTokenStr = HttpRequestUtil.sendGet(getTokenUrl, "");
        JSONObject newTokenJson = JSONObject.fromObject(newTokenStr);
        if(null!=newTokenJson.get("errcode")){
        	System.out.println("获取token失败:"+newTokenStr);
        	return "";
        }
        newToken = newTokenJson.getString("access_token");
        if(newToken !=null && newToken!="") {
            //写入数据库
        	wxInfoService.updateAccessToken(newToken);
        }
        return newToken;
	}
	
	
	//通过token获取jsapi_ticket
	public String getJsapiTicketOfJssdk(String accessToken) {
		String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		
		WxInfo wxInfo = wxInfoService.getWxInfo("1");
		if(StringUtils.isNotBlank(wxInfo.getJsapiticket())){
			//获取上一次的更新时间
			Date validdate = wxInfo.getJsapiticketdate();
			//比较与当前时间差
			Long times = DateTimeUtils.getDiffMinute(validdate);
            if(times > 90) {//token>90分钟，则重新获取
            	return getJsapiTicket(jsapiUrl);
            }else{
            	return wxInfo.getJsapiticket();
            }
		}else{
			return getJsapiTicket(jsapiUrl);
		}
	}
	
	public String getJsapiTicket(String jsapiUrl){
		String newTicket = "";
		String newTicketStr = HttpRequestUtil.sendGet(jsapiUrl, "");
        JSONObject newTokenJson = JSONObject.fromObject(newTicketStr);
        newTicket = newTokenJson.getString("ticket");
        if(newTicket !=null && newTicket!="") {
            //写入数据库
        	wxInfoService.updateJsapiTicket(newTicket);
        }
        return newTicket;
	}
	
	
	//通过sha1签名加密
	public String getSha1(String str) {
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
              
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
}
