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
			<c:if test="${empty(articleType.typeId)}">添加文章类型信息</c:if>
			<c:if test="${!empty(articleType.typeId)}">修改文章类型信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(articleType.typeId)}">${basePath }articleTypeController/addSubmitArticle.html</c:if><c:if test="${!empty(articleType.typeId)}">${basePath }articleTypeController/updateSubmitArticleType.html</c:if>" method="post">
			<input type="hidden" name="typeId" id="typeId" value="${articleType.typeId}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>文章类型名称：</dt>
					<dd>
						<input type="text" name="typeName" id="typeName" value="${articleType.typeName }" maxlength="50" require="true" requireMsg="文章类型名称为必填项!" dataType="Require" />
					</dd>
				</dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="typeStatus" id="typeStatus" value="${articleType.typeStatus}"  require="true" requireMsg="使用状态为必填项!" dataType="Require" style="width: 150px;"></exp:select>
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