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

<script type="text/javascript" src="${basePath }resources/js/layer/layer.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		var id = '${bookListType.id}';
		if(id!=undefined && id!=""){
			//选中多选框
			var bookLabelIds = '${bookListType.labelIds}';
			console.log(bookLabelIds);
			if(bookLabelIds.length>0){
				var bookLabelIdArray= bookLabelIds.split(",");
				$.each(bookLabelIdArray,function(index,item){
					$("#tag"+item).attr("checked", true);
				});
			}
			
			var bookLists = JSON.parse('${bookLists}');
			var typeBookNames = "";
			$.each(bookLists,function(index,item){
				if(index!=0){
					typeBookNames+=",";
				}
				typeBookNames+=item.bookName;
			});
			$("#typeBookNames").val(typeBookNames);
		}
	});



	function submitForm() {
		var editForm = document.getElementById("editForm");
		if (Validator.Validate(editForm)) {
			
			var id = $("#id").val();
			var typeName = $("#typeName").val().trim();
			var wbTitle = $("#wbTitle").val().trim();
			var wbImg = $("#wbImg").val().trim();
			var typeDes = $("#typeDes").val();
			var status = $("#status").val();
			var typeImg = $("#typeImg").val();
			var typeBookNames = $("#typeBookNames").val();
			typeBookNames = typeBookNames.replace("，",",");
			var typeBookNamesArray = typeBookNames.split(",");
			
			var dataJson = {"id":id,"typeName":typeName,"wbTitle":wbTitle,"wbImg":wbImg,"typeDes":typeDes,"status":status,"typeImg":typeImg,"bookNameList":[]};
			
			dataJson.bookNameList = typeBookNamesArray;
			
			//获取所选择的文章标签
			var bookTags = "";
			var bookTagIds = "";
			$.each($("input[id^='tag']:checked"),function(index,item){
				if(index!=0){
					bookTags+=",";
					bookTagIds+=",";
				}
				bookTagIds+=item.value.split("-")[0];
				bookTags+=item.value.split("-")[1];
			});
			dataJson["bookTags"] = bookTags;
			dataJson["bookTagIds"] = bookTagIds;
			
			console.log("dataJson",dataJson);
			
			
			var url = "${basePath}bookListTypeController/addBookListTypeSubmit.html";
			if(id!="" && id!=undefined){
				url = "${basePath}bookListTypeController/updateBookListTypeSubmit.html";
			}
			
			var loading_add = layer.load(2, {shade: [0.2,'#333']});
			$.ajax({
				type:"POST",
				async: true,
				dataType:"json",
			    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
				data:JSON.stringify(dataJson),
				url:url,
				success:function(result){
					layer.close(loading_add);
					console.log(result);
					if(result.success){
						if(result.data!=null){
							var bookNameStr = "书单信息已保存成功<br/>";
							if(result.data.infoDeficiencyList.length>0){
								bookNameStr+="以下书籍信息不完整<br/><br/><span style='color:red;'>";
								$.each(result.data.infoDeficiencyList,function(index,item){
									bookNameStr+=(index+1)+"："+item.bookName+"<br/>";
								});
								bookNameStr+="</span><br/>请去书籍列表中完善信息<br/><span style='color:red;'>请保存以上信息，便于完善书籍！！！</span>";
								
							}
							if(result.data.noDataList.length>0){
								bookNameStr = "以下书籍信息未搜索到结果：<br/><br/><span style='color:red;'>";
								$.each(result.data.noDataList,function(index,item){
									bookNameStr+=(index+1)+"："+item.bookName+"<br/>";
								});
								bookNameStr+="</span><br/>请去书籍列表中添加书籍信息后，再来添加书单。<br/><span style='color:red;'>请保存以上信息，便于完善书籍！！！</span>";
							}
							layer.confirm(bookNameStr, {
							  	btn: ['知道了'] //按钮
							}, function(){
							  	window.location.href="${basePath }bookListTypeController/bookListType.html";
							}, function(){
								window.location.href="${basePath }bookListTypeController/bookListType.html";
							});
						}else{
							alert("保存成功");
							window.location.href="${basePath }bookListTypeController/bookListType.html";
						}
					}
				}
			});
		}
	}
	
</script>
</head>
<body>
	<div class="page_title">
		<h5>
			<c:if test="${empty(bookListType.id)}">添加书单信息</c:if>
			<c:if test="${!empty(bookListType.id)}">修改书单信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm" method="post">
			<input type="hidden" name="id" id="id" value="${bookListType.id}"/>
			<div class="form_main">
				<dl>
					<dt>
						<i>*</i>书单名称：
					</dt>
					<dd>
						<input type="text" name="typeName" id="typeName" value="${bookListType.typeName }" maxlength="20" require="true"
							requireMsg="书单名称为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>微博正文：</dt>
					<dd>
						<input type="text" name="wbTitle" id="wbTitle" value="${bookListType.wbTitle }" maxlength="140" />
						<span style="color: red;">注：如果‘微博正文’内容为空，则发微博时采用‘书单名称’的内容</span>
					</dd>
				</dl>
				<dl>
					<dt>
						微博正文图：
					</dt>
					<dd>
						<input type="hidden" name="wbImg" id="wbImg" value="${bookListType.wbImg }"/>
						<img alt="" id="wbImg" src="" width="150" height="150">
						<c:if test="${!empty(bookListType.wbImg) }">
							<a href="${basePath}${bookListType.wbImg}" target="_blank"><img src="${basePath}${bookListType.wbImg}" width="50" height="50"></a>
						</c:if>
						<div id="upload_wbImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=wbImg&path=booktype/wb&typeExts=5" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
		            <dt>缩略图：</dt>
		            <dd>
		            	<div id="upload_typeImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=typeImg&path=booktype&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
		            	<input type="hidden" name="typeImg" id="typeImg" value="${bookListType.typeImg }" maxlength="100"/>
		            	<c:if test="${!empty(bookListType.typeImg)}">
		            		<a href="${basePath}${bookListType.typeImg}" target="_blank"><img alt="" src="${basePath}${bookListType.typeImg}" width="60" height="60"></a>	
		            	</c:if>
		            	<span style="color: red;">注：缩略图需要大于300px，分享时才会显示</span>
		            </dd>
		        </dl>
				<dl>
		            <dt>书单标签：</dt>
		            <dd>
		            	<c:forEach items="${bookTypeLabel }" var="item">
		            		<input type="checkbox" name="tag" id="tag${item.labelId}" value="${item.labelId }-${item.labelName}" style="width: 18px;height: 18px;"/>${item.labelName }
		            	</c:forEach>
		            </dd>
		        </dl>
		        <dl>
					<dt>
						<i>*</i>添加书籍：
					</dt>
					<dd>
						<input type="text" name="typeBookNames" id="typeBookNames" maxlength="1000" require="true" requireMsg="书籍至少添加一个!" dataType="Require" style="width: 1000px;height: 30px;"/>
						<br/><span style="color: red;">注：每本书以,号分割</span>
					</dd>
				</dl>
				<dl>
					<dt>书单描述：</dt>
					<dd>
						<textarea rows="8" cols="50" name="typeDes" id="typeDes" maxlength="300">${bookListType.typeDes}</textarea>
					</dd>
				</dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="status" id="status" value="${bookListType.status}"  require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
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