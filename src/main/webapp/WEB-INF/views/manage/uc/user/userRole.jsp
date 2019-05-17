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
 var userId = '${user.userId }';
	$(document).ready(function(){
		roleChecked();
	});
	
	//获取此角色下的权限   动态选中已有的菜单权限
	function roleChecked(){
		$.ajax({
			type:"POST",
			async: false,
			dataType:"json",
			url:"${basePath}userController/findRoleByUserId.html?userId="+userId,
			success:function(data){
				var roleId = $("input[id*=roleId]");
				$.each(data,function(index,item){
					$.each(roleId,function(index,element){
						if(item.roleId==element.value){
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
		var roleIds=document.getElementsByName('roleId');
		for(var i=0;null!=roleIds&&i<roleIds.length;i++){
			if(roleIds[i].checked){
				countChecked++;
			}
		}
		if(countChecked==0){
			alert("请选择角色");
		}else{
			$("form[name=editForm]").ajaxSubmit({
				type:"POST",
				dataType:"html",
				url:"${basePath}userController/userRoleSubmit.html",
			    async: false,
			    success: function(data){
			    	if(data==1){
			    		alert("分配成功");
			    		window.location.href="${basePath}userController/userList.html";
			    	}
			    }
			});  
		}
	}
	
	// 只能单选
	function checkOne(obj){
		if($(obj).attr("checked")){
			$("input:checkbox[name='roleId']").not($(obj)).attr("checked",false);
		}
	}
</script> 
</head>
<body>
<div class="page_title"><h5>用户【${user.userName}】分配角色</h5></div>
<div class="page_main">
<form name="editForm" id="editForm" method="post">
	<input type="hidden" name="userId" value="${user.userId }"/>
    <div class="form_main">
        <dl>
	         <dt>用户：</dt>
	         <dd>${user.userName}</dd>
	     </dl>
        <dl>
        	 <dt>角色列表：</dt>
            <dd style="line-height: 30px;">
            	<c:if test="${userRoleLists.size()==0}">
            		暂无数据...
            	</c:if>
            	<c:if test="${userRoleLists.size()>0}">
            		<c:forEach items="${userRoleLists}" var="item">
		            	<input type="checkbox" name="roleId" 
		            		<c:if test="${user.userId eq item.userId }">checked="checked"</c:if> id="roleId${item.roleId }" value="${item.roleId }" onclick="checkOne(this)"/>
		            		${item.roleName}
		            		<c:if test="${item.roleCreateUserId ne userAdminId }">
			            		（${item.roleCreateUserName }）
		            		</c:if>
	            		<br/>
	            	</c:forEach>
            	</c:if>
            </dd>
        </dl>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                    <input type="button" class="btn btn-blue" value="返回" onclick="javascript:window.location.href='${basePath}userController/userList.html'" />
                </div>
            </dd>
        </dl>
    </div>
</form>
</div>
</body>
</html>