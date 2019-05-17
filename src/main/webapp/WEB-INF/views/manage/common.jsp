<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/expend-tag.tld" prefix="exp" %>
<%@page import="com.winnerbook.base.common.util.SpringContextHolder"%>
<%@page import="com.winnerbook.system.service.impl.UserServiceImpl"%>
<%@page import="com.winnerbook.system.service.UserService"%>
<%@page import="com.winnerbook.system.dto.User"%>
<%@page import="com.winnerbook.base.frame.content.ThreadLocalWrapper"%>
<%@page import="com.winnerbook.base.frame.content.UserContext"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/"; 


String requestUrl = request.getServletPath();
UserContext userContext = (UserContext) session.getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
User user = userContext.getUser();
UserService userService = SpringContextHolder.getBean("userService");
User adminUser = userService.getAdmin();

request.setAttribute("sessionUser", user);
request.setAttribute("userAdminId", adminUser.getUserId());

%>
