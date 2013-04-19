<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/updateblackwhite/editDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<input type="hidden" name="id"  value="${blackWhite.id}"/>
	<div class="pageFormContent" layoutH="56">
		<p>
			<label>电话号码: </label>
			<input type="text" name="phone" class="required" maxlength="20" value="${blackWhite.phone}"/>
		</p>
		<p>
			<label>类型: </label>
				<select name="type" class="required combox">
					<option value="">所有</option>
					<option value="WHITE" <c:if test="${blackWhite.type == 'WHITE'}">selected</c:if>>白名单</option>
					<option value="BLACK" <c:if test="${blackWhite.type == 'BLACK'}">selected</c:if>>黑名单</option>
				</select>
		</p>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
