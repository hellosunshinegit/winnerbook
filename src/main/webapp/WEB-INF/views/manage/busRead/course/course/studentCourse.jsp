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
<!-- 查询是否有权限查看视频 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/jsFun/course_play.js"></script>


<script type="text/javascript">
	var courseId = '${course.courseId}';
	
	$(document).ready(function(){
		addressFun('${basePath}');
		//定时获取观看时间
        playInterval('${basePath}',"main_video",courseId,1);//type=1 主视频  type=2 主音频  主视频
        playInterval('${basePath}',"main_audio",courseId,2);//type=1 主视频  type=2 主音频   主音频
        playInterval('${basePath}',"main_video_href",courseId,3);//type=1 主视频  type=2 主音频   主音频
        
        if(JSON.parse('${jsonCourseFiles}').length>0){
        	$.each(JSON.parse('${jsonCourseFiles}'),function(index,item){
        		console.log(item.fileId);
        		if(item.fileType=="1"){
        			playInterval('${basePath}',"file_video_"+item.fileId,courseId,3,item.fileId);
        		}else if(item.fileType=="2"){
        			playInterval('${basePath}',"file_audio_"+item.fileId,courseId,3,item.fileId);
        		}
        	});
        }
	});
	
	function backReturn(){
		window.location.href="${basePath}courseController/courseList.html";
	}
	
	
	//点击观看主视频
	function lookMainPer(url,type){
		//如果创建人是管理员，那么他的视频则要权限控制，如果是企业管理员创建的，则不需要控制
		if('${course.createUserId}' == '${userAdminId}' && ('${sessionUser.isAdmin}'!='1')){
			if(userCourseTypes.indexOf('${course.courseTypeIds}')>=0){
				if(type==1){
					$("#main_video_div").css("display","");
				}else if(type==2){
					$("#main_audio_div").css("display","");
				}else if(type==3){
					$("#main_video_div_href").css("display","");
				}
			}else{
				alert("没有观看权限，如要观看，请联系管理员。");
			}
		}else{
			if(type==1){
				$("#main_video_div").css("display","");
			}else if(type==2){
				$("#main_audio_div").css("display","");
			}else if(type==3){
				$("#main_video_div_href").css("display","");
			}
		}
	}
/* 	
	function addReadThoughtFun(){
		window.location.href="${basePath}readThoughtController/addReadThought.html?type=1&courseId="+courseId;
	}
 */
	//点击播放
	function clickPlay(fileId){
		console.log(fileId);
		$("#course_file_"+fileId).css("display","");
	}
	
