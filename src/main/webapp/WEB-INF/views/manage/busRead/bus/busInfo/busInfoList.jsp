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
			$("#busName").val("");	
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>企业客户列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}busInfoController/busInfoList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>企业名称：</dt>
            <dd>
             <input type="text" name="busName" id="busName" value="${busName }">
            </dd>
            <dd>
              <input type="submit" class="btn btn-blue" id="search" value="搜索"></input>
              <input type="button" class="btn btn-blue" value="重置" onclick="resetFun()"></input>    
            </dd>
          </dl>
        </div>
        
         <div class="data_list">
          <table>
            <thead>
              <tr>
              	<td>序号</td>
                <td>登录名</td>
                <td>企业名</td>
				<td>企业logo</td>
				<td>企业描述</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.userName}</td>
						<td>${item.busName}</td>
						<td><c:if test="${!empty(item.busLogo) }"><img src="${basePath}${item.busLogo}" width="30" height="30"></c:if></td>
						<td>${item.busDes}</td>
						<td>
							<a href="${basePath }busInfoController/updateBusInfo.html?userId=${item.userId}">完善企业信息</a>
							<a href="${basePath }busInfoController/viewBusInfo.html?userId=${item.userId}">详情</a>
							<a href="${basePath }busInfoController/customCourseType.html?userId=${item.userId}">定制课程分类</a>
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