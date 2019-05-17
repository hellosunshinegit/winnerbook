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
			$("#name").val("");	
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }qrcodeController/addQrcode.html";
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>二维码列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}qrcodeController/qrcodeList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>二维码标题：</dt>
            <dd>
             <input type="text" name="name" id="name" value="${name }">
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
                <td>二维码标题</td>
                <td>二维码图片</td>
                <td>扫描次数</td>
				<td>跳转链接</td>
				<td>位置</td>
				<td>状态</td>
				<td>生成时间</td>
				<td>生成人</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.name}</td>
						<td><a href="${basePath }qrcodeController/qrcodeUpload.html?id=${item.id}" title="点击下载"><img alt="" src="${basePath}${item.img}" width="100" height="100"></a></td>
						<td>${item.scanCount}</td>
						<td title="${item.forwardUrl } ">
							<c:if test="${fn:length(item.forwardUrl)>20}">
								${fn:substring(item.forwardUrl,0,20)}...
							</c:if>
							<c:if test="${fn:length(item.forwardUrl)<=20}">
								${item.forwardUrl}
							</c:if>
						</td>
						<td>${item.address}</td>
						<td><exp:code code="STATUS" value="${item.status }"></exp:code> </td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>${item.createUserName}</td>
						<td>
							<a href="${basePath }qrcodeController/updateQrcode.html?id=${item.id}">修改</a>
							<a href="${basePath }qrcodeController/viewQrcodeRecord.html?qrcodeId=${item.id}">扫码记录</a>
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