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

	function submitForm() {
		var editForm = document.getElementById("editForm");
		if (Validator.Validate(editForm)) {
			//在同一个用户创建的数据中，名字不可以重复。但是在不同企业中，如果书籍已经存在，则加入中间表，用户id对应的书籍id
			if(validateBookName()){
				document.editForm.submit();
			}else{
				alert("您已添加相同名称的书籍，请检查");
			}
		}
	}
	
	//判断书名是否重复 在同一个用户创建中不可以重复
	function validateBookName(){
		var result = true;
		var bookName = $("#bookName").val();
		var str = {"bookName":bookName,"bookId":'${bookList.bookId}'};
		$.ajax({
			type:"POST",
			async: false,
			dataType:"json",
		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
			data:JSON.stringify(str),
			url:"${basePath}bookListController/validateBookName.html",
			success:function(data){
				console.log(data);
				if(data.length>0){
					result = false;
				}
			}
		});
		return result;
	}
	
	
	//根据书名进行搜索
	function searchBookName(){
		var bookName = $("#bookName").val();
		if(bookName==""){
			alert("书名不可以为空");
			return false;
		}
		var loading = layer.load(2, {shade: [0.2,'#333']});
		clearInputValue();
		
		var dataJson = {"bookName":bookName};
		$.ajax({
			type:"POST",
			async: true,
			dataType:"json",
		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
			data:JSON.stringify(dataJson),
			url:"${basePath}bookListController/searchBookList.html",
			success:function(data){
				console.log(data);
				layer.close(loading);
				if(data!=undefined && data.length>0){
					var bookLi = "";
					$.each(data,function(index,item){
						if(index<10){
							var bookName = item.bookName;
							if(bookName.length>40){
								bookName = bookName.substring(0,40)+"...";
							}
							bookLi+="<li><a id='li_"+item.liId+"' title="+item.bookName+" href='javascript:clickHref(\""+item.liId+"\",\""+item.href+"\",\""+item.bookName+"\",\""+item.bookContentDes+"\")'>"+bookName+"</a> "+item.author+""+item.press+"</li>";
						}
					});
					$("#bookLi").html(bookLi);
					$("#searchBookList").css("display","");
				}else{
					alert("没有搜索到该信息");
				}
				
			}
		});
	}
	
	//点击返回数据
	function clickHref(liId,url,bookTitle,bookContentDes){
		var loading_href = layer.load(2, {shade: [0.2,'#333']});
		clearInputValue();
		$.each($("a[id^=li_]"),function(){
			$(this).css("color","blue");
		});
		$("#li_"+liId).css("color","red");
		
		$("#bookSearchName").val(bookTitle);
		$("#bookContentDes").val(bookContentDes);
		var bookNameUrl = {"bookNameUrl":url,"bookName":$("#bookName").val(),"bookTitle":bookTitle};
		$.ajax({
			type:"POST",
			async: true,
			dataType:"json",
		    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
			data:JSON.stringify(bookNameUrl),
			url:"${basePath}bookListController/searchBookName.html",
			success:function(data){
				layer.close(loading_href);
				if(data.code=="200" && data.bookAuthor!=""){
					$("#bookAuthor").val(data.bookAuthor);
					$("#bookPublishers").val(data.bookPublishers);
					$("#bookPublicationDate").val(data.bookPublicationDate);
					$("#openBook").val(data.openBook);
					$("#bookPaper").val(data.bookPaper);
					$("#bookPack").val(data.bookPack);
					$("#isSuit").val(data.isSuit);
					$("#bookIsbn").val(data.bookIsbn); 
					$("#bookClass").val(data.bookClass);
					$("#bookUrl").val(data.bookUrl);
				}else{
					alert("获取信息不完整，请完善其他数据");
				}
				$("#bookImg").val(data.bookImg);
				$("#bookImgUrl").attr("src",data.bookImg);
			}
		});
	}
	
	function clearInputValue(){
		$("#bookSearchName").val("");
		$("#bookContentDes").val("");
		$("#bookAuthor").val("");
		$("#bookPublishers").val("");
		$("#bookPublicationDate").val("");
		$("#openBook").val("");
		$("#bookPaper").val("");
		$("#bookPack").val("");
		$("#isSuit").val("");
		$("#bookIsbn").val(""); 
		$("#bookClass").val("");
		$("#bookUrl").val("");
		$("#bookImg").val("");
		$("#bookImgUrl").attr("src","");
	}
	
