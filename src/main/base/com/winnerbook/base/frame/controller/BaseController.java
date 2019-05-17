package com.winnerbook.base.frame.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Component
public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private UserService userService;
	
	public User getSessionUser(){
		HttpServletRequest req =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserContext userContext = (UserContext) req.getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
		User user = userContext.getUser();
		JSONObject adminUser = JSONObject.fromObject(getAdminStr());
		user.setAdminId(adminUser.getString("userId"));
		return user;
	}
	
	public String getAdminStr(){
		User user = null;
		try {
			user = userService.getAdmin();
		} catch (Exception e) {
			/*HttpSession session = request.getSession();
			String contextPath = (String) session.getAttribute("path");
			try {
				response.sendRedirect(contextPath+"/user/login.html");
			} catch (IOException e1) {
				logger.info("登陆失效...."+e1.getMessage());
			}*/
			logger.info("登陆失效...."+e.getMessage());
		}
		return JSONObject.fromObject(user).toString();
	}
	
	public User getAdmin(){
		return userService.getAdmin(); 
	}
	
	public Map<String,Object> getUserOrgan(Map<String,Object> map){
	/*	User user = getSessionUser();
		if("1".equals(user.getUserJoinTraderType())){//加盟商企业
			map.put("organId", user.getOrganId());
		}else if("2".equals(user.getUserJoinTraderType())){//加盟商个人
			map.put("userId", user.getUserId());
		}else{
			map.put("userId", user.getUserId());
		}*/
		return map;
	}
	
}
