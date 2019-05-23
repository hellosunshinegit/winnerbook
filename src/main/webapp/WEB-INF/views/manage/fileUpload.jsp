<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<!-- 上传文件插件 -->
<script type="text/javascript" src="${basePath}resources/fileupload/h5/jquery.js"></script>
<script type="text/javascript" src="${basePath}resources/fileupload/h5/jquery.Huploadify.js?f=<%=System.currentTimeMillis()%>"></script>
<link href="${basePath}resources/fileupload/h5/Huploadify.css" type="text/css" rel="stylesheet"/>
<meta charset="utf-8">
<script type="text/javascript">

	//定位id，上传类型，清空id,上传路径
	var uploadId = '${uploadId}';
	var typeExts = '${typeExts}';//1 图片  2视频   3音频
	var path = '${path}';//上传路径
	var filePath = '${filePath}';//上传路径
	var fileName = '';//上传文件名称
	var result = '${res}';//显示上传结果

	$(document).ready(function(){
		
		var multi = false;//上传路径
		var fileTypeExts = "";
		var fileSizeLimit = "";
		if(typeExts==1){
			fileTypeExts="*.png;*.PNG;*.jpg;*.JPG;*.jpeg;*.JPEG";
			fileSizeLimit=10;
		}else  if(typeExts==2){//视频
			fileTypeExts="*.wmv;*.avi;*.dat;*.asf;*.mp4;*.MP4;*.mov";
			fileSizeLimit=200;
			fileName = 'name_'+'${filePath}';//上传文件名称
		}else if(typeExts==3){//音频
			fileTypeExts="*.MP3;*.mp3;*.WMA;*.FLAC;*.WAV";
			fileSizeLimit=30;
			fileName = 'name_'+'${filePath}';//上传文件名称
		}else if(typeExts==4){
			fileTypeExts="*.doc;*.docx;*.xls;*.xlxs";
			fileSizeLimit=10;
			fileName = 'name_'+'${filePath}';//上传文件名称
		}else if(typeExts==5){//微博图片
			fileTypeExts="*.png;*.PNG;*.jpg;*.JPG;*.jpeg;*.JPEG";
			fileSizeLimit=5;
		}
		
		$("#fileTypeExts").html("文件最大支持"+fileSizeLimit+"M，支持格式："+fileTypeExts);
		
		 //上传主讲人图片
		$('#'+uploadId).Huploadify({
	        uploader:'${basePath }fileUploadController/uploadFileSubmit.html;jsessionid=${pageContext.session.id}?path='+path+"&type="+typeExts,
	        auto:true,
	        fileTypeExts:fileTypeExts,
	        multi:multi,//是否允许多个上传
	        fileSizeLimit:1024*fileSizeLimit,//kb为单位 10M  1m=1024k
	        showUploadedPercent:true,//显示时时上传的百分比
	        onUploadComplete:function(file,data){
	        	if(data.indexOf("html")>=0){
	        		alert("文件太大，上传失败");
	        	}else{
	        		var dataJson = eval("("+data+")");
		            if(dataJson.code=="200"){
		            	$("#"+filePath,parent.document).click().val(dataJson.urlPath);
		            	if(fileName!=""){
			        		$("#"+fileName,parent.document).click().val(dataJson.fileName);
		            	}
		        		$("#"+result).html("<span style='color:#000;' id='res'>"+dataJson.fileName+"&nbsp;&nbsp;<span style='color:#FF0000;'>上传成功！</span><a href='javascript:deleteFile(\""+filePath+"\")' style='font-size:12px;'>删除</a></span>");
		            }
	        	}
	        },
	        onCancel:function(file){
	            console.log(file,"取消");
	        }
	    });
	});
	
	
	//点击删除
	function deleteFile(filePathDomId){
		if(confirm("确定要删除吗？")){
			var filePathValue =  $('#'+filePathDomId,parent.document).click().val();
			if(filePathValue.length>0){
				$.ajax({
					type:"POST",
					async: false,
					dataType:"html",
					url:"${basePath}fileUploadController/fileDelete.html?filePath="+filePathValue,
					success:function(data){
						if(data==1){
							alert("文件删除成功！");
							if(fileName!=""){
								$("#"+fileName,parent.document).click().val("");
							}
							$("#"+filePath,parent.document).click().val("");
							$("#"+result).html("");
						}else{
							alert("文件删除失败");
							if(fileName!=""){
								$("#"+fileName,parent.document).click().val("");
							}
							$("#"+filePath,parent.document).click().val("");
							$("#"+result).html("");
						}
					}
				});
			}else{
				alert("您已删除，不需要重复操作");
			}
		}
	}
	
</script>
</head>
<body>
    <div id="${uploadId}"></div>
    <span id="${res}" style="font-size: 13px;color: red;font-family:'微软雅黑';"></span><br/>
    <span style="font-size: 13px;color: red;font-family:'微软雅黑';" id="fileTypeExts">
		<%-- <c:if test="${typeExts eq 1}">
			单张图片最大支持5M，支持格式为：.jpg&nbsp;.png&nbsp;.jpeg
		</c:if>
		<c:if test="${typeExts eq 2}">
			视频最大支持30M，支持格式为：.wmv,&nbsp;.avi,&nbsp;.dat,&nbsp;.asf,&nbsp;.mp4
		</c:if>
		<c:if test="${typeExts eq 3}">
			音频最大支持30M，支持格式为：.MP3,&nbsp;.WMA,&nbsp;.FLAC,&nbsp;.WAV
		</c:if> --%>
	</span>
</body>
</html>