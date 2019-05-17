<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common.jsp"%>
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
<!-- ue编辑器 -->
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 日期 -->
<script type="text/javascript" src="${basePath }resources/My97DatePicker/WdatePicker.js"></script>
<!-- 百度地图 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=mlBpFWqZyLv9pGBsG0aVz3f1MLwpM7Zo"></script>
<style type="text/css">
	#l-map{height:250px;width:800px;}
</style>

<script type="text/javascript">
	var map = "";
	var myValue;
	$(document).ready(function(){
		UE.getEditor('content',{
			wordCount:true, //是否开启字数统计
		    maximumWords:15000, //允许的最大字符数
		});
		
		//如果是修改，则地图要显示到已经保存的地图
		var activityId = '${activity.id}';
		var lngLat = '${activity.lngLat}';
		if(activityId!='' && lngLat!=''){
			console.log(lngLat);
			map = new BMap.Map("l-map");
			var point = new BMap.Point(lngLat.split(",")[0],lngLat.split(",")[1]);
			console.log(point);
			map.centerAndZoom(point,18);
			map.addOverlay(new BMap.Marker(point));    //添加标注
		}else{
			map = new BMap.Map("l-map");// 初始化地图,设置城市和地图级别。
			map.centerAndZoom("北京",12);
		}
		
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "suggestId"
				,"location" : map
		});

		ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
			
			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
			G("searchResultPanel").innerHTML = str;
		});

		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
			console.log(_value);
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
			//设置地址
			$("#city").val(_value.province +  _value.city);
			$("#address").val(myValue);
			setPlace();
		});

	});

	
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}
	
	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
			
			//获取经纬度  //lng: 116.421518, lat: 40.043387
			console.log(pp);
			$("#lngLat").val(pp.lng+","+pp.lat);
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	function submitForm() {
		var editForm = document.getElementById("editForm");
		if (Validator.Validate(editForm)) {
			//编辑器
			var str = UE.getEditor('content').getContentTxt();
        	var leng = str.length;
			if(leng>15000){
				 alert("错误，最多输入两百字");
				 return false;
			}else{
				document.editForm.submit();
			}
		}
	}
</script>
</head>
<body>
	<div class="page_title">
		<h5>
			<c:if test="${empty(activity.id)}">添加活动信息</c:if>
			<c:if test="${!empty(activity.id)}">修改活动信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(activity.id)}">${basePath }activityController/addSubmitActivity.html</c:if><c:if test="${!empty(activity.id)}">${basePath }activityController/updateActivitySubmit.html</c:if>" method="post">
			<input type="hidden" name="id" id="id" value="${activity.id}"/>
			<div class="form_main">
				<dl>
					<dt><i>*</i>主题：</dt>
					<dd>
						<input type="text" name="title" id="title" value="${activity.title }" maxlength="50" require="true" requireMsg="主题为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>
						活动图片：
					</dt>
					<dd>
						<input type="hidden" name="imgUrl" id="imgUrl" value="${activity.imgUrl }"/>
						<img alt="" id="imgUrl" src="" width="150" height="150">
						<c:if test="${!empty(activity.imgUrl) }">
							<a href="${basePath}${activity.imgUrl}" target="_blank"><img src="${basePath}${activity.imgUrl}" width="50" height="50"></a>
						</c:if>
						<div id="upload_imgUrl"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=imgUrl&path=activity&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
		            <dt><i>*</i>活动开始时间：</dt>
		            <dd>
		            	<input type="text" name="startDate" id="startDate" value="${activity.startDate}" class="Wdate" onfocus="var endDate=$dp.$('endDate');WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" style="width: 150px;" require="true" requireMsg="报名开始时间为必填项!" dataType="Require" />
			            <exp:select code="TIME_PART" name="startDateTime" id="startDateTime" value="${activity.startDateTime }"></exp:select>
		            </dd>
		        </dl>
				<dl>
		            <dt><i>*</i>活动结束时间：</dt>
		            <dd>
		            	<input type="text" name="endDate" id="endDate" value="${activity.endDate}" class="Wdate" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" style="width: 150px;" require="true" requireMsg="报名结束时间为必填项!" dataType="Require" />
		            	<exp:select code="TIME_PART" name="endDateTime" id="endDateTime" value="${activity.endDateTime }"></exp:select>
		            </dd>
		        </dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="status" id="status" value="${activity.status}"  require="true" requireMsg="使用状态为必填项!" dataType="Require" style="width: 150px;"></exp:select>
		            </dd>
		        </dl>
		        <dl>
		            <dt>内容细节：</dt>
		            <dd>
		            	<textarea name="content" id="content" style="width:800px;height:250px;">${activity.content}</textarea>
		            </dd>
		        </dl>
		        <dl style="height:auto !important;">
		            <dt><i>*</i>活动地址：</dt>
		            <dd style="height:auto !important;">
		            	<div id="l-map"></div>
		            	搜索：<input type="text" name="suggestId" id="suggestId"/> 详细地址：<input type="text" name="detailAddress" id="detailAddress" value="${activity.detailAddress}" maxlength="100"/><br/>
		            	<c:if test="${!empty(activity.id)}">
		            		已保存地址：${activity.address}
		            	</c:if>
		            	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;z-index: 10000;"></div>
		            	
		            	<%-- <input type="hidden" name="province" value="${activity.province}"/> --%>
		            	<input type="hidden" name="city" id="city" value="${activity.city}"/>
		            	<input type="hidden" name="address" id="address" value="${activity.address}" require="true" requireMsg="活动地址为必填项!" dataType="Require" />
		            	<input type="hidden" name="lngLat" id="lngLat" value="${activity.lngLat}"/>
		            </dd>
		        </dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<div>
							<input type="button" onclick="submitForm()" class="btn btn-blue"
								value="保存" /> <input type="reset" class="btn" value="重置">
						</div>
					</dd>
				</dl>
			</div>
		</form>
	</div>
</body>
</html>