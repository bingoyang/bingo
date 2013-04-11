<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${ctx}/bz/addcommunity/communityDialog" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent" layoutH="56">
		<table width="100%">
			<tr>
				<td>小区名称: </td><td><input type="text" name="communityName" class="required" maxlength="20"/></td>
			</tr>
			<tr>
				<td>归属商圈: </td><td>
					<select class="combox" id="combox_area" name="area">
      					 <option value="">所有商圈</option>
      					 <c:forEach var="item" items="${areas}" >
      					 	<option value="${item.id}">${item.areaName}</option>
      					 </c:forEach>
                       </select>
					<input type="hidden" id="areaName" name="areaName"/>
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
<script>
	$("#combox_area").bind("change",function(){
		  var area = $("#combox_area").find("option:selected").text();
		  $("#areaName").val(area);
	})
	
</script>