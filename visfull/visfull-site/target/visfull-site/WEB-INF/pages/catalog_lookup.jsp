<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
			<ul id="look_root" class="tree treeFolder">
			</ul>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
<!--
	$.ajax({
		type : "get",
		url : "${ctx}/bz/listnodetree",
		async : false,
		dataType : "json",
		success : function(data) {
			$.each(data, function(index, item) {
				if (item.pid == null || item.pid == 0) {
					var inner = "<li id='looknode_"+item.id+"'><a id='a_"+item.id+"' href='javascript:' >"+ item.dataName + "</a></li>";
					$("#look_root").append(inner);
				} else {
					if($("#c_looknode_"+item.pid)[0]){
						$("#c_looknode_"+item.pid).append("<li id='looknode_"+item.id+"'><a id='a_"+item.id+"' href='javascript:'>"+ item.dataName + "</a></li>");
					}else{
						$("#looknode_"+item.pid).append("<ul id = 'c_looknode_"+item.pid+"'><li id='looknode_"+item.id+"'><a id='a_"+item.id+"' href='javascript:'>"+ item.dataName + "</a></li></ul>");
					}
				}
				$("#a_"+item.id).bind("click",function(){
						$.bringBack({catalogId:item.id,catalogName:item.dataName});
				});
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
//-->
</script>