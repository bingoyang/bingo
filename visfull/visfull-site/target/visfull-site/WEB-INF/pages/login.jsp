<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
			request.setAttribute("ctx", ctx);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>用户登录</TITLE>
<script src="${ctx}/script/dwz/js/jquery-1.7.1.js"
    type="text/javascript"></script>
<LINK href="${ctx}/script/login/Default.css" type=text/css
    rel=stylesheet>
<LINK href="${ctx}/script/login/xtree.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/script/login/User_Login.css" type=text/css
    rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
<script type="text/javascript">
	function login() {
		document.getElementById("login").submit();
	}
</script>
</HEAD>
<BODY id=userlogin_body>
    <form id="login" action="${ctx}/login" method="post">
        <DIV></DIV>
        <DIV id=user_login>
            <DL>
                <DD id=user_top>
                    <UL>
                        <LI class=user_top_l></LI>
                        <LI class=user_top_c></LI>
                        <LI class=user_top_r></LI>
                    </UL>
                <DD id=user_main>
                    <UL>
                        <LI class=user_main_l></LI>
                        <LI class=user_main_c>
                            <DIV class=user_main_box>
                                <UL>
                                    <LI class=user_main_text>用户名：</LI>
                                    <LI class=user_main_input><INPUT
                                        class=TxtUserNameCssClass
                                        id=TxtUserName maxLength=20
                                        name="userName">
                                    </LI>
                                </UL>
                                <UL>
                                    <LI class=user_main_text>密 码：</LI>
                                    <LI class=user_main_input><INPUT
                                        class=TxtPasswordCssClass
                                        id=TxtPassword type=password
                                        name="passWord">
                                    </LI>
                                </UL>
                                <UL>
                                    <LI class=user_main_text>Cookie：</LI>
                                    <LI class=user_main_input><SELECT
                                        id=DropExpiration
                                        name=DropExpiration>
                                            <OPTION value=None selected>不保存</OPTION>
                                            <OPTION value=Day>保存一天</OPTION>
                                            <OPTION value=Month>保存一月</OPTION>
                                            <OPTION value=Year>保存一年</OPTION>
                                    </SELECT>
                                    </LI>
                                </UL>
                            </DIV>
                        </LI>
                        <LI class=user_main_r><INPUT
                            class=IbtnEnterCssClass id=IbtnEnter
                            style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
                            onclick='javascript:login()' type=image
                            src="script/login/user_botton.gif"
                            name=IbtnEnter>
                        </LI>
                    </UL>
                <DD id=user_bottom>
                    <UL>
                        <LI class=user_bottom_l></LI>
                        <LI class=user_bottom_c><SPAN
                            style="MARGIN-TOP: 40px">如果您尚未在本站注册为用户，请先点此
                                <A href="http://www.huacker.com.cn">注册</A>
                                。</SPAN>
                        </LI>
                        <LI class=user_bottom_r></LI>
                    </UL>
                </DD>

            </DL>
        </DIV>
        <SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
        <SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
        <SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
        <DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

        <DIV></DIV>
    </form>
</BODY>
</HTML>
