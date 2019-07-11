package com.winnerbook.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jcraft.jsch.UserInfo;
import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.system.dto.User;
import com.winnerbook.wx.service.WxInfoService;
@Controller
public class WbShare {
	
	@Autowired
	private WxInfoService wxInfoService;
	
	@Autowired
	private BusInfoService busInfoService;

	//获取code的值
	@RequestMapping("getWbCode.jhtml")
	@ResponseBody
	public void getWbCode(HttpServletRequest request,HttpServletResponse response){
		
		String returnHtml = "";
		//获取coce，获取发送文章的id
		String code = request.getParameter("code");
		String idInfo = request.getParameter("id");//格式id=activity_3
		String[] idInfoArray = idInfo.split("_");
		String type = idInfoArray[0];
		System.out.println("====wb-code====="+code);
		//获取当前登录人的id，然后查询是否有权限权限发送微博，或者说发送微博次数是否已经上限
		String isSendWb = "";
		HttpServletRequest req =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserContext userContext = (UserContext) req.getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
		User user = userContext.getUser();
		if(null==user){
			returnHtml = "<script language=\"javascript\">setTimeout(window.close,5000);</script><div style='font-size:15px;text-align: center;'>您处于未登录状态，不可以发微博。</div>";
		}
		int sendWbCount = 0;
		int alearySendWb = 0;
		if("1".equals(user.getUserId()+"")){//为1 是管理员，不限制发微博
			isSendWb = "0";
		}else{
			UserBusInfo userBusInfo = busInfoService.findById(user.getBelongBusUserId());
			
			sendWbCount = null!=userBusInfo.getSendWbCount()?userBusInfo.getSendWbCount():0;
			
			List<Map<String, Object>> busWbCount = busInfoService.getBusSendWbList(type, user.getBelongBusUserId()+"");
			alearySendWb = busWbCount.size();
			
			if(alearySendWb>=sendWbCount){
				isSendWb = "1";//不可发微博
			}else{
				isSendWb = "0";
			}
		}
		
		if("1".equals(isSendWb)){
			returnHtml = "<script language=\"javascript\">setTimeout(window.close,5000);</script><div style='font-size:15px;text-align: center;'>允许发微博次数为"+sendWbCount+"次，您已发送"+alearySendWb+"次。如您需要发送微博，请联系管理员！！！</div>";
		}else{
			String accessToken = wxInfoService.getWbAccessToken(code);
			
			//通过id查询数据，并发送微博
			String resultId = wxInfoService.sendWbInfo(idInfo,accessToken);
			returnHtml = "<script language=\"javascript\">setTimeout(window.close,5000);</script><div style='font-size:15px;text-align: center;'>微博信息发送成功。5秒后页面关闭！！！</div>";
			if(!StringUtils.isNotBlank(resultId)){
				returnHtml = "<script language=\"javascript\">setTimeout(window.close,5000);</script><div style='font-size:15px;text-align: center;'>微博信息发送失败，请联系管理员。5秒后页面关闭！！！</div>";
			}
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
