/**
 * 在屏幕中央弹出一个新窗口
 * 
 * @param {Object}
 *            url
 * @param {Object}
 *            width
 * @param {Object}
 *            height
 */
function openPopWindow(url, width, height) {
	var top = window.screen.availHeight / 2 - (height / 2);
	var left = window.screen.availWidth / 2 - (width / 2);

	window.open(url, "pop", "height=" + height + ", width=" + width + ", "
			+ "top=" + top + ", left=" + left + ", toolbar=no, "
			+ "menubar=no, scrollbars=yes,resizable=no,"
			+ "location=no, status=yes");
}

function closeWindow(msgs){
	if(confirm(msgs)){
		window.opener=null;
		window.close();
	}
}
function specialChar(pat){//检查是否包含特殊字符 
    //特殊字符包括：\ / @ # $ % ^ & * = < > \n \r 
    //如果需要自定义，可以在参数中设定 
    var test_val = /[\\\/\@\#\$\%\^\&\*\=\<\>\n\r]+/;
    if(test_val.test(pat)){
    	return false;
    }
    return true; 
}
function loginout(basepath){
	location.href=basepath+"/loginout.html";
}
/**
 * 根据主checkbox的状态连动改变子checkbox的状态 常用于grid
 */
function checkAll() {
	if ($(":checkbox[alt=all]").attr("checked") == undefined
			|| $(".all").attr("checked") == ""
			|| $(".all").attr("checked") == false) {
		$(":checkbox[alt=one]").attr("checked", false);
		$("#uniform-undefined span").removeClass("checked");
	} else {
		$(":checkbox[alt=one]").attr("checked", true);
		$("#uniform-undefined span").addClass("checked");
	}
}

function getSelectDataAsArray(){
	var ids = $('input[type=checkbox][alt=one]:checked');
	var data=new Array();
	$(ids).each(function(index,value){
		data.push($(this).val());
	});
	return data;
}

/**
 * 刷新当前页面 刷新父页面opener.refreshPage()
 */
function refreshPage() {
	window.location.replace(window.location.href);
}

function isJOSN(obj) {
	var isjson = typeof (obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}

function jumpPage(url) {
	window.location.href = url;
}

var Block = {
	blockUI : function() {
		$.blockUI({
			message : '请稍后....',
			css : {
				border : 'none',
				padding : '15px',
				backgroundColor : '#000',
				'-webkit-border-radius' : '10px',
				'-moz-border-radius' : '10px',
				opacity : .5,
				color : '#fff'
			}
		});
	},
	unblockUI : function() {
		setTimeout($.unblockUI, 500);
	}
};

function ajaxRequest(url, params, func, errorFunc) {
	Block.blockUI();
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : params,
		success : function(result) {
			Block.unblockUI();

			var success = result.success;
			var data = result.data;
			var msg = result.msg;

			if (success || success == "true") {
				func(msg, data);
			} else {
				errorFunc(msg, data);
			}
		},
		error : function() {
			Block.unblockUI();
			alert("Request timeout, please try again!");
		}
	});
}

function ajaxNormalRequest(url, params, func, errorFunc) {
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : params,
		success : function(result) {
			var success = result.success || result.code;
			var data = result.data;
			var msg = result.msg;

			if (success || success == "true") {
				func(msg, data);
			} else {
				errorFunc(msg, data);
			}
		},
		error : function() {
			alert("Request timeout, please try again!");
		}
	});
}

function ajaxSubmit(formId, callFunc, validationfunc, errorFunc) {
	var option = new Object();

	option.beforeSubmit = function(formData, jqForm, options) {
		var isOK = true;

		if (validationfunc != null && validationfunc != undefined) {
			isOK = validationfunc(formData, jqForm, options);
		}

		if (isOK) {
			Block.blockUI();
		}

		return isOK;
	};

	option.success = function(result) {
		Block.unblockUI();

		if (!isJOSN(result)) {
			result = eval("(" + result + ")");
		}

		var success = result.success;
		var data = result.data;
		var msg = result.msg;
		var url = result.url;

		if (success || success == "true") {
			callFunc(msg, data, url);
		} else {
			errorFunc(msg, data);
		}
	};
	option.dataType="json";
	$("#" + formId).ajaxSubmit(option);
}

function ajaxNormalSubmit(formId, callFunc, validationfunc, errorFunc) {
	var option = new Object();

	option.beforeSubmit = function(formData, jqForm, options) {
		var isOK = true;

		if (validationfunc != null && validationfunc != undefined) {
			isOK = validationfunc(formData, jqForm, options);
		}

		return isOK;
	};

	option.success = function(result) {

		if (!isJOSN(result)) {
			result = eval("(" + result + ")");
		}

		var success = result.success;
		var data = result.data;
		var msg = result.msg;
		var url = result.url;

		if (success || success == "true") {
			callFunc(msg, data,url);
		} else {
			errorFunc(msg, data);
		}
	};

	$("#" + formId).ajaxSubmit(option);
}

var Dialog = {
	openDialog : function(contentId) {
		var width=$("#"+contentId).width();  
		var height=$("#"+contentId).height();
		$.blockUI({
			message : $("#"+contentId),
			css:{
                top:  ($(window).height() - height) /2 + 'px', 
                left: ($(window).width() - width) /2 + 'px', 
				width:'auto',
				cursor:'default'
			},
			fadeIn:  50,			
			fadeOut: 50
		});
		$('.blockOverlay').click($.unblockUI);
	},
	closeDialog : function() {
		$.unblockUI({ fadeOut: 100 }); 
	}
};
/**
 * 
 * @param v
 * @param e
 * @returns {Number}
 */
