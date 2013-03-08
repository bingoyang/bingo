<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/addprovider/addProviderDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent" layoutH="56">
		<table width="100%">
			<tr>
				<td>服务提供商编码: </td><td><input type="text" name="serviceCode" class="required" maxlength="20"/></td>
				<td>服务提供商名称: </td><td><input type="text" name="serviceName" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>服务提供商密码: </td><td><input type="password" name="servicePwd" class="required" maxlength="20"/></td>
				<td>联系人: </td><td><input type="text" name="linkMan" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>海报: </td><td><input type="text" name="poster" class="required" maxlength="20"/></td>
				<td>主页: </td><td><input type="text" name="homePage" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>营业时间: </td><td><input type="text" name="serviceHours" class="required" maxlength="20"/></td>
				<td>服务提供商电话:</td><td><input type="text" name="phoneNo" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>消费标准: </td><td><input type="text" name="consumeStandard" class="required" maxlength="20"/></td>
				<td>归属运营商:</td><td>			<input id="inputOp" name="opId" value="" type="hidden"/>
			<input class="required" name="opName" type="text"  suggestFields="name"  lookupGroup=""/>
			<a class="btnLook" href="${ctx}/bz/initselectoperator" lookupGroup="">查找带回</a>	
			<span class="info">选择</span></td>
			</tr>
			<tr>
				<td>状态: </td><td>				<select name="status" class="required combox">
					<option value="">所有</option>
					<option value="NORMAL">正常</option>
					<option value="STOP">停用</option>
				</select></td>
				<td>成立时间:</td><td><input type="text" name="initDate" class="date"
						format="yyyy-MM-dd" yearstart="-5" yearend="5" /> <a
						class="inputDateButton" href="javascript:;">选择</a></td>
			</tr>
			
				<tr>
					<td>座右铭:</td>
					<td><input type="text" name="serviceMotto" class="required"
						maxlength="20" />
					</td>
					<td>加入日期:</td>
					<td><input type="text" name="joinDate" class="date"
						format="yyyy-MM-dd" yearstart="-5" yearend="5" /> <a
						class="inputDateButton" href="javascript:;">选择</a>
					</td>
				</tr>
			<tr>
					<td>归属分类:</td>
					<td colspan="3">
						<input name="catalogId" value="" type="hidden"/>
						<input class="required" name="catalogName" type="text" readonly/>
						<a class="btnLook" href="${ctx}/bz/cataloglookup" lookupGroup="">查找带回</a>	
					</td>
			</tr>
			<tr>
					<td>服务提供商介绍:</td>
					<td colspan="3"><textarea name="serviceIntroduce" rows="8"
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
