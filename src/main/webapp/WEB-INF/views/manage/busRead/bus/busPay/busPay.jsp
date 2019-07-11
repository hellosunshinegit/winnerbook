<%@ page contentType="text/html; charset=UTF-8" %>
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
<!-- 下拉搜索 -->
<link rel="stylesheet" href="${basePath }resources/select2/select2.min.css" />
<script src="${basePath }resources/select2/select2.min.js"></script>
<style type="text/css">
	input[type="checkbox"]{
		height: 17px;
    	width: 17px;
	}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		if('${sessionUser.userId}'=='1'){
			//下拉选择搜索框
			 $('#busId').select2();
		}
		
		if('${sessionUser.isBusinessAdmin}'=='1'){//如果是企业管理员
			$("#busId").val(${sessionUser.belongBusUserId});
			selectBusIdFun();
		}
	});
	
	//点击选择企业id
	function selectBusIdFun(){
		var	busId = $("#busId").val();
		if(busId==0){
			$("#selectDiv").css("display","none");
			return false;
		}
		//根据企业id查询企业信息和企业员工信息
		$.ajax({
			type:"GET",
			async: false,
			dataType:"json",
		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
			url:"${basePath}busPayUserController/getBusEmp.html?busId="+busId,
			success:function(data){
				$("#selectDiv").css("display","");
				$("#busName").html(data.userBusInfo.busName);
				$("#userUnitName").html(data.userBusInfo.userUnitName);
				$("#empUseNum").html(data.userBusInfo.empUseNum!=null?data.userBusInfo.empUseNum:0);
				$("#selectEmpUseNum").html(data.busPayUsers.length);
				$("#overEmpUseNum").html(parseInt(data.userBusInfo.empUseNum!=null?data.userBusInfo.empUseNum:0)-parseInt(data.busPayUsers.length));
				
				//已经选择的用户放到map中过滤
				var payMap = [];
				$.each(data.busPayUsers,function(index,item){
					payMap[item.userId] = item.userId;
				});
				
				
				var empStr = "";
				$.each(data.empList,function(index,item){
					if(index%4==0){
						empStr+="<br/>";
					}
					if(payMap[item.userId]!=undefined){//证明是已经选择的付费用户
						empStr+="<input type='checkbox' name='userId' value='"+item.userId+"' checked disabled='disabled'/><span style='color:#808080;'>"+item.userUnitName+"（"+item.userName+"）</span>&nbsp;&nbsp;&nbsp;&nbsp;";
					}else{
						empStr+="<input type='checkbox' name='userId' value='"+item.userId+"' onclick='selectFun()'/>"+item.userUnitName+"（"+item.userName+"）&nbsp;&nbsp;&nbsp;&nbsp;";
					}
				});
				if(empStr==""){
					empStr = "暂无数据...";
				}
				$("#empDD").html(empStr);
			}
		});
	}
	
	//每次选择用户后
	function selectFun(){
		var userrIdChecked = $("input[name=userId]:checked").length;
		$("#selectEmpUseNum").html(userrIdChecked);
		$("#overEmpUseNum").html(parseInt($("#empUseNum").html())-parseInt(userrIdChecked));
	}
	
	//点击全选
	function selectAll(value){
		$.each($("input[name=userId]"),function(index,item){
			if(item.disabled==false){
				if(value.checked){
					item.checked = true;
				}else{
					item.checked = false;
				}
			}
		});
		selectFun();
	}
	
	
	function submitForm(){
		
		//判断选择的人数和剩余的名额
		var selectEmpUseNum = $("#selectEmpUseNum").html();
		var empUseNum = $("#empUseNum").html();
		if(parseInt(selectEmpUseNum)>parseInt(empUseNum)){
			alert("选择人数已经超过上限，请重新选择");
			return false;
		}
		
		var	busId = $("#busId").val();
		
		var userrIdChecked = $("input[name=userId]:checked");
		var userIds = new Array();
		$.each(userrIdChecked,function(index,item){
			if(item.disabled==false){
				userIds.push(item.value);
			}
		});
		if(userIds.length==0){
			alert("所选员工为空");
			return false;
		}
		
		var jsonStr = {"busId":busId,"userIds":userIds.join(",")};
		$.ajax({
			type:"POST",
			async: false,
			dataType:"json",
		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
		    data:JSON.stringify(jsonStr),
			url:"${basePath}busPayUserController/addPayBusEmp.html",
			success:function(data){
				console.log(data);
				if(data.success){
					alert("保存成功");
					selectBusIdFun();
				}else{
					alert(data.msg);
				}
			}
		});
	}
</script>
</head>
<body>
<div class="page_title"><h5>付费用户选择
</h5></div>
<div class="page_main">
    <div class="form_main">
    	<c:if test="${sessionUser.userId eq 1 }"><!-- 是管理员 -->
	    	<dl>
	            <dt>选择企业：</dt>
	            <dd>
	            	<select name="busId" id="busId" class="busIdsSelect" onchange="selectBusIdFun()" style="width: 210px;">
						<option value="0">---请选择---</option>       	
	            		<c:forEach items="${busList}" var="item">
	            			<option value="${item.busId }" ><c:if test="${item.busName eq '' || item.busName eq null}">${item.userUnitName}</c:if><c:if test="${item.busName ne ''}">${item.busName}</c:if></option>
	            		</c:forEach>
	            	</select>
	            </dd>
	        </dl>
    	</c:if>
        <c:if test="${sessionUser.isBusinessAdmin eq 1 }"><!-- 是企业管理员 -->
        	<input type="hidden" id="busId"/>
        </c:if>
        <div id="selectDiv" style="display: none;">
        	<dl>
	            <dt>企业信息：</dt>
	            <dd>
	            	企业名称：<span id="busName" style="font-size: 15px;color: red;"></span> &nbsp;&nbsp;&nbsp;&nbsp;
	            	企业管理员名称：<span id="userUnitName"></span>&nbsp;&nbsp;&nbsp;&nbsp;
	            	企业付费名额：<span id="empUseNum" style="font-size: 15px;color: red;">0</span>人&nbsp;&nbsp;&nbsp;&nbsp;
	            	已选择用户名额：<span id="selectEmpUseNum" style="font-size: 15px;color: red;">0</span>人&nbsp;&nbsp;&nbsp;&nbsp;
	            	剩余用户名额：<span id="overEmpUseNum" style="font-size: 15px;color: red;">0</span>人&nbsp;&nbsp;&nbsp;&nbsp;
	            </dd>
	        </dl>
	        <dl>
	            <dt> </dt>
	            <dd>
	            	<span style="color: red;">注：会员一旦选择，不可修改</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>选择员工： </dt>
	            <dd>
	            	<div><input type="checkbox"  onclick="selectAll(this)"/> 全选 </div>
	            	<div id="empDD"></div>
	            </dd>
	        </dl>
	        
        </div>
        
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <div>
                    <input type="button" onclick="submitForm()" class="btn btn-blue" value="保存"/>
                </div>
            </dd>
        </dl>
    </div>
</div>
</body>
</html>