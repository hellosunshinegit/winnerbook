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
			$("#menuName").val("");	
			//document.searchForm.submit();
		}
		//删除
		function deleteFun(menuId){
			if(confirm("确定要删除吗")){
				window.location.href="${basePath}menuController/deleteMenu.html?menuId="+menuId;
			}
		}
		
		function addFun(){
			window.location.href="${basePath}menuController/addInputMenu.html";
		}
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>菜单列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}menuController/menuList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>
              	菜单名称：
            </dt>
            <dd>
             <input type="text" name="menuName" id="menuName" value="${menuName }">
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
                <td>菜单名称</td>
                <td>菜单编码</td>
				<td>菜单状态</td>
				<td>创建时间</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.menuName }</td>
						<td>${item.menuCode}</td>
						<td><exp:code code="STATUS" value="${item.menuStatus}"/></td>
						<td><fmt:formatDate value="${item.menuCreatedate}" type="both"/></td>
						<td>
							<a href="${basePath }menuController/updateInputMenu.html?menuId=${item.menuId}">修改</a>
							<a href="javascript:deleteFun(${item.menuId})">删除</a>
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