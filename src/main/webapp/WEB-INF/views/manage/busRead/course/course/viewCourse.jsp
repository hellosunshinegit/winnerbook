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
<!-- 查询是否有权限查看视频 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/jsFun/course_play.js"></script>

<script type="text/javascript">

	var courseId = '${course.courseId}';
	$(document).ready(function(){
		addressFun('${basePath}');
		var ue = UE.getEditor('content');
		ue.ready(function() {
        	//不可编辑
        	ue.setDisabled();
        });
		
		//定时获取观看时间
        var main_video=document.getElementById("main_video");//video标签对象
        playInterval('${basePath}',main_video,courseId,1);//type=1 主视频  type=2 主音频 
        
        var main_audio=document.getElementById("main_audio");//audio标签对象
        playInterval('${basePath}',main_audio,courseId,2);//type=1 主视频  type=2 主音频 
	});
	
	//点击观看主视频和主音频
	function lookMainPer(url,type){
		//如果创建人是管理员，那么他的视频则要权限控制，如果是企业管理员创建的，则不需要控制
		if('${course.createUserId}' == '${userAdminId}' && (${sessionUser.isAdmin}!='1')){
			if(userCourseTypes.indexOf('${course.courseTypeIds}')>=0){
				if(type==1){
					$("#main_video_div").css("display","");
				}else if(type==2){
					$("#main_audio_div").css("display","");
				}
			}else{
				alert("没有观看权限，如要观看，请联系管理员。");
			}
		}else{
			if(type==1){
				$("#main_video_div").css("display","");
			}else if(type==2){
				$("#main_audio_div").css("display","");
			}
		}
	}
	
	function backReturn(){
		window.location.href="${basePath}courseController/courseList.html";
	}