</script>
</head>
<body>
<div class="page_title"><h5>课程学习</h5></div>
<div class="page_main">
	<form name="editForm" id="editForm" >
    <div class="form_main">
	        <dl>
	            <dt>相关图片：</dt>
	            <dd>
	            	<c:if test="${!empty(course.mainGuestImg)}">
		            	<span><a href="${basePath}${course.mainGuestImg}" target="_blank"><img alt="" src="${basePath}${course.mainGuestImg}" width="60" height="80"></a></span>
	            	</c:if>
	            	<c:if test="${!empty(course.recommendBookImg)}">
	            		<a href="${basePath}${course.recommendBookImg}" target="_blank"><img alt="" src="${basePath}${course.recommendBookImg}" width="60" height="80"></a>	
	            	</c:if>
	            	<c:if test="${!empty(course.recommendReasonImg)}">	
	            		<a href="${basePath}${course.recommendReasonImg}" target="_blank"><img alt="" src="${basePath}${course.recommendReasonImg}" width="60" height="80"></a>
	            	</c:if>
	            </dd>
	        </dl>
	        <c:if test="${!empty(course.mainVideoTime) }">
		         <dl>
		            <dt>视频时长：</dt>
		            <dd>
		            	<div class="dd_radio">
			            	${course.mainVideoTime }
		            	</div>
		            </dd>
		        </dl>
	        </c:if>
	        <dl>
	            <dt>视频地址：</dt>
	            <dd>
	            	<c:if test="${!empty(course.mainVideoLink)}">
		            	<span style="line-height: 28px;">
		            		<a href="javascript:lookMainPer('${course.mainVideoLink}','3')" target="_blank" style="color: blue;">点击播放</a>
		            	</span>
		            	
		            	<div id="main_video_div_href" style="display: none;">
				            <video controls="controls" id="main_video_href" style="width:700px;"><source src="${course.mainVideoLink}"/></video>
		            	</div>
	            	</c:if>
	            	<c:if test="${empty(course.mainVideoLink)}">
		            	<span>暂无数据...</span>
	            	</c:if>
	            </dd>
	        </dl> 
	        <dl>
	            <dt style="height: 50px;line-height: 50px;">视频：</dt>
	            <dd><!-- autoplay="autoplay" 自动播放   poster="${basePath }resources/images/video.loading.gif"  视频加载不出来时显示的图片-->
	            	<!-- 主视频判断 ,如果当前的企业管理员没有该课程分类的的权限，则不可以点击播放-->
	            	<div style="height: 50px;line-height: 50px;">
	            		<c:if test="${empty(course.mainVideoUrl)}">
		            		暂无视频...
		            	</c:if>
	            		<c:if test="${!empty(course.mainVideoUrl)}">
			            	<span style="color: red;">点击播放：
			            	<a title="点击可观看" href="javascript:lookMainPer('${basePath}${course.mainVideoUrl}','1')" target="_blank" style="color: blue;">${course.mainVideoFilename}</a></span>
		            	</c:if>
	            	</div>
	            	<div id="main_video_div" style="display: none;">
			            <video controls="controls" id="main_video" ><source src="${basePath}${course.mainVideoUrl}" id="source"/></video>
	            	</div>
	            </dd>
	        </dl>
	        <dl>
	            <dt style="height: 50px;line-height: 50px;">音频：</dt>
	            <dd>
	            	<div style="height: 50px;line-height: 50px;">
	            		<c:if test="${!empty(course.mainAudioUrl)}">
		            		<span style="color: red;">点击播放：<a title="点击可收听" href="javascript:lookMainPer('${basePath}${course.mainAudioUrl}','2')" target="_blank" style="color: blue;">${course.mainAudioFilename}</a></span>
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
					                <td>类型</td>
					                <td>标题</td>
									<td>文件名称</td>
					              </tr>
					            </thead>
					           
								<c:forEach items="${courseFileList}" var="item" varStatus="status">
									<tr>
										<td><exp:code code="COURSE_FILE_TYP" value="${item.fileType }"></exp:code></td>
										<td>${item.fileTitle}</td>
										<td>
											<c:if test="${item.fileType eq 1 or  item.fileType eq 2}">
												<span style="color: red;">点击播放：</span><a href="javascript:clickPlay(${item.fileId })" style="color: blue;" target="_blank" title="点击播放">${item.fileName}</a>
											</c:if>
											<c:if test="${item.fileType eq 3}">
												<span style="color: red;">点击下载：</span><a href="${basePath }${item.fileUrl}" style="color: blue;" target="_blank" title="点击播放">${item.fileName}</a>
											</c:if>
											<div id="course_file_${item.fileId }" style="display: none;">
												<c:if test="${item.fileType eq 1 }">
								            		<video controls="controls" id="file_video_${item.fileId }"><source src="${basePath}${item.fileUrl}"></video>
												</c:if>
												<c:if test="${item.fileType eq 2 }">
								            		<audio controls="controls" id="file_audio_${item.fileId }"><source src="${basePath}${item.fileUrl}"></audio>
												</c:if>
							            	</div>
										</td>
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
	            <dd style="margin-top: 15px;">
	            	${course.content}
	            </dd>
	        </dl>
	        <dl>
	            <dt>&nbsp;</dt>
	            <dd>
	                <div>
	                    <!-- <input type="button" onclick="addReadThoughtFun()" class="btn btn-blue" value="写读后感"/> -->
	                    <input type="button" onclick="backReturn()" class="btn btn-blue" value="返回"/>
	                </div>
	            </dd>
	        </dl>
    </div>
</form>
</div>
</body>
</html>