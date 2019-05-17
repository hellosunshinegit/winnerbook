var Global_utils = {
	showDialog:function(id){
		$("#"+id).modal("show");
	},
	hideDialog:function(id){
		$("#"+id).modal("hide");
	},
	//快递类型
	kuaidi:[{id:1,name:"EMS"},{id:2,name:"顺丰"},{id:2,name:"申通"},{id:4,name:"圆通"},{id:5,name:"中通"},{id:6,name:"韵达"}],
	//拒绝类型
	jujueleixing:[{id:1,name:"退还商品破损"},{id:2,name:"与描述退款理由不符"},{3:"与买家协商取消退款"},{4:"其他"}],
	showSuccessMsg:function (text){
		var options = {
			type:"success",
			layout:"top",
			text:text
		}
		noty(options);
	},
	showErrorMsg : function (text){
		var options = {
			type:"error",
			layout:"top",
			text:text
		}
		noty(options);
	},
	showInfo : function (text){
		var options = {
			type:"information",
			layout:"top",
			text:text
		}
		noty(options);
	},
	showInfoBottonRight : function (text){
		var options = {
			type:"error",
			layout:"bottomRight",
			text:text
		}
		noty(options);
	},
	openPage:function(url){
		if(url.indexOf(_base)>0){
		}else{
			url = _base+url;
		}
		window.open(url);
	},
	confirm:function(option){
		var opt = {title:"提示",msg:"确定继续操作？",fun:function(){}}
		$.extend(opt,option);
		var timeId = "myModal"+new Date().getTime();
		$("[confirmdiv=true]").remove();
		var div = '<div confirmdiv=true class="modal hide fade" id="'+timeId+'">'+
			'<div class="modal-header">'+
				'<button type="button" class="close" data-dismiss="modal">×</button>'+
				'<h3>'+opt.title+'</h3>'+
			'</div>'+
			'<div class="modal-body">'+
				'<p>'+opt.msg+'</p>'+
			'</div>'+
			'<div class="modal-footer">'+
				'<a href="#" class="btn" data-dismiss="modal">取消</a>'+
				'<a href="#" class="btn btn-primary" >确定</a>'+
			'</div>'+
		'</div>';
		$(div).appendTo($("body"));
		$("#"+timeId).find(".btn-primary").unbind().bind("click",function(){opt.fun();$("#"+timeId).remove();$("#"+timeId).modal("hide");});
		$("#"+timeId).modal("show")
	},
	confirm1:function(option){
		var opt = {title:"提示",msg:"确定继续操作？",fun:function(){}}
		$.extend(opt,option);
		var timeId = "myModal"+new Date().getTime();
		$("[confirmdiv=true]").remove();
		var div = '<div confirmdiv=true class="modal hide fade" id="'+timeId+'">'+
			'<div class="modal-header">'+
				'<button type="button" class="close" data-dismiss="modal">×</button>'+
				'<h3>'+opt.title+'</h3>'+
			'</div>'+
			'<div class="modal-body">'+
				'<p>'+opt.msg+'</p>'+
			'</div>'+
			'<div class="modal-footer">'+
				'<a href="#" class="btn" data-dismiss="modal">取消</a>'+
				'<a href="#" class="btn btn-primary" >确定</a>'+
			'</div>'+
		'</div>';
		$(div).appendTo($("body"));
		$("#"+timeId).find(".btn-primary").unbind().bind("click",function(){opt.fun();$("#"+timeId).remove();$("#"+timeId).modal("hide");});
		$("#"+timeId).modal("show")
	}
	
}