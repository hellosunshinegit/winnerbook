package com.winnerbook.web.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winnerbook.wx.service.WxInfoService;
@Controller
public class WbShare {
	
	@Autowired
	private WxInfoService wxInfoService;

	//获取code的值
	@RequestMapping("getWbCode.jhtml")
	@ResponseBody
	public void getWbCode(HttpServletRequest request,HttpServletResponse response){
		//获取coce，获取发送文章的id
		String code = request.getParameter("code");
		String idInfo = request.getParameter("id");//格式id=activity_3
		System.out.println("====wb-code====="+code);
		String accessToken = wxInfoService.getWbAccessToken(code);
		
		//通过id查询数据，并发送微博
		String resultId = wxInfoService.sendWbInfo(idInfo,accessToken);
		String returnHtml="<script language=\"javascript\">setTimeout(window.close,5000);</script><div style='font-size:15px;text-align: center;'>微博信息发送成功。5秒后页面关闭！！！</div>";
		if(!StringUtils.isNotBlank(resultId)){
			returnHtml = "<script language=\"javascript\">setTimeout(window.close,5000);</script><div style='font-size:15px;text-align: center;'>微博信息发送失败，请联系管理员。5秒后页面关闭！！！</div>";
		}
		response.setContentType("text/html;charset=UTF-8");
	    try {
			response.getWriter().print(returnHtml);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
