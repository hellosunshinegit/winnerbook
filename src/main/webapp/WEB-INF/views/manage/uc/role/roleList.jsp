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
			$("#roleName").val("");	
			//document.searchForm.submit();
		}
		//删除
		function deleteFun(roleId){
			if(confirm("确定要删除吗")){
				//判断角色下是否有权限，如果有，则不可以删除
				$.ajax({
					type:"POST",
					dataType:"html",
					url:"${basePath}roleController/findMenuByRoleIdCount.html?roleId="+roleId,
					success:function(data){
						if(data>0){
							alert("该角色下有权限，不可以删除");
						}else{
							window.location.href="${basePath}roleController/deleteRole.html?roleId="+roleId;
						}
					}
				});
			}
		}
		//点击清除权限
		function clearPermission(roleId){
			if(confirm("确定要清除该角色下的权限吗？")){
				$.ajax({
					type:"POST",
					dataType:"html",
					async: false,
					url:"${basePath }roleController/clearPermission.html?roleId="+roleId,
					success:function(data){
						if(data==0){
							alert("该角色下没有权限");
						}else if(data==1){
							alert("权限清除成功");
						}else{
							alert("清除异常，请联系管理员");
						}
					}
				});	
			}
		}
		
		//添加角色
		function addFun(){
			window.location.href="${basePath }roleController/addInputRole.html";
		}
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>角色列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}roleController/roleList.html" method="post" >
        <div class="data_search clearfix">
          <dl>
            <dt>
              	角色名称：
            </dt>
            <dd>
             <input type="text" name="roleName" id="roleName" value="${roleName }">
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
                <td>角色名称</td>
                <td>角色描述</td>
				<td>角色状态</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.roleName }</td>
						<td>${item.roleDesc}</td>
						<td><exp:code code="STATUS" value="${item.roleStatus}"/></td>
						<td><fmt:formatDate value="${item.roleCreatedate}" type="both"/></td>
						<td>${item.roleCreateUserName}</td>
						<td>
							<a href="${basePath }roleController/updateInputRole.html?roleId=${item.roleId}">修改</a>
							<a href="javascript:deleteFun(${item.roleId})">删除</a>
							<a href="${basePath }roleController/assignPermission.html?roleId=${item.roleId}">分配权限</a>
							<a href="javascript:clearPermission(${item.roleId});">清除权限</a>
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