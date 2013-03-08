<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./pager/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<ul id="catalogTree" class="ztree"></ul>
	</div>
</div>
<script type="text/javascript">
<!--
var setting1 = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	edit: {
		enable: true,
		editNameSelectAll: true
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
		beforeDrag: beforeDrag,
		beforeEditName: beforeEditName,
		beforeRemove: beforeRemove,
		beforeRename: beforeRename,
		onRemove: onRemove,
		onRename: onRename
	}
};

var log, className = "dark";
function beforeDrag(treeId, treeNodes) {
	return false;
}
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	var zTree = $.fn.zTree.getZTreeObj("catalogTree");
	zTree.selectNode(treeNode);
}
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	var zTree = $.fn.zTree.getZTreeObj("catalogTree");
	zTree.selectNode(treeNode);
	if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？")){
        $.ajax({
            type:"get",
            url:"${ctx}/bz/deletecatalog/"+treeNode.id,
            async: false,
            success:function(result){
				return true;
            }
         });
	}else{
		return false;
	}
		
}
function onRemove(e, treeId, treeNode) {
}
function beforeRename(treeId, treeNode, newName) {
	className = (className === "dark" ? "":"dark");
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj("catalogTree");
		setTimeout(function(){zTree.editName(treeNode)}, 10);
		return false;
	}
    $.ajax({
        type:"post",
        url:"${ctx}/bz/updatecatalog",
        data:"dataName="+newName+"&id="+treeNode.id,
        async: false,
        success:function(result){
			return true;
        },
        error:function(result){
        	alert(result);
        	return false;
        }
     });
}
function onRename(e, treeId, treeNode) {
}

function getTime() {
	var now= new Date(),
	h=now.getHours(),
	m=now.getMinutes(),
	s=now.getSeconds(),
	ms=now.getMilliseconds();
	return (h+":"+m+":"+s+ " " +ms);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id
		+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.id);
	if (btn) btn.bind("click", function(){
        $.ajax({
            type:"post",
            url:"${ctx}/bz/addcatalog",
            dataType:"json",
            data:"dataName=新节点&pid="+treeNode.id,
            async: false,
            success:function(result){
        		var zTree = $.fn.zTree.getZTreeObj("catalogTree");
        		zTree.addNodes(treeNode,result);		
            }
         });
		
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.id).unbind().remove();
};

$.fn.zTree.init($("#catalogTree"), setting1);
//-->

</script>