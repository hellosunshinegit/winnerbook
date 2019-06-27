<%@page import="com.winnerbook.base.common.util.ConstantUtils"%>
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
			$("#status").val("");	
		}
		
		//点击审核
		function examineFun(id,type){//1通过 2拒绝
			//layer 方法
			var statusStr = "";
			if(type==1){
				statusStr = "<div style='color:red;font-size:13px;padding:10px;'>审核通过后，会为该用户创建企业管理员的账号，请慎重！！！</div>";
			}
			var str = "<div style='text-align: center;width:100%;'>"+statusStr+"<textarea rows='8' cols='40' id='statusReason' maxlength='400' placeholder='请填写调查结果'></textarea></div>";
			var openDiv = layer.open({
			  title:'审核信息原因填写',
			  btn: ['确定', '取消'],
			  type: 1,
			  area: ['400px', '300px'], //宽高
			  content: str,
			  yes:function(index){
				  var statusReason = $("#statusReason").val();//原因不可为空
				  if(statusReason==''){
					  alert("审核信息原因为必填项");
				  }else{
					  var str = {"id":id,"status":type,"statusReason":statusReason};
					  //提交
					  $.ajax({
							type:"POST",
							async: false,
							dataType:"json",
						    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
							data:JSON.stringify(str),
							url:"${basePath}busLookInfoController/examineBus.html",
							success:function(data){
								console.log(data);
								if(data.success){
									alert("操作成功");
									window.location.href="${basePath}busLookInfoController/busLookInfoList.html";
								}else{
									alert(data.msg);
								}
							}
						});
				  }
			  }
			});
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>调查结果列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}busLookInfoController/busLookInfoList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>状态：</dt>
            <dd>
                <exp:select code="USER_APPLY_STATUS" name="status"  id="status" value="${status}" headerKey="" headerValue="---请选择---"></exp:select>
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
                <td>企业名称</td>
				<td>企业书单</td>
				<td>选择的课程包</td>
				<td>企业读书计划</td>
				<td>联系方式</td>
				<td>是否适用读书云</td>
				<td>状态</td>
				<td>创建时间</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.busName }</td>
						<td title="${item.bookListName }">
							<c:if test="${fn:length(item.bookListName)>15}">
								${fn:substring(item.bookListName,0,15)}...
							</c:if>
							<c:if test="${fn:length(item.bookListName)<=15}">
								${item.bookListName}
							</c:if>
						</td>
						<td>${item.courseList }</td>
						<td>${item.readBookPlan }</td>
						<td>${item.telphone }</td>
						<td><c:if test="${item.isUseBookYun eq '1'}">是</c:if><c:if test="${item.isUseBookYun ne '1'}">否</c:if> </td>
						<td><exp:code code="USER_APPLY_STATUS" value="${item.status }"></exp:code> </td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>
							<c:if test="${item.status==0 }">
	 							<a href="javascript:examineFun(${item.id },'1')">通过</a>
	 							<a href="javascript:examineFun(${item.id },'2')">拒绝</a>
							</c:if>
							<a href="${basePath }busLookInfoController/viewLookInfo.html?id=${item.id}">详情</a>
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