function round(v,e){
	if(isNaN(v)){
		return;
	}
	var t=1;
	for(;e>0;t*=10,e--);
	for(;e<0;t/=10,e++);
	return Math.round(v*t)/t;
} 

function getRequestArguments() {
	var url = window.location.search;
	var theRequest = new Object();

	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}

	return theRequest;
}

//统计文本字符
function countChar(countLimit,textId,showCountId){
	var tarea=document.getElementById(textId);
	var textLength=tarea.value.length;
	var surplus=countLimit-textLength;
	if(surplus<0) surplus=0;
	document.getElementById(showCountId).innerHTML = surplus;
	if(countLimit-textLength<=0){
		tarea.value=tarea.value.substring(0,countLimit);
	}	 
}  
//统计字符长度中文为2，英文为1
function getlength(str) {
	if(str == null ) {
		return 0;
	}
	var len = 0;
	for(i=0;i<str.length;i++) {   
		if(str.charCodeAt(i)>256){   
		   len   +=   2;   
		} else {   
		   len++;   
		}   
	} 
	return len;
}

function  selectLeftItem(selectUrl){
	$('#_nav_ul_ > li > a').each(function(){
		var url = $(this).attr("href");
		if(url.indexOf(selectUrl) >= 0) {
			$(this).addClass(' BB ');
			$(this).parent().addClass(' nav_hover ');
		}
	});
	$('#_head_ul_ > li > a').each(function(){
		var url = $(this).attr("href");
		if(url.indexOf(selectUrl) >= 0) {
			$(this).addClass(' BB ');
			$(this).parent().addClass(' nav_hover ');
		}
	});
}

//查询排序
function px(sortColumn,sortType,obj){
	if(sortColumn=="up_time"){
		$("#min_price").html($("#min_price1").html());
        $("#store").html($("#store1").html());
        $("#clickCounts").html($("#clickCounts1").html());
        $("#collectCounts").html($("#collectCounts1").html());
        $("#sellCounts").html($("#sellCounts1").html());
        $("#changeRate").html($("#changeRate1").html());
	}
	
	if(sortColumn=="min_price"){
		$("#store").html($("#store1").html());
        $("#clickCounts").html($("#clickCounts1").html());
        $("#collectCounts").html($("#collectCounts1").html());
        $("#sellCounts").html($("#sellCounts1").html());
        $("#changeRate").html($("#changeRate1").html());
         $("#up_time").html($("#up_time1").html());
	}
	if(sortColumn=="store"){
		$("#min_price").html($("#min_price1").html());
        $("#clickCounts").html($("#clickCounts1").html());
        $("#collectCounts").html($("#collectCounts1").html());
        $("#sellCounts").html($("#sellCounts1").html());
        $("#changeRate").html($("#changeRate1").html());
         $("#up_time").html($("#up_time1").html());
	}
	if(sortColumn=="clickCounts"){
		$("#min_price").html($("#min_price1").html());
        $("#store").html($("#store1").html());
        $("#collectCounts").html($("#collectCounts1").html());
        $("#sellCounts").html($("#sellCounts1").html());
        $("#changeRate").html($("#changeRate1").html());
         $("#up_time").html($("#up_time1").html());
	}
	if(sortColumn=="collectCounts"){
		$("#min_price").html($("#min_price1").html());
        $("#store").html($("#store1").html());
        $("#clickCounts").html($("#clickCounts1").html());
        $("#sellCounts").html($("#sellCounts1").html());
        $("#changeRate").html($("#changeRate1").html());
         $("#up_time").html($("#up_time1").html());
	}
	if(sortColumn=="sellCounts"){
		$("#min_price").html($("#min_price1").html());
        $("#store").html($("#store1").html());
        $("#clickCounts").html($("#clickCounts1").html());
        $("#collectCounts").html($("#collectCounts1").html());
        $("#changeRate").html($("#changeRate1").html());
         $("#up_time").html($("#up_time1").html());
	}
	if(sortColumn=="changeRate"){
		$("#min_price").html($("#min_price1").html());
        $("#store").html($("#store1").html());
        $("#clickCounts").html($("#clickCounts1").html());
        $("#collectCounts").html($("#collectCounts1").html());
        $("#sellCounts").html($("#sellCounts1").html());
         $("#up_time").html($("#up_time1").html());
	}
	if(sortType == "asc"){
		$(obj).attr("src","../resources/images/up_on.png");
		$(obj).parent().find("img:last").attr("src","../resources/images/down.png");
		
	}else{
		$(obj).attr("src","../resources/images/down_on.png");
		$(obj).parent().find("img:first").attr("src","../resources/images/up.png");
	}
	
	$("#sortColumn").val(sortColumn);
	$("#sortType").val(sortType);
	showPage(false);
}

function block_div(id){
	$('#'+id).block({ 
         message: '<img src="'+_base+'/resources/myui/img/ajax-loaders/ajax-loader-7.gif" >', 
         css:{border:'none'},
         overlayCSS:{'background-color':'white'}
    }); 
}