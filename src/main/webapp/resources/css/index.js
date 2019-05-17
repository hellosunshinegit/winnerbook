/* 名称：模块、大型页面私有函数库 */
/* 功能：模块、大型页面私有函数库 */
/* 作者: H.yIng */
/* Email: hyingreborn@qq.com */
/* 更新时间: 2012-12-19 */

/*  ----- 1/2 页面加载完毕执行 -----  */
$(function(){

	//开启模拟登陆，地址栏包含#login即可访问index.html
	//admin_login();
	
	//重置框架尺寸
	resize_article(".article",".sidebar",".main",".swich",".sidenav");
	
	//浏览器窗口拉伸时重置框架尺寸
	window.onresize = function() {
		resize_article(".article",".sidebar",".main",".swich",".sidenav");
	}
	
	//导航点击事件
	$('.header .nav ul li a').click(function(){
		$('.header .nav ul li').removeClass('act');
		$(this).parent().addClass('act');
		if ( $(this).hasClass('noside') ){
			$('.sidebar').addClass('hidden');
			$('.main').addClass('noside');
			core.loadPage("welcome.html",".main","mainframe",false);
		}else{
			$('.sidebar').removeClass('hidden');
			$('.sidebar').removeClass('off');
			$('.main').removeClass('noside');
			$('.main').removeClass('sideoff');
			var i = $(this).parent().index()-1;
			$('.sidebar .sidenav .nav2').removeClass('act');
			$('.sidebar .sidenav .nav2').eq(i).addClass('act');
			choose_sidenav(0,0);
		}
	});
	
	//侧栏开关
	$('.sidebar .swich a').click(function(){
		if ( $(this).parent().parent().hasClass('off') ){
			$('.sidebar').removeClass('off');
			$('.main').removeClass('sideoff');
		}else{
			$('.sidebar').addClass('off');
			$('.main').addClass('sideoff');
		}
	});
	//侧栏菜单
	$('.sidebar .sidenav a').click(function(){
		var pid = $(this).parent().parent().parent().parent().index();
		var id = $(this).parent().index();
		//alert(pid+'+'+id);
		choose_sidenav(pid,id);
		return false;
	});
	
});

/*  ----- 2/2 预定义函数 -----  */

//详细页面加载后重置尺寸
function resize_article(target,target2,target3,target4,target5){
	var target_height = target;
	target_height = $(window).height()-$('.header').height()-$('.footer').height();
	$(target).height(target_height);
	$(target2).height(target_height);
	$(target3).height(target_height);
	$(target4).height(target_height);
	$(target5).height(target_height);
}

//选择侧栏菜单
function choose_sidenav(pid,id){
	//alert(pid+'+'+id);

	choose_nav2 = $('.sidebar .nav2.act');
	choose_dl = choose_nav2.find('dl').eq(pid);
	choose_li = choose_dl.find('li').eq(id);
	choose_target = choose_li.find('a');
	
	$('.sidebar .sidenav li').removeClass('act');
	choose_target.parent().addClass('act');
	
	var url = choose_target.attr('href');
	/*if (url.indexOf("here")>=0){
		//alert('这个还没设置链接，自动打开腾讯法律研究中心网站');
		url = "http://law.tencent.com";
	}*/
	core.loadPage(url,".main","mainframe",false);
}

//模拟登陆限制
function admin_login(){
	var i = window.location.hash;
	if (i.indexOf("login")>=0){
		
	}else{
		window.location.href="login.html";	
	}	
}
