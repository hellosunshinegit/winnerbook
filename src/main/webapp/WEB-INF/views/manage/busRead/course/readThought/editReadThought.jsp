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

<!-- 验证表单 -->
<script type="text/javascript" src="${basePath }resources/js/validator_utf8.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		UE.getEditor('thoughtDes');
	});
	
	function submitForm(){
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			document.editForm.submit();
		}
	}
	
</script>
</head>
<body>
<div class="page_title"><h5>写读后感</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="<c:if test="${empty(readThought.thoughtId)}">${basePath }readThoughtController/addReadThoughtSubmit.html</c:if><c:if test="${!empty(readThought.thoughtId)}">${basePath }readThoughtController/updateSubmitReadThought.html</c:if>" method="post">
	<input type="hidden" name="thoughtId" value="${readThought.thoughtId }"/>
    <div class="form_main">
	        <%-- <dl>
	            <dt><i>*</i>选择课程：</dt>
	            <dd>
	            	<select name="courseId" id="courseId" require="true" requireMsg="课程类型为必选项!" dataType="Require" style="width: 200px;">
	            		<option value="">---请选择---</option>
	            		<c:forEach items="${courseList }" var="item">
	            			<option value="${item.courseId }"  <c:if test="${item.courseId eq readThought.courseId }">selected="selected"</c:if> >${item.title }</option>
	            		</c:forEach>
	            	</select>
	            </dd>
	        </dl> --%>
	        <dl>
	            <dt><i>*</i>输入书籍名称：</dt>
	            <dd>
	            	<input type="text" name="bookListName" id="bookListName" value="${readThought.bookListName }" maxlength="50" require="true" requireMsg="书籍名称为必填项!" dataType="Require"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt>读后感标题：</dt>
	            <dd>
	            	<input type="text" name="thoughtTitle" id="thoughtTitle" value="${readThought.thoughtTitle }" maxlength="100" />
	            </dd>
	        </dl>
	        <dl>
	            <dt>上传文档：</dt>
	            <dd style="padding-top: 10px;">
	            	<c:if test="${!empty(readThought.thoughtUrl)}">
	            		已上传文件：<a href="${basePath }readThoughtController/downLoadReadThought.html?thoughtId=${readThought.thoughtId }" style="color: blue;">${readThought.thoughtFilename }</a>	
	            	</c:if>
	            	<div id="upload_thoughtUrl"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=thoughtUrl&path=course&typeExts=4" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            	<input type="hidden" name="thoughtUrl" id="thoughtUrl" value="${readThought.thoughtUrl }" maxlength="100" />
	            	<input type="hidden" name="thoughtFilename" id="name_thoughtUrl" value="${readThought.thoughtFilename }" maxlength="100" />
	            </dd>
	        </dl>
	        <dl>
	            <dt><i>*</i>是否公开：</dt>
	            <dd>		
	                <exp:select code="READ_THOUGHT_OPEN" name="isOpen" id="isOpen" value="${readThought.isOpen}"  require="true" requireMsg="是否公开为必填项!" dataType="Require"></exp:select>
	            </dd>
	        </dl>
	        <dl>
	            <dt><i>*</i>读后感：</dt>
	            <dd>
	            	<textarea name="thoughtDes" id="thoughtDes" style="width:900px;height:300px;" require="true" requireMsg="读后感为必填项!" dataType="Require" >${readThought.thoughtDes}</textarea>
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