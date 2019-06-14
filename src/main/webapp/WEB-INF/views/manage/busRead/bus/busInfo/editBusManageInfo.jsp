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
<!--引入js -->
<script type="text/javascript" src="${basePath }resources/jsFun/js_address.js"></script>
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		 UE.getEditor('busDetail');
		 var buId = '${busInfo.buId}';
		 if(buId!=''){
			 addressValue('${basePath}',"provinceId","cityId","areaId",'${busInfo.busProvince}','${busInfo.busCity}','${busInfo.busCounty}');
		 }else{
			address('${basePath}',"provinceId");
		 }
	});
	
	function submitForm(){
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			document.editForm.submit();
			alert("修改成功");
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
</script>
</head>
<body>
<div class="page_title"><h5>
完善企业信息
</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" action="${basePath }busInfoController/updateSubmitBusInfo.html" method="post" enctype="multipart/form-data">
    <div class="form_main">
	        <dl>
	            <dt>企业管理员：</dt>
	            <dd>
	            	<div class="dd_radio">${busInfo.userUnitName}</div>
	            	<input type="hidden" name="userId" value="${busInfo.userId }"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt><i>*</i>企业名称：</dt>
	            <dd>
		            <input type="text" name="busName" id="busName" value="${busInfo.busName }" maxlength="16" require="true" requireMsg="企业名称为必填项!" dataType="Require"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt>企业短名称：</dt>
	            <dd><input type="text" name="mobileBusName" id="mobileBusName" value="${busInfo.mobileBusName }" maxlength="5"/>
	            	<span style="color: red;">(注：用于手机端显示企业名称)</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>读书会铭牌名称：</dt>
	            <dd>
	            	<c:if test="${busInfo.brandBusName ne '' && busInfo.brandBusName ne null && busInfo.brandBusName ne undefined}">
		            	<input type="text" name="brandBusName" id="brandBusName" value="${busInfo.brandBusName }" disabled="disabled"/>
		            	<input type="hidden" name="brandBusName" value="${busInfo.brandBusName }"/>
		            	<span style="color: red;">注：读书会铭牌名称添加后不可修改。如需修改，请联系管理员。</span>
	            	</c:if>
	            	<c:if test="${busInfo.brandBusName eq '' || busInfo.brandBusName eq null || busInfo.brandBusName eq undefined}">
		            	<input type="text" name="brandBusName" id="brandBusName" maxlength="16"  value="${busInfo.brandBusName }" />
	            		<span style="color: red;">注：读书会铭牌名称添加后不可修改。最长16个字，如果超过，请联系管理员。</span>
	            	</c:if>
	            </dd>
	        </dl>
	        <dl>
	            <dt>企业名牌：</dt>
	            <dd>
	            	<c:if test="${busInfo.brandImg ne '' && busInfo.brandImg ne null && busInfo.brandImg ne undefined}">
	            		<a href="${basePath}busInfoController/uploadBrandImg.html?busId=${busInfo.userId}" style="color:blue;text-decoration: underline;" title="点击下载读书会铭牌">下载企业名牌</a>
	            	</c:if>
	            	<c:if test="${busInfo.brandImg eq '' || busInfo.brandImg eq null || busInfo.brandImg eq undefined}">
	            		请联系管理员生成读书会铭牌
	            	</c:if>
	            	<input type="hidden" name="brandImg" value="${busInfo.brandImg }"/>
	            </dd>
	        </dl>
	        <dl>
	            <dt><c:if test="${empty(busInfo.busLogo)}"><i>*</i></c:if>企业logo：</dt>
	            <dd>
		           	<input type="hidden" name="busLogo" id="busLogo" value="${busInfo.busLogo }"/>
					<img alt="" id="wbImg" src="" width="150" height="150">
					<c:if test="${!empty(busInfo.busLogo) }">
						<a href="${basePath}${busInfo.busLogo}" target="_blank"><img src="${basePath}${busInfo.busLogo}" width="50" height="50"></a>
					</c:if>
					<div id="upload_busLogo"></div>
	            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=busLogo&path=bus&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
	            </dd>
	        </dl>
	        <dl>
	            <dt>所属行业：</dt>
	            <dd><input type="text" name="busIndustry" id="busIndustry" value="${busInfo.busIndustry }" maxlength="30" /></dd>
	        </dl>
	        <dl>
	            <dt>企业地址：</dt> 
	            <dd>
	            	<input type="hidden" name="basePath" id="basePath" value="${basePath }"/>
	            	<select name="busProvince" id="provinceId"  onchange="addressFun(this.value,2,'cityId')"></select>
	            	<select name="busCity" id="cityId"  onchange="addressFun(this.value,3,'areaId')"></select>
	            	<select name="busCounty" id="areaId"  ></select>
	                <input type="text" name="busAddress" id="busAddress" value="${busInfo.busAddress}" maxlength="100"/>(详细住址)
	            </dd>
	        </dl>
	        <dl>
	            <dt>铭牌编号：</dt>
	            <dd>
	            	${busInfo.busNumber}
	            	<input type="hidden" name="busNumber" value="${busInfo.busNumber }"/>
	        	</dd>
	        </dl>
	        <dl>
	            <dt>铭牌授予时间：</dt>
	            <dd>
	            	${busInfo.brandDateChinese }
	            	<input type="hidden" name="brandDate" value="${busInfo.brandDate }"/>
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