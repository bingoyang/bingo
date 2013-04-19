<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/bz/queryservicerecord/${serverId}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>
<div class="pageContent">
	<table class="table" width="100%"  layoutH="250">
		<thead>
			<tr>
				<th width="50">序号</th>
				<th>用户名称</th>
				<th>用户电话</th>
				<th>服务信息描述</th>
				<th>服务时间</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.data}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}">
				<td>${s.index + 1}</td>
				<td>${item.customerName}</td>
				<td>${item.customerPhone}</td>
				<td>${item.serviceInfo}</td>
				<td><fmt:formatDate value="${item.serviceDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="./pager/panelBar.jsp"></c:import>
</div>