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
			    top: 336px;
			    left: 402px;
			    font-size: 18px;
			    font-family: 楷体;
			    font-weight: bold;
			    color: #000;
    	}
    	.brandDate{
    		    position: absolute;
			    top: 360px;
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
		    top: 70px;
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
    				var busUrl = "<a href="+'<%=ConstantUtils.H5_URL%>?busId='+data.busId+''+" target='_black'> PC预览 </a>";
    				if(data.isNewGenerate=="1"){//新生成
    					content = "<span style='text-align: center;display: block;margin: 5px;font-size: 14px;'>二维码生成成功</span><div style='text-align: center;margin-top:10px;'>"+busUrl+"<br/><img src="+data.img+" width='200' heigth='200'></div>";
    				}else{
	    				content="<div style='text-align: center;margin-top:10px;'>"+busUrl+"<br/><img src="+data.img+" width='200' heigth='200'></div>";
    				}
    				layer.open({
    				  title:busName!=''?busName:"手机端二维码",
		   			  type: 1,
		   			  area: ['300px', '300px'], //宽高
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
    				var content = "<div style='margin: 5px 50px 0px 50px;'><div style='text-align: center;width:600px;height:440px;background:url(${basePath}resources/images/bus_brand_custom.jpg) no-repeat;background-size:600px;'>"+
    				"<span class='bus_name'>授予："+busName+"</span><span class='bus_num'>编号："+busNumber+"</span><span class='brandDate'>"+brandDateChinese+"</span><span class='bus_qrcode'><img src='"+data.qrcode.img+"'></span>"+
    				"</div><div class='brand_upload'><a href='${basePath}busInfoController/uploadGenerateBrandImg.html?busId="+busId+"'>生成读书会铭牌图片并下载</a></div></div>";
    				layer.open({
    				  title:busName!=''?busName:"读书会铭牌",
		   			  type: 1,
		   			  area: ['700px', '550px'], //宽高
		   			  content: content
		   			});
    			}
    		});
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
				<td>企业描述</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.userName}</td>
						<td>${item.busName}</td>
						<td><c:if test="${!empty(item.busLogo) }"><img src="${basePath}${item.busLogo}" width="30" height="30"></c:if></td>
						<td title="${item.busDes}">
							<c:if test="${fn:length(item.busDes)>15}">
								${fn:substring(item.busDes,0,15)}...
							</c:if>
							<c:if test="${fn:length(item.busDes)<=15}">
								${item.busDes}
							</c:if>
						</td>
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