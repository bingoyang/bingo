<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/bz/customerlist">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>
<form method="post" rel="pagerForm" action="${ctx}/bz/customerlist" onsubmit="return navTabSearch(this)">
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
			<td>性别：</td>
			<td>
				<select name="gender" class="combox">
					<option value="">--所有--</option>
					<option value="MALE" <c:if test="${param.status == 'MALE'}">selected</c:if>>男</option>
					<option value="FEMALE" <c:if test="${param.status == 'FEMALE'}">selected</c:if>>女</option>
				</select>
			</td>
							<td>用户名称：</td>
							<td>
				<input type="text" name="opName" class="textInput" value="${param.opName}" size="30" /></td>
							<td>电话号码：</td>
							<td>
				<input type="text" name="phone" class="textInput" value="${param.phone}" size="30"/></td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
			</tr>
		</table>
	</div>
</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="navTab" rel="addNavTab"  href="${ctx}/bz/initaddcustomer" title="添加"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="editnavTab" href="${ctx}/bz/initeditcustomer/{slt_objId}" title="编辑"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="${ctx}/bz/deletecustomer/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="50">序号</th>
				<th>用户名称</th>
				<th>用户电话</th>
				<th>性别</th>
				<th>年龄</th>
				<th>状态</th>
				<th>格言</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.data}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${s.index + 1}</td>
				<td>${item.name}</td>
				<td>${item.bindPhone}</td>
				<td>${item.gender.displayName}</td>
				<td>${item.age}</td>
				<td>正常</td>
				<td>${item.residence}</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="./pager/panelBar.jsp"></c:import>
</div>