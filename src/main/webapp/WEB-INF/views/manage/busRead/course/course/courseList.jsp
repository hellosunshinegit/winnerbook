<%@ page import="java.util.*" contentType="text/html; charset=UTF-8"%>
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
	<script type="text/javascript">
		//重置
		function resetFun(){
			$("#title").val("");
			$("#courseTypeId").val("");
			$("#courseStatus").val("");
		}
		
		//添加
		function addFun(){
			window.location.href="${basePath }courseController/addCourse.html";
		}
		
		function updateCourseStatue(courseId,coursStatus){
			if(coursStatus=="2"){
				coursStatus = "1";
			}else{
				coursStatus = "2";
			}
			window.location.href="${basePath }courseController/updateCourseStatus.html?courseId="+courseId+"&coursStatus="+coursStatus+"&pageIndex=${pageDTO.pageIndex}";
		}
		
		function updateCourseRes(courseId,courseReleaseId,courseReleaseStatus){
			if(courseReleaseStatus!=0 && courseReleaseStatus==1){
				courseReleaseStatus = 2;
			}else{
				courseReleaseStatus = 1;
			}
			window.location.href="${basePath }courseController/updateCourseChannel.html?courseId="+courseId+"&courseReleaseId="+courseReleaseId+"&courseReleaseStatus="+courseReleaseStatus+"&pageIndex=${pageDTO.pageIndex}";
		}
		
		//全部推送手机
		function releaseFun(){
			$.ajax({
				type:"GET",
				async: false,
				dataType:"json",
			    contentType : "application/json;charset=utf-8",//必须要设置contentType，不然后台接收不到json数据格式，并且是乱码
				url:"${basePath}courseController/release.html",
				success:function(data){
					console.log(data);
					if(data.statue=="0"){
						alert(data.msg);
						window.location.href="${basePath }courseController/courseList.html";
					}else{
						alert(data.msg);
					}
				}
			});
		}
	</script>
  </head>
  <body>
    <div class="page_title">
      <h5>课程列表 </h5>
    </div>
      <div class="page_main">
	    <form id="searchForm" name="searchForm" action="${basePath}courseController/courseList.html" method="post">
        <div class="data_search clearfix">
          <dl>
            <dt style="width: 200px;">课程标题/主嘉宾/推荐书目：</dt>
            <dd>
             <input type="text" name="title" id="title" value="${title }">
            </dd>
            <dt>课程类型：</dt>
            <dd>
            	<select name="courseTypeId" id="courseTypeId">
            		<option value="">---请选择---</option>
            		<c:forEach items="${courseTypeList }" var="item">
		              	<option value="${item.typeId }" <c:if test="${courseTypeId eq item.typeId }">selected="selected"</c:if>>${item.typeName }&nbsp;&nbsp;&nbsp;&nbsp;(${item.createUserName })</option>
		            </c:forEach>
            	</select>
            </dd>
            <dt>课程发布状态：</dt>
            <dd>
            	<exp:select code="COURSE_STATUS" name="courseStatus" id="courseStatus" headerKey="" headerValue="---请选择---" value="${courseStatus }"></exp:select>
            </dd>
            <dd>
              <input type="submit" class="btn btn-blue" id="search" value="搜索"></input>
              <input type="button" class="btn btn-blue" value="重置" onclick="resetFun()"></input>    
            </dd>
          </dl>
          <c:if test="${sessionUser.isAdmin eq '1' || sessionUser.isBusinessAdmin eq '1'}">
          <dl>
          	<dd><input type="button" class="btn btn-blue" value="添加" onclick="addFun()"></input></dd>
          </dl>
          </c:if>
           <c:if test="${sessionUser.isBusinessAdmin eq '1'}">
           <dl>
          	<dd><input type="button" class="btn btn-blue" value="全部推送手机" onclick="releaseFun()"></input></dd>
           </dl>
          </c:if>
        </div>
        
         <div class="data_list">
          <table>
            <thead>
              <tr>
              	<td>序号</td>
                <td>课程类型</td>
                <td>课程标题</td>
				<!-- <td>总裁课程类型</td> -->
				<td>主讲嘉宾</td>
				<!-- <td>主讲嘉宾职务</td>
				<td>推荐书目</td> -->
				<c:if test="${sessionUser.isAdmin eq '1' || sessionUser.isBusinessAdmin eq '1'}">
					<td>课程状态</td>
					<td>创建时间</td>
					<td>排序</td>
					<td>创建人/推送手机</td>
				</c:if>
				<td>发微博次数</td>
				<td>操作</td>
              </tr>
            </thead>
            <c:if test="${not empty pageDTO.data }">
				<c:forEach items="${pageDTO.data}" var="item" varStatus="status">
					<tr>
						<td>${(pageDTO.pageIndex-1)*pageDTO.pageSize+status.index+1}</td>
						<td>${item.courseTypeName}</td>
						<td title="${item.title}">
							<c:if test="${fn:length(item.title)>15}">
								${fn:substring(item.title,0,14)}...
							</c:if>
							<c:if test="${fn:length(item.title)<=15}">
								${item.title}
							</c:if>
						</td>
						<%-- <td><exp:code code="COURSE_TYPE" value="${item.courseType }"></exp:code></td>--%>
						<td>${item.mainGuest}</td> 
						<%-- <td>${item.mainGuestPost}</td>
						<td title="${item.recommendBook}">
							<c:if test="${fn:length(item.recommendBook)>10}">
								${fn:substring(item.recommendBook,0,10)}...
							</c:if>
							<c:if test="${fn:length(item.recommendBook)<=10}">
								${item.recommendBook}
							</c:if>
						</td> --%>
						<c:if test="${sessionUser.isAdmin eq '1' || sessionUser.isBusinessAdmin eq '1'}">
							<td>
								<c:choose>
									<c:when test="${sessionUser.isAdmin eq '1' || item.createUserId eq sessionUser.userId}">
										<a href="javascript:updateCourseStatue('${item.courseId}','${item.courseStatus }')" style="color: blue;" title="点击修改状态"><exp:code code="COURSE_STATUS" value="${item.courseStatus }"></exp:code></a>
									</c:when>
									<c:when test="${item.createUserId eq 1 && item.createUserId ne sessionUser.userId}">
										已购
									</c:when>
									<c:otherwise>
										<exp:code code="COURSE_STATUS" value="${item.courseStatus }"></exp:code>
									</c:otherwise>
								</c:choose>
							</td>
							<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></td>
							<td>${item.courseSort }</td>
							<td>${item.createUserName}
							<c:if test="${item.createUserId eq 1}">
								（<a href="javascript:updateCourseRes('${item.courseId}','${item.courseReleaseId }','${item.courseReleaseStatus}')" style="color: blue;">${item.courseReleaseStatus eq '1'?"是":"否"}</a>）
							</c:if>
							</td>
						</c:if>
						<td>${item.wbCount }</td>
						<td>
							<c:choose>
								<c:when test="${sessionUser.isAdmin eq '1' || item.createUserId eq sessionUser.userId}">
									<a href="${basePath }courseController/updateCourse.html?courseId=${item.courseId}">修改</a>
									<a href="${basePath }courseController/courseUploadFile.html?courseId=${item.courseId}">上传相关资料</a>
									<c:if test="${sessionUser.isAdmin eq '1' }"><!-- 不是超级管理员，也不是企业管理员，则可以学习 -->
										<a href="${basePath }courseController/studentCourse.html?courseId=${item.courseId}">开始学习</a>
									</c:if>
									<c:if test="${sessionUser.userId eq 1 || sessionUser.isBusinessAdmin eq 1}"><!-- 是admin或者是企业管理员 -->
										<a href="https://api.weibo.com/oauth2/authorize?client_id=${wxInfo.appid }&response_type=code&redirect_uri=${wxInfo.redirectUri }?id=course_${item.courseId}" target="_blank">发微博</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${(sessionUser.isBusinessAdmin ne '1' && sessionUser.isAdmin eq '0') or sessionUser.isAdmin eq '1' }"><!-- 不是超级管理员，也不是企业管理员，则可以学习 -->
										<a href="${basePath }courseController/studentCourse.html?courseId=${item.courseId}">开始学习</a>
									</c:if>
								</c:otherwise>
							</c:choose>
							<a href="${basePath }courseController/viewCourse.html?courseId=${item.courseId}">详情</a>
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