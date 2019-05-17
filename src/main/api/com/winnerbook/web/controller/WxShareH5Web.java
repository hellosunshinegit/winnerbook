package com.winnerbook.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.web.service.WxInfoServiceWeb;
import com.winnerbook.wx.dto.WxInfo;
import com.winnerbook.wx.service.WxInfoService;

@Controller
public class WxShareH5Web {
	
	@Autowired
	private WxInfoServiceWeb wxInfoServiceWeb;
	
	@Autowired
	private WxInfoService wxInfoService;

	@RequestMapping(value="getWxInfo.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getArticles(HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		
		WxInfo wxInfo = wxInfoService.getWxInfo("1");//微信的信息
		
		//获取access_token
	    String access_token = wxInfoServiceWeb.getAccessTokenOfJssdk(wxInfo);
	    if(!StringUtils.isNotBlank(access_token)){
	    	result.setMsg("获取微信信息失败");
	    	return callback+"("+JSONObject.fromObject(result)+")";
	    }
		//根据token获取jsapi_ticket
		String jsapi_ticket = wxInfoServiceWeb.getJsapiTicketOfJssdk(access_token);
		Long timestamp = new Date().getTime()/1000;
		UUID uuid = UUID.randomUUID();
		String noncestr=uuid.toString();
        String pageUrl = request.getParameter("localUrl");
		String signature = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+pageUrl;
		signature = wxInfoServiceWeb.getSha1(signature);
		
		Map<String,Object> wechatSign = new HashMap<String,Object>();//返回的数据
		wechatSign.put("timestamp", timestamp);
        wechatSign.put("nonceStr", noncestr);
        wechatSign.put("signature", signature);
        wechatSign.put("appId", wxInfo.getAppid());
        wechatSign.put("pageUrl", pageUrl);
	
 		result.setSuccess(true);
		result.setMsg("获取微信信息成功");
		result.setData(wechatSign);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
}
