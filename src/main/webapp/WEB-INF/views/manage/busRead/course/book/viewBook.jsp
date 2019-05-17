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

</head>
<body>
	<div class="page_title">
		<h5>查看书单信息</h5>
	</div>
	<div class="page_main">
		<div class="form_main">
			<dl>
				<dt>
					书名：
				</dt>
				<dd>
					<input type="text" name="bookName" id="bookName" value="${bookList.bookName }" disabled="disabled" />
				</dd>
			</dl>
			<dl> 
				<dt>
					作者：
				</dt>
				<dd>
					<input type="text" name="bookAuthor" id="bookAuthor" value="${bookList.bookAuthor }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					出版社：
				</dt>
				<dd>
					<input type="text" name="bookPublishers" id="bookPublishers" value="${bookList.bookPublishers }"  disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					出版日期：
				</dt>
				<dd>
            		<input type="text" name="bookPublicationDate" id="bookPublicationDate" value="${bookList.bookPublicationDate}" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					开本：
				</dt>
				<dd>
					<input type="text" name="openBook" id="openBook" value="${bookList.openBook }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					纸 张：
				</dt>
				<dd>
					<input type="text" name="bookPaper" id="bookPaper" value="${bookList.bookPaper }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					包 装：
				</dt>
				<dd>
					<input type="text" name="bookPack" id="bookPack" value="${bookList.bookPack }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					是否套装：
				</dt>
				<dd>
					<input type="text" name="isSuit" id="isSuit" value="${bookList.isSuit }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					国际标准书号：
				</dt>
				<dd>
					<input type="text" name="bookIsbn" id="bookIsbn" value="${bookList.bookIsbn }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					分类：
				</dt>
				<dd>
					<input type="text" name="bookClass" id="bookClass" value="${bookList.bookClass }" disabled="disabled"/>
				</dd>
			</dl>
			<dl>
				<dt>
					图片：
				</dt>
				<dd>
					<c:if test="${!empty(bookList.bookImg) }">
						<a href="${basePath}${bookList.bookImg}" target="_blank"><img src="${basePath}${bookList.bookImg}" width="150" height="150"></a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>内容简介：</dt>
				<dd>
					<textarea rows="8" cols="50" name="bookContentDes" id="bookContentDes" disabled="disabled">${bookList.bookContentDes}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>作者简介：</dt>
				<dd>
					<textarea rows="8" cols="40" name="bookAuthorDes" id="bookAuthorDes" disabled="disabled">${bookList.bookAuthorDes}</textarea>
				</dd>
			</dl>
			 <dl>
	            <dt>使用状态：</dt>
	            <dd>		
	                <exp:select code="STATUS" name="bookStatus" id="bookStatus" value="${bookList.bookStatus}" disabled="true"></exp:select>
	            </dd>
	        </dl>
			<dl>
				<dt>&nbsp;</dt>
				<dd>
					<div>
						<input type="button" onclick="javascript:window.location.href='${basePath}bookListController/bookList.html'" class="btn btn-blue" value="返回" /> 
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>