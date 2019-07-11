<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="${basePath }resources/css/rui.css">
<script type="text/javascript" src="${basePath }resources/css/rui.js"></script>
<!-- Global Framework -->
<link rel="stylesheet" type="text/css" href="${basePath }resources/css/common.css">
<script type="text/javascript" src="${basePath }resources/css/common.js"></script>
<script type="text/javascript" src="${basePath }resources/js/jquery-1.8.1.min.js"></script>
<!-- 验证表单 -->
<script type="text/javascript" src="${basePath }resources/js/validator_utf8.js"></script>
<script type="text/javascript" src="${basePath }resources/js/jquery.form.js"></script>

<!-- 下拉搜索 -->
<link rel="stylesheet" href="${basePath }resources/select2/select2.min.css" />
<script src="${basePath }resources/select2/select2.min.js"></script>

<style type="text/css">
	.form_main{font-size: 14px;}
</style>

 <script type="text/javascript">
 
	$(document).ready(function(){
		//下拉选择搜索框
	    $('#selectUserId').select2();
	});
 
	//保存
	function submitForm(){
		//是否选中
		var empId = $("#selectUserId").val();
		console.log(selectUserId);
		if(selectUserId==""){
			alert("请选择权限转移用户");
		}else{
			$("form[name=editForm]").ajaxSubmit({
				type:"GET",
				dataType:"html",
				url:"${basePath}userController/busAdminTransferSubmit.html?userId=${user.userId}&selectUserId="+empId,
			    async: false,
			    success: function(data){
			    	if(data==1){
			    		alert("交换成功");
			    		window.location.href="${basePath}userController/userList.html";
			    	}
			    }
			});  
		}
	}
	
</script> 
</head>
<body>
<div class="page_title"><h5>用户【${user.userName}】管理权限转移</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" method="post">
	<input type="hidden" name="userId" value="${user.userId }"/>
    <div class="form_main">
        <dl>
	         <dt>用户：</dt>
	         <dd>${user.userName}</dd>
	     </dl>
        <dl>
        	 <dt>权限转移列表：</dt>
            <dd style="line-height: 30px;">
            	<c:if test="${empUserList.size()==0}">
            		暂无数据...
            	</c:if>
            	<c:if test="${empUserList.size()>0}">
            		<select name="selectUserId" id="selectUserId" class="courseIdSelect" style="width: 210px;">
	            		<option value="">---请选择---</option>
	            		<c:forEach items="${empUserList}" var="item">
	            			<option value="${item.userId }" >${item.userUnitName}</option>
	            		</c:forEach>
	            	</select>
	            	<span style="color: red;">注：可下拉搜索</span>
            	</c:if>
            </dd>
        </dl>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                    <input type="button" class="btn btn-blue" value="返回" onclick="javascript:window.location.href='${basePath}userController/userList.html'" />
                </div>
            </dd>
        </dl>
    </div>
</form>
</div>
</body>
</html>