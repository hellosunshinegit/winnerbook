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
<script type="text/javascript" src="${basePath }resources/js/jquery.form.js"></script>
<style type="text/css">
	.form_main{font-size: 14px;}
</style>

<script type="text/javascript">
	var roleId = '${role.roleId }';
	$(document).ready(function(){
		menuChecked();
	});
	
	//获取此角色下的权限   动态选中已有的菜单权限
	function menuChecked(){
		$.ajax({
			type:"POST",
			async: false,
			dataType:"json",
			url:"${basePath}busInfoController/getCourseType.html?userId="+'${user.userId}',
			success:function(data){
				var courseTypes = $("input[id*=courseType]");
				$.each(data,function(index,item){
					$.each(courseTypes,function(index,element){
						if(item.courseTypeId==element.value){
							element.checked="checked";
						}
					});
				});
			}
		});
	}
	
	//点击全选触发的事件
	function checkAllFun(){
		var checkType = document.getElementById("checkAll").checked;
		if(checkType){
			$.each($("input[id^='courseType']"),function(index,item){
				item.checked = true;
			});
		}else{
			$.each($("input[id^='courseType']"),function(index,item){
				item.checked = false;
			});
		}
	}

	//保存
	function submitForm(){
		if($("input[id^='courseType']:checked").length==0){
			alert("请选择课程分类");
			return false;
		}
		var jsonStr = {
			"userId":'${user.userId}',
			"courseTypeArray":[]
		};
		var courseTypeArray = new Array();
		$.each($("input[id^='courseType']"),function(index,item){
			if(item.checked){
				courseTypeArray.push({"courseTypeId":item.value});
			}
		});
		jsonStr.courseTypeArray = courseTypeArray;
		$.ajax({
			type:"POST",
		    async: false,
			dataType:"json",
		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
			data:JSON.stringify(jsonStr),
			url: "${basePath}busInfoController/customCourseTypeSubmit.html",
		    success: function(data){
		    	if(data=="200"){
		    		alert("保存成功");
		    		window.location.href="${basePath}busInfoController/busInfoList.html";
		    	}else{
		    		alert("保存失败");
		    	}
		    }
		});
	}

</script>
</head>
<body>
<div class="page_title"><h5>企业管理员分配课程分类</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" method="post">
	<input type="hidden" name="userId" value="${user.userId }"/>
    <div class="form_main">
        <dl>
	         <dt>企业名：</dt>
	         <dd class="dd_radio">${user.userUnitName }</dd>
	     </dl>
		<dl>
	         <dt>选择情况：</dt>
	         <dd class="dd_radio"><input type="checkbox" id="checkAll" onclick="checkAllFun()" class="checkboxcss"/>全选</dd>
	     </dl>
        <dl>
        	 <dt>课程分类：</dt>
            <dd style="line-height: 30px;">
            	<c:forEach items="${courseTypeList}" var="item">
	            	<input type="checkbox" name="courseTypeId" id="courseType${item.typeId}" value="${item.typeId }" class="checkboxcss"/>${item.typeName }<br/>
            	</c:forEach>
            </dd>
        </dl>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                    <input type="button" class="btn btn-blue" value="返回" onclick="javascript:window.location.href='${basePath}busInfoController/busInfoList.html'" />
                </div>
            </dd>
        </dl>
    </div>
</form>
</div>
</body>
</html>