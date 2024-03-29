<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./include.inc.jsp"%>

<div class="panelBar">
	<div class="pages">
		<span>每页</span>
		<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
		<span>Total: ${page.total}</span>
	</div>
	
	<div class="pagination" targetType="navTab" totalCount="${page.total}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageNo}"></div>
</div>