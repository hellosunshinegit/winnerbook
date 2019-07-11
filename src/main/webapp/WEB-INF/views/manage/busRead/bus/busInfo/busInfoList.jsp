<%@ page import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@page import="com.winnerbook.base.common.util.ConstantUtils"%>
<%@ include file="../../../common.jsp"%>
<!DOCTYPE html>
<html>
  <!--<![endif]-->
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/rui.css">
    <script type="text/javascript" src="${basePath }resources/css/rui.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath }resources/css/common.css">
    <script type="text/javascript" src="${basePath }resources/css/common.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="${basePath }resources/js/layer/layer.js"></script>
    <style type="text/css">
    	.bus_qrcode{
    		position: relative;
		    top: 55px;
		    right: 55px;
		    float: right;
    	}
    	.bus_qrcode img{
    		width:80px;
    	}
    	.bus_num{
    		    position: absolute;
			    top: 356px;
			    left: 402px;
			    font-size: 18px;
			    font-family: 楷体;
			    font-weight: bold;
			    color: #000;
    	}
    	.brandDate{
    		    position: absolute;
			    top: 380px;
			    left: 402px;
			    font-size: 18px;
			    font-family: 楷体;
			    font-weight: bold;
			    color: #000;
    	}
    	
    	.bus_logo{
    		position: absolute;
		    top: 82px;
		    right: 255px;
    	}
    	.bus_logo img{
    		width:58px;
    	}
    	
    	.bus_name{
    		position: absolute;
		    top: 90px;
		    left: 86px;
		    font-size: 21px;
		    color: #000;
		    font-family: 黑体;
		    font-weight: 600;
		}
		
		.brand_upload{
		    text-align: center;
		    font-size: 18px;
		    color: blue;
		    text-decoration: underline;
		    padding-top: 10px;
		}
		
		.radioDiv{
			text-align: center;font-size: 14px;
		}
		
		.radioDiv span{
			margin-left:15px;
		}
		
		.notice{
			color: red;
			font-size: 13px;
		}
    	
    </style>
	<script type="text/javascript">
		//重置
		function resetFun(){
			$("#busName").val("");	
		}
		
		//生成企业二维码
		function getBusQrcode(busId,busName){
			//getBusQrcode
			//首先查询该企业下是否有二维码，如果没有则生成，有则直接返回
			var str = {"busId":busId};
			$.ajax({
    			type:"POST",
    			async: false,
    			dataType:"json",
    		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
    			data:JSON.stringify(str),
    			url:"${basePath}busInfoController/getBusQrcode.html",
    			success:function(data){
    				var content = "";
    				var busUrl = "PC预览链接：<br/><a href="+'<%=ConstantUtils.H5_URL%>?busId='+data.busId+''+" target='_black' title=点击预览><%=ConstantUtils.H5_URL%>?busId="+data.busId+"</a>";
    				if(data.isNewGenerate=="1"){//新生成
    					content = "<span style='text-align: center;display: block;margin: 5px;font-size: 14px;'>二维码生成成功</span><div style='text-align: center;margin-top:10px;'><img src="+data.img+" width='200' heigth='200'><br/>"+busUrl+"</div>";
    				}else{
	    				content="<div style='text-align: center;margin-top:10px;'><img src="+data.img+" width='200' heigth='200'><br/>"+busUrl+"</div>";
    				}
    				layer.open({
    				  title:busName!=''?busName:"手机端二维码",
		   			  type: 1,
		   			  area: ['320px', '320px'], //宽高
		   			  content: content
		   			});
    			}
    		});
		}
		
		//生成企业名牌 (把编码和二维码查询出来返回，用户自己点击生成再生成。)
		function getBusBrand(busId,busName){
			var str = {"busId":busId};
			$.ajax({
    			type:"POST",
    			async: false,
    			dataType:"json",
    		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
    			data:JSON.stringify(str),
    			url:"${basePath}busInfoController/getBusBrandInfo.html",
    			success:function(data){
    				console.log(data);
    				var busName = data.userBusInfo.brandBusName!=null?data.userBusInfo.brandBusName:"";
    				var busNumber = data.userBusInfo.busNumber!=null?data.userBusInfo.busNumber:"";
    				var brandDateChinese = data.userBusInfo.brandDateChinese!=null?data.userBusInfo.brandDateChinese:"";
    				/* var busLogo = data.userBusInfo.busLogo!=null?"<img src='"+data.userBusInfo.busLogo+"'/>":""; */
    				var content = "<div class='radioDiv'><span><input type='radio' name='brandType' value='0' checked onclick='brandSelect(0)'/>企业会员单位</span><span><input type='radio' name='brandType' value='1' onclick='brandSelect(1)'/>学区示范单位</span><span><input type='radio' name='brandType' value='2' onclick='brandSelect(2)'/>省级示范单位</span><span><input type='radio' name='brandType' value='3' onclick='brandSelect(3)'/>全国示范单位</span></div>"+
    				"<div style='margin: 5px 50px 0px 50px;'><div id='brandImg' style='text-align: center;width:600px;height:440px;background:url(${basePath}resources/images/bus_brand_custom.jpg) no-repeat;background-size:600px;'>"+
    				"<span class='bus_name'>授予："+busName+"</span><span class='bus_num'>编号："+busNumber+"</span><span class='brandDate'>"+brandDateChinese+"</span><span class='bus_qrcode'><img src='"+data.qrcode.img+"'></span>"+
    				"</div><div class='brand_upload'><a href='javascript:brandDown(\""+busId+"\")'>生成读书会铭牌图片并下载</a></div></div>";
    				layer.open({
    				  title:(busName!=''?busName:"读书会铭牌")+"<span class='notice'>(管理员点击生成铭牌后，企业管理员才可以下载)</span>",
		   			  type: 1,
		   			  area: ['700px', '580px'], //宽高
		   			  content: content
		   			});
    			}
    		});
		}
		
		function brandSelect(brandType){
			if(brandType==0){
				$("#brandImg").css("background","url(${basePath}resources/images/bus_brand_custom.jpg) no-repeat");
				$("#brandImg").css("background-size","600px");
			}else if(brandType==1){
				$("#brandImg").css("background","url(${basePath}resources/images/brand_img_region.jpg) no-repeat");
				$("#brandImg").css("background-size","600px");
			}else if(brandType==2){
				$("#brandImg").css("background","url(${basePath}resources/images/brand_img_province.jpg) no-repeat");
				$("#brandImg").css("background-size","600px");
			}else if(brandType==3){
				$("#brandImg").css("background","url(${basePath}resources/images/brand_img_country.jpg) no-repeat");
				$("#brandImg").css("background-size","600px");
			}
		}
		
		//点击下载
		function brandDown(busId){
			//判断下选择的是哪个
			 var brandType = $(':radio[name="brandType"]:checked').val();
			window.location.href="${basePath}busInfoController/uploadGenerateBrandImg.html?busId="+busId+"&brandType="+brandType;
		}
		
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>企业客户列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}busInfoController/busInfoList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt>企业名称：</dt>
            <dd>
             <input type="text" name="busName" id="busName" value="${busName }">
            </dd>
            <dd>
              <input type="submit" class="btn btn-blue" id="search" value="搜索"></input>
              <input type="button" class="btn btn-blue" value="重置" onclick="resetFun()"></input>    
            </dd>
          </dl>
        </div>
        
         <div class="data_list">
          <table>
            <thead>
              <tr>
              	<td>序号</td>
                <td>登录名</td>
                <td>企业名</td>
				<td>企业logo</td>
				<td>限制发微博次数</td>
				<td>企业员工使用数</td>
				<td>是否生成app</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.userName}</td>
						<td>${item.busName}</td>
						<td><c:if test="${!empty(item.busLogo) }"><img src="${basePath}${item.busLogo}" height="30"></c:if></td>
						<td>${item.sendWbCount }</td>
						<td>${item.empUseNum }</td>
						<td><c:if test="${item.isGenerateApp eq 1}">是</c:if><c:if test="${item.isGenerateApp ne 1}">否</c:if></td>
						<td>
							<a href="${basePath }busInfoController/updateBusInfo.html?userId=${item.userId}">完善企业信息</a>
							<a href="${basePath }busInfoController/viewBusInfo.html?userId=${item.userId}">详情</a>
							<a href="${basePath }busInfoController/customCourseType.html?userId=${item.userId}">定制课程包</a>
							<a href="javascript:getBusQrcode(${item.userId},'${item.busName}')">手机端预览</a>
							<a href="javascript:getBusBrand(${item.userId},'${item.busName}')">读书会铭牌</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
          </table>
        </div>
        <div class="pagination clearfix">
          <div class="pagination-info">
          	<c:import url="../../../page.jsp">
				<c:param name="pageSize" value="${pageDTO.pageSize }" />
				<c:param name="pageIndex" value="${pageDTO.pageIndex }" />
				<c:param name="rowSize" value="${pageDTO.rowSize }" />
				<c:param name="currentFormId" value="searchForm" />
			</c:import>
          </div>
        </div>
        </form>
      </div>
  </body>
</html>