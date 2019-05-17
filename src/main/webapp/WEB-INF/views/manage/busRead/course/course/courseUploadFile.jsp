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

<script type="text/javascript">
	var courseId = '${courseId}';
	var dataJson = {
		"courseId":courseId,
		"videoList":[],
		"audioList":[],
		"fileList":[]
	};
	function submitForm(){
		var editForm = document.getElementById("editForm");
		
		if(Validator.Validate(editForm)){
			//视频
			var video_array = new Array();
			$.each($("input[id^='video_fileTitle']"),function(index,item){
				if(item.value!=""){
					video_array.push({"fileTitle":item.value,"fileName":$("#name_video_fileUrl"+index).val(),"fileUrl":$("#video_fileUrl"+index).val(),"fileType":"1"});//视频
				}
			});
			dataJson.videoList = video_array;
			var audio_array = new Array();
			$.each($("input[id^='audio_fileTitle']"),function(index,item){
				if(item.value!=""){
					audio_array.push({"fileTitle":item.value,"fileName":$("#name_audio_fileUrl"+index).val(),"fileUrl":$("#audio_fileUrl"+index).val(),"fileType":"2"});//音频
				}
			});
			dataJson.audioList = audio_array;
			var file_array = new Array();
			$.each($("input[id^='file_fileTitle']"),function(index,item){
				if(item.value!=""){
					file_array.push({"fileTitle":item.value,"fileName":$("#name_file_fileUrl"+index).val(),"fileUrl":$("#file_fileUrl"+index).val(),"fileType":"3"});//附件文档
				}
			});
			dataJson.fileList = file_array;
			console.log(dataJson,JSON.stringify(dataJson));
			
			if(video_array.length==0 && audio_array.length==0 && file_array.length==0){
				alert("请至少上传一个文件");
				return false;
			}
			
			$.ajax({
				type:"POST",
				async: false,
				dataType:"json",
			    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
				data:JSON.stringify(dataJson),
				url:"${basePath}courseController/courseUploadFileSubmit.html",
				success:function(data){
					if(data=="200"){
						alert("保存成功");
						window.location.href="${basePath }courseController/courseList.html";
					}
				}
			});
		}
	}
	
	var video_index = 0;
	function addVideoFun(){
		if(video_index>8){
			alert("最多可添加十个小视频");
			//return false;
		}else{
			video_index++;
			var add_video="<div id='videoFileId_"+video_index+"'><dl><dt>小视频标题"+video_index+"：</dt><dd><input type='text' name='video_fileTitle"+video_index+"' id='video_fileTitle"+video_index+"' maxlength='50' />&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteFun(\"videoFileId_"+video_index+"\")' style='color: blue;'>删除</a></dd></dl>"+
			"<dl><dt>小视频"+video_index+"：</dt><dd><div id='upload_video_fileUrl"+video_index+"'></div>"+
			"<iframe src='${basePath}fileUploadController/uploadFileIframe.html?filePath=video_fileUrl"+video_index+"&path=course&typeExts=2' id='file' width='800px;' height='110px;' frameborder='0' scrolling='no'></iframe>"+
			"<input type='hidden' name='video_fileUrl"+video_index+"' id='video_fileUrl"+video_index+"' /><input type='hidden' name='video_fileName"+video_index+"' id='name_video_fileUrl"+video_index+"' /></dd></dl></div>";
			$("#video_dom").append(add_video);
		}
	}
	
	function deleteFun(value){
		$("#"+value).remove();
		video_index--;
	}
	
	var audio_index = 0;
	function addAudioFun(){
		if(audio_index>8){
			alert("最多可添加十个音频");
			//return false;
		}else{
			audio_index++;
			var add_audio="<div id='audioFileId_"+audio_index+"'><dl><dt>音频标题"+audio_index+"：</dt><dd><input type='text' name='audio_fileTitle"+audio_index+"' id='audio_fileTitle"+audio_index+"' maxlength='50' />&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteAudioFun(\"audioFileId_"+audio_index+"\")' style='color: blue;'>删除</a></dd></dl>"+
			"<dl><dt>音频"+audio_index+"：</dt><dd><div id='upload_audio_fileUrl"+audio_index+"'></div>"+
			"<iframe src='${basePath}fileUploadController/uploadFileIframe.html?filePath=audio_fileUrl"+audio_index+"&path=course&typeExts=3' id='file' width='800px;' height='110px;' frameborder='0' scrolling='no'></iframe>"+
			"<input type='hidden' name='audio_fileUrl"+audio_index+"' id='audio_fileUrl"+audio_index+"' /><input type='hidden' name='audio_fileName"+audio_index+"' id='name_audio_fileUrl"+audio_index+"' /></dd></dl></div>";
			$("#audio_dom").append(add_audio);
		}
	}
	
	function deleteAudioFun(value){
		$("#"+value).remove();
		audio_index--;
	}
	
	var file_index = 0;
	function addFileFun(){
		if(file_index>8){
			alert("最多可添加十个文件");
			//return false;
		}else{
			file_index++;
			var add_file="<div id='fileFileId_"+file_index+"'><dl><dt>文件标题"+file_index+"：</dt><dd><input type='text' name='file_fileTitle"+file_index+"' id='file_fileTitle"+file_index+"' maxlength='50' />&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deletefileFun(\"fileFileId_"+file_index+"\")' style='color: blue;'>删除</a></dd></dl>"+
			"<dl><dt>文件"+file_index+"：</dt><dd><div id='upload_file_fileUrl"+file_index+"'></div>"+
			"<iframe src='${basePath}fileUploadController/uploadFileIframe.html?filePath=file_fileUrl"+file_index+"&path=course&typeExts=4' id='file' width='800px;' height='110px;' frameborder='0' scrolling='no'></iframe>"+
			"<input type='hidden' name='file_fileUrl"+file_index+"' id='file_fileUrl"+file_index+"' /><input type='hidden' name='file_fileName"+file_index+"' id='name_file_fileUrl"+file_index+"' /></dd></dl></div>";
			$("#file_dom").append(add_file);
		}
	}
	
	function deletefileFun(value){
		$("#"+value).remove();
		file_index--;
	}
	
	function deleteCourseFileFun(fileId){
		if(confirm("确定要删除吗？")){
			$.ajax({
				type:"POST",
				async: false,
				dataType:"html",
				url:"${basePath}courseController/deleteFile.html?courseId="+courseId+"&fileId="+fileId,
				success:function(data){
					if(data=="200"){
						alert("删除成功");
						window.location.href="${basePath }courseController/courseUploadFile.html?courseId="+courseId;
					}
				}
			});
		}
	}
