<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title></title>
<script type="text/javascript">


	$(function() {
				
		DWZ.init("${ctx}/script/dwz/dwz.frag.xml", {
			loginUrl : "login_dialog.html",
			loginTitle : "登录", // 弹出登录对话框
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //【可选】
			pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, //【可选】
			debug : true, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "${ctx}/script/dwz/themes"
				}); // themeBase 相对于index页面的主题base路径
				$.ajax({
			        type: "get",
			        url: "${ctx}/menus/init",
			        async: false,
			        dataType: "json",
			        success: function (data) {
						$.each(data,function(index,item){
							if(item.pId == null||item.pId == 0){
								$("#ul_root").append("<li id='node_"+item.id+"'><a href='"+item.url+"' rel='rel_"+item.id+"' target='navTab'>"+item.nodeName+"</a></li>");
							}else{
								
							}
						});
			        },
			        error: function (XMLHttpRequest, textStatus, errorThrown) {
			                alert(errorThrown);
			        }
				});
			}
		});
	});
	
</script>
</head>
<body scroll="no">
    <div id="layout">
        <div id="header">
            <div class="headerNav">
                <ul class="nav">
                    <li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换城市</a>
                        <ul>
                            <li><a href="sidebar_1.html">北京</a>
                            </li>
                            <li><a href="sidebar_2.html">上海</a>
                            </li>
                            <li><a href="sidebar_2.html">南京</a>
                            </li>
                            <li><a href="sidebar_2.html">深圳</a>
                            </li>
                            <li><a href="sidebar_2.html">广州</a>
                            </li>
                            <li><a href="sidebar_2.html">天津</a>
                            </li>
                            <li><a href="sidebar_2.html">杭州</a>
                            </li>
                        </ul></li>
                    <li><a href="https://me.alipay.com/dwzteam"
                        target="_blank">捐赠</a>
                    </li>
                    <li><a href="changepwd.html" target="dialog"
                        width="600">设置</a>
                    </li>
                    <li><a href="http://www.cnblogs.com/dwzjs"
                        target="_blank">博客</a>
                    </li>
                    <li><a href="http://weibo.com/dwzui"
                        target="_blank">微博</a>
                    </li>
                    <li><a href="http://bbs.dwzjs.com"
                        target="_blank">论坛</a>
                    </li>
                    <li><a href="login.html">退出</a>
                    </li>
                </ul>
                <ul class="themeList" id="themeList">
                    <li theme="default"><div class="selected">蓝色</div>
                    </li>
                    <li theme="green"><div>绿色</div>
                    </li>
                    <!--<li theme="red"><div>红色</div></li>-->
                    <li theme="purple"><div>紫色</div>
                    </li>
                    <li theme="silver"><div>银色</div>
                    </li>
                    <li theme="azure"><div>天蓝</div>
                    </li>
                </ul>
            </div>

            <!-- navMenu -->

        </div>

        <div id="leftside">
            <div id="sidebar_s">
                <div class="collapse">
                    <div class="toggleCollapse">
                        <div></div>
                    </div>
                </div>
            </div>
            <div id="sidebar">
                <div class="toggleCollapse">
                    <h2>主菜单</h2>
                    <div>收缩</div>
                </div>

                <div class="accordion" fillSpace="sidebar">
                    <div class="accordionHeader">
                        <h2>
                            <span>Folder</span>菜单
                        </h2>
                    </div>
                    <div class="accordionContent">
						<ul id="ul_root" class="tree treeFolder">
						</ul>
                    </div>
                </div>
            </div>
        </div>
        <div id="container">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                        <ul class="navTab-tab">
                            <li tabid="main" class="main"><a
                                href="javascript:;"><span><span
                                        class="home_icon">我的主页</span> </span> </a>
                            </li>
                        </ul>
                    </div>
                    <div class="tabsLeft">left</div>
                    <!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                    <div class="tabsRight">right</div>
                    <!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                    <div class="tabsMore">more</div>
                </div>
                <ul class="tabsMoreList">
                    <li><a href="javascript:;">我的主页</a>
                    </li>
                </ul>
                <div class="navTab-panel tabsPageContent layoutBox">
                    <div class="page unitBox">
                        <div class="pageFormContent" layoutH="80"
                            style="margin-right: 230px"></div>
                    </div>

                </div>
            </div>
        </div>

    </div>
    <div id="footer">
        Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">BINGO-SITE</a>
    </div>
</body>
</html>