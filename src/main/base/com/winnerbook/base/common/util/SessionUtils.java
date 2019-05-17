package com.winnerbook.base.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

public class SessionUtils {

	public static void bindUserContext(HttpServletRequest request,String wechatid) {
		UserContext userContext = ThreadLocalWrapper.getUserContext();
		if((userContext == null ||  userContext.getUser() == null )&& StringUtils.isNotBlank(wechatid)){
			userContext = new UserContext();
			UserService userInfoService = (UserService) SpringUtils.getBean("userService");
			User userInfo = userInfoService.findUserById(wechatid);
			userContext.setUser(userInfo);
			ThreadLocalWrapper.bind(userContext);
			request.getSession().setAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY, userContext);
		}
	}

}
