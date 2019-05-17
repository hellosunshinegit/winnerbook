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
			if(tagsCount()>9){
				alert("标签添加不可以超过10个");
				return false;
			}
			document.editForm.submit();
		}
	}
	
	function tagsCount(){
		var resultData = 0;
		$.ajax({
			type:"POST",
			async: false,
			dataType:"json",
			url:"${basePath}tagsController/tagsCount.html",
			success:function(data){
				resultData = data;
			}
		});
		return resultData;
	}
</script>
</head>
<body>
	<div class="page_title">
		<h5>
			<c:if test="${empty(tags.tagId)}">添加标签信息</c:if>
			<c:if test="${!empty(tags.tagId)}">修改标签信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(tags.tagId)}">${basePath }tagsController/addSubmitTags.html</c:if><c:if test="${!empty(tags.tagId)}">${basePath }tagsController/updateSubmitTags.html</c:if>" method="post">
			<input type="hidden" name="tagId" id="tagId" value="${tags.tagId}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>标签名称：</dt>
					<dd>
						<input type="text" name="tagName" id="tagName" value="${tags.tagName }" maxlength="50" require="true" requireMsg="标签名称为必填项!" dataType="Require" />
					</dd>
				</dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="tagStatus" id="tagStatus" value="${tags.tagStatus}"  require="true" requireMsg="使用状态为必填项!" dataType="Require" style="width: 150px;"></exp:select>
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