</script>
</head>
<body>
<div class="page_title"><h5>查看课程信息</h5></div>
<div class="page_main">
	<form name="editForm" id="editForm" >
    <div class="form_main">
    		<%-- <dl>
	            <dt>课程类型：</dt>
	            <dd>
	            	<c:if test="${courseTypeList.size()>0}">
		            	<select name="courseTypeId" id="courseTypeId" disabled="disabled" style="width: 210px;">
		            		<option value="">---请选择---</option>
		            		<c:forEach items="${courseTypeList}" var="item">
		            			<option value="${item.typeId }" <c:if test="${item.typeId eq course.courseTypeId}"> selected </c:if> >${item.typeName}</option>
		            		</c:forEach>
		            	</select>
	            	</c:if>
	            	
	            </dd>
	        </dl> --%>
	        <dl>
	            <dt>标题：</dt>
	            <dd><input type="text" name="title" id="title" value="${course.title }" disabled="disabled"/></dd>
	        </dl>
	        <dl>
				<dt>微博正文：</dt>
				<dd>
					<input type="text" name="wbTitle" id="wbTitle" value="${course.wbTitle }" disabled="disabled" />
				</dd>
			</dl>
			<dl>
				<dt>
					微博正文图：
				</dt>
				<dd>
					<c:if test="${!empty(course.wbImg) }">
						<a href="${basePath}${course.wbImg}" target="_blank"><img src="${basePath}${course.wbImg}" width="50" height="50"></a>
					</c:if>
				</dd>
			</dl>
	        <dl>
	            <dt>总裁课程类型：</dt>
	            <dd>
	                <exp:select code="COURSE_TYPE" name="courseType" id="courseType" value="${course.courseType}" disabled="true" style="width: 310px;"></exp:select>
	            </dd>
	        </dl>
	        <dl>
	            <dt>描述：</dt>
	            <dd>
	            	<textarea rows="8" cols="50" name="courseDesc" id="courseDesc" disabled="disabled">${course.courseDesc}</textarea>
	            </dd>
	        </dl>
	        <dl>
	            <dt>主讲嘉宾：</dt>
	            <dd><input type="text" name="mainGuest" id="mainGuest" value="${course.mainGuest }" disabled="disabled" /></dd>
	        </dl>
	        <dl>
	            <dt>主嘉宾介绍：</dt>
	            <dd><input type="text" name="mainGuestIntroduce" id="mainGuestIntroduce" value="${course.mainGuestIntroduce }" disabled="disabled"/></dd>
	        </dl>
	        <dl>
	            <dt>主嘉宾图片：</dt>
	            <dd>
	            	<c:if test="${!empty(course.mainGuestImg)}">
		            	<span><a href="${basePath}${course.mainGuestImg}" target="_blank"><img alt="" src="${basePath}${course.mainGuestImg}" width="60" height="80"></a></span>
	            	</c:if>
	            	<c:if test="${empty(course.mainGuestImg) }">
	            		暂无数据...
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>主讲嘉宾职务：</dt>
	            <dd><input type="text" name="mainGuestPost" id="mainGuestPost" value="${course.mainGuestPost }" disabled="disabled"/></dd>
	        </dl>
	        <dl>
	            <dt>对话嘉宾：</dt>
	            <dd><input type="text" name="dialogGuest" id="dialogGuest" value="${course.dialogGuest }" disabled="disabled"/></dd>
	        </dl>
	        <dl>
	            <dt>对话嘉宾职务：</dt>
	            <dd><input type="text" name="dialogGuestPost" id="dialogGuestPost" value="${course.dialogGuestPost }" disabled="disabled" /></dd>
	        </dl>
	        <dl>
	            <dt>主持人：</dt>
	            <dd><input type="text" name="presenter" id="presenter" value="${course.presenter }" disabled="disabled"/></dd>
	        </dl>
	        <dl>
	            <dt>推荐书目：</dt>
	            <dd><input type="text" name="recommendBook" id="recommendBook" value="${course.recommendBook }" disabled="disabled" /></dd>
	        </dl>
	        <dl>
	            <dt>推荐书目图片：</dt>
	            <dd>
	            	<c:if test="${!empty(course.recommendBookImg)}">
	            		<a href="${basePath}${course.recommendBookImg}" target="_blank"><img alt="" src="${basePath}${course.recommendBookImg}" width="60" height="80"></a>	
	            	</c:if>
	            	<c:if test="${empty(course.recommendBookImg) }">
	            		暂无数据...
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>书籍介绍：</dt>
	            <dd><input type="text" name="recommendBookIntroduce" id="recommendBookIntroduce" value="${course.recommendBookIntroduce }" disabled="disabled" /></dd>
	        </dl>
	        <dl>
	            <dt>推荐理由：</dt>
	            <dd><input type="text" name="recommendReason" id="recommendReason" value="${course.recommendReason }" disabled="disabled" /></dd>
	        </dl>
	        <dl>
	            <dt>推荐理由图片：</dt>
	            <dd>
	            	<c:if test="${!empty(course.recommendReasonImg)}">	
	            		<a href="${basePath}${course.recommendReasonImg}" target="_blank"><img alt="" src="${basePath}${course.recommendReasonImg}" width="60" height="80"></a>
	            	</c:if>
	            	<c:if test="${empty(course.recommendReasonImg) }">
	            		暂无数据...
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>录制时间：</dt>
	            <dd>
	            	<input type="text" name="recordingDate" id="recordingDate" value="${course.recordingDate}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" disabled="disabled" style="width: 150px;"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt>视频地址：</dt>
	            <dd style="height: 30px;line-height: 30px;">
	            	<c:if test="${!empty(course.mainVideoLink)}">
		            	<span><a href="${course.mainVideoLink}" href="javascript:lookMainPer('${course.mainVideoLink}')" target="_blank" style="color: blue;">点击播放</a></span>
	            	</c:if>
	            	<c:if test="${empty(course.mainVideoLink)}">
		            	<span>暂无数据...</span>
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt style="height: 50px;line-height: 50px;">主视频：</dt>
	            <dd>
	            	<div style="height: 50px;line-height: 50px;">
	            		<c:if test="${!empty(course.mainVideoUrl)}">
			            	<span>已上传视频：<a title="点击可观看" href="javascript:lookMainPer('${basePath}${course.mainVideoUrl}','1')" target="_blank" style="color: blue;" >${course.mainVideoFilename}</a></span>
		            	</c:if>
	            	</div>
	            	<div id="main_video_div" style="display: none;">
			            <video controls="controls" id="main_video" ><source src="${basePath}${course.mainVideoUrl}" id="source"/></video>
	            	</div>
	            </dd>
	        </dl>
	         <dl>
	            <dt>视频时长：</dt>
	            <dd>
	            	<input type="text" name="mainVideoTime" id="mainVideoTime" value="${course.mainVideoTime }"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt style="height: 50px;line-height: 50px;">主音频：</dt>
	            <dd>
	            	<div style="height: 50px;line-height: 50px;">
	            		<c:if test="${!empty(course.mainAudioUrl)}">
		            		<span>已上传音频：<a title="点击可收听" href="javascript:lookMainPer('${basePath}${course.mainAudioUrl}','2')" target="_blank" style="color: blue;">${course.mainAudioFilename}</a></span>
		            	</c:if>
	            	</div>
	            	<div id="main_audio_div" style="display: none;">
	            		<audio controls="controls" id="main_audio"><source src="${basePath}${course.mainAudioUrl}"></audio>
	            	</div>
	            </dd>
	        </dl>
	    	<c:if test="${not empty courseFileList }">
		        <dl>
		            <dt>相关资料：</dt>
		            <dd>
		            	<!-- 展示已经上传的数据 -->
			    		<div style="width: 900px;">
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
					              </tr>
					            </thead>
					           
								<c:forEach items="${courseFileList}" var="item" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td><exp:code code="COURSE_FILE_TYP" value="${item.fileType }"></exp:code></td>
										<td>${item.fileTitle}</td>
										<td><a href="${basePath }${item.fileUrl}" style="color: blue;" target="_blank">${item.fileName}</a></td>
										<td>${item.createUserName}</td>
										<td><fmt:formatDate value="${item.createDate}" type="both"/></td>
									</tr>
								</c:forEach>
					          </table>
					        </div>
			    		</div>
		            </dd>
		        </dl>
			</c:if>
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
	                    <input type="button" onclick="backReturn()" class="btn btn-blue" value="返回"/>
	                </div>
	            </dd>
	        </dl>
    </div>
</form>
</div>
</body>
</html>