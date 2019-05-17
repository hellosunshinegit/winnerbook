//---广告轮播代码部分-------------
function bannerScroll(){
    $('.DB_tab25').DB_tabMotionBanner({
			key:'b28551',
			autoRollingTime:3000,                            
			bgSpeed:500,
			motion:{
				DB_1_1:{left:-50,opacity:0,speed:1000,delay:500},
				DB_1_2:{left:-50,opacity:0,speed:1000,delay:1000},
				DB_1_3:{left:100,opacity:0,speed:1000,delay:1500},
				DB_2_1:{top:50,opacity:0,speed:1000,delay:500},
				DB_2_2:{top:50,opacity:0,speed:1000,delay:1000},
				DB_2_3:{top:100,opacity:0,speed:1000,delay:1500},
				DB_3_1:{left:-50,opacity:0,speed:1000,delay:500},
				DB_3_2:{top:50,opacity:0,speed:1000,delay:1000},
				DB_3_3:{top:0,opacity:0,speed:1000,delay:1500},
				DB_4_1:{top:50,opacity:0,speed:1000,delay:500},
				DB_4_2:{top:0,opacity:0,speed:1000,delay:1000},
				DB_4_3:{top:0,opacity:0,speed:1000,delay:1500},
				DB_4_4:{top:30,opacity:0,speed:1000,delay:2000},
				DB_4_5:{top:100,opacity:0,speed:1000,delay:3000},
				end:null
			}
	})
}
//---广告轮播代码部分  End-------------

function initHome(){
	bannerScroll(); //广告轮播
	$("#computeBtn").click(function(){
		window.location.href="computeIncome.html";
    });//还款计算
}
initHome();