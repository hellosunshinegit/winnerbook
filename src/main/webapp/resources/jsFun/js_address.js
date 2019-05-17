/**
 * 获取省市县三级联动封装  
 * 2016/02/19
 */
var basePathDom = "";
var provinceIdDom = "";

var provinceValueDom = "";

//初始化省
function address(basePath,provinceId,provinceValue){
	basePathDom = basePath;
	provinceIdDom = provinceId;
	provinceValueDom = provinceValue;
	addressFun(0,1,provinceIdDom,'');
}
//省市县层级查询
function addressFun(value,type,idDom,inputValue){
	$.ajax({
		type:"POST",
		async: false,
		dataType:"json",
		url:basePathDom+"ucAddressController/addressQuery.html?addressId="+value,
		success:function(data){
			var addressOption="<option value='0'>---请选择---</option>";
			$.each(data,function(index,item){
				var selectDom = "";
				if(inputValue==item.addressId){
					selectDom="selected=selected";
				}
				addressOption+="<option value="+item.addressId+" "+selectDom+">"+item.addressName+"</option>";
			});
			$("#"+idDom).html(addressOption);
		}
	});
}

//更新时显示数据
function addressValue(basePath,provinceDom,cityDom,areaIdDom,provinceIdValue,cityIdValue,areaIdValue){
	basePathDom = basePath;
	addressFun(0,1,provinceDom,provinceIdValue);
	addressFun(provinceIdValue,2,cityDom,cityIdValue);
	addressFun(cityIdValue,3,areaIdDom,areaIdValue);
}

