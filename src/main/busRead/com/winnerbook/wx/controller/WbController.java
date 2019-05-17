package com.winnerbook.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winnerbook.wx.dto.WxInfo;
import com.winnerbook.wx.service.WxInfoService;

@Controller
public class WbController {
	
	@Autowired
	private WxInfoService wxInfoService;
	
	//获取code的值
	@RequestMapping("getWbCode.html")
	public void getWbCode(HttpServletRequest request){
		//获取coce，获取发送文章的id
		String code = request.getParameter("code");
		String articleId = request.getParameter("id");
		String type = request.getParameter("type");//企业风采的id
		
		//获取Access Token  post方式  已修改数据库，直接从数据库中获取appkey 和sercet两个值,还有Access Token,在测试阶段需要1天获取一次，通过审核后30天获取一次。
		WxInfo wxInfo = wxInfoService.getWxInfo("2");
		String getAccess_token = "https://api.weibo.com/oauth2/access_token?client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE";
		
		
		
		
		
	}

}
