<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="./pager/include.inc.jsp"%>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>

<div class="pageContent" style="padding: 5px">
	<div>
		<div layoutH="146"
			style="float: left; display: block; overflow: auto; width: 240px; border: solid 1px #CCC; line-height: 21px; background: #fff">
			<ul id="catg_root" class="tree treeFolder">
			</ul>
		</div>
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="add" target="dialog" rel="treeNodeDialog"
						href="${ctx}/bz/toaddtreenode" onclick = "initHref(this)" width="600" height="200"
						title="添加"><span>添加</span>
					</a>
					</li>
					<li class="line">line</li>
				</ul>
			</div>
			<div id="jbsxBox" class="unitBox">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function initHref(obj){
	if($("#catg_root div[class=selected]")[0]){
		var liId = $("#catg_root div[class=selected]").parent().attr("id");
		var chArray = liId.split("_");
		obj.href = obj.href+"/"+chArray[1];
	}	
}
<!--
	$.ajax({
		type : "get",
		url : "${ctx}/bz/listnodetree",
		async : false,
		dataType : "json",
		success : function(data) {
			$.each(data, function(index, item) {
				var url = "${ctx}/bz/gettreenode/"+item.id;
				if (item.pid == null || item.pid == 0) {
					$("#catg_root").append(
							"<li id='catgnode_"+item.id+"'><a href='"+url+"' target='ajax' rel='jbsxBox'>"
									+ item.dataName + "</a></li>");
				} else {
					if($("#c_catgnode_"+item.pid)[0]){
						
						$("#c_catgnode_"+item.pid).append("<li id='catgnode_"+item.id+"'><a href='"+url+"' target='ajax' rel='jbsxBox'>"
								+ item.dataName + "</a></li>");
					}else{
						$("#catgnode_"+item.pid).append("<ul id = 'c_catgnode_"+item.pid+"'><li id='catgnode_"+item.id+"'><a href='"+url+"' target='ajax' rel='jbsxBox'>"
								+ item.dataName + "</a></li></ul>");
					}
				}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
//-->
</script>


