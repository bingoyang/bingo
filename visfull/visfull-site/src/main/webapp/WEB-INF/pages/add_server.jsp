<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/addserver/addServerDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
		<table width="100%">
			<tr>
				<td>服务人员编码: </td><td><input type="text" name="serverCode" class="required" maxlength="20"/></td>
				<td>服务人员名称: </td><td><input type="text" name="serverName" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>服务人员密码: </td><td><input type="password" name="pwd" class="required" maxlength="20"/></td>
				<td>服务人员电话: </td><td><input type="text" name="phone" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>性别: </td><td>				<select name="gender" class="required combox">
					<option value="">所有</option>
					<option value="MALE">男</option>
					<option value="FEMALE">女</option>
				</select></td>
				<td>年龄: </td><td><input type="text" name="age" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>身份证号: </td><td><input type="text" name="identity" class="required" maxlength="20"/></td>
				<td>所属省份: </td><td><input type="text" name="province" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>状态: </td><td>				<select name="status" class="required combox">
					<option value="">所有</option>
					<option value="NORMAL">正常</option>
					<option value="STOP">停用</option>
				</select></td>
				<td>归属服务提供商:</td><td>			<input id="inputOp" name="spId" value="" type="hidden"/>
			<input class="required" name="name" type="text"  suggestFields="name"  lookupGroup=""/>
			<a class="btnLook" href="${ctx}/bz/initselectprovider" lookupGroup="">查找带回</a>	
			<span class="info">选择</span></td>
			</tr>
							<tr>
					<td>服务人员技能介绍:</td>
					<td colspan="3"><textarea name="skillIntroduce" rows="8"
							cols="80"></textarea>
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
