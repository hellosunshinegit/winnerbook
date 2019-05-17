/**
 * 获取登录人对应的企业的课程包
 * 2019/03/28
 */
var userCourseTypes = "";
function addressFun(basePath){
	$.ajax({
		type:"POST",
		async: false,
		dataType:"json",
		url:basePath+"userController/userCourseTypes.html",
		success:function(data){
			if(data!=null){
				userCourseTypes = JSON.stringify(data);
			}
		}
	});
}


/**
 * 观看视频和音频时，定时监控，观看时间做记录
 */
//获取播放时间
function playInterval(basePath,main_play_id,courseId,type,fileId){
	var main_play = document.getElementById(main_play_id);// video标签对象
	var totalTime = "";
	var interval_main_play = null;
	function timeInterval() {
		// 10s钟发送后台一个请求
		console.log('lookTime----' + main_play.currentTime);
		studentLook(basePath,courseId, main_play.currentTime,type,0,fileId,totalTime);
	}

	// 视频加载完成
	main_play.oncanplay = function() {
		var hour = parseInt((main_play.duration)/3600);
		var minute = parseInt((main_play.duration%3600)/60);
		var second = Math.floor(main_play.duration%60);
		var time = "";
		if(hour>0){
			time+=hour+"小时";
		}
		if(minute>0){
			time+=minute+"分";
		}
		if(second>0){
			if((second+"").length<2){
				second = "0"+second;
			}
			time+=second+"秒";
		}
		totalTime = time;
		console.log("视频加载完成",time);
	};

	// 视频播放事件
	main_play.onplay = function() {
		console.log("记录学习");
		// 定时1分钟一次发送后台
		interval_main_play = setInterval(function() {
			timeInterval();
		}, 1000 * 10);
		studentLook(basePath,courseId, main_play.currentTime,type,0,fileId,totalTime);
	};

	// 暂停 每暂停一次发送一次记录
	main_play.onpause = function() {
		console.log("视频暂停");
		window.clearInterval(interval_main_play);
		// 发送数据向后台
		studentLook(basePath,courseId, main_play.currentTime,type,0,fileId,totalTime);
	};

	main_play.onended = function() {
		console.log("视频播放完成");
		window.clearInterval(interval_main_play);
		studentLook(basePath,courseId, main_play.currentTime,type,1,fileId,totalTime); // duration获取总时间长度
		// 发送数据向后台
	};
	
	//直接关闭页面，并向后台发送数据  测试过了  没作用
    /* if(window.addEventListener){
    	console.log("打开此页面");
    	//studentLook(courseId, main_video.currentTime); //duration获取总时间长度
    }else{
    	console.log("关闭页面222");
    	studentLook(courseId, main_video.currentTime); //duration获取总时间长度
    } */
}


var recordIdInsert = '';
//记录开始学习
function studentLook(basePath,courseId,time,type,isEnd,fileId,totalTime){
	time = time+"";
	console.log(time);
	var param = {"courseId":courseId,"time":time,"type":type,"recordId":recordIdInsert,"isEnd":isEnd,"fileId":fileId,"totalTime":totalTime};
	$.ajax({
		type:"POST",
		async: false,
		dataType:"json",
	    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
		data:JSON.stringify(param),
		url:basePath+"studentRecordController/startStudent.html",
		success:function(data){
			console.log(data);
			var dataJson = data;
			if(dataJson.code=="200"){
				console.log("记录成功");
				recordIdInsert = dataJson.recordId; 
			}
		}
	});
}