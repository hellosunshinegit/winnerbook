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
<c:if test="${empty(dictionary.dicId)}">添加参数</c:if>
<c:if test="${!empty(dictionary.dicId)}">修改参数</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="<c:if test="${empty(dictionary.dicId)}">${basePath }dictionaryController/addSubmitDictionary.html</c:if><c:if test="${!empty(dictionary.dicId)}">${basePath }dictionaryController/updateSubmitDictionary.html</c:if>" method="post">
	<input type="hidden" name="dicId" value="${dictionary.dicId}"/>
    <div class="form_main">
        <dl>
            <dt><i>*</i>类型编码：</dt>
            <dd><input type="text" name="dicItemcode" id="dicItemcode" value="${dictionary.dicItemcode }" maxlength="50" require="true" requireMsg="类型编码为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>类型名称：</dt>
            <dd><input type="text" name="dicItemcodename" id="dicItemcodename" value="${dictionary.dicItemcodename }" maxlength="50" require="true" requireMsg="类型名称为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>key值 ：</dt>
            <dd><input type="text" name="dicItemname" id="dicItemname" value="${dictionary.dicItemname }" maxlength="10" require="true" requireMsg="key值 为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>value值：</dt>
            <dd><input type="text" name="dicItemvalue" id="dicItemvalue" value="${dictionary.dicItemvalue }" maxlength="10" require="true" requireMsg="value值为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>排序：</dt>
            <dd><input type="text" name="dicItemsort" id="dicItemsort" value="${dictionary.dicItemsort }" maxlength="10" require="true" requireMsg="排序为必填项!" dataType="Require"/></dd>
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