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
			$("#articleTitle").val("");
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }articleController/addArticle.html";
		}
		
		function updateStatue(articleId,status){
			if(status==1){
				status=2;
			}else{
				status=1;
			}
			window.location.href="${basePath }articleController/updateStatus.html?articleId="+articleId+"&status="+status;
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>文章列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}articleController/articleList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>标题：</dt>
            <dd>
             <input type="text" name="articleTitle" id="articleTitle" value="${articleTitle }">
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
                <td>文章类型</td>
				<td>标签</td>
				<td>所属版块</td>
				<td>推送频道</td>
				<td>状态</td>
				<td>排序</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.articleTitle}</td>
						<td>${item.articleAuthor}</td>
						<td>${item.articleTypeName}</td>
						<td>${item.articleTags}</td>
						<td>${item.blockStr}</td>
						<td><exp:code code="CHANNEL" value="${item.articleChannel }"></exp:code> </td>
						<td><a href="javascript:updateStatue('${item.articleId }','${item.articleStatus }')" style="color: blue;"><exp:code code="ARTICLE_STATUS" value="${item.articleStatus }"></exp:code></a></td>
						<td>${item.articleSort}</td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<c:if test="${sessionUser.isAdmin eq '1' or item.createUserId eq sessionUser.userId }">
								<a href="${basePath }articleController/updateArticle.html?articleId=${item.articleId}">修改</a>
							</c:if>
							<a href="${basePath }articleController/viewArticle.html?articleId=${item.articleId}">详情</a>
							<c:if test="${sessionUser.userId eq 1 }">
								<a href="https://api.weibo.com/oauth2/authorize?client_id=${wxInfo.appid }&response_type=code&redirect_uri=${wxInfo.redirectUri }?id=article_${item.articleId}" target="_blank">发微博</a>
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