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
			$("#blockName").val("");	
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }blockController/addBlock.html";
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>版块列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}blockController/blockList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>主题：</dt>
            <dd>
             <input type="text" name="blockName" id="blockName" value="${blockName }">
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
                <td>版块名称</td>
                <td>版块小图标</td>
				<td>版块推送类型</td>
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
						<td>${item.blockName}</td>
						<td><a href="${basePath }${item.blockImgUrl}" target="_blank" title="点击查看"><img src="${basePath }${item.blockImgUrl}" width="50" height="50"></a></td>
						<td><exp:code code="CHANNEL" value="${item.blockType}"></exp:code> </td>
						<td><exp:code code="STATUS" value="${item.blockStatus }"></exp:code> </td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<a href="${basePath }blockController/updateBlock.html?blockId=${item.blockId}">修改</a>
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