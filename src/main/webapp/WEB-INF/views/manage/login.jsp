<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企业读书云平台</title>
<link rel="icon" href="/resources/images/def_img1.png">
<link rel="stylesheet" type="text/css" href="${basePath }resources/css/rui.css">
<link rel="stylesheet" type="text/css" href="${basePath }resources/css/common.css">
<script type="text/javascript" src="${basePath }resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${basePath }resources/js/jquery.form.js"></script>
<script type="text/javascript" src="${basePath }resources/js/common.js"></script>
<style type="text/css">
html,body { background:url(${basePath}resources/new_images/bj.jpg);}
.login_box { /*background:url(${basePath}resources/images/bg.jpg); margin-top:100px; border-top:2px solid #689c38; box-shadow:0px 4px 4px #1A86BA;*/ width:402px; margin: 0 auto;margin-top: 200px;}
.login_box .lf {overflow:hidden; float:left; text-align:right;}
.login_box .lf .logo {font-size: 25px;color: #a0a0a1;padding:0px;display:block;margin-left: 90px;margin-bottom:20px;}
.login_box .lf .logo img{float:left;text-align:center;}
.login_box .lf .logo .yy{ margin:10px 0 10px 62px;}
/*background:url(${basePath}resources/images/login_logo.png) center right no-repeat; display:block; height:195px; padding:0px; margin-right:50px; text-indent:-999em;*/
.login_box .rt { width:402px;overflow:hidden; float:left; padding:10px 0px;background:url(${basePath}resources/new_images/bjk.png) no-repeat; }
.login_box .rt dl { /*margin-left:50px;*/}
.login_box .rt dt { padding:7px 0px;}
.login_box .rt dd {}
.login_box .rt input[type=text],.login_box .rt input[type=password] {width:200px;height:57px;font-size: 16px;border: none;background:none;line-height: 57px;}
.login_box .rt .btn-green { width:402px; height:40px;}
.footer { text-align:center; line-height:40px; color:#a0a0a1;}

/*遮罩*/
.shield {width:100%;height:100%;background-color:#000;position:absolute;top:0;left:0;z-index:2;opacity:0.3;filter: alpha(opacity=30);display:none;}
.xcConfirm {width:570px; height:300px;background-color:#fff;  margin: auto;position: absolute;z-index:3;top: 0;bottom: 0;left: 0;right: 0;display:none;}

.xcConfirm .popBox{position: fixed; left: 50%; top: 50%; background-color: #ffffff; z-index: 2147000001; width: 570px; height: 300px; margin-left: -285px; margin-top: -150px; border-radius: 5px; font-weight: bold; color: #535e66;}
.xcConfirm .popBox .ttBox{height: 30px; line-height: 30px; padding: 14px 30px; border-bottom: solid 1px #eef0f1;}
.xcConfirm .popBox .ttBox .tt{font-size: 18px; display: block; float: left; height: 30px; position: relative;}
.xcConfirm .popBox .ttBox .clsBtn{display: block; cursor: pointer; width: 12px; height: 12px; position: absolute; top: 22px; right: 30px; background: url(${basePath}resources/images/icons.png) -48px -96px no-repeat;}
.xcConfirm .popBox .txtBox{margin: 40px 65px; height: 100px; overflow-y: auto;font-size: 13px;font-weight: normal;}
.xcConfirm .popBox .txtBox ul li {height: 30px;line-height: 30px;}
.xcConfirm .popBox .txtBox ul li span{margin-right: 20px;}
.xcConfirm .popBox .txtBox ul li .checkcss{width: 16px;height: 16px;}
.xcConfirm .popBox .btnArea{border-top: solid 1px #eef0f1;}
.xcConfirm .popBox .btnGroup{float: right;}
.xcConfirm .popBox .btnGroup .sgBtn{margin-top: 14px; margin-right: 10px;}
.xcConfirm .popBox .sgBtn{display: block; cursor: pointer; float: left; width: 95px; height: 35px; line-height: 35px; text-align: center; color: #FFFFFF; border-radius: 5px;}
.xcConfirm .popBox .sgBtn.ok{background-color: #0095d9; color: #FFFFFF;}
.xcConfirm .popBox .sgBtn.cancel{background-color: #546a79; color: #FFFFFF;}


</style>
<script type="text/javascript">
$(function(){
	//如果跳转的在iframe里面 则跳出来
	if(top!=self){
	    if(top.location != self.location){
	        top.location=self.location; 
	    }
	}
	$('#_userName').focus();
	$('#_code').keyup(function(eventObject){
		if(13 == eventObject.keyCode){
			dologin();
		}
	});
});
function dologin(){
	var userName = $.trim($('#_userName').val());
	if (!userName) { 
        alert('请输入手机号码!'); 
        return false; 
    } 
    var password = $.trim($('#_password').val());
    if (!password) { 
        alert('请输入密码!'); 
        return false; 
    } 
    var _code = $.trim($('#_code').val());
    if (!_code) { 
        alert('请输入验证码!'); 
        return false; 
    } 
    
	var selectUser = $('input[name="selectUserInput"]:checked').val();
	$("#selectUser").val(selectUser);
    
	$("form[name=loginForm]").ajaxSubmit({
		type:"POST",
		dataType:"json",
		async: false,
		url:"${basePath }user/dologin.html",
		success:function(data){
			if(data.success==false){
				alert(data.msg);
				cancel_shield();
	            if(data.data!=undefined){
					$("#tel").html(userName);	            	
	            	var str = "";
					$.each(data.data,function(index,item){
						str+="<li><input type='radio' name='selectUserInput' class='checkcss' value='"+item.userId+"-"+item.busId+"'/>&nbsp;&nbsp;<span>姓名："+item.userName+"</span><span>企业名称："+item.busName+"</span></li>";
					});
					$("#selUser").html(str);
	            	shield();
	            }
			}else{
				window.location.href="${basePath }user/layout.html";
			}
		}
	});
	
}

function reloadcode(){
    var verify=document.getElementById('code');
    verify.setAttribute('src','${basePath }verifyController/verifyCode.do?it='+Math.random());
}

//显示
function shield(){
    var s = document.getElementById("shield");
    s.style.display = "block";
    
    var l = document.getElementById("log_window");
    l.style.display = "block";
}
//隐藏
function cancel_shield(){
    var s = document.getElementById("shield");
    s.style.display = "none";
    
    var l = document.getElementById("log_window");
    l.style.display = "none";
}

</script>
</head>
<body>

<div class="login_box clearfix">
    <div class="lf">
        <div class="logo"><%-- <img src="${basePath }resources/new_images/log.png">--%><%--  <img class="yy" src="${basePath }resources/new_images/tt.png"> --%>企业读书云平台</div>
  </div>
    <div class="rt">
    <form name="loginForm" id="loginForm" method="post">
    	<input type="hidden" name="selectUser" id="selectUser"/>
        <dl>

            <dd>
                <input type="text"  value="" name="userName" id="_userName" placeholder="请输入手机号码">
             </dd>
        </dl>
        <dl>

            <dd>
                <input type="password" value="" name="userPassword" id="_password" placeholder="请输入密码">
            </dd>
        </dl>
        <dl>

            <dd>
                <input type="text" name="code" id="_code" placeholder="请输入验证码">
              	&nbsp;&nbsp;
				<img src="${basePath }verifyController/verifyCode.do" id="code" onclick="reloadcode()" style="cursor: pointer;" alt="看不清楚,换一张" >
            </dd>
        </dl>
        
       </form>
            <div class="an">
                <input type="button" value="登录系统" class="btn btn-green" onclick="dologin();"/>
            </div>
  </div>
</div>
<!-- 背景遮罩 -->
<div class="shield" id="shield"></div>
<div class="xcConfirm" id="log_window">
	<div class="popBox">
		<div class="ttBox">
			<a href="javascript:cancel_shield()" class="clsBtn"></a>
			<span class="tt">请选择所属企业<span style="font-weight: normal;font-size: 14px;">（手机号：<span id="tel"></span>）</span></span>
		</div>
		<div class="txtBox">
			<ul id="selUser">
			</ul>
		</div>
		<div class="btnArea">
			<div class="btnGroup">
				<a href="javascript:dologin()" class="sgBtn ok">确定</a>
				<a href="javascript:cancel_shield()" class="sgBtn cancel">取消</a>
			</div>
		</div>
	</div>
	
</div>

<div class="footer">
    Copyright @ 1998 - 2019 winnerbook. All Rights Reserved
</div>

<script>
// Current JS 计算高度
/* function resize_loginbox(){
    var target_height = ($(window).height()-$('.login_box').height())/2-20;
    $('.login_box').css('margin-top',target_height);
}

$(function(){
    resize_loginbox();
    
    window.onresize = function() {
        resize_loginbox();
    }
}); */
</script>
</body>
</html>