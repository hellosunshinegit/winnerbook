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
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		 var ue = UE.getEditor('busDetail');
		 ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
        });
	});

	//点击返回
	function backReturn(){
		window.location.href="${basePath}busInfoController/busInfoList.html";
	}	
</script>
</head>

<body>
<div class="page_title"><h5>
完善企业信息
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" method="post">
    <div class="form_main">
	        <dl>
	            <dt>企业管理员：</dt>
	            <dd>
	            	<div class="dd_radio">${busInfo.userUnitName}</div>
	            </dd>
	        </dl>
	        <dl>
	            <dt>企业名称：</dt>
	            <dd><input type="text" name="busName" id="busName" value="${busInfo.busName }" disabled="disabled"/></dd>
	        </dl>
	        <dl>
	            <dt>名牌授予时间：</dt>
	            <dd>
	            	<input type="text" name="brandDate" id="brandDate" value="${busInfo.brandDate}" disabled="disabled"/>
	        	</dd>
	        </dl>
	        <dl>
	            <dt>企业logo：</dt>
	            <dd>
	            	<c:choose>
	            		<c:when test="${!empty(busInfo.busLogo)}">
		            		<img alt="" src="${bathPath }${busInfo.busLogo}" width="50" height="50">
	            		</c:when>
	            		<c:otherwise>
	            			暂无上传图片
	            		</c:otherwise>
	            	</c:choose>
	            </dd>
	        </dl>
	        <dl>
	            <dt>企业描述：</dt>
	            <dd>
	            	<textarea rows="8" cols="50" name="busDes" id="busDes" maxlength="300" disabled="disabled">${busInfo.busDes}</textarea>
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
	                    <input type="button" onclick="backReturn()" class="btn btn-blue" value="返回"/>
	                </div>
	            </dd>
	        </dl>
    </div>
</form>
</div>
</body>
</html>