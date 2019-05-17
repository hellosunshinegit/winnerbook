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
<script type="text/javascript">

	function submitForm(){
		var editForm = document.getElementById("editForm");
		if(Validator.Validate(editForm)){
			var UPwd = $("#userPassword").val();
			var UPwd1 = $("#userPassword1").val();
	
			if(UPwd!=UPwd1){
				alert("密码和确认密码不一致，请重新输入");
				return false;
			}
			
			$("form[name=editForm]").ajaxSubmit({
				type:"POST",
				dataType:"html",
				async: false,
				url:"${basePath}userController/editPasswordSubmit.html?userId=${user.userId}?userPassword=${user.userPassword}",
				success:function(data){
					console.log(data);
					var jsonData = eval("("+data+")");
					if(jsonData.code!="200"){
						alert(jsonData.msg);
					}else{
						alert("密码更改成功"); 
						window.location.href="${path }/user/logout.html";
					}
				}
			});
		}
	}
</script>
</head>
<body>
<div class="page_title"><h5>修改密码</h5></div>
<div class="page_main">

<form name="editForm" id="editForm"  method="post">
    <div class="form_main">
        <dl>
            <dt><i>*</i>新密码：</dt>
            <dd><input type="password" name="userPassword" id="userPassword" require="true" requireMsg="密码为必填项!" dataType="Username" msg="密码必须由字母 或 字母和数字组成"/></dd>
        </dl>
        <dl>
            <dt><i>*</i>确认密码：</dt>
            <dd><input type="password" name="userPassword1" id="userPassword1" require="true" requireMsg="确认密码为必填项!" dataType="Require"/></dd>
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