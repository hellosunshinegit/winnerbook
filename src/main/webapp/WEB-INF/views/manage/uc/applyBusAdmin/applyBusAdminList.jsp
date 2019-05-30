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
    <script type="text/javascript" src="${basePath }resources/js/jquery.form.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/layer/layer.js"></script>
	<script type="text/javascript">
		//重置
		function resetFun(){
			$("#userName").val("");	
			$("#status").val("");
		}
		
		//点击审核
		function examineFun(uaId,type){//1通过 2拒绝
			//layer 方法
			var statusStr = "";
			if(type==1){
				statusStr = "<div style='color:red;font-size:13px;padding:10px;'>审核通过后，会为该用户创建企业管理员的权限！！！</div>";
			}
			var str = "<div style='text-align: center;width:100%;'>"+statusStr+"<textarea rows='8' cols='40' id='statusReason' maxlength='400' placeholder='请填写原因'></textarea></div>";
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
					  var str = {"uaId":uaId,"status":type,"statusReason":statusReason};
					  //提交
					  $.ajax({
							type:"POST",
							async: false,
							dataType:"json",
						    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
							data:JSON.stringify(str),
							url:"${basePath}userApplyBusAdminController/updateSubmitUser.html",
							success:function(data){
								console.log(data);
								if(data.success){
									alert("操作成功");
									window.location.href="${basePath}userApplyBusAdminController/userApplyList.html";
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
      <h5>
        申请列表
      </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}userApplyBusAdminController/userApplyList.html" method="post" >
        <div class="data_search clearfix">
          <dl>
            <dt>
             	申请人：
            </dt>
            <dd>
              <input type="text" name="userName" id="userName" value="${userName }">
            </dd>
            <dt>
             	申请状态：
            </dt>
            <dd>	
                <exp:select code="USER_APPLY_STATUS" name="status"  id="status" value="${status}" headerKey="" headerValue="---请选择---"></exp:select>
			</dd>
            <dt>
              &nbsp;
            </dt>
            <dd>
              <input type="submit" class="btn btn-blue" id="search" value="搜索"></input>
              <input type="button" class="btn btn-blue" value="重置" onclick="resetFun()">    
            </dd>
          </dl>
        </div>
         <div class="data_list">
          <table>
            <thead>
              <tr>
              	<td>序号</td>
              	<td>申请人</td>
              	<td>申请企业名称</td>
				<td>申请描述</td>
				<td>状态</td>
				<td>创建时间</td>
				<td>操作</td>	
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.userName}</td>
						<td>${item.applyBusName}</td>
						<td>${item.applyBusDes}</td>
						<td><exp:code code="USER_APPLY_STATUS" value="${item.status}" /></td>
						<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
						<td>
							<c:if test="${item.status==0 }">
	 							<a href="javascript:examineFun(${item.uaId },'1')">通过</a>
	 							<a href="javascript:examineFun(${item.uaId },'2')">拒绝</a>
							</c:if>
							<a href="${basePath }userApplyBusAdminController/viewApplyBusAdmin.html?uaId=${item.uaId}">详情</a>
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