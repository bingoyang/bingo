<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/addtreenode/treeNodeDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent" layoutH="56">
	<input type="hidden" name="pid" value="${pId}"/>
		<table width="100%">
			<tr>
				<td>类型:</td><td><input type="text" name="dataType" class="required" maxlength="20" /></td>
				<td>节点类型:</td><td><input type="text" name="nodeType" class="required" maxlength="20"/></td>
				<td>名称:</td><td><input type="text" name="dataName" class="required" maxlength="20"/></td>
			</tr>
			<tr></tr>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
