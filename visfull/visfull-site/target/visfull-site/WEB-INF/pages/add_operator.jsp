<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="./pager/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/bz/addoperator"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table width="100%">
				<tr>
					<td width="10%">运营商编码:</td>
					<td><input type="text" name="opCode" class="required"
						maxlength="20" />
					</td>
					<td width="10%">运营商名称:</td>
					<td><input type="text" name="opName" class="required"
						maxlength="20" />
					</td>
				</tr>
				<tr>
					<td>运营商电话:</td>
					<td><input type="text" name="opPhone" class="required"
						maxlength="20" />
					</td>
					<td>运营商主页:</td>
					<td><input type="text" name="opHomepage" class="required"
						maxlength="20" />
					</td>
				</tr>
				<tr>
					<td>营业时间:</td>
					<td><input type="text" name="opHours" class="required"
						maxlength="20" />
					</td>
					<td>运营商海报:</td>
					<td><span id="uploadtag"><img src="${ctx}/script/ajaxfileupload/loading.gif" id="uploadloading"
						style="display:none;"> <input type="file" id="file"
						name="file" /> <input type="button" value="上传"
						onclick="return ajaxFileUpload();"></span><span style="display:none;" id="datatag"><input type="text" id="opPoster" name="opPoster" readonly
						size="30" /></span></td>
				</tr>
				<tr>
					<td>运营商地址:</td>
					<td><input type="text" name="opAddress" class="required"
						maxlength="20" />
					</td>
					<td>座右铭:</td>
					<td><input type="text" name="opMotto" class="required"
						maxlength="20" />
					</td>
				</tr>
				<tr>
					<td>运营商状态:</td>
					<td><select name="status" class="required combox">
							<option value="">所有</option>
							<option value="NORMAL">正常</option>
							<option value="STOP">停用</option>
					</select>
					</td>
					<td>运营商密码:</td>
					<td><input type="password" name="opPwd"  maxlength="20" />
					</td>
				</tr>
				<tr>
					<td>加入日期:</td>
					<td><input type="text" name="joinDate" class="date"
						format="yyyy-MM-dd" yearstart="-5" yearend="5" /> <a
						class="inputDateButton" href="javascript:;">选择</a>
					</td>
					<td>成立日期:</td>
					<td><input type="text" name="initDate" class="date"
						format="yyyy-MM-dd" yearstart="-5" yearend="5" /> <a
						class="inputDateButton" href="javascript:;">选择</a>
					</td>
				</tr>
				<tr>
					<td>运营商介绍:</td>
					<td colspan="3"><textarea name="opServiceintroduce" rows="8"
							cols="80"></textarea>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
<!--
	function ajaxFileUpload() {
		$("#uploadloading").show();
		$.ajaxFileUpload({
			url : '${ctx}/file/upload',
			secureuri : false,
			fileElementId : 'file',
			dataType : 'json',
			success : function(data, status) {
				$("#opPoster").attr("value",data.fileUrl);
				$("#uploadtag").hide();
				$("#datatag").show();
			},
			error : function(data, status, e) {
				alert(e);
			}
		})
		return false;
	}
//-->
</script>
