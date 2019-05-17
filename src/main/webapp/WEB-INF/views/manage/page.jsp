<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common.jsp"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.winnerbook.base.common.GlobalConfigure"%>

<%
	int pageSize = GlobalConfigure.DEFAULT_PAGE_SIZE; //每页显示的行数
	int pageIndex = 1; //当前页数
	int rowSize = 0; //记录集大小

	if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
		pageSize = Integer.parseInt(request.getParameter("pageSize")); //如果传入每页显示的页数，则使用传入值
	}
	if (!StringUtils.isEmpty(request.getParameter("pageIndex"))) {
		pageIndex = Integer.parseInt(request.getParameter("pageIndex")); //获取当前的页数 
	}
	if (!StringUtils.isEmpty(request.getParameter("rowSize"))) {
		rowSize = Integer.parseInt(request.getParameter("rowSize")); //获取记录集大小
	}

	int pages = (rowSize % pageSize) == 0
			? rowSize / pageSize
			: rowSize / pageSize + 1; //根据记录集大小和页大小，计算出总页数

	int per = pageIndex - 2;
	int suf = pages - pageIndex - 1;
	int startPage = pageIndex - 3;
	int endPage = pageIndex + 2;

	if (startPage < 1) {
		startPage = 2;
	}
	if (endPage > pages) {
		endPage = pages - 1;
	}
	
	
	request.setAttribute("per", per);
	request.setAttribute("suf", suf);
	request.setAttribute("startPage", startPage);
	request.setAttribute("endPage", endPage);
	

	request.setAttribute("pageSize", pageSize);
	request.setAttribute("pageIndex", pageIndex);
	request.setAttribute("pages", pages);
	request.setAttribute("rowSize", rowSize);
%>
<script type="text/javascript">
    var startPNum=1;
    var endPNum=${pages};
	function show(pageIndex) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageIndex ><%=pages%>) {
			pageIndex =<%=pages%>;
		}
		var form = $("#" + "${param.currentFormId}");
		$("#pageIndex").val(pageIndex);
		form.submit();
	}
	
	function toSomePage(){
		var pageNum=$("#toPage").val();
		var pattern=/^[0-9]*[1-9][0-9]*$/;
		var flag = pattern.test(pageNum);
		var pageTo=parseInt(pageNum);
		if(flag){
			if(pageTo>0&&pageTo<=endPNum){
				show(pageNum);
			}else{
				alert("请输入正确的跳转页数，页数范围为1-"+endPNum+"！");
				$("#toPage").val('');
			}
		}else{
			alert("请输入要跳转的页数！");
			$("#toPage").val('');
		}
	}
	
	
	
</script>
<c:if test="${rowSize==0}">
	<div style="text-align: center;width: 1500px;height:30px;line-height:30px;background: #f9f9f9;">
		暂无数据...
	</div>
</c:if>
<div class="pagination clearfix">
   <c:if test="${endPage>-1 }">
	<div>
		<ul>
			<li class="pageinfo">共${rowSize}条</li>
			<c:choose>
				<c:when test="${pageIndex > 1 }">
					<li><a href="javascript:show('1')">首页</a></li>
					<li><a href="javascript:show('<%=pageIndex - 1%>')">上一页</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled">首页</li>
					<li class="disabled">上一页</li>
				</c:otherwise>
			</c:choose>
			<c:if test="${startPage >1}">
			    <c:choose>
					<c:when test="${pageIndex == 1 }">
						<li class="current">1</li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:show('1')">1</a></li>
					</c:otherwise>
			     </c:choose>
			</c:if>
	
            <c:if test="${per >3}">
				<li class="shenglie">...</li>					
			</c:if>
			<c:forEach var="r" begin="${startPage}" end="${endPage}">
				<c:choose>
					<c:when test="${pageIndex == r }">
						<li class="current">${r}</li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:show(${r})">${r}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
				
			<c:if test="${suf >2}">
				<li class="shenglie">...</li>					
			</c:if>
            <c:if test="${endPage <pages && pages>1}">
                 <c:choose>
					<c:when test="${pageIndex == pages }">
						<li class="current">${pages}</li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:show('${pages}')">${pages}</a></li>
					</c:otherwise>
			     </c:choose>
			</c:if>
			<c:choose>
				<c:when test="${pageIndex < pages }">
					<li><a href="javascript:show('<%=pageIndex + 1%>')">下一页</a></li>
					<li><a href="javascript:show('<%=pages%>')">尾页</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled">下一页</li>
					<li class="disabled">尾页</li>
				</c:otherwise>
			</c:choose>
			<li class="pageinfo">共${pages}页</li>
			<li class="pageinfo">到第 <input type="text" name="toPage"
				id="toPage" value="${pageIndex }" /> 页 <input type="button" id="btn_toPage"
				name="btn_toPage" value="确定" onClick="toSomePage()"/>
				
			</li>
		</ul>
	</div>
   </c:if>
</div>
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageDTO.pageIndex }">
<input type="hidden" name="pageSize" id="pageSize" value="${pageDTO.pageSize }">