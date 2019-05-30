<%@ page contentType="text/html; charset=UTF-8"%>
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

</head>
<body>
	<div class="page_title">
		<h5>申请信息</h5>
	</div>
	<div class="page_main">
		<div class="form_main">
			<dl>
				<dt>
					申请人：
				</dt>
				<dd>
					<input type="text" name="userName" id="userName" value="${applyBusAdmin.userName }" disabled="disabled" />
				</dd>
			</dl>
			<dl> 
				<dt>
					申请企业名称：
				</dt>
				<dd>
					<input type="text" name="applyBusName" id="applyBusName" value="${applyBusAdmin.applyBusName }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					申请的描述：
				</dt>
				<dd>
					<textarea rows="8" cols="50" name="applyBusDes" id="applyBusDes" disabled="disabled">${applyBusAdmin.applyBusDes}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>
					申请时间：
				</dt>
				<dd>
            		<fmt:formatDate value="${applyBusAdmin.createDate }" type="both"/>
				</dd>
			</dl>
			<dl>
				<dt>
					状态：
				</dt>
				<dd>
					<exp:code code="USER_APPLY_STATUS" value="${applyBusAdmin.status}" />
				</dd>
			</dl>
			<dl>
				<dt>
					审核通过时间：
				</dt>
				<dd>
            		<fmt:formatDate value="${applyBusAdmin.successDate }" type="both"/>
				</dd>
			</dl>
			<dl>
				<dt>
					审核备注：
				</dt>
				<dd>
					<textarea rows="8" cols="50" name="statusReason" id="statusReason" disabled="disabled">${applyBusAdmin.statusReason}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>&nbsp;</dt>
				<dd>
					<div>
						<input type="button" onclick="javascript:window.location.href='${basePath}userApplyBusAdminController/userApplyList.html'" class="btn btn-blue" value="返回" /> 
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>