</script>
</head>
<body>
	<div class="page_title">
		<h5>
			<c:if test="${empty(bookList.bookId)}">添加书单信息</c:if>
			<c:if test="${!empty(bookList.bookId)}">修改书单信息</c:if>
		</h5>
	</div>
	<div class="page_main">
		<form name="editForm" id="editForm"
			action="<c:if test="${empty(bookList.bookId)}">${basePath }bookListController/addSubmitBook.html</c:if><c:if test="${!empty(bookList.bookId)}">${basePath }bookListController/updateSubmitBook.html</c:if>" method="post">
			<input type="hidden" name="bluId" id="bluId" value="${bookList.bluId}"/>
			<input type="hidden" name="bookId" id="bookId" value="${bookList.bookId}"/>
			<input type="hidden" name="bookUrl" id="bookUrl" value="${bookList.bookUrl}"/>
			<div class="form_main">
				<dl>
					<dt>
						<i>*</i>书名：
					</dt>
					<dd>
						<input type="text" name="bookName" id="bookName" value="${bookList.bookName }" maxlength="500" require="true" requireMsg="书名为必填项!" dataType="Require" />
						<a href="javascript:searchBookName()" style="color: blue;">获取数据</a>
					</dd>
				</dl>
				<dl id="searchBookList" style="display: none;">
					<dt>
						搜索结果：
					</dt>
					<dd>
						<div>
							<ul id="bookLi" class="book_search" >
							</ul>
						</div>
					</dd>
				</dl>
				<dl> 
					<dt>
						<i>*</i>搜索书名：
					</dt>
					<dd>
						<input type="text" name="bookSearchName" id="bookSearchName" value="${bookList.bookSearchName }" maxlength="200" require="true" requireMsg="搜索书名为必填项!" dataType="Require"/>
					</dd>
				</dl>
				<dl> 
					<dt>
						<i>*</i>作者：
					</dt>
					<dd>
						<input type="text" name="bookAuthor" id="bookAuthor" value="${bookList.bookAuthor }" maxlength="20" require="true" requireMsg="作者为必填项!" dataType="Require" />
					</dd>
				</dl>
				<dl>
					<dt>
						出版社：
					</dt>
					<dd>
						<input type="text" name="bookPublishers" id="bookPublishers" value="${bookList.bookPublishers }"  maxlength="100"/>
					</dd>
				</dl>
				<dl>
					<dt>
						出版日期：
					</dt>
					<dd>
	            		<input type="text" name="bookPublicationDate" id="bookPublicationDate" value="${bookList.bookPublicationDate}" maxlength="20"/>
					</dd>
				</dl>
				<dl>
					<dt>
						开本：
					</dt>
					<dd>
						<input type="text" name="openBook" id="openBook" value="${bookList.openBook }" maxlength="20"/>
					</dd>
				</dl>
				<dl>
					<dt>
						纸 张：
					</dt>
					<dd>
						<input type="text" name="bookPaper" id="bookPaper" value="${bookList.bookPaper }" maxlength="20"/>
					</dd>
				</dl>
				<dl>
					<dt>
						包 装：
					</dt>
					<dd>
						<input type="text" name="bookPack" id="bookPack" value="${bookList.bookPack }" maxlength="20"/>
					</dd>
				</dl>
				<dl>
					<dt>
						是否套装：
					</dt>
					<dd>
						<input type="text" name="isSuit" id="isSuit" value="${bookList.isSuit }" maxlength="20"/>
					</dd>
				</dl>
				<dl>
					<dt>
						国际标准书号：
					</dt>
					<dd>
						<input type="text" name="bookIsbn" id="bookIsbn" value="${bookList.bookIsbn }" maxlength="20"/>
					</dd>
				</dl>
				<dl>
					<dt>
						分类：
					</dt>
					<dd>
						<input type="text" name="bookClass" id="bookClass" value="${bookList.bookClass }" maxlength="100"/>
					</dd>
				</dl>
				<dl>
					<dt>
						图片：
					</dt>
					<dd>
						<input type="hidden" name="bookImg" id="bookImg" value="${bookList.bookImg }" />
						<img alt="" id="bookImgUrl" src="" width="150" height="150">
						<c:if test="${!empty(bookList.bookImg) }">
							已上传图片：
							<a href="${basePath}${bookList.bookImg}" target="_blank"><img src="${basePath}${bookList.bookImg}" width="150" height="150"></a>
						</c:if>
						<div id="upload_bookImg"></div>
		            	<iframe src="${basePath}fileUploadController/uploadFileIframe.html?filePath=bookImg&path=bookList&typeExts=1" id="file" width="800px;" height="110px;" frameborder="0" scrolling="no"></iframe>
					</dd>
				</dl>
				<dl>
					<dt>内容简介：</dt>
					<dd>
						<textarea rows="8" cols="50" name="bookContentDes" id="bookContentDes" maxlength="300">${bookList.bookContentDes}</textarea>
					</dd>
				</dl>
				<dl>
					<dt>作者简介：</dt>
					<dd>
						<textarea rows="8" cols="40" name="bookAuthorDes" id="bookAuthorDes" maxlength="300">${bookList.bookAuthorDes}</textarea>
					</dd>
				</dl>
				 <dl>
		            <dt><i>*</i>使用状态：</dt>
		            <dd>		
		                <exp:select code="STATUS" name="bookStatus" id="bookStatus" value="${bookList.bookStatus}"  require="true" requireMsg="使用状态为必填项!" dataType="Require"></exp:select>
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