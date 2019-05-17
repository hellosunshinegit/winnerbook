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
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			document.editForm.submit();
		}
	}
</script>
</head>
<body>
<div class="page_title"><h5>
<c:if test="${empty(role.roleId)}">添加角色</c:if>
<c:if test="${!empty(role.roleId)}">修改角色</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="<c:if test="${empty(role.roleId)}">${basePath }roleController/addSubmitRole.html</c:if><c:if test="${!empty(role.roleId)}">${basePath }roleController/updateSubmitRole.html</c:if>" method="post">
	<input type="hidden" name="roleId" value="${role.roleId }"/>
    <div class="form_main">
        <dl>
            <dt><i>*</i>角色名称：</dt>
            <dd><input type="text" name="roleName" id="roleName" value="${role.roleName }" maxlength="10" require="true" requireMsg="角色名称为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt>角色描述：</dt>
            <dd>
            	 <textarea rows="3" cols="50" name="roleDesc" id="roleDesc" maxlength="100">${role.roleDesc }</textarea>
            </dd>
        </dl>
        <dl>
            <dt><i>*</i>使用状态	：</dt>
            <dd>		
                <exp:select code="STATUS" name="roleStatus" id="roleStatus" value="${role.roleStatus}" require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
            </dd>
        </dl>
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