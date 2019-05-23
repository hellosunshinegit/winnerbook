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

<script type="text/javascript">

function submitForm(){
		var userId = '${user.userId}';
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			var userName = $("#userName").val();
			var checkResult = checkUserName(userId,userName);
			if(checkResult){
				document.editForm.submit();
			}
		}
	}
	
	//验证userName唯一性
	function checkUserName(userId,userName){
		userId = userId!=""?userId:"";
		var result = true;
		$.ajax({
			type:"POST",
			async: false,
			dataType:"html",
			url:"${basePath}userController/checkUserName.html?userId="+userId+"&userName="+userName,
			success:function(data){
				var dataJson = eval("("+data+")");
				if(dataJson.userNameCount>0){
					alert("该手机号已存在，请重新输入");
					result=false;
				}
			}
		});
		return result;
	}
	
	

	
</script>
</head>
<body>
<div class="page_title"><h5>
<c:if test="${empty(user.userId)}">添加用户</c:if>
<c:if test="${!empty(user.userId)}">修改用户</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="<c:if test="${empty(user.userId)}">${basePath }userController/addSubmitUser.html</c:if><c:if test="${!empty(user.userId)}">${basePath }userController/updateSubmitUser.html</c:if>" method="post">
	<input type="hidden" name="userId" value="${user.userId }"/>
    <div class="form_main">
        <dl>
            <dt><i>*</i>手机号：</dt>
            <dd><input type="text" name="userName" id="userName" value="${user.userName }" maxlength="11" require="true" requireMsg="登录名为必填项!" dataType="Mobile" msg="手机格式不正确"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>姓名：</dt>
            <dd><input type="text" name="userUnitName" value="${user.userUnitName }" maxlength="50" require="true" requireMsg="个人/企业名为必填项!" dataType="Require" />
            </dd>
        </dl>
        <dl>
            <dt>所属部门：</dt>
            <dd>
                <input type="text" name="department" id="department" value="${user.department}" maxlength="50"/>
            </dd>
        </dl>
        <dl>
            <dt>是否部门主管：</dt>
            <dd>
                <exp:select code="USER_LEADER" name="isDepartLeader" id="isDepartLeader" value="${user.isDepartLeader}" headerKey="" headerValue="---请选择---"></exp:select>
            </dd>
        </dl>
        <dl>
            <dt><i>*</i>使用状态：</dt>
            <dd>		
                <exp:select code="STATUS" name="userStatue" id="userStatue" value="${user.userStatue}"  require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
            </dd>
        </dl>
        <c:choose>
        	<c:when test="${sessionUser.userId eq userAdminId }">
        		<dl>
		            <dt><i>*</i>企业管理员：</dt>
		            <dd>		
		            	<div class="dd_radio">
		            		<input type="radio" name="isBusinessAdmin" <c:if test="${user.isBusinessAdmin eq '0'}"> checked </c:if> checked="checked" value="0" />否&nbsp;&nbsp;&nbsp;&nbsp;
		            		<input type="radio" name="isBusinessAdmin" <c:if test="${user.isBusinessAdmin eq '1'}"> checked </c:if> value="1" />是
		            	</div>
		            </dd>
		        </dl>
        	</c:when>
        	<c:otherwise>
        		<input type="hidden" name="isBusinessAdmin" value="0"/>
        	</c:otherwise>
        </c:choose>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                    <input type="reset" class="btn" value="重置">
                </div>
            </dd>
        </dl>
    </div>
</form>
</div>
</body>
</html>