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
    <script type="text/javascript" src="${basePath }resources/js/jquery.form.js"></script>
	<script type="text/javascript">
	
	//删除
	function deleteFun(userId){
		if(confirm("确定要删除吗")){
			window.location.href="${basePath}userController/deleteUser.html?userId="+userId;
		}
	}
		
	//添加角色
	function addFun(){
		window.location.href="${basePath }userController/addInputUser.html";
	}
	//重置
	function resetFun(){
		$("#userName").val("");	
		$("#userContactMobile").val("");
		$("#userStatue").val("");
		//document.searchForm.submit();
	}
	//导入
	function importFun(){
		window.location.href="${basePath }userController/importUser.html?type=2";
	}
	
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>
        企业通讯录
      </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}busEmpController/userEmpList.html" method="post" >
        <div class="data_search clearfix">
          <dl>
            <dt>
             	手机号：
            </dt>
            <dd>
              <input type="text" name="userName" id="userName" value="${userName }">
            </dd>
            <dt>
             	启用状态：
            </dt>
            <dd>	
                <exp:select code="STATUS" name="userStatue"  id="userStatue" value="${userStatue}" headerKey="" headerValue="---请选择---"></exp:select>
			</dd>
            <dt>
              &nbsp;
            </dt>
            <dd>
              <input type="submit" class="btn btn-blue" id="search" value="搜索"></input>
              <input type="button" class="btn btn-blue" value="重置" onclick="resetFun()">    
            </dd>
            <!-- 只有企业管理员才可以导入 -->
			<dd>
    		  <c:if test="${sessionUser.isBusinessAdmin eq 1 }">
	    		  <input type="button" class="btn btn-blue" value="导入" onclick="importFun()">
    		  </c:if>
    		</dd>
          </dl>
        </div>
         <div class="data_list">
          <table>
            <thead>
              <tr>
              	<td>序号</td>
              	<td>手机号(登录名)</td>
              	<td>姓名</td>
              	<td>所属部门</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>启用状态</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="user" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${user.userName}</td>
						<td>${user.userUnitName}</td>
						<td>${user.department}</td>
						<td><fmt:formatDate value="${user.userCreateDate}" type="both"/></td>
						<td>${user.userCreateUserName}</td>
						<td><exp:code code="STATUS" value="${user.userStatue}" /></td>
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