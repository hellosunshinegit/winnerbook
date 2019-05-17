<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common.jsp"%>
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
<!-- <style type="text/css">
	.form_main input{background: #f5f5f5;}
	.form_main textarea{background: #f5f5f5;}
</style> -->

<script type="text/javascript">

	$(document).ready(function(){
		 var ue = UE.getEditor('clubDes');
		 ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
         });
	});
	
	function returnFun(){
		window.location.href="${basePath}readBookClubController/readBookClubList.html";
	}

</script>
</head>
<body>
	<div class="page_title">
		<h5>查看读书会信息</h5>
	</div>
	<div class="page_main">
			<div class="form_main">
				<dl>
					<dt>主题：</dt>
					<dd>
						<input type="text" name="clubTitle" id="clubTitle" value="${readBookClub.clubTitle }" disabled="disabled" />
					</dd>
				</dl>
				<dl>
		            <dt>时间：</dt>
		            <dd>
		            	<input type="text" name="clubDate" id="clubDate" value="${readBookClub.clubDate}" disabled="disabled" style="width: 150px;"/>
		            </dd>
		        </dl>
		        <dl>
					<dt>地点：</dt>
					<dd>
						<input type="text" name="clubPlace" id="clubPlace" value="${readBookClub.clubPlace }" disabled="disabled" />
					</dd>
				</dl>
		        <dl>
					<dt>主讲人：</dt>
					<dd>
						<input type="text" name="clubMainGuest" id="clubMainGuest" value="${readBookClub.clubMainGuest }" disabled="disabled"/>
					</dd>
				</dl>
		        <dl>
					<dt>推荐书目：</dt>
					<dd>
						<input type="text" name="clubMainGuestBook" id="clubMainGuestBook" value="${readBookClub.clubMainGuestBook }" disabled="disabled"/>
					</dd>
				</dl>
				<dl>
					<dt>主讲人介绍：</dt>
					<dd>
						<textarea rows="8" cols="50" name="clubMainGuestIntroduce" id="clubMainGuestIntroduce" maxlength="300" style="width: 500px;height: 100px;" disabled="disabled">${readBookClub.clubMainGuestIntroduce}</textarea>
					</dd>
				</dl>
				<dl>
					<dt>主持人：</dt>
					<dd>
						<input type="text" name="clubPresenter" id="clubPresenter" value="${readBookClub.clubPresenter }" maxlength="20" disabled="disabled"/>
					</dd>
				</dl>
				 <dl>
		            <dt>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="clubStatus" id="clubStatus" value="${readBookClub.clubStatus}"  require="true" requireMsg="使用状态为必填项!" dataType="Require" disabled="true" style="width: 150px;"></exp:select>
		            </dd>
		        </dl>
		        <dl>
		            <dt>内容细节：</dt>
		            <dd>
		            	<textarea name="clubDes" id="clubDes" style="width:900px;height:300px;">${readBookClub.clubDes}</textarea>
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