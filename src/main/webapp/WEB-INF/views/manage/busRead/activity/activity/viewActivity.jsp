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
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		 var ue = UE.getEditor('content');
		 ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
         });
	});
	
	function returnFun(){
		window.location.href="${basePath}activityController/activityList.html";
	}

</script>
</head>
<body>
	<div class="page_title">
		<h5>查看活动信息</h5>
	</div>
	<div class="page_main">
			<div class="form_main">
				<dl>
					<dt>主题：</dt>
					<dd>
						<input type="text" name="title" id="title" value="${activity.title }" disabled="disabled" />
					</dd>
				</dl>
				<dl>
					<dt>微博正文：</dt>
					<dd>
						<input type="text" name="wbTitle" id="wbTitle" value="${activity.wbTitle }" disabled="disabled"/>
					</dd>
				</dl>
				<dl>
					<dt>
						微博正文图：
					</dt>
					<dd>
						<c:if test="${!empty(activity.wbImg) }">
							<a href="${basePath}${activity.wbImg}" target="_blank"><img src="${basePath}${activity.wbImg}" width="50" height="50"></a>
						</c:if>
					</dd>
				</dl>
				<dl>
					<dt>
						活动缩略图：
					</dt>
					<dd>
						<c:if test="${!empty(activity.imgUrl) }">
							<a href="${basePath}${activity.imgUrl}" target="_blank"><img src="${basePath}${activity.imgUrl}" width="50" height="50"></a>
						</c:if>
					</dd>
				</dl>
				<dl>
		            <dt>时间：</dt>
		            <dd>
		            	<input type="text" name="endDate" id="endDate" value="${activity.endDate}" disabled="disabled" style="width: 150px;"/>
		            </dd>
		        </dl>
		        
				 <dl>
		            <dt>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="status" id="status" value="${activity.status}" disabled="true" style="width: 150px;"></exp:select>
		            </dd>
		        </dl>
		        <dl>
		            <dt>活动地址：</dt>
		            <dd>		
		               <input type="text" name="address" id="address" value="${activity.address }" disabled="disabled" />
		               <input type="text" name="detailAddress" id="detailAddress" value="${activity.detailAddress}" disabled="disabled"/>
		            </dd>
		        </dl>
		        <dl>
		            <dt>内容细节：</dt>
		            <dd>
		            	<textarea name="content" id="content" style="width:900px;height:300px;">${activity.content}</textarea>
		            </dd>
		        </dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<div>
							<input type="button" onclick="returnFun()" class="btn btn-blue" value="返回" /> 
						</div>
					</dd>
				</dl>
			</div>
	</div>
</body>
</html>