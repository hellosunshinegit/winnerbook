<%@ page contentType="text/html; charset=UTF-8"%>
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

<script type="text/javascript">
	function submitForm() {
		var editForm = document.getElementById("editForm");
		if (Validator.Validate(editForm)) {
			document.editForm.submit();
		}
	}
</script>
</head>
<body>
	<div class="page_title">
		<h5>
			<c:if test="${empty(courseType.typeId)}">添加课程类型信息</c:if>
			<c:if test="${!empty(courseType.typeId)}">修改课程类型信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(courseType.typeId)}">${basePath }courseTypeController/addSubmitCourseType.html</c:if><c:if test="${!empty(courseType.typeId)}">${basePath }courseTypeController/updateSubmitCourseType.html</c:if>" method="post">
			<input type="hidden" name="typeId" id="typeId" value="${courseType.typeId}"/>
			<div class="form_main">
				<dl>
					<dt>
						<i>*</i>课程类型名称：
					</dt>
					<dd>
						<input type="text" name="typeName" id="typeName" value="${courseType.typeName }" maxlength="20" require="true"
							requireMsg="课程类型名称为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>课程类型描述：</dt>
					<dd>
						<textarea rows="8" cols="50" name="typeDesc" id="typeDesc" maxlength="300">${courseType.typeDesc}</textarea>
					</dd>
				</dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="typeStatus" id="typeStatus" value="${courseType.typeStatus}"  require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
		            </dd>
		        </dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<div>
							<input type="button" onclick="submitForm()" class="btn btn-blue"
								value="保存" /> <input type="reset" class="btn" value="重置">
						</div>
					</dd>
				</dl>
			</div>
		</form>
	</div>
</body>
</html>