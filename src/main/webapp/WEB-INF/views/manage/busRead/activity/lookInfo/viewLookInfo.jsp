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
<style type="text/css">
.page_title h6{
	color: #1890ff;
    font-weight: bold;
    line-height: 30px;
    margin: 0px;
    padding: 0px;
    padding-left: 35px;
    background-color: #f0f2f5;
}
</style>
<script type="text/javascript">
	
	function returnFun(){
		window.location.href="${basePath}busLookInfoController/busLookInfoList.html";
	}

</script>
</head>
<body>
	<div class="page_title">
		<h5>查看调查表信息</h5>
	</div>
	<div class="page_main">
			<div class="form_main">
				<dl>
					<dt>企业名称：</dt>
					<dd>
						<input type="text" name="busName" id="busName" value="${busLookInfo.busName }" disabled="disabled" />
					</dd>
				</dl>
				<div class="page_title">
					<h6>企业书单</h6>
				</div>
				<dl>
					<dt>企业书单：</dt>
					<dd>
						<textarea name="bookListName" id="bookListName" disabled="disabled">${busLookInfo.bookListName }</textarea>
					</dd>
				</dl>
				<dl>
					<dt>课程包：</dt>
					<dd>
						<input type="text" name="courseList" id="courseList" value="${busLookInfo.courseList }" disabled="disabled"  />
					</dd>
				</dl>
				<dl>
					<dt>读书计划：</dt>
					<dd>
						<input type="text" name="readBookPlan" id="readBookPlan" value="${busLookInfo.readBookPlan }" disabled="disabled"  />
					</dd>
				</dl>
				<div class="page_title">
					<h6>企业读书计划</h6>
				</div>
				<dl>
					<dt>一年必读：</dt>
					<dd>
						<input type="text" name="readBookPlan" id="readBookPlan" value="${busLookInfo.mustReadNum }" disabled="disabled" style="width: 50px;"/>&nbsp;&nbsp;本书目
					</dd>
				</dl>
				<dl>
					<dt>一年选读：</dt>
					<dd>
						<input type="text" name="readBookPlan" id="readBookPlan" value="${busLookInfo.selectReadNum }" disabled="disabled" style="width: 50px;"/>&nbsp;&nbsp;本书目
					</dd>
				</dl>
				<dl>
					<dt>是否写读后感：</dt>
					<dd>
						<c:if test="${busLookInfo.isReadThought eq 1}">是</c:if>
						<c:if test="${busLookInfo.isReadThought eq 2}">否</c:if>
					</dd>
				</dl>
				<dl>
					<dt>是否召开读书会：</dt>
					<dd>
						<c:if test="${busLookInfo.isReadClud eq 1}">是&nbsp;&nbsp;${busLookInfo.longReadClud }</c:if>
						<c:if test="${busLookInfo.isReadClud eq 2}">否</c:if>
						
					</dd>
				</dl>
				<dl>
					<dt>是否分享微博：</dt>
					<dd>
						<c:if test="${busLookInfo.isShareWb eq 1}">是</c:if>
						<c:if test="${busLookInfo.isShareWb eq 2}">否</c:if>
					</dd>
				</dl>
				<div class="page_title">
					<h6>企业家信息</h6>
				</div>
				<dl>
					<dt>是否读书：</dt>
					<dd>
						<c:if test="${busLookInfo.isReadBook eq 1}">是</c:if>
						<c:if test="${busLookInfo.isReadBook eq 2}">否</c:if>
						
					</dd>
				</dl>
				<dl>
					<dt>是否写书：</dt>
					<dd>
						<c:if test="${busLookInfo.isWriteBook eq 1}">是</c:if>
						<c:if test="${busLookInfo.isWriteBook eq 2}">否</c:if>
						
					</dd>
				</dl>
				<dl>
					<dt>是否分享书：</dt>
					<dd>
						<c:if test="${busLookInfo.isShareBook eq 1}">是</c:if>
						<c:if test="${busLookInfo.isShareBook eq 2}">否</c:if>
					</dd>
				</dl>
				<div class="page_title">
					<h6>企业基本信息</h6>
				</div>
				<dl>
					<dt>公众号信息：</dt>
					<dd>
						<input type="text" name="publicNum" id="publicNum" value="${busLookInfo.publicNum }" disabled="disabled"  />
					</dd>
				</dl>
				<dl>
					<dt>企业主页：</dt>
					<dd>
						<input type="text" name="homepageUrl" id="homepageUrl" value="${busLookInfo.homepageUrl }" disabled="disabled"  />
					</dd>
				</dl>
				<dl>
					<dt>人员数量：</dt>
					<dd>
						<input type="text" name="empNum" id="empNum" value="${busLookInfo.empNum }" disabled="disabled"  />
					</dd>
				</dl>
				<dl>
					<dt>地址：</dt>
					<dd>
						<input type="text" name="companyAddress" id="companyAddress" value="${busLookInfo.companyAddress }" disabled="disabled"  />
					</dd>
				</dl>
				<dl>
					<dt>所属行业：</dt>
					<dd>
						<input type="text" name="companyIndustry" id="companyIndustry" value="${busLookInfo.companyIndustry }" disabled="disabled"  />
					</dd>
				</dl>
				<div class="page_title">
					<h6>企业联系方式</h6>
				</div>
				<dl>
		            <dt>联系电话：</dt>
		            <dd>
		            	<input type="text" name="telphone" id="telphone" value="${busLookInfo.telphone}" disabled="disabled"/>
		            </dd>
		        </dl>
				<dl>
		            <dt>试用企业读书云：</dt>
		            <dd>
		            	<c:if test="${busLookInfo.isUseBookYun eq '1'}">是</c:if>
		            	<c:if test="${busLookInfo.isUseBookYun ne '1'}">否</c:if>
		            </dd>
		        </dl>
				 <dl>
		            <dt>使用状态：</dt>
		            <dd>		
		                <exp:select code="USER_APPLY_STATUS" name="status" id="status" value="${busLookInfo.status}"  require="true" requireMsg="使用状态为必填项!" dataType="Require" disabled="true" style="width: 150px;"></exp:select>
		            </dd>
		        </dl>
				<dl>
		            <dt>申请时间：</dt>
		            <dd>		
		                <fmt:formatDate value="${busLookInfo.createDate}" type="both"/>
		            </dd>
		        </dl>
				<dl>
		            <dt>通过/拒绝时间：</dt>
		            <dd>		
		                <fmt:formatDate value="${busLookInfo.updateDate}" type="both"/>
		            </dd>
		        </dl>
		        <dl>
					<dt>状态原因：</dt>
					<dd>
						<textarea name="statusReason" id="statusReason" disabled="disabled">${busLookInfo.statusReason }</textarea>
					</dd>
				</dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<div>
							<input type="button" onclick="returnFun()" class="btn btn-blue" value="返回" /> 
						</div>
					</dd>
				</dl>
			</div>
	</div>
</body>
</html>