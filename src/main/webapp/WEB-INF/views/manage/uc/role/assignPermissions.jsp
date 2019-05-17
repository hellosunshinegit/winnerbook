<%@ page contentType="text/html; charset=UTF-8" %>
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
			url:"${basePath}roleController/findMenuByRoleId.html?roleId="+roleId,
			success:function(data){
				var menuIds = $("input[id*=menuCode]");
				$.each(data,function(index,item){
					$.each(menuIds,function(index,element){
						if(item.menuId==element.value){
							element.checked="checked";
						}
					});
				});
			}
		});
	}

	//保存
	function submitForm(){
		//是否选中
		var countChecked = 0;
		var menuIds=document.getElementsByName('menuIds');
		for(var i=0;null!=menuIds&&i<menuIds.length;i++){
			if(menuIds[i].checked){
				countChecked++;
			}
		}
		if(countChecked==0){
			alert("请选择权限");
		}else{
			$("form[name=editForm]").ajaxSubmit({
				type:"POST",
				dataType:"html",
			    async: false,
				url: "${basePath}roleController/assignPermissionSubmit.html",
			    success: function(data){
			    	if(data==1){
			    		alert("分配成功");
			    		window.location.href="${basePath}roleController/roleList.html";
			    	}
			    }
			});  
		}
	}
	
	//点击全选触发的事件
	function checkAllFun(value){
		var checkType = document.getElementById("checkAll").checked;
		if(checkType){
			//先清除所有的选中的数据
			var menuIds=document.getElementsByName('menuIds');
			for(var i=0;null!=menuIds&&i<menuIds.length;i++){
				menuIds[i].checked=false;
			}
		}
		//再重新选择
		var menuIds=document.getElementsByName('menuIds');
		for(var i=0;null!=menuIds&&i<menuIds.length;i++){
			if(menuIds[i].checked){
				menuIds[i].checked=false;
			}else{
				menuIds[i].checked=true;
			}
		}
	}
	
	//点击权限列表
	function clickCheckbox(code){
		if(code.length==6){
			if($("#menuCode"+code).attr("checked")=="checked"){
				 $("input[id=menuCode"+code.substr(0,4)+"]").attr("checked","checked");
				 $("input[id=menuCode"+code.substr(0,2)+"]").attr("checked","checked");
			}
		}else if(code.length==4){
			if($("#menuCode"+code).attr("checked")=="checked"){
				 $("input[id*=menuCode"+code+"]").attr("checked","checked");
				 $("input[id=menuCode"+code.substr(0,2)+"]").attr("checked","checked");
			}else{
				 $("input[id*=menuCode"+code+"]").removeAttr("checked","checked");
				 $("input[id=menuCode"+code.substr(0,2)+"]").removeAttr("checked","checked");
			}
		}else{
			if($("#menuCode"+code).attr("checked")=="checked"){
				 $("input[id*=menuCode"+code+"]").attr("checked","checked");
			}else{
				 $("input[id*=menuCode"+code+"]").removeAttr("checked","checked");
			}
		}
	}
</script>
</head>
<body>
<div class="page_title"><h5>角色【${role.roleName }】分配权限</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" method="post">
	<input type="hidden" name="roleId" value="${role.roleId }"/>
    <div class="form_main">
        <dl>
	         <dt>分配权限角色：</dt>
	         <dd>${role.roleName }</dd>
	     </dl>
		<dl>
	         <dt>选择情况：</dt>
	         <dd><input type="checkbox" id="checkAll" onclick="checkAllFun()"/>全选</dd>
	     </dl>
        <dl>
        	 <dt>权限列表：</dt>
            <dd style="line-height: 30px;">
            	<c:forEach items="${menuLists}" var="item">
            		<c:if test="${fn:length(item.menuCode)==2 }"><br/></c:if>
            		<c:if test="${fn:length(item.menuCode)==4 }"><br/>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
	            	<input type="checkbox" name="menuIds" id="menuCode${item.menuCode}" value="${item.menuId }" onclick="clickCheckbox('${item.menuCode}')"/>${item.menuName }
            	</c:forEach>
            </dd>
        </dl>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                    <input type="button" class="btn btn-blue" value="返回" onclick="javascript:window.location.href='${basePath}roleController/roleList.html'" />
                </div>
            </dd>
        </dl>
    </div>
</form>
</div>
</body>
</html>