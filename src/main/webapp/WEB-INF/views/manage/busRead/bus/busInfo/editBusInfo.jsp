<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../common.jsp"%>
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

<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		 UE.getEditor('busDetail');
	});
	
	function submitForm(){
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			document.editForm.submit();
		}
	}
	
	function isAginFun(){
		var isAgin = $("#isAgin").is(':checked');
		if(isAgin){//重新上传
			$("#file").css("display","");
			$("#file").attr("require","true");
			$("#file").attr("requireMsg","企业logo为必选项!");
			$("#file").attr("dataType","Require");
		}else{
			$("#file").css("display","none");
			$("#file").removeAttr("require","true");
			$("#file").removeAttr("requireMsg","企业logo为必选项!");
			$("#file").removeAttr("dataType","Require");
		}	
	}
</script>
</head>
<body>
<div class="page_title"><h5>
<c:if test="${empty(busInfo.buId)}">添加企业信息</c:if>
<c:if test="${!empty(busInfo.buId)}">修改企业信息</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="${basePath }busInfoController/updateSubmitBusInfo.html" method="post" enctype="multipart/form-data">
    <div class="form_main">
   		<dl>
            <dt>企业管理员：</dt>
            <dd>
            	<div class="dd_radio">${busInfo.userUnitName}</div>
            	<input type="hidden" name="userId" value="${busInfo.userId }"/>
            </dd>
        </dl>
        <dl>
            <dt><i>*</i>企业名称：</dt>
            <dd><input type="text" name="busName" id="busName" value="${busInfo.busName }" maxlength="30" require="true" requireMsg="企业名称为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt><c:if test="${empty(busInfo.busLogo)}"><i>*</i></c:if>企业logo：</dt>
            <dd>
            	<c:if test="${!empty(busInfo.busLogo)}">
            		<img alt="" src="${bathPath }${busInfo.busLogo}" width="50" height="50">
            	</c:if>
            	<c:if test="${!empty(busInfo.busLogo)}">
            		<input type="checkbox" name="isAgin" id="isAgin" value="1" onchange="isAginFun(this)" >是否重新上传
            	</c:if>
            	&nbsp;&nbsp;
            	<input type="file" name="file" id="file" <c:if test="${empty(busInfo.busLogo)}">require="true"  requireMsg="企业logo为必选项!" dataType="Require" </c:if><c:if test="${!empty(busInfo.busLogo)}"> style="display:none "</c:if> />
            	<input type="hidden" name="busLogo" id="busLogo" value="${busInfo.busLogo}">
            </dd>
        </dl>
        <dl>
            <dt>企业描述：</dt>
            <dd>
            	<textarea rows="8" cols="50" name="busDes" id="busDes" maxlength="300">${busInfo.busDes}</textarea>
            </dd>
        </dl>
        <dl>
            <dt>企业详情：</dt>
            <dd>
            	<textarea name="busDetail" id="busDetail" style="width:900px;height:300px;">${busInfo.busDetail}</textarea>
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