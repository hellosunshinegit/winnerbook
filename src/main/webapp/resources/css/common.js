/* 名称：项目公用函数库 */
/* 功能：项目公用函数库 */
/* 作者: H.yIng */
/* Email: hyingreborn@qq.com */
/* 更新时间: 2013-05-11 */

/*  ----- 1/2 页面加载完毕执行 -----  */
$(function(){

	//Resize Frame
	//resize_page();
	
	//隔行变色
	datalist_even();
	
	//OnResize window
	/*window.onresize = function() {
		resize_page();
		//
		resize_tree();
		//
		
	}*/
	
	//导航点击事件

});

/*  ----- 2/2 预定义函数 -----  */

//详细页面加载后重置尺寸
function resize_page(){
	var target = '.page_main';
	var target_height = $(window).height()-$('.page_title').height();
	//liyanlin 注释计算行高
	$(target).height(target_height);
	//alert('2');
	resize_pagecont();
	resize_tree();
}
//添加文章2中尺寸重置
function resize_pagecont(){
	target_height = $('.page_main').height()-$('.page_tab').height()-2;
	$('.page_cont').height(target_height);
}
//树维护尺寸重置
function resize_tree(){
	target_height = $('.page_main').height()-80;
	rt_height = target_height + 50;
	$('#treeDemo').height(target_height);
	$('.col.rt').height(rt_height);
}

//开启数据列表隔行换色
function datalist_even(){
	$('.data_list table tr:odd').addClass('even');	
}

/* 身份证合法性校验函数 */
var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
function isIDCard(idCardNum){
	if(!/^\d{17}([0-9]|X)$/.test(idCardNum)) return "身份证号码长度或格式有误";
	idCardNum = idCardNum.replace(/x$/i, "a");
	if(city[parseInt(idCardNum.substr(0,2))]==null) return "身份证地区非法";
	var birthday = idCardNum.substr(6,4)+"-"+Number(idCardNum.substr(10,2))+"-"+Number(idCardNum.substr(12,2));
	var d = new Date(birthday.replace(/-/g, "/"));
	if(birthday!=(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate())) return "身份证出生日期非法";
	
	return true;
}