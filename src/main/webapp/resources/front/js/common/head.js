//---登录、注册开关滑动代码部分-------------
function animateBtn(){
    var navbg = $('.navbg'),
		navtab = $('.version a'),
		ie = $.browser.msie && ($.browser.version == "6.0") && !$.support.style,
		bak = '';
	navtab.mouseenter(function(){
		navbg.stop(true,true);
		if(this.className.indexOf('_f')>0) return;
		var type = $(this).attr('type');
		!ie&&navtab.stop(true,true).fadeOut('fast');
		if(type=='pcv'){
			navbg.animate({left:0},'fast',function(){
				navtab[0].className='pcv_f';
				navtab[1].className='mcv';
			})
		}else{
			navbg.animate({left:'65px'},'fast',function(){
				navtab[0].className='pcv';
				navtab[1].className='mcv_f';
			}) 
		}
		!ie&&setTimeout(function(){navtab.fadeIn('fast')})
	});
	$('.version').mouseleave(function(e) {
		navtab.eq(0).mouseenter()
	});
}
//---登录、注册开关滑动代码部分  End-------------

//---菜单导航代码部分-------------
function loadMenu(){
    //一级菜单效果添加
    $("#menu_wydk").mouseover(function(){
        $('.wydk_box').removeClass('hideDiv');
    }).mouseout(function(){
        $('.wydk_box').addClass('hideDiv');
    });
    $("#menu_xszn").mouseover(function(){
        $('.xszn_box').removeClass('hideDiv');
    }).mouseout(function(){
        $('.xszn_box').addClass('hideDiv');
    });
    $("#menu_gywm").mouseover(function(){
        $('.gywm_box').removeClass('hideDiv');
    }).mouseout(function(){
        $('.gywm_box').addClass('hideDiv');
    });
	//二级菜单效果添加
	$('.tanchu_main').each(function(item){
		$(this).mouseover(function(){
			$(this).parent().removeClass('hideDiv');
		}).mouseout(function(){
			$(this).parent().addClass('hideDiv');
		});
	});
};
//---菜单导航代码部分  End-------------

function initHead(){
   animateBtn(); //登录、注册按钮滑动
   loadMenu(); //菜单导航
}
initHead();