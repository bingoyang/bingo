<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<form method="post" action="${ctx}/bz/updatetreenode/treeNodeDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent" layoutH="56">
		<input type="hidden" name="id" value="${node.id}"/>
		<input type="hidden" name="pid" value="${node.pid}"/>
		<table width="100%">
			<tr>
				<td>类型:</td><td><input type="text" name="dataType" class="required" maxlength="20" value="${node.dataType}"/></td>
				<td>节点类型:</td><td><input type="text" name="nodeType" class="required" maxlength="20" value="${node.nodeType.displayName}"/></td>
				<td>名称:</td><td><input type="text" name="dataName" class="required" maxlength="20" value="${node.dataName}"/></td>
			</tr>
			<tr><td><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></td></tr>
		</table>
	</div>
</form>
