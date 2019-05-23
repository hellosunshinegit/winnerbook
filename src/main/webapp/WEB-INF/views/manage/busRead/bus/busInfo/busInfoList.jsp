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
    <script type="text/javascript" src="${basePath }resources/js/layer/layer.js"></script>
	<script type="text/javascript">
		//重置
		function resetFun(){
			$("#busName").val("");	
		}
		
		function getBusQrcode(busId,busName){
			//getBusQrcode
			//首先查询该企业下是否有二维码，如果没有则生成，有则直接返回
			var str = {"busId":busId};
			$.ajax({
    			type:"POST",
    			async: false,
    			dataType:"json",
    		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
    			data:JSON.stringify(str),
    			url:"${basePath}busInfoController/getBusQrcode.html",
    			success:function(data){
    				var content = "";
    				if(data.isNewGenerate=="1"){//新生成
    					content = "<span style='text-align: center;display: block;margin: 5px;font-size: 14px;'>二维码生成成功</span><div style='text-align: center;margin-top:15px;'><img src="+data.img+" width='200' heigth='200'></div>";
    				}else{
	    				content="<div style='text-align: center;margin-top:25px;'><img src="+data.img+" width='200' heigth='200'></div>";
    				}
    				layer.open({
    				  title:busName!=''?busName:"手机端二维码",
		   			  type: 1,
		   			  area: ['300px', '300px'], //宽高
		   			  content: content
		   			});
    			}
    		});
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
						<td title="${item.busDes}">
							<c:if test="${fn:length(item.busDes)>15}">
								${fn:substring(item.busDes,0,15)}...
							</c:if>
							<c:if test="${fn:length(item.busDes)<=15}">
								${item.busDes}
							</c:if>
						</td>
						<td>
							<a href="${basePath }busInfoController/updateBusInfo.html?userId=${item.userId}">完善企业信息</a>
							<a href="${basePath }busInfoController/viewBusInfo.html?userId=${item.userId}">详情</a>
							<a href="${basePath }busInfoController/customCourseType.html?userId=${item.userId}">定制课程分类</a>
							<a href="javascript:getBusQrcode(${item.userId},'${item.busName}')">手机端预览</a>
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