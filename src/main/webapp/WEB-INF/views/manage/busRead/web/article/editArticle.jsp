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

<!-- 下拉搜索 -->
<link rel="stylesheet" href="${basePath }resources/select2/select2.min.css" />
<script src="${basePath }resources/select2/select2.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		 UE.getEditor('articleContent');
		//下拉选择搜索框
		 $('#articleTypeId').select2();
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

	function submitForm() {
		var editForm = document.getElementById("editForm");
		if (Validator.Validate(editForm)) {
			//获取所选择的文章标签
			var articleTags = "";
			var articleTagIds = "";
			$.each($("input[id^='tag']:checked"),function(index,item){
				if(index!=0){
					articleTags+=",";
					articleTagIds+=",";
				}
				articleTagIds+=item.value.split("-")[0];
				articleTags+=item.value.split("-")[1];
			});
			$("#articleTags").val(articleTags);
			$("#articleTagIds").val(articleTagIds);
		
			
			//获取板块id
			var blockStr = "";
			$.each($("input[id^='articleBlock']:checked"),function(index,item){
				if(index!=0){
					blockStr+=",";
				}
				blockStr+=item.value;
			});
			$("#blockStr").val(blockStr);
			
			//文章类型和文章标签至少输入一项判断
			var articleTypeId = $("#articleTypeId").val();
			if(articleTypeId=='' && articleTags==''){
				alert("文章类型和文章标签至少输入一项");
				return false;
			}
			
			document.editForm.submit();
		}
	}
</script>
</head>
<body>
	<div class="page_title">
		<h5>
			<c:if test="${empty(article.articleId)}">添加文章信息</c:if>
			<c:if test="${!empty(article.articleId)}">修改文章信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(article.articleId)}">${basePath }articleController/addSubmitArticle.html</c:if><c:if test="${!empty(article.articleId)}">${basePath }articleController/updateSubmitArticle.html</c:if>" method="post">
			<input type="hidden" name="articleId" id="articleId" value="${article.articleId}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>标题：</dt>
					<dd>
						<input type="text" name="articleTitle" id="articleTitle" value="${article.articleTitle }" maxlength="50" require="true" requireMsg="标题为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>
						<i>*</i>缩略图：
					</dt>
					<dd>
						<input type="hidden" name="articleImg" id="articleImg" value="${article.articleImg }" />
						<c:if test="${!empty(article.articleImg) }">
							<a href="${basePath}${article.articleImg}" target="_blank"><img src="${basePath}${article.articleImg}" width="60" height="60"></a>
						</c:if>
						<div id="upload_articleImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=articleImg&path=article&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>作者：</dt>
		            <dd>
		            	<input type="text" name="articleAuthor" id="articleAuthor" value="${article.articleAuthor }" maxlength="50" require="true" requireMsg="文章作者为必填项!" dataType="Require" style="width: 200px;"/>
		            </dd>
		        </dl>
				<dl>
		            <dt>文章类型：</dt>
		            <dd>
		            	<select name="articleTypeId" id="articleTypeId" class="courseIdSelect" style="width: 210px;">
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
		            	<c:forEach items="${tagList }" var="item">
		            		<input type="checkbox" name="tag" id="tag${item.tagId}" value="${item.tagId }-${item.tagName}" style="width: 18px;height: 18px;"/>${item.tagName }
		            	</c:forEach>
		            	<input type="hidden" name="articleTags" id="articleTags" value="${article.articleTags }"/>
		            	<input type="hidden" name="articleTagIds" id="articleTagIds" />
		            </dd>
		        </dl>
				<dl>
		            <dt>文章所属版块：</dt>
		            <dd>
		            	<c:forEach items="${blockList }" var="item">
		            		<input type="checkbox" name="articleBlock" id="articleBlock${item.blockId}" value="${item.blockId }" style="width: 18px;height: 18px;"/>${item.blockName }
		            	</c:forEach>
		            	<input type="hidden" name="blockStr" id="blockStr" value="${article.blockStr }" />
		            </dd>
		        </dl>
				<dl>
		            <dt><i>*</i>推送频道：</dt>
		            <dd>
		            	<exp:select code="CHANNEL" name="articleChannel" id="articleChannel" value="${article.articleChannel }" require="true" requireMsg="推送频道为必填项!" dataType="Require" style="width: 210px;"></exp:select>
		            </dd>
		        </dl>
		        <dl>
					<dt>描述：</dt>
					<dd>
						<textarea rows="8" cols="50" name="articleDes" id="articleDes" maxlength="300" style="width: 500px;height: 100px;">${article.articleDes}</textarea>
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>文章状态：</dt>
		            <dd>
		            	<exp:select code="ARTICLE_STATUS" name="articleStatus" id="articleStatus"  value="${article.articleStatus }" require="true" requireMsg="文章状态为必填项!" dataType="Require" style="width: 210px;"></exp:select>
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