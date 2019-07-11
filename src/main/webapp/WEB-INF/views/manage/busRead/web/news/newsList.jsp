<%@ page import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common.jsp"%>
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
			$("#newTitle").val("");
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }newsController/addNews.html";
		}
		
		function updateStatue(newId,status){
			if(status==1){
				status=2;
			}else{
				status=1;
			}
			window.location.href="${basePath }newsController/updateStatus.html?newId="+newId+"&status="+status;
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>企业风采列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}newsController/newList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>标题：</dt>
            <dd>
             <input type="text" name="newTitle" id="newTitle" value="${newTitle }">
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
                <td>标题</td>
                <td>作者</td>
				<td>描述</td>
				<td>发微博次数</td>
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
						<td>${item.newTitle}</td>
						<td>${item.newAuthor}</td>
						<td title="${item.newDes}">
							<c:if test="${fn:length(item.newDes)>20}">
								${fn:substring(item.newDes,0,20)}...
							</c:if>
							<c:if test="${fn:length(item.newDes)<=20}">
								${item.newDes}
							</c:if>
						</td>
						<td>${item.wbCount }</td>
						<td><a href="javascript:updateStatue('${item.newId }','${item.newStatus }')" style="color: blue;"><exp:code code="ARTICLE_STATUS" value="${item.newStatus }"></exp:code></a></td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<a href="${basePath }newsController/updateNews.html?newId=${item.newId}">修改</a>
							<a href="${basePath }newsController/viewNews.html?newId=${item.newId}">详情</a>
							<c:if test="${sessionUser.userId eq 1 || sessionUser.isBusinessAdmin eq 1}"><!-- 是admin或者是企业管理员 -->
								<a href="https://api.weibo.com/oauth2/authorize?client_id=${wxInfo.appid }&response_type=code&redirect_uri=${wxInfo.redirectUri }?id=new_${item.newId}" target="_blank">发微博</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
          </table>
        </div>
        <div class="pagination clearfix">
          <div class="pagination-info">
          	<c:import url="../../../page.jsp">
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