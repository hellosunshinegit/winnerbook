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

<script type="text/javascript">
	$(document).ready(function(){
		 UE.getEditor('busDetail');
	});
	
	function submitForm(){
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			document.editForm.submit();
		}
	}
	
	function isAginFun(){
		var isAgin = $("#isAgin").is(':checked');
		if(isAgin){//重新上传
			$("#file").css("display","");
			$("#file").attr("require","true");
			$("#file").attr("requireMsg","企业logo为必选项!");
			$("#file").attr("dataType","Require");
		}else{
			$("#file").css("display","none");
			$("#file").removeAttr("require","true");
			$("#file").removeAttr("requireMsg","企业logo为必选项!");
			$("#file").removeAttr("dataType","Require");
		}	
	}
	
	//重新生成编号
	function generateCodeFun() {
		$.ajax({
			type:"GET",
			async: false,
			dataType:"html",
			url:"${basePath}busInfoController/generateCode.html",
			success:function(data){
				if(data!=""){
					$("#busNumber_show").val(data);
					$("#busNumber").val(data);
				}
			}
		});
	}
</script>
</head>
<body>
<div class="page_title"><h5>
<c:if test="${empty(busInfo.buId)}">添加企业信息</c:if>
<c:if test="${!empty(busInfo.buId)}">修改企业信息</c:if>
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="${basePath }busInfoController/updateSubmitBusInfo.html" method="post" enctype="multipart/form-data">
    <div class="form_main">
   		<dl>
            <dt>企业管理员：</dt>
            <dd>
            	<div class="dd_radio">${busInfo.userUnitName}</div>
            	<input type="hidden" name="userId" value="${busInfo.userId }"/>
            	<input type="hidden" name="busProvince" value="${busInfo.busProvince }"/>
            	<input type="hidden" name="busCity" value="${busInfo.busCity }"/>
            	<input type="hidden" name="busCounty" value="${busInfo.busCounty }"/>
            	<input type="hidden" name="busAddress" id="busAddress" value="${busInfo.busAddress}" maxlength="100"/>
            </dd>
        </dl>
        <dl>
            <dt><i>*</i>企业名称：</dt>
            <dd><input type="text" name="busName" id="busName" value="${busInfo.busName }" maxlength="30" require="true" requireMsg="企业名称为必填项!" dataType="Require"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>企业短名称：</dt>
            <dd><input type="text" name="mobileBusName" id="mobileBusName" value="${busInfo.mobileBusName }" maxlength="5" require="true" requireMsg="企业短名称为必填项!" dataType="Require"/>
            	<span style="color: red;">(注：用于手机端显示企业名称，最多5个字)</span>
            </dd>
        </dl>
        <dl>
            <dt>企业logo：</dt>
            <dd>
            	<input type="hidden" name="busLogo" id="busLogo" value="${busInfo.busLogo }"/>
				<img alt="" id="wbImg" src="" width="150" height="150">
				<c:if test="${!empty(busInfo.busLogo) }">
					<a href="${basePath}${busInfo.busLogo}" target="_blank"><img src="${basePath}${busInfo.busLogo}" height="50"></a>
				</c:if>
				<div id="upload_busLogo"></div>
            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=busLogo&path=bus&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
            </dd>
        </dl>
        <dl>
            <dt>读书会铭牌名称：</dt>
            <dd><input type="text" name="brandBusName" id="brandBusName" value="${busInfo.brandBusName }" maxlength="14"/>
            	<span style="color: red;">(注：用于生成读书会铭牌时的名称)</span>
            </dd>
        </dl>
        <dl>
            <dt>读书会铭牌编号：</dt>
            <dd>
            	<input type="text" name="busNumber_show" id="busNumber_show" value="${busInfo.busNumber}" disabled="disabled"/><span style="margin-left: 10px;"><a href="javascript:generateCodeFun()" style="text-decoration: underline;">重新生成编号</a></span>
            	<input type="hidden" name="busNumber"  id="busNumber" value="${busInfo.busNumber }"/>
        	</dd>
        </dl>
        <dl>
            <dt>铭牌授予时间：</dt><!-- startDate:'%y-%M-01 00:00:00' -->
            <dd>
            	<input type="text" name="brandDate" id="brandDate" value="${busInfo.brandDate}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px;"/>
        	</dd>
        </dl>
        <dl>
            <dt>发微博次数：</dt>
            <dd>
            	<input type="number" name="sendWbCount" id="sendWbCount" value="${busInfo.sendWbCount}" />
        	</dd>
        </dl>
        <dl>
            <dt>员工使用人数：</dt>
            <dd>
            	<input type="number" name="empUseNum" id="empUseNum" value="${busInfo.empUseNum}" />
        	</dd>
        </dl>
        <dl>
            <dt>是否生成app：</dt>
            <dd>
            	<input type="radio" name="isGenerateApp" id="isGenerateApp" value="1" <c:if test="${busInfo.isGenerateApp eq 1}">checked</c:if>/>是
            	<input type="radio" name="isGenerateApp" id="isGenerateApp" value="" <c:if test="${busInfo.isGenerateApp ne 1}">checked</c:if>/>否
        	</dd>
        </dl>
        <dl>
            <dt>企业描述：</dt>
            <dd>
            	<textarea rows="8" cols="50" name="busDes" id="busDes" maxlength="300">${busInfo.busDes}</textarea>
            </dd>
        </dl>
        <dl>
            <dt>企业详情：</dt>
            <dd>
            	<textarea name="busDetail" id="busDetail" style="width:900px;height:300px;">${busInfo.busDetail}</textarea>
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