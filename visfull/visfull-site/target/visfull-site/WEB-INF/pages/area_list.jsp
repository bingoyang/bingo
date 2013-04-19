<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/bz/arealist">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>
<form method="post" rel="pagerForm" action="${ctx}/bz/arealist" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<table>
			<tr>
			<td>开始日期：</td>
			<td><input type="text" name="startDate" class="date" value="${param.startDate}" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a></td>
			<td>结束日期：</td>
			<td><input type="text" name="endDate" class="date" value="${param.endDate}" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a></td>
							<td>商圈名称：</td>
							<td>
				<input type="text" name="name" class="textInput" value="${param.name}" size="30" /></td>
	
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
			</tr>
		</table>
	</div>
</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="dialog" rel="areaDialog" href="${ctx}/bz/toaddarea" width="400" height="200" title="添加"><span>添加</span></a></li>
			<li><a class="edit" target="dialog" rel="editDialog" href="${ctx}/bz/toupdatearea/{slt_objId}" title="编辑"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="${ctx}/bz/deletearea/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="50">序号</th>
				<th>商圈编码</th>
				<th>商圈名称</th>
				<th>商圈归属区域</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.data}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}">
				<td>${item.id}</td>
				<td>${item.areaCode}</td>
				<td>${item.areaName}</td>
				<td>${item.countyName}</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="./pager/panelBar.jsp"></c:import>
</div>