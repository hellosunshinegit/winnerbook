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
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		 UE.getEditor('newContent');
	});

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
			<c:if test="${empty(news.newId)}">添加企业风采信息</c:if>
			<c:if test="${!empty(news.newId)}">修改企业风采信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(news.newId)}">${basePath }newsController/addSubmitNews.html</c:if><c:if test="${!empty(news.newId)}">${basePath }newsController/updateSubmitNews.html</c:if>" method="post">
			<input type="hidden" name="newId" id="newId" value="${news.newId}"/>
			<input type="hidden" name="newChannel" id="newChannel" value="${news.newChannel}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>标题：</dt>
					<dd>
						<input type="text" name="newTitle" id="newTitle" value="${news.newTitle }" maxlength="50" require="true" requireMsg="标题为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>微博正文：</dt>
					<dd>
						<input type="text" name="wbTitle" id="wbTitle" value="${news.wbTitle }" maxlength="140" />
						<span style="color: red;">注：如果‘微博正文’内容为空，则发微博时采用‘标题’的内容</span>
					</dd>
				</dl>
				<dl>
					<dt>
						微博正文图：
					</dt>
					<dd>
						<input type="hidden" name="wbImg" id="wbImg" value="${news.wbImg }"/>
						<img alt="" id="wbImg" src="" width="150" height="150">
						<c:if test="${!empty(news.wbImg) }">
							<a href="${basePath}${news.wbImg}" target="_blank"><img src="${basePath}${news.wbImg}" width="50" height="50"></a>
						</c:if>
						<div id="upload_wbImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=wbImg&path=news/wb&typeExts=5" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
					<dt>
						<i>*</i>缩略图：
					</dt>
					<dd>
						<input type="hidden" name="newImg" id="newImg" value="${news.newImg }" require="true" requireMsg="缩略图为必填项!" dataType="Require"/>
						<c:if test="${!empty(news.newImg) }">
							<a href="${basePath}${news.newImg}" target="_blank"><img src="${basePath}${news.newImg}" width="80" height="80"></a>
						</c:if>
						<div id="upload_newImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=newImg&path=news&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>作者：</dt>
		            <dd>
		            	<input type="text" name="newAuthor" id="newAuthor" value="${news.newAuthor }" maxlength="50" require="true" requireMsg="文章作者为必填项!" dataType="Require" style="width: 200px;"/>
		            </dd>
		        </dl>
		        <dl>
					<dt>描述：</dt>
					<dd>
						<textarea rows="8" cols="50" name="newDes" id="newDes" maxlength="300" style="width: 500px;height: 100px;">${news.newDes}</textarea>
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>文章状态：</dt>
		            <dd>
		            	<exp:select code="ARTICLE_STATUS" name="newStatus" id="newStatus"  value="${news.newStatus }" require="true" requireMsg="状态为必填项!" dataType="Require" style="width: 210px;"></exp:select>
		            </dd>
		        </dl>
				<dl>
					<dt>内容：</dt>
					<dd>
	            		<textarea name="newContent" id="newContent" style="width:900px;height:300px;">${news.newContent}</textarea>
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