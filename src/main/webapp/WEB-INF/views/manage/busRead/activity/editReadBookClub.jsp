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
<script type="text/javascript" src="${basePath }resources/js/jquery-1.8.1.min.js"></script>
<!-- 验证表单 -->
<script type="text/javascript" src="${basePath }resources/js/validator_utf8.js"></script>
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript" src="${basePath }resources/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		 UE.getEditor('clubDes');
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
			<c:if test="${empty(readBookClub.clubId)}">添加读书会信息</c:if>
			<c:if test="${!empty(readBookClub.clubId)}">修改读书会信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(readBookClub.clubId)}">${basePath }readBookClubController/addSubmitReadBookClub.html</c:if><c:if test="${!empty(readBookClub.clubId)}">${basePath }readBookClubController/updateSubmitReadBookClub.html</c:if>" method="post">
			<input type="hidden" name="clubId" id="clubId" value="${readBookClub.clubId}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>主题：</dt>
					<dd>
						<input type="text" name="clubTitle" id="clubTitle" value="${readBookClub.clubTitle }" maxlength="50" require="true" requireMsg="主题为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>
						<i>*</i>活动图片：
					</dt>
					<dd>
						<input type="hidden" name="clubImg" id="clubImg" value="${readBookClub.clubImg }" require="true" requireMsg="活动图片为必填项!" dataType="Require"/>
						<img alt="" id="bookImgUrl" src="" width="150" height="150">
						<c:if test="${!empty(readBookClub.clubImg) }">
							<a href="${basePath}${readBookClub.clubImg}" target="_blank"><img src="${basePath}${readBookClub.clubImg}" width="150" height="150"></a>
						</c:if>
						<div id="upload_clubImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=clubImg&path=readBookClub&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>时间：</dt>
		            <dd>
		            	<input type="text" name="clubDate" id="clubDate" value="${readBookClub.clubDate}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px;"/>
		            </dd>
		        </dl>
		        <dl>
					<dt><i>*</i>地点：</dt>
					<dd>
						<input type="text" name="clubPlace" id="clubPlace" value="${readBookClub.clubPlace }" maxlength="50" require="true" requireMsg="地点为必填项!" dataType="Require" />
					</dd>
				</dl>
		        <dl>
					<dt><i>*</i>主讲人：</dt>
					<dd>
						<input type="text" name="clubMainGuest" id="clubMainGuest" value="${readBookClub.clubMainGuest }" maxlength="50" require="true" requireMsg="主讲人为必填项!" dataType="Require" />
					</dd>
				</dl>
		        <dl>
					<dt><i>*</i>推荐书目：</dt>
					<dd>
						<input type="text" name="clubMainGuestBook" id="clubMainGuestBook" value="${readBookClub.clubMainGuestBook }" maxlength="50" require="true" requireMsg="推荐书目为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>主讲人介绍：</dt>
					<dd>
						<textarea rows="8" cols="50" name="clubMainGuestIntroduce" id="clubMainGuestIntroduce" maxlength="300" style="width: 500px;height: 100px;">${readBookClub.clubMainGuestIntroduce}</textarea>
					</dd>
				</dl>
				<dl>
					<dt>主持人：</dt>
					<dd>
						<input type="text" name="clubPresenter" id="clubPresenter" value="${readBookClub.clubPresenter }" maxlength="20" />
					</dd>
				</dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="clubStatus" id="clubStatus" value="${readBookClub.clubStatus}"  require="true" requireMsg="使用状态为必填项!" dataType="Require" style="width: 150px;"></exp:select>
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