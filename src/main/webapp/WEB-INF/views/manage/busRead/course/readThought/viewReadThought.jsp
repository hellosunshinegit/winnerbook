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

	$(document).ready(function() {
		var ue = UE.getEditor('thoughtDes');
		ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
        });
	});
	
</script>
</head>
<body>
<div class="page_title"><h5>读后感详情</h5></div>
<div class="page_main">
    <div class="form_main">
	       <dl>
	            <dt>书籍名称：</dt>
	            <dd>
	            	<input type="text" name="bookListName" id="bookListName" value="${readThought.bookListName }" disabled="disabled" style="width: 200px;"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上传文档：</dt>
	            <dd style="padding-top: 10px;">
	            	<c:if test="${!empty(readThought.thoughtUrl)}">
	            		已上传文件：<a href="${basePath }readThoughtController/downLoadReadThought.html?thoughtId=${readThought.thoughtId }" style="color: blue;">${readThought.thoughtFilename }</a>	
	            	</c:if>
	            	<c:if test="${empty(readThought.thoughtUrl)}">
	            		暂无上传文档	
	            	</c:if>
	            </dd>
	        </dl>
	         <dl>
	            <dt>是否公开：</dt>
	            <dd>		
	                <exp:select code="READ_THOUGHT_OPEN" name="isOpen" id="isOpen" value="${readThought.isOpen}" disabled="true"></exp:select>
	            </dd>
	        </dl>
	        <dl>
	            <dt>读后感：</dt>
	            <dd>
	            	<textarea name="thoughtDes" id="thoughtDes" style="width:900px;height:300px;" >${readThought.thoughtDes}</textarea>
	            </dd>
	        </dl>
	        <dl>
	            <dt>&nbsp;</dt>
	            <dd>
	                <div>
	                    <input type="button" onclick="javascript:window.location.href='${basePath}readThoughtController/readThoughtList.html'" class="btn btn-blue" value="返回"/>
	                </div>
	            </dd>
	        </dl>
    </div>
</form>
</div>
</body>
</html>