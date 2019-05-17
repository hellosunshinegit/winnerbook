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
			$("#bookName").val("");	
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }bookListController/addBook.html";
		}
		
		//点击修改
		function updateFun(bookId,bluId){
			window.location.href="${basePath }bookListController/updateBook.html?bookId="+bookId+"&bluId="+bluId;
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>书单列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}bookListController/bookList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>书名：</dt>
            <dd>
             <input type="text" name="bookName" id="bookName" value="${bookName }">
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
                <td>书名</td>
                <td>作者</td>
				<td>出版社</td>
				<td>出版日期</td>
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
						<td title="${item.bookSearchName }">${item.bookName}</td>
						<td title="${item.bookAuthor } ">
							<c:if test="${fn:length(item.bookAuthor)>7}">
								${fn:substring(item.bookAuthor,0,5)}...
							</c:if>
							<c:if test="${fn:length(item.bookAuthor)<=7}">
								${item.bookAuthor}
							</c:if>
						</td>
						<td>${item.bookPublishers}</td>
						<td>${item.bookPublicationDate}</td>
						<td><exp:code code="STATUS" value="${item.bookStatus }"></exp:code></td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<c:if test="${sessionUser.isAdmin eq '1' or item.createUserId eq sessionUser.userId }">
								<a href="javaScript:updateFun('${item.bookId}','${item.bluId }')">修改</a>
							</c:if>
							<a href="${basePath }bookListController/viewBook.html?bookId=${item.bookId}">详情</a>
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