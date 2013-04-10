<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/addarea" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
		<table width="100%">
			<tr>
				<td>商圈名称: </td><td><input type="text" name="areaName" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>商圈归属区域: </td><td>
					省:<select class="combox" name="province" ref="combox_city" refUrl="${ctx}/bz/citylist/{value}">
      					 <option value="">所有省市</option>
      					 <c:forEach var="item" items="${provinces}" >
      					 	<option value="${item.id}">${item.name}</option>
      					 </c:forEach>
                       </select>
					市:<select class="combox" name="city" id="combox_city" ref="combox_area" refUrl="${ctx}/bz/countylist/{value}">
      					<option value="">所有城市</option>
					   </select>
					区县:<select class="combox" name="countyId" id="combox_area">
      						<option value="">所有区县</option>
						</select>
						<input type="hidden" name="countyName"/>
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
