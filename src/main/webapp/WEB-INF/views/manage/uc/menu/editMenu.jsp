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
<c:if test="${empty(menu.menuId)}">添加菜单</c:if>
<c:if test="${!empty(menu.menuId)}">修改菜单</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="<c:if test="${empty(menu.menuId)}">${basePath }menuController/addSubmitMenu.html</c:if><c:if test="${!empty(menu.menuId)}">${basePath }menuController/updateSubmitMenu.html</c:if>" method="post">
	<input type="hidden" name="menuId" value="${menu.menuId }"/>
    <div class="form_main">
        <dl>
            <dt><i>*</i>模块名称：</dt>
            <dd><input type="text" name="menuName" id="menuName" value="${menu.menuName }" maxlength="10" require="true" requireMsg="模块名称为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt>父模块名称：</dt>
            <dd>
	            <select name="menuParentid" id="menuParentid">
	            	<option value="">---请选择---</option>
	            	<c:forEach items="${menuList}" var="item">
	            		<c:if test="${fn:length(item.menuCode)==2 }"><option value="${item.menuId}" <c:if test="${menu.menuParentid eq item.menuId}">selected="selected"</c:if>>${item.menuName }</option></c:if>
            			<c:if test="${fn:length(item.menuCode)==4 }"><option value="${item.menuId}" <c:if test="${menu.menuParentid eq item.menuId}">selected="selected"</c:if>>----${item.menuName }</option></c:if>
            		</c:forEach>
	            </select>
            </dd>
        </dl>
        <dl>
            <dt><i>*</i>模块编码：</dt>
            <dd><input type="text" name="menuCode" id="menuCode" value="${menu.menuCode }" maxlength="10" require="true" requireMsg="模块编码为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt>页面地址：</dt>
            <dd><input type="text" name="menuUrl" id="menuUrl" value="${menu.menuUrl }" /></dd>
        </dl>
        <dl>
            <dt><i>*</i>使用状态	：</dt>
            <dd>		
                <exp:select code="STATUS" name="menuStatus" id="menuStatus" value="${menu.menuStatus}" require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
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