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
			$("#typeName").val("");	
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }readThoughtController/addReadThought.html";
		}
		
		function deleteReadThought(thoughtId){
			if(confirm("确定要删除吗？")){
				window.location.href="${basePath }readThoughtController/deleteReadThought.html?thoughtId="+thoughtId;
			}
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>读后感列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}readThoughtController/readThoughtList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>课程类型名称：</dt>
            <dd>
             <input type="text" name="typeName" id="typeName" value="${typeName }">
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
                <td>书籍名称</td>
                <td>读后感标题</td>
                <!-- <td>读后感</td> -->
                <td>附件名称</td>
                <td>是否公开</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.bookListName}</td>
						<td>${item.thoughtTitle}</td>
						<%-- <td title="${item.thoughtDes } ">
							<c:if test="${fn:length(item.thoughtDes)>20}">
								${fn:substring(item.thoughtDes,0,20)}...
							</c:if>
							<c:if test="${fn:length(item.thoughtDes)<=20}">
								${item.thoughtDes}
							</c:if>
						</td> --%>
						<td><a href="${basePath }readThoughtController/downLoadReadThought.html?thoughtId=${item.thoughtId }" style="color: blue;" title="点击可下载">${item.thoughtFilename}</a></td>
						<td><exp:code code="READ_THOUGHT_OPEN" value="${item.isOpen }"></exp:code></td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<c:if test="${sessionUser.isAdmin eq '1' or item.createUserId eq sessionUser.userId }">
								<a href="${basePath }readThoughtController/updateReadThought.html?thoughtId=${item.thoughtId}">修改</a>
								<a href="javascript:deleteReadThought('${item.thoughtId }')">删除</a>
							</c:if>
							<a href="${basePath }readThoughtController/viewReadThought.html?thoughtId=${item.thoughtId}">详情</a>
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