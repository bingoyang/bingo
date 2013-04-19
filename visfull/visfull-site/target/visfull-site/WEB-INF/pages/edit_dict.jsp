<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/updatedict/editDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="id"  value="${dict.id}"/>
	<div class="pageFormContent" layoutH="56">
		<p>
			<label>字典类型: </label>
					<select name="dictType" class="required combox">
					<option value="XIAOQU" <c:if test="${dict.dictType == 'XIAOQU'}">selected</c:if>>小区</option>
					<option value="SHANGQUAN" <c:if test="${dict.dictType == 'SHANGQUAN'}">selected</c:if>>商圈</option>
				 	<option value="OTHER" <c:if test="${dict.dictType == 'OTHER'}">selected</c:if>>其他</option>
					</select>
		</p>
		<p>
			<label>字典编码: </label>
			<input type="text" name="dictCode" class="required" value="${dict.dictCode}" maxlength="20"/>
		</p>
		<p>
			<label>字典名称: </label>
			<input type="text" name="dictName" class="required" value="${dict.dictName}" maxlength="20"/>
		</p>
		<p>
			<label>字典描述: </label>
			<input type="text" name="dictDescribe" class="required" value="${dict.dictDescribe}" maxlength="20"/>
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
