function isMobel(value){   
    var exp_mobile = /^1[3|4|5|8][0-9]{9}$/;
    return exp_mobile.test(value);
} 
var hasinput = false;
$(function(){
     $('#getCodeBtn').click(function(){
         var _mobile = $.trim($('#mobilenum').val());
         var _that = this;
        if(!mobileIsNotnull(_mobile)){return false;}
             $.ajax({
                    type: "post",
                    url: "/cardorder/smsverify.jhtml",
                    data: {mobilenum:_mobile},
                    dataType: "json",
                    success: function (data) {
                        if(data.success){  
                            // $(_that).parent().find('.codeTips').html("验证码已发送至您的手机").show();
                            alert(data.msg); 
                            sendMessage();
                        } else {
                        	 alert(data.msg); 
                        }
                   }
              });
    });
    
     $('#submitbtn').click(function(){
        var checkMobile = chekMobile();
        var checkCode = checkVerifyCode();
        if(checkMobile&&checkCode){
            $('#bindMobileForm').submit();
        }
    });
    
    
});

function sms_time_down(_that){
        var $sec = parseInt($('#sp-second').html());
        if($sec>0){
            $sec--;
            $('#sp-second').html($sec);
            setTimeout(function(){sms_time_down(_that);},1000);
        }else{
            $('#sp-hide').hide();
            $("#getCodeBtn").html('重新发送').show();
            $('#User_mobile').removeAttr("readonly");
            var _code = $.trim($('#User_verifyCode').val());
            $("#yyCodeId .okBnt").show();
            hasinput = false;
            $(_that).parent().find('.codeTips').hide();
        }
}
function checkInputMoblie(obj){
    if(!hasinput){
        if(chekMobile(obj.value)){
            $("#getCodeBtn").show();
            $("#sp-hide").hide();
        }
    }
}

function checkVerifyCode(){
    var verifyCode = $('#User_verifyCode').val();
    if(verifyCode == "" || verifyCode == null){
       $('#User_verifyCode_em_').html("请填写验证码").show();
       return false;
    }
    return true;
}

function chekMobile() {
    var mob = Trim($('#User_mobile').val());
    $('#User_mobile').val(mob);
    $('#mobileTips').attr("style","");
    if (mob == "" || mob == null) {
        $('#mobileTips').html("请输入手机号码");
        $('#mobileTips').attr("class","error");
        return false;
    } else if(!isMobel(mob)){
        $('#mobileTips').html("请输入正确的手机号码");
        $('#mobileTips').attr("class","error");
        return false;
    }
    var rs = ajaxCheckMobile(mob);
    if (rs == 1) {
        $('#mobileTips').html("手机号码已经被使用，请更换");
        $('#mobileTips').attr("class","error");
        return false;
    }else {
        $('#mobileTips').html("");
        $('#mobileTips').attr("class","yes");
    }
    return true;
}

function ajaxCheckMobile(str) { 
    $.ajaxSetup({
        async : false
    });
    var url = "/web.ajax/register/checkmobile.jsp";
    var mobile = encodeURI(str);
    var rs = null;
    $.post(url, {
        "mobile" : mobile
    }, function(data, varStatus) {
        rs = data;
    }, "text");
    return rs;
}

function sendMessage() {
	  　 curCount = 60; 
	   $("#getCodeBtn").attr("disabled", "true"); 
	   InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
}

	//timer处理函数
function SetRemainTime() {
         if (curCount == 0) {                
            window.clearInterval(InterValObj);//停止计时器
	        $("#getCodeBtn").removeAttr("disabled");//启用按钮
	        $("#getCodeBtn").val("重新发送验证码");
	    }
	    else {
	        curCount--;
	        $("#sp-second").html(curCount);
	    }
}
function mobileIsNotnull(mob){
	if (mob == "" || mob == null) {
		alert("请输入手机号");
		 return false; 
	}else if (!isMobel(mob)) {
		alert('请输入正确的手机号码');
	    return false; 
	}
	return true;
}
