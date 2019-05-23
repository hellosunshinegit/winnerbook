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
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		var ue = UE.getEditor('newContent');
		ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
        });
	});

</script>
</head>
<body>
	<div class="page_title">
		<h5>
			查看文章信息
		</h5>
	</div>
	<div class="page_main">
		<div class="form_main">
			<dl>
				<dt>标题：</dt>
				<dd>
					<input type="text" name="newTitle" id="newTitle" value="${news.newTitle }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>微博正文：</dt>
				<dd>
					<input type="text" name="wbTitle" id="wbTitle" value="${news.wbTitle }" maxlength="140" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					微博正文图：
				</dt>
				<dd>
					<c:if test="${!empty(news.wbImg) }">
						<a href="${basePath}${news.wbImg}" target="_blank"><img src="${basePath}${news.wbImg}" width="50" height="50"></a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>
					缩略图：
				</dt>
				<dd>
					<c:if test="${!empty(news.newImg) }">
						<a href="${basePath}${news.newImg}" target="_blank"><img src="${basePath}${news.newImg}" width="80" height="80"></a>
					</c:if>
				</dd>
			</dl>
			<dl>
	            <dt>作者：</dt>
	            <dd>
	            	<input type="text" name="newAuthor" id="newAuthor" value="${news.newAuthor }" maxlength="50" disabled="disabled" style="width: 200px;"/>
	            </dd>
	        </dl>
	        <dl>
				<dt>描述：</dt>
				<dd>
					<textarea rows="8" cols="50" name="newDes" id="newDes" maxlength="300" style="width: 500px;height: 100px;" disabled="disabled">${news.newDes}</textarea>
				</dd>
			</dl>
			<dl>
	            <dt>文章状态：</dt>
	            <dd>
	            	<exp:select code="ARTICLE_STATUS" name="newStatus" id="newStatus"  value="${news.newStatus }" disabled="true" style="width: 210px;"></exp:select>
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
						<input type="button" onclick="javascript:window.location.href='${basePath}articleController/articleList.html'" class="btn btn-blue" value="返回"/>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>