</script>
</head>
<body>
<div class="page_title"><h5>相关资料</h5></div>
<div class="page_main">
    <div class="form_main">
    	<!-- 展示已经上传的数据 -->
    	<c:if test="${not empty courseFileList }">
	    	<div class="data_list">
	          <table>
	            <thead>
	              <tr>
	              	<td>序号</td>
	                <td>类型</td>
	                <td>标题</td>
					<td>文件名称</td>
					<td>创建人</td>
					<td>创建时间</td>
					<td>操作</td>
	              </tr>
	            </thead>
	           
					<c:forEach items="${courseFileList}" var="item" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td><exp:code code="COURSE_FILE_TYP" value="${item.fileType }"></exp:code></td>
							<td>${item.fileTitle}</td>
							<td>
								<c:choose>
									<c:when test="${item.fileType eq '3' }">
										<a href="${basePath }courseController/downLoadCourseFile.html?fileId=${item.fileId }" style="color: blue;" title="点击可下载">${item.fileName}</a>
									</c:when>
									<c:otherwise>
										<a href="${basePath }${item.fileUrl}" style="color: blue;" target="_blank">${item.fileName}</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${item.createUserName}</td>
							<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
							<td>
								<a href="javascript:deleteCourseFileFun('${item.fileId }')" style="color: blue;">删除</a>
							</td>
						</tr>
					</c:forEach>
	          </table>
	        </div>
		</c:if>
        <form name="editForm" id="editForm" method="post">
	    	<div id="video_dom" style="padding-top: 50px;width: 50%;">
	    		<div id="videoFileId_0">
	    			<dl>
		            <dt>小视频标题：</dt>
			            <dd><input type="text" name="video_fileTitle0" id="video_fileTitle0"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:addVideoFun()" style="color: blue;">添加更多</a></dd>
			         </dl>
			        <dl>
			            <dt>小视频：</dt>
			            <dd>
			            	<div id="upload_video_fileUrl0"></div>
			            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=video_fileUrl0&path=course&typeExts=2" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
			            	<input type="hidden" name="video_fileUrl0" id="video_fileUrl0"/>
			            	<input type="hidden" name="video_fileName0" id="name_video_fileUrl0" />
			            </dd>
			        </dl>
	    		</div>
	    	</div>
	    	<div id="audio_dom" style="border-top: 1px solid #bfbfbf;padding-top: 20px;width: 50%;">
	    		<div id="audioFileId_0">
	    			<dl>
		            <dt>音频标题：</dt>
			            <dd><input type="text" name="audio_fileTitle0" id="audio_fileTitle0" maxlength="50"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:addAudioFun()" style="color: blue;">添加更多</a></dd>
			         </dl>
			        <dl>
			            <dt>音频：</dt>
			            <dd>
			            	<div id="upload_audio_fileUrl0"></div>
			            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=audio_fileUrl0&path=course&typeExts=3" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
			            	<input type="hidden" name="audio_fileUrl0" id="audio_fileUrl0"/>
			            	<input type="hidden" name="audio_fileName0" id="name_audio_fileUrl0" />
			            </dd>
			        </dl>
	    		</div>
	    	</div>
	    	
	    	<div id="file_dom" style="border-top: 1px solid #bfbfbf;padding-top: 20px;width: 50%;">
	    		<div id="fileFileId_0">
	    			<dl>
		            <dt>文件标题：</dt>
			            <dd><input type="text" name="file_fileTitle0" id="file_fileTitle0" maxlength="50" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:addFileFun()" style="color: blue;">添加更多</a></dd>
			         </dl>
			        <dl>
			            <dt>文件：</dt>
			            <dd>
			            	<div id="upload_file_fileUrl0"></div>
			            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=file_fileUrl0&path=course&typeExts=4" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
			            	<input type="hidden" name="file_fileUrl0" id="file_fileUrl0"/>
			            	<input type="hidden" name="file_fileName0" id="name_file_fileUrl0" />
			            </dd>
			        </dl>
	    		</div>
	    	</div>
       
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                    <input type="reset" class="btn" value="重置">
                </div>
            </dd>
        </dl>
	</form>
    </div>
</div>
</body>
</html>