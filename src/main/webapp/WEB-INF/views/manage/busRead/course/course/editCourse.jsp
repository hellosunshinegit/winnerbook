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
<!-- 验证表单 -->
<script type="text/javascript" src="${basePath }resources/js/validator_utf8.js"></script>
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${basePath }resources/My97DatePicker/WdatePicker.js"></script>
<!-- 下拉搜索 -->
<link rel="stylesheet" href="${basePath }resources/select2/select2.min.css" />
<script src="${basePath }resources/select2/select2.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		 UE.getEditor('content');
		//下拉选择搜索框
	    $('#courseTypeId').select2();
	    $('#bookListId').select2();
	    
	    if('${course.courseId}'!=null && '${course.courseId}'!=''){
	    	var typeIds = '${course.courseTypeIds}'.split(",");
	    	$("#courseTypeId").val(typeIds).trigger('change');
	    }
	    
	});
	
	function submitForm(){
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			/* if($("#name_mainVideoUrl").val()=="" && $("#mainVideoLink").val()==""){
				alert("主视频地址或者主视频至少输入一项");
				return false;
			} */
			$("#courseTypeIds").val($("#courseTypeId").val().join(","));
			document.editForm.submit();
		}
	}
</script>
</head>
<body>
<div class="page_title"><h5>
<c:if test="${empty(course.courseId)}">添加课程信息</c:if>
<c:if test="${!empty(course.courseId)}">修改课程信息</c:if>
</h5></div>
<div class="page_main">
	<form name="editForm" id="editForm" action="<c:if test="${empty(course.courseId)}">${basePath }courseController/addSubmitCourse.html</c:if><c:if test="${!empty(course.courseId)}">${basePath }courseController/updateSubmitCourse.html</c:if>" method="post">
    <input type="hidden" name="courseId" id="courseId" value="${course.courseId }"/>
    <input type="hidden" name="courseStatus" id="courseStatus" value="${course.courseStatus }"/>
    <input type="hidden" name="courseTypeIds" id="courseTypeIds" value="${course.courseTypeIds }"/>
    <div class="form_main">
    		<dl>
	            <dt><i>*</i>课程类型：</dt>
	            <dd>
	            	<select name="courseTypeId" id="courseTypeId" class="courseIdSelect" multiple="multiple" style="width: 210px;" require="true" requireMsg="课程类型为必填项!" dataType="Require">
	            		<c:forEach items="${courseTypeList}" var="item">
	            			<option value="${item.typeId }" >${item.typeName}</option>
	            		</c:forEach>
	            	</select>
	            	<c:if test="${courseTypeList.size()==0 }">
	            		${course.courseTypeName }
	            		<span style="color: red;margin-left: 20px;">该企业管理员还未定制课程包，请尽快定制。</span>
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt><i>*</i>标题：</dt>
	            <dd><input type="text" name="title" id="title" value="${course.title }" maxlength="50" require="true" requireMsg="标题为必填项!" dataType="Require" /></dd>
	        </dl>
	        <dl>
				<dt>微博正文：</dt>
				<dd>
					<input type="text" name="wbTitle" id="wbTitle" value="${course.wbTitle }" maxlength="140" />
					<span style="color: red;">注：如果‘微博正文’内容为空，则发微博时采用‘主题’的内容</span>
				</dd>
			</dl>
			<dl>
				<dt>
					微博正文图：
				</dt>
				<dd>
					<input type="hidden" name="wbImg" id="wbImg" value="${course.wbImg }"/>
					<img alt="" id="wbImg" src="" width="150" height="150">
					<c:if test="${!empty(course.wbImg) }">
						<a href="${basePath}${course.wbImg}" target="_blank"><img src="${basePath}${course.wbImg}" width="50" height="50"></a>
					</c:if>
					<div id="upload_wbImg"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=wbImg&path=course/wb&typeExts=5" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
				</dd>
			</dl>
	        <dl>
	            <dt><i>*</i>总裁课程类型：</dt>
	            <dd>
	                <exp:select code="COURSE_TYPE" name="courseType" id="courseType" value="${course.courseType}"  require="true" requireMsg="课程类型为必填项!" dataType="Require" style="width: 310px;"></exp:select>
	            </dd>
	        </dl>
	        <dl>
	            <dt>描述：</dt>
	            <dd>
	            	<textarea rows="8" cols="50" name="courseDesc" id="courseDesc" maxlength="300">${course.courseDesc}</textarea>
	            </dd>
	        </dl>
	        <dl>
	            <dt>主讲嘉宾：</dt>
	            <dd><input type="text" name="mainGuest" id="mainGuest" value="${course.mainGuest }" maxlength="50" /></dd>
	        </dl>
	        <dl>
	            <dt>主嘉宾介绍：</dt>
	            <dd>
	            	<textarea rows="8" cols="50" name="mainGuestIntroduce" id="mainGuestIntroduce" maxlength="400">${course.mainGuestIntroduce}</textarea>
	            </dd>
	        </dl>
	        <dl>
	            <dt>主嘉宾百度链接：</dt>
	            <dd><input type="text" name="mainGuestBaiduKnow" id="mainGuestBaiduKnow" value="${course.mainGuestBaiduKnow }" maxlength="200"/></dd>
	        </dl>
	        <dl>
	            <dt>主嘉宾图片：</dt>
	            <dd>
	            	<div id="upload_mainGuestImg"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=mainGuestImg&path=course&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            	<input type="hidden" name="mainGuestImg" id="mainGuestImg" value="${course.mainGuestImg }" maxlength="50"/>
	            	<c:if test="${!empty(course.mainGuestImg)}">
		            	<span><a href="${basePath}${course.mainGuestImg}" target="_blank"><img alt="" src="${basePath}${course.mainGuestImg}" width="80" height="80"></a></span>
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>主讲嘉宾职务：</dt>
	            <dd><input type="text" name="mainGuestPost" id="mainGuestPost" value="${course.mainGuestPost }"/></dd>
	        </dl>
	        <dl>
	            <dt>对话嘉宾：</dt>
	            <dd><input type="text" name="dialogGuest" id="dialogGuest" value="${course.dialogGuest }" maxlength="50"/></dd>
	        </dl>
	        <dl>
	            <dt>对话嘉宾职务：</dt>
	            <dd><input type="text" name="dialogGuestPost" id="dialogGuestPost" value="${course.dialogGuestPost }" maxlength="50" /></dd>
	        </dl>
	        <dl>
	            <dt>主持人：</dt>
	            <dd><input type="text" name="presenter" id="presenter" value="${course.presenter }" maxlength="50"/></dd>
	        </dl>
	        <dl>
	            <dt>推荐书目：</dt>
	            <dd>
	            	<select name="bookListId" id="bookListId" style="width: 210px;">
	            		<option value="">---请选择---</option>
	            		<c:forEach items="${bookList}" var="item">
	            			<option value="${item.bookId }" <c:if test="${item.bookId eq course.bookListId}"> selected </c:if> >${item.bookName}</option>
	            		</c:forEach>
	            	</select>
	            </dd>
	        </dl>
	        <dl>
	            <dt>推荐书目图片：</dt>
	            <dd>
	            	<div id="upload_recommendBookImg"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=recommendBookImg&path=course&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            	<input type="hidden" name="recommendBookImg" id="recommendBookImg" value="${course.recommendBookImg }" maxlength="100" />
	            	<c:if test="${!empty(course.recommendBookImg)}">
	            		<a href="${basePath}${course.recommendBookImg}" target="_blank"><img alt="" src="${basePath}${course.recommendBookImg}" width="60" height="80"></a>	
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>书籍介绍：</dt>
	            <dd><input type="text" name="recommendBookIntroduce" id="recommendBookIntroduce" value="${course.recommendBookIntroduce }" maxlength="100" /></dd>
	        </dl>
	        <dl>
	            <dt>推荐理由：</dt>
	            <dd>
	            	<textarea rows="8" cols="50" name="recommendReason" id="recommendReason" maxlength="300">${course.recommendReason}</textarea>
	            </dd>
	        </dl>
	        <dl>
	            <dt>推荐理由图片：</dt>
	            <dd>
	            	<div id="upload_recommendReasonImg"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=recommendReasonImg&path=course&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            	<input type="hidden" name="recommendReasonImg" id="recommendReasonImg" value="${course.recommendReasonImg }" maxlength="100" />
	            	<c:if test="${!empty(course.recommendReasonImg)}">	
	            		<a href="${basePath}${course.recommendReasonImg}" target="_blank"><img alt="" src="${basePath}${course.recommendReasonImg}" width="60" height="80"></a>
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>录制时间：</dt>
	            <dd>
	            	<input type="text" name="recordingDate" id="recordingDate" value="${course.recordingDate}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px;"/>
	        	</dd>
	        </dl>
	   
	        <dl>
	            <dt>主视频地址：</dt>
	            <dd>
	            	<input type="text" name="mainVideoLink" id="mainVideoLink" value="${course.mainVideoLink}" maxlength="200" style="width: 300px;"/>
	            	<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;注：主视频太大建议直接输入视频地址。<!-- 主视频地址和视频至少输入一项哦! --></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt style="height: 50px;line-height: 50px;">主视频：</dt>
	            <dd>
	            	<div id="upload_mainVideoUrl"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=mainVideoUrl&path=course&typeExts=2" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            	<input type="hidden" name="mainVideoUrl" id="mainVideoUrl" value="${course.mainVideoUrl }" />
	            	<input type="hidden" name="mainVideoFilename" id="name_mainVideoUrl" value="${course.mainVideoFilename }" />
	            	<c:if test="${!empty(course.mainVideoUrl)}">
		            	<span>已上传视频：<a href="${basePath}${course.mainVideoUrl}" target="_blank" style="color: blue;">${course.mainVideoFilename}</a></span>
	            	</c:if>
	            </dd>
	        </dl>
	         <dl>
	            <dt>主音频地址：</dt>
	            <dd>
	            	<input type="text" name="mainAudioLink" id="mainAudioLink" value="${course.mainAudioLink}" maxlength="200" style="width: 300px;"/>
	            	<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;注：主音频太大建议直接输入音频地址。</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt style="height: 50px;line-height: 50px;">主音频：</dt>
	            <dd>
	            	<div id="upload_mainAudioUrl"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=mainAudioUrl&path=course&typeExts=3" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            	<input type="hidden" name="mainAudioUrl" id="mainAudioUrl" value="${course.mainAudioUrl }" />
	            	<input type="hidden" name="mainAudioFilename" id="name_mainAudioUrl" value="${course.mainAudioFilename }"/>
	            	<c:if test="${!empty(course.mainAudioUrl)}">
	            		<span>已上传音频：<a href="${basePath}${course.mainAudioUrl}" target="_blank" style="color: blue;">${course.mainAudioFilename}</a></span>
	            	</c:if>
	            </dd>
	        </dl>
	         <dl>
	            <dt>主视频时长：</dt>
	            <dd>
		            <input type="text" name="mainVideoTime" id="mainVideoTime" value="${course.mainVideoTime }" maxlength="20" />
	            </dd>
	        </dl>
	        <dl>
	            <dt>排序：</dt>
	            <dd>
	            	<input type="number" name="courseSort" id="courseSort" value="${course.courseSort }" />
	            </dd>
	        </dl>
	        <dl>
	            <dt>课程内容：</dt>
	            <dd>
	            	<textarea name="content" id="content" style="width:900px;height:300px;">${course.content}</textarea>
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