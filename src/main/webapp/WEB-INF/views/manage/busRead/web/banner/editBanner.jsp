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
			<c:if test="${empty(banner.bannerId)}">添加宣传图信息</c:if>
			<c:if test="${!empty(banner.bannerId)}">修改宣传图信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(banner.bannerId)}">${basePath }bannerController/addSubmitBanner.html</c:if><c:if test="${!empty(banner.bannerId)}">${basePath }bannerController/updateSubmitBanner.html</c:if>" method="post">
			<input type="hidden" name="bannerId" id="bannerId" value="${banner.bannerId}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>标题：</dt>
					<dd>
						<input type="text" name="bannerTitle" id="bannerTitle" value="${banner.bannerTitle }" maxlength="50" require="true" requireMsg="主题为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>宣传图：</dt>
		            <dd>
						<div id="upload_bannerUrl"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=bannerUrl&path=web&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
		            	<input type="hidden" name="bannerUrl" id="bannerUrl" value="${banner.bannerUrl }" maxlength="50" require="true" requireMsg="宣传图为必填项!" dataType="Require" />
		            	<c:if test="${!empty(banner.bannerUrl)}">
			            	<span><a href="${basePath}${banner.bannerUrl}" target="_blank"><img alt="" src="${basePath}${banner.bannerUrl}" width="60" height="80"></a></span>
		            	</c:if>
		            	<span style="color:red;">（注：上传图片宽高比例4：1）</span>
		            </dd>
		        </dl>
		        <dl>
					<dt><i>*</i>推送频道：</dt>
					<dd>
						<exp:select code="BANNER_TYPE" name="bannerType" id="bannerType" value="${banner.bannerType}" require="true" requireMsg="推送频道为必填项!" dataType="Require"></exp:select>
					</dd>
				</dl>
				<dl>
					<dt>跳转url：</dt>
					<dd>
						<input type="text" name="bannerClickUrl" id="bannerClickUrl" value="${banner.bannerClickUrl }" maxlength="200"  style="width: 500px;"/>
					</dd>
				</dl>
		        <dl>
					<dt><i>*</i>状态：</dt>
					<dd>
						<exp:select code="STATUS" name="bannerStatus" id="bannerStatus" value="${banner.bannerStatus }"></exp:select>
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