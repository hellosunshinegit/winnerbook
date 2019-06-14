<%@ page import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common.jsp"%>
<!DOCTYPE html>
<html>
  <!--<![endif]-->
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/rui.css">
    <script type="text/javascript" src="${basePath }resources/css/rui.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/common.css">
    <script type="text/javascript" src="${basePath }resources/css/common.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript">
		//重置
		function resetFun(){
			$("#clubTitle").val("");	
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }readBookClubController/addreadBookClub.html";
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>读书会列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}courseTypeController/courseTypeList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>主题：</dt>
            <dd>
             <input type="text" name="clubTitle" id="clubTitle" value="${clubTitle }">
            </dd>
            <dd>
              <input type="submit" class="btn btn-blue" id="search" value="搜索"></input>
              <input type="button" class="btn btn-blue" value="重置" onclick="resetFun()"></input>    
            </dd>
          </dl>
          <dl>
          	<dd><input type="button" class="btn btn-blue" value="添加" onclick="addFun()"></input></dd>
          </dl>
          
        </div>
        
         <div class="data_list">
          <table>
            <thead>
              <tr>
              	<td>序号</td>
                <td>主题</td>
                <td>录制时间</td>
				<td>主讲人</td>
				<td>推荐书目</td>
				<td>状态</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.clubTitle}</td>
						<td>${item.clubDate}</td>
						<td>${item.clubMainGuest}</td>
						<td>${item.clubMainGuestBook}</td>
						<td><exp:code code="STATUS" value="${item.clubStatus }"></exp:code> </td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<a href="${basePath }readBookClubController/updateReadBookClub.html?clubId=${item.clubId}">修改</a>
							<a href="${basePath }readBookClubController/deleteReadBookClub.html?clubId=${item.clubId}">删除</a>
							<a href="${basePath }readBookClubController/viewReadBookClub.html?clubId=${item.clubId}">详情</a>
							<c:if test="${sessionUser.userId eq 1 }">
								<a href="https://api.weibo.com/oauth2/authorize?client_id=${wxInfo.appid }&response_type=code&redirect_uri=${wxInfo.redirectUri }?id=club_${item.clubId}" target="_blank">发微博</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
          </table>
        </div>
        <div class="pagination clearfix">
          <div class="pagination-info">
          	<c:import url="../../page.jsp">
				<c:param name="pageSize" value="${pageDTO.pageSize }" />
				<c:param name="pageIndex" value="${pageDTO.pageIndex }" />
				<c:param name="rowSize" value="${pageDTO.rowSize }" />
				<c:param name="currentFormId" value="searchForm" />
			</c:import>
          </div>
        </div>
        </form>
      </div>
  </body>
</html>