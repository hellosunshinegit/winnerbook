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
			window.location.href="${basePath }courseTypeController/addCourseType.html";
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>课程分类列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}courseTypeController/courseTypeList.html" method="post">
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
              	<c:if test="${sessionUser.userId eq 1 }">
                <td>标签名称</td>
                </c:if>
                <td>课程类型名称</td>
                <td>图片</td>
                <td>课程类型描述</td>
				<td>排序</td>
				<c:if test="${sessionUser.userId eq 1 }">
				<td>是否免费</td>
				</c:if>
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
						<c:if test="${sessionUser.userId eq 1 }"><td>${item.typeLabelName}</td></c:if>
						<td>${item.typeName}</td>
						<td><img alt="" src="${basePath }${item.typeImg}" width="30px" height="30px"></td>
						<td title="${item.typeDesc}">
							<c:if test="${fn:length(item.typeDesc)>15}">
								${fn:substring(item.typeDesc,0,15)}...
							</c:if>
							<c:if test="${fn:length(item.typeDesc)<=15}">
								${item.typeDesc}
							</c:if>
						</td>
						<td>${item.typeSort}</td>
						<c:if test="${sessionUser.userId eq 1 }"><td><exp:code code="COURSE_TYPE_ISFREE" value="${item.typeIsFree }"></exp:code> </td></c:if>
						<td><exp:code code="STATUS" value="${item.typeStatus }"></exp:code> </td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<c:if test="${sessionUser.isAdmin eq '1' or item.createUserId eq sessionUser.userId }">
								<a href="${basePath }courseTypeController/updateCourseType.html?typeId=${item.typeId}">修改</a>
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