<%@page import="com.winnerbook.base.common.util.ConstantUtils"%>
<%@page import="com.winnerbook.system.dto.Menu"%>
<%@page import="com.winnerbook.system.service.MenuService"%>
<%@page import="com.winnerbook.base.common.util.SpringContextHolder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.winnerbook.base.common.GlobalConfigure"%>
<%@ page import="com.winnerbook.base.frame.content.ThreadLocalWrapper"%>
<%@ page import="com.winnerbook.system.dto.User"%>
<%@ page import="com.winnerbook.base.frame.content.UserContext"%>
<%

request.setAttribute("user",user);

//如果是admin 则显示出所有的权限就可以 ，不用查询
//查询该用户下的权限
 MenuService menuService = SpringContextHolder.getBean("menuService");
List menuLists = null;
if("1".equals(user.getIsAdmin())){
	menuLists = menuService.queryAllMenuByParentId("");
}else{
	menuLists = menuService.queryMenuByUserId(user.getUserId().toString());
}
request.setAttribute("menuLists",menuLists);
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>企业读书云平台</title>
    <link rel="icon" href="/resources/images/def_img1.png">
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/rui.css">
    <script type="text/javascript" src="${basePath }resources/css/rui.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/common.css">
    <script type="text/javascript" src="${basePath }resources/css/common.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/index.css">
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/zzsc.css">
    <script type="text/javascript" src="${basePath }resources/css/index.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/html5.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/layer/layer.js"></script>
    <style type="text/css">
    	.message{display: inline-block;position: absolute;top: -3px;left: -20px;width: 7px;height: 7px;background: red;padding: 5px;border-radius: 20px;text-align: center;line-height: 7px;color: #FFF;font-size: 12px;text-indent: 0;}
	    .ordernew{padding-right: 10px;font-size: 14px;}
    </style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	userClick();
	    	
	    	//手风琴效果的下拉框
	    	$(".menu_list dl .menu_body:eq(0)").show();
	    	$(".menu_list dl p.menu_head").click(function(){
	    		var classnew = $(this).attr("class");
	    		if(classnew.indexOf("current")>=0){
	    			$(this).removeClass("current").next("dd.menu_body").slideToggle(300).siblings("dd.menu_body").slideUp("no");
	    		}else{
		    		$(this).addClass("current").next("dd.menu_body").slideToggle(300).siblings("dd.menu_body").slideUp("slow");
	    		}
	    	});
	    });
    
		//点击直接打开用户管理
	   	function userClick(){
	   		$(".nav ul li").attr("class","");
	   		$("#li1").attr("class","act");
	   		$("#href1").click();
	   	}
	    
    	function clicktop(){
    		core.loadPage("${basePath}user/welcome.html", ".main", "mainframe", false);
    	}
    	
    	//点击扫描二维码
    	function mobileFun(){
    		//获取当前登录的企业，点击获取二维码
    		var str = {"busId":'${user.belongBusUserId}'};
    		$.ajax({
    			type:"POST",
    			async: false,
    			dataType:"json",
    		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
    			data:JSON.stringify(str),
    			url:"${basePath}userController/getBusQrcode.html",
    			success:function(data){
    				var busUrl = "PC预览链接：<br/><a href="+'<%=ConstantUtils.H5_URL%>?busId='+data.busId+''+" target='_black' title=点击预览><%=ConstantUtils.H5_URL%>?busId="+data.busId+"</a>";
    				var content = "<div style='padding:10px 20px 20px 20px;font-size:14px;'>该企业没有生成二维码，请联系管理员。</div>";
    				if(data!=null){
    					content = "<div style='text-align: center;'><br/><img src="+data.img+" width='200' heigth='200'><br/>"+busUrl+"</div>";
    				}
    				layer.open({
    				  title:"手机端二维码",
		   			  type: 1,
		   			  /* skin: 'layui-layer-rim', //加上边框 */
		   			  area: ['320px', '320px'], //宽高
		   			  content: content
		   			});
    			}
    		});
    	}
    	
    </script>
  </head>
  <body style="padding: 0;">
    <div class="header">
    <%-- <img src="${basePath }resources/new_images/logo.png"> --%><a href="javascript:void(0);" class="logo" hidefocus="true">企业读书云平台</a>
    <div class="user"> 
	    <!-- <span id="ordernew" class="ordernew"><a href="javascript:void(0);" ><span style="position: relative;color:#e6002d;">消息<b id="message_id">(2)</b></span></a></span> -->
	    <c:if test="${user.userId ne 1}">
		    <span onclick="mobileFun()" title="点击预览手机端 " style="padding: 10px;cursor:pointer;text-decoration: underline;">手机端</span>
	    </c:if>
	    ${user.userName}，欢迎您！  -<a href="${path }/user/logout.html" style="text-decoration: underline;">退出系统</a>
    </div>
    <div class="nav" style="display: none;"><!-- 右上部分导航去掉，不需要 -->
    <ul>
      <li>
        <a href="javascript:clicktop()" class="noside">首页</a>
      </li>
      <c:forEach items="${menuLists }" var="item">
      	  <li id="li${item.menuId }" class="act">
	        <a href="#here" id="href${item.menuId }">${item.menuName }</a>
	      </li>
      </c:forEach>
    </ul>
    </div>
    </div>
    <div class="article">
		 <div class="sidebar hidden">
		  <div class="swich">
		     <a href="javascript://"></a>
		  </div>
	      <div class="sidenav" style="background: #001529;">
	      	<%-- <div id="list0" class="menu_list nav2">
	      	<dl>
 				<dd style="display:blockl;font-size:14px;" class="menu_body">
 					<ul>
						 <li><a id="menu0" href="${basePath }user/welcome.html" hidefocus>首页</a></li>
				   </ul>
		 		</dd>
  		  	 </dl>
  		   </div> --%>
	      	
	      	<%
	    	for(int i = 0;i<menuLists.size();i++){
	      		Menu menu = (Menu)menuLists.get(i);
	      		%>
				<div id="list<%=menu.getMenuId() %>" class="menu_list nav2">
					<dl>
		 				<dd style="display:blockl;font-size:14px;" class="menu_body">
		 					<ul>
								 <li><a id="menu0" href="${basePath }user/welcome.html" hidefocus>首页</a></li>
						   </ul>
				 		</dd>
  		  		 	</dl>
	      		<%
	      		List listMenu = null;
      			if("1".equals(user.getIsAdmin())){
      				listMenu = menuService.queryAllMenuByParentId(menu.getMenuId().toString());
      			}else{
      				listMenu = menuService.queryMenuByParentUserId(user.getUserId().toString(), menu.getMenuId().toString());
      			}
      			for(int j = 0;j<listMenu.size();j++){
      				Menu menuj = (Menu)listMenu.get(j);
      				%>
      					<dl>
      					<p class="menu_head <%if(j==0){%>current<%}%>">
      						<%-- <img src="${basePath }resources/images/icon/111.png" width="20" height="20"> --%>
      						<%=menuj.getMenuName() %>
      					</p>
      					<dd style="<%if(j==0){%>display:blockl;font-size:14px;<%}%><%if(j!=0){%>display:none;font-size:14px;<%}%>" class="menu_body">
      					<ul>
      				<%
      				
      				List listMenuM = null;
  					if("1".equals(user.getIsAdmin())){
  						listMenuM = menuService.queryAllMenuByParentId(menuj.getMenuId().toString());
  					}else{
  						listMenuM = menuService.queryMenuByParentUserId(user.getUserId().toString(),menuj.getMenuId().toString());
  					}
  					for(int m = 0;m<listMenuM.size();m++){
  						Menu menuM = (Menu)listMenuM.get(m);
  						%>
  						 <li><a id="menu<%=menuM.getMenuId() %>" href="${basePath }<%=menuM.getMenuUrl()%>" hidefocus><%=menuM.getMenuName() %></a></li>
  						<%
  					}
  					%>
  						</ul>
  					 </dd>
  					</dl>
  					<%
      			}
	      		%>
	      		</div>
	      		<%	
	      	}
	      	%>
      </div>
    </div>
    <div class="main noside">
    	<script type="text/javascript">core.loadPage("${basePath}user/welcome.html", ".main", "mainframe", false);</script>
    </div>
    </div>
   <!--  <div class="footer">
    Copyright @ 1998 - 2016 winnerbookjinrong. All Rights Reserved
    </div> -->
  </body>
</html>