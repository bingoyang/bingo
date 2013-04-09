<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
   String ctx =  request.getContextPath();
   request.setAttribute("ctx",ctx);
   String path = request.getContextPath();    
%>
<script>
var contextPath = "<c:out value="${ctx}"/>";
</script>
<link href="${ctx}/script/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/script/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/script/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/script/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="${ctx}/script/dwz/js/speedup.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/xheditor/xheditor-1.1.12-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<script src="${ctx}/script/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/script/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="${ctx}/script/dwz/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/script/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx}/script/ajaxfileupload/ajaxfileupload.js"></script>





