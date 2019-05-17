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
    <script type="text/javascript" src="${basePath }resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//重置
		function resetFun(){
			$("#logType").val("");	
			$("#logSourse").val("");	
			$("#starttime").val("");	
			$("#endtime").val("");	
			//document.searchForm.submit();
		}
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>日志列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}ucSystemLogRecordController/logRecordList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>
              	日志类型：
            </dt>
            <dd>
             <exp:select code="LOG_TYPE" name="logType" id="logType" value="${logType }" headerKey="" headerValue="---请选择---"></exp:select>
            </dd>
          </dl>
          <dl>
            <dt>
              	日志来源：
            </dt>
            <dd>
             <exp:select code="LOG_SOURSE" name="logSourse" id="logSourse" value="${logSourse }" headerKey="" headerValue="---请选择---"></exp:select>
            </dd>
          </dl>
          <dl>
			<dt>时间范围：</dt>
			<dd style="width: 270px;">
				<input type="text" name="starttime" id="starttime" value="${starttime}" class="Wdate" onfocus="var endtime=$dp.$('endtime');WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',onpicked:function(){endtime.focus();},maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d'})" style="width: 100px;"/>-
				<input type="text" name="endtime" id="endtime" value="${endtime}" class="Wdate" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'%y-%M-%d'})" style="width: 100px;"/>
			</dd>
		  </dl>
		  <dl>
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
                <td>操作内容</td>
                <td>操作ip</td>
                <td>操作类型</td>
                <td>日志来源</td>
                <td>操作人</td>
                <td>操作时间</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.logDes }</td>
						<td>${item.logIp}</td>
						<td><exp:code code="LOG_TYPE" value="${item.logType}"></exp:code> </td>
						<td><exp:code code="LOG_SOURSE" value="${item.logSource}"></exp:code> </td>
						<td>${item.logCreateUserName}</td>
						<td><fmt:formatDate value="${item.logCreateDate}" type="both"/></td>
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