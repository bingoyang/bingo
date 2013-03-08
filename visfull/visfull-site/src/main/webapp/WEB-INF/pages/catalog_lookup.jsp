<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<ul id="lookupTree" class="ztree"></ul>
	</div>
</div>
<script type="text/javascript">
<!--
var setting2 = {
	view: {
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:"id",
			pIdKey:"pid",
			rootPId:null
		},
		key:{
			name:"dataName"
		}
		
	},
	async: {
		enable: true,
		url:"${ctx}/bz/listcatalog/CATALOG"
	},
	callback: {
		onDblClick:function(event, treeId, treeNode){
			$.bringBack({catalogId:treeNode.id,catalogName:treeNode.dataName});
		}
	}
};
$.fn.zTree.init($("#lookupTree"), setting2);
//-->
</script>