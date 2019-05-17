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

<!-- 树形结构开始 -->
<link rel="stylesheet" type="text/css" href="${basePath}resources/js/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/treeSelect.css" />
<script type="text/javascript" src="${basePath}resources/js/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.js"></script>
<!-- 省市县查询 -->

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
<c:if test="${empty(address.addressId)}">添加地址</c:if>
<c:if test="${!empty(address.addressId)}">修改地址</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="<c:if test="${empty(address.addressId)}">${basePath }ucAddressController/addSubmitAddress.html</c:if><c:if test="${!empty(address.addressId)}">${basePath }ucAddressController/updateSubmitAddress.html</c:if>" method="post">
	<input type="hidden" name="addressId" value="${address.addressId }"/>
    <div class="form_main">
        <dl>
            <dt><i>*</i>地址名称：</dt>
            <dd><input type="text" name="addressName" id="addressName" value="${address.addressName }" maxlength="10" require="true" requireMsg="地址名称为必填项!" dataType="Require"/></dd>
        </dl>
        
        <dl>
            <dt><i>*</i>所属父节点：</dt>
            <dd>
            	<exp:treeSelect code="UC_ADDRESS" name="addressParentId" id="addressParentId" value="${address.addressParentId }" expendLevel="3" dataType="Require" msg="父节点不能为空!"> </exp:treeSelect>
            </dd>
        </dl>
        <dl>
            <dt><i>*</i>使用状态：</dt>
            <dd>		
                <exp:select code="STATUS" name="addressStatue" id="addressStatue" value="${address.addressStatue}" require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
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