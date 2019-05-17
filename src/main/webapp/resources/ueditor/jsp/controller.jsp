<%@page import="com.winnerbook.base.common.util.ConstantUtils"%>
<%@page import="com.winnerbook.base.common.util.FileUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	String saveRootPath = ConstantUtils.FILE_SERVER_LOCAL_PATH;
	String domain = ConstantUtils.BASE_PATH_URL+"upload";
	System.out.print("===saveRootPath====="+saveRootPath+"=====domain="+domain);
	
	out.write( new ActionEnter( request, saveRootPath, domain, rootPath ).exec() );
	
%>