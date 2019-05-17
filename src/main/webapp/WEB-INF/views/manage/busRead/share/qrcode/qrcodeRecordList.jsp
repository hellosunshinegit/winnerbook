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
			$("#mobileDevice").val("");	
		}
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>${qrcodeInfo.name}-扫描二维码记录</h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}qrcodeController/viewQrcodeRecord.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>手机型号：</dt>
            <dd>
              <input type="hidden" name="qrcodeId" id="qrcodeId" value="${qrcodeId }">
             <input type="text" name="mobileDevice" id="mobileDevice" value="${mobileDevice }">
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
                <td>ip</td>
                <td>手机版本</td>
                <td>手机型号</td>
				<td>扫描时间</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.ip}</td>
						<td title="${item.browserType}">
							<c:if test="${fn:length(item.browserType)>15}">
								${fn:substring(item.browserType,0,15)}...
							</c:if>
							<c:if test="${fn:length(item.browserType)<=15}">
								${item.browserType}
							</c:if>
						</td>
						<td>${item.mobileDevice}</td>
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
					<input type="button" onclick="javascript:window.location.href='${basePath}qrcodeController/qrcodeList.html'" class="btn btn-blue" value="返回" /> 
				</div>
			</dd>
		</dl>
      </div>
  </body>
</html>