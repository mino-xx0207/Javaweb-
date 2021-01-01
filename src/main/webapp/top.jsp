<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSU网</title>
    <link href="css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="head">
    <div class="head_area">
        <div class="head_nav">
            <ul>
                <li><img src="images/nav_inc1.png" /><a href="index.jsp">首页</a></li>
                <li><img src="images/nav_inc2.png" /><a href="#">成功案例</a></li>
                <li><img src="images/nav_inc3.png" /><a href="#">关于JSU</a></li>
                <li><img src="images/nav_inc2.png" /><a href="ResumeBasicinfoServlet?type=select">我的简历</a></li>
                <li><img src="images/nav_inc2.png" /><a href="JobApplyServlet?type=myapply">我的申请</a></li>
            </ul>
        </div>
     <%--   <div class="head_logo">
            <img src="images/logo.jpg" />
        </div>--%>
        <div class="head_user">
            <a href="login.jsp" target="_parent"><span class="type1">登录</span></a><a
                href="register.jsp" target="_parent"><span class="type2">注册</span></a>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="top_main">
    <div class="top_logo">
        <img src="images/mainLogo.png" />
    </div>
    <div class="top_instr"><img src="images/寻找软件人才.jpg" /></div>
    <div class="top_tel">
        <img src="images/consultPhone.jpg" />
    </div>
</div>
<div class="clear"></div>
</body>
</html>