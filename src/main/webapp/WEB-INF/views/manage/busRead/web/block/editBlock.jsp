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
			<c:if test="${empty(block.blockId)}">添加读书会信息</c:if>
			<c:if test="${!empty(block.blockId)}">修改读书会信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(block.blockId)}">${basePath }blockController/addSubmitBlock.html</c:if><c:if test="${!empty(block.blockId)}">${basePath }blockController/updateSubmitBlock.html</c:if>" method="post">
			<input type="hidden" name="blockId" id="blockId" value="${block.blockId}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>版块名称：</dt>
					<dd>
						<input type="text" name="blockName" id="blockName" value="${block.blockName }" maxlength="50" require="true" requireMsg="主题为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>版块图片：</dt>
		            <dd>
						<div id="upload_blockImgUrl"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=blockImgUrl&path=web&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
		            	<input type="hidden" name="blockImgUrl" id="blockImgUrl" value="${block.blockImgUrl }" maxlength="50" require="true" requireMsg="版块图片为必填项!" dataType="Require"/>
		            	<c:if test="${!empty(block.blockImgUrl)}">
			            	<span><a href="${basePath}${block.blockImgUrl}" target="_blank"><img alt="" src="${basePath}${block.blockImgUrl}" width="60" height="80"></a></span>
		            	</c:if>
		            </dd>
		        </dl>
		        <dl>
					<dt><i>*</i>频道类型：</dt>
					<dd>
						<exp:select code="CHANNEL" name="blockType" id="blockType" value="${block.blockType }" require="true" requireMsg="频道类型为必填项!" dataType="Require"></exp:select>
					</dd>
				</dl>
		        <dl>
					<dt><i>*</i>状态：</dt>
					<dd>
						<exp:select code="STATUS" name="blockStatus" id="blockStatus" value="${block.blockStatus }"></exp:select>
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