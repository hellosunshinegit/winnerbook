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
			$("#userName").val("");	
			$("#phone").val("");	
		}
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>${activity.title}-报名记录</h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}activityController/singupActivityList.html" method="post">
            <input type="hidden" name="activityId" id="activityId" value="${activityId }">
	        <div class="data_search clearfix">
	          <dl>
	            <dt>姓名：</dt>
	            <dd>
	             <input type="text" name="userName" id="userName" value="${userName }">
	            </dd>
	            <dt>手机号：</dt>
	            <dd>
	             <input type="text" name="phone" id="phone" value="${phone }">
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
                <td>姓名</td>
                <td>手机号</td>
                <td>报名时间</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.userName}</td>
						<td>${item.phone}</td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
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
      <div style="margin-left: 30px;">
      	<dl>
			<dd>
				<div style="background-color: #fff;"> 
					<input type="button" onclick="javascript:window.location.href='${basePath}activityController/activityList.html'" class="btn btn-blue" value="返回" /> 
				</div>
			</dd>
		</dl>
      </div>
  </body>
</html>