<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/updatecustomer" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
	<input type="hidden" name="id"  value="${customer.id}"/>
	<input type="hidden" name="createDate"  value="${customer.createDate}"/>
		<table width="100%">
			<tr>
				<td>用户姓名: </td><td><input type="text" name="name" class="required" maxlength="20" value="${customer.name}"/></td>
				<td>用户电话: </td><td><input type="text" name="bindPhone" class="required" maxlength="20" value="${customer.bindPhone}"/></td>
			</tr>
			<tr>
				<td>用户密码: </td><td><input type="password" name="pwd" class="required" maxlength="20" value="${customer.pwd}"/></td>
				<td>确认密码: </td><td><input type="password" name="pwd" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>性别: </td><td>				<select name="gender" class="required combox">
					<option value="">所有</option>
					<option value="MALE" <c:if test="${customer.gender == 'MALE'}">selected</c:if>>男</option>
					<option value="FEMALE" <c:if test="${customer.gender == 'FEMALE'}">selected</c:if>>女</option>
				</select></td>
				<td>年龄: </td><td><input type="text" name="age" class="required" maxlength="20" value="${customer.age}"/></td>
			</tr>
			<tr>
					<td>服务人员技能介绍:</td>
					<td colspan="3"><textarea name="residence" rows="8"
							cols="80">${customer.residence}</textarea>
					</td>
				</tr>
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
