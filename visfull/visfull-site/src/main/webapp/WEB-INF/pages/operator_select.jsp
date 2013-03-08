<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/bz/selectoperatorlist">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>
<form method="post" rel="pagerForm" action="${ctx}/bz/selectoperatorlist" onsubmit="return dialogSearch(this,'dialog')">
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
							<td>运营商名称：</td>
							<td>
				<input type="text" name="opName" class="textInput" value="${param.opName}" size="30" /></td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
			</tr>
		</table>
	</div>
</div>
</form>

<div class="pageContent">
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="50">序号</th>
				<th>运营商编码</th>
				<th>运营商名称</th>
				<th>电话</th>
				<th>介绍</th>
				<th>状态</th>
				<th>最后修改时间</th>
				<th>创建时间</th>
				<th>带回</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.data}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${s.index + 1}</td>
				<td>${item.opCode}</td>
				<td>${item.opName}</td>
				<td>${item.opPhone}</td>
				<td>${item.opServiceintroduce}</td>
				<td>${item.status.displayName}</td>
				<td><fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><a class="btnSelect" href="javascript:$.bringBack({opId:${item.id},opName:'${item.opName}'})" title="查找带回">选择</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="./pager/panelBar.jsp"></c:import>
</div>