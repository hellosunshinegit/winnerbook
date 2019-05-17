<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="${basePath }resources/css/rui.css">
<!-- 上传文件插件开始 -->
<script type="text/javascript" src="${basePath}resources/fileupload/h5/jquery.js"></script>
<script type="text/javascript" src="${basePath}resources/fileupload/h5/jquery.Huploadify.js?f=<%=System.currentTimeMillis()%>"></script>
<link href="${basePath}resources/fileupload/h5/Huploadify.css" type="text/css" rel="stylesheet"/>
<!-- 上传文件插件结束 -->

<script type="text/javascript">
	var type = '${type}';
	$(document).ready(function(){
		$('#upload').Huploadify({
	        uploader:'${basePath }userController/importUserSubmit.html;jsessionid=${pageContext.session.id}',
	        method:'post',
	        auto:true,
	        fileTypeExts:'*.xls,*.xlsx',
	        multi:false,
	        fileSizeLimit:1024*10*10,//kb为单位
	        showUploadedPercent:false,
	        showUploadedSize:true,
	        removeTimeout:3000,//上传完成后进度条的消失时间，单位毫秒
	        showUploadedSize:false,//是否实时显示已上传的文件大小，如1M/2M
	        buttonText:'点击上传文件',
	        onUploadStart:function(){//开始上传
	         },
	        onInit:function(){//初始化成功
	         },
	        onUploadComplete:function(file,data){
	        	console.log(data);
	        	var jsondata = eval("("+data+")");
	            if(jsondata.code=="200"){
	            	var str = "共"+jsondata.excelDataCount+"条数据，成功导入"+jsondata.userListInsert+"条\n";
	            	if(jsondata.repeatPhoneList.length>0){
	            		str+="手机号重复"+jsondata.repeatPhoneList.length+"条，包括<br/>";
	            		$.each(jsondata.repeatPhoneList,function(index,item){
	            			str+="&nbsp;&nbsp;&nbsp;&nbsp;"+item.contactMobile+"<br/>";
	            		});
	            	}
	            	if(jsondata.phoneFormatList.length>0){
	            		str+="手机号格式错误"+jsondata.phoneFormatList.length+"条，包括<br/>";
	            		$.each(jsondata.phoneFormatList,function(index,item){
	            			str+="&nbsp;&nbsp;&nbsp;&nbsp;"+item.contactMobile+"<br/>";
	            		});
	            	}
	            	$("#result").html(str);
	            }else{
	            	alert("上传失败");
	            }
	        },
	        onCancel:function(file){
	            console.log(file);
	        }
	    });
	});
	
	function returnFun(){
		//type==1 是用户点击过来的导入 type==2是企业通讯录中点击
		if(type==1){
			window.location.href="${basePath}userController/userList.html";
		}else{
			window.location.href="${basePath}busEmpController/userEmpList.html";
		}
	}

</script>
</head>
<body>
	<div style="margin-left: 30px;margin-top: 10px;font-size: 14px;">
		<div style="color: red;margin-left: 20px;">注：请先下载模板，再上传</div>
		<div style="margin-left: 20px;margin-top: 20px;"><a href="${basePath }resources/template/user.xlsx" style="color: blue;">下载模板</a></div>
		<div style="margin-top: 10px;margin-left: 20px;">
			模板浏览：<br/>
			<img alt="" src="${basePath }resources/template/user_import.png">
		</div>
		<div style="margin-top: 30px;margin-left: 20px;">上传文件：</div>
		<div id="upload" style="margin: 20px;"></div>
		<span id="result" style="font-size: 13px;color: red;font-family:'微软雅黑';"></span><br/><br/><br/><br/><br/><br/><br/>
		
		<input type="button" onclick="returnFun()" class="btn btn-blue" value="返回" style="margin-left: 20px;"/>
	</div>
</body>
</html>