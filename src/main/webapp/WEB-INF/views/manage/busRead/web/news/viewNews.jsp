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
		var ue = UE.getEditor('articleContent');
		ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
        });
		var articleId = '${article.articleId}';
		if(articleId!=undefined){
			//选中多选框
			var articleTagIds = '${article.articleTagIds}';
			var blockStr = '${article.blockStr}';
			if(articleTagIds.length>0){
				var articleTagIdArray= articleTagIds.split(",");
				$.each(articleTagIdArray,function(index,item){
					$("#tag"+item).attr("checked", true);
				});
			}
			if(blockStr.length>0){
				var blockStrArray= blockStr.split(",");
				$.each(blockStrArray,function(index,item){
					$("#articleBlock"+item).attr("checked", true);
				});
			}
		}
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
					<input type="text" name="articleTitle" id="articleTitle" value="${article.articleTitle }" disabled="disabled" />
				</dd>
			</dl>
			<dl>
	            <dt>作者：</dt>
	            <dd>
	            	<input type="text" name="articleAuthor" id="articleAuthor" value="${article.articleAuthor }" disabled="disabled" style="width: 200px;"/>
	            </dd>
	        </dl>
			<dl>
	            <dt>文章类型：</dt>
	            <dd>
	            	<select name="articleTypeId" id="articleTypeId" class="courseIdSelect" style="width: 210px;" disabled="disabled">
	            		<option value="">---请选择---</option>
	            		<c:forEach items="${articleTypeList}" var="item">
	            			<option value="${item.typeId }" <c:if test="${item.typeId eq article.articleTypeId}"> selected </c:if> >&nbsp;&nbsp;${item.typeName}</option>
	            		</c:forEach>
	            	</select>
	            	<span style="color: red;font-size: 12px;">（注：文章类型和文章标签至少输入一项）</span>
	            </dd>
	        </dl>
			<dl>
	            <dt>文章标签：</dt>
	            <dd>
	            	<c:forEach items="${tagList }" var="item" >
	            		<input type="checkbox" name="tag" id="tag${item.tagId}" value="${item.tagId }-${item.tagName}" style="width: 18px;height: 18px;" disabled="disabled"/>${item.tagName }
	            	</c:forEach>
	            </dd>
	        </dl>
			<dl>
	            <dt>文章所属版块：</dt>
	            <dd>
	            	<c:forEach items="${blockList }" var="item">
	            		<input type="checkbox" name="articleBlock" id="articleBlock${item.blockId}" value="${item.blockId }" style="width: 18px;height: 18px;" disabled="disabled"/>${item.blockName }
	            	</c:forEach>
	            </dd>
	        </dl>
			<dl>
	            <dt>推送频道：</dt>
	            <dd>
	            	<exp:select code="CHANNEL" name="articleChannel" id="articleChannel" headerKey="" headerValue="---请选择---" value="${article.articleChannel }" disabled="true" style="width: 210px;"></exp:select>
	            </dd>
	        </dl>
	        <dl>
				<dt>描述：</dt>
				<dd>
					<textarea rows="8" cols="50" name="articleDes" id="articleDes" maxlength="300" style="width: 500px;height: 100px;" disabled="disabled">${article.articleDes}</textarea>
				</dd>
			</dl>
			<dl>
	            <dt>文章状态：</dt>
	            <dd>
	            	<exp:select code="ARTICLE_STATUS" name="articleStatus" id="articleStatus"  value="${article.articleStatus }" disabled="true" style="width: 210px;"></exp:select>
	            </dd>
	        </dl>
			<dl>
				<dt>内容：</dt>
				<dd>
            		<textarea name="articleContent" id="articleContent" style="width:900px;height:300px;">${article.articleContent}</textarea>
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