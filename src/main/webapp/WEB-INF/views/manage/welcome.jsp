<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${basePath }resources/css/welcome.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<div class="title">欢迎来到企业读书云平台后台</div>
    <div class="content">
    	<div>第一步：企业管理->企业通讯录  导入企业用户</div>
    	<div>第二步：课程管理->书籍列表   添加企业书籍</div>
    	<div class="wel_img">
    		<span><img alt="" src="${basePath }resources/images/welcome_01.png" width="550" height="330"></span>
    		<span><img alt="" src="${basePath }resources/images/welcome_02.png" width="550" height="330"></span>
    	</div>
    </div>
  </body>
</html>
