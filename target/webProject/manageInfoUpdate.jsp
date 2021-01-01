<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.xfb.bean.ResumeBasicinfo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>简历基本信息修改 - JSU网</title>
    <base href="<%=basePath%>">
    <link href="css/base.css" type="text/css" rel="stylesheet" />
    <link href="css/resume.css" type="text/css" rel="stylesheet" />
    <meta content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职" name="keywords">
    <meta content="JSU专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。" name="description">
    <!-- 日期控件js -->
    <script src="js/Calendar6.js" type="text/javascript" language="javascript"></script>
    <script type="text/javascript">
        function validate() {
            var realname = document.getElementById("realName");
            var telephone = document.getElementById("telephone");
            var email = document.getElementById("email");
            // 邮箱格式正则表达式
            var emailPattern =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            // 手机号格式正则表达式
            var phonePattern = /^(((13[0-9]{1})|159|153)+\d{8})$/;

            if (realname.value == ""){
                alert("姓名不能为空！");
                realname.focus();
                return false;
            }
            if (telephone.value == ""){
                alert("手机不能为空！");
                telephone.focus();
                return false;
            }else if(!phonePattern.test(telephone.value)){
                alert("手机号格式不正确！");
                telephone.focus();
                return false;
            }
            if (email.value == ""){
                alert("邮箱不能为空！");
                email.focus();
                return false;
            }else if(!emailPattern.test(email.value)){
                alert("邮箱格式不正确！");
                email.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<jsp:include page="/top1.jsp"></jsp:include>
<!-- 我的简历页面 开始 -->
<div class="resume_con">

    <!--主体部分-->
    <div class="resume_main">
        <!--左边-->
        <div class="resume_left">
            <div class="resume_title">
                <div style="float: left">基本信息</div>
            </div>
            <div class="all_resume" style="text-align: center;" align="center">
                <h3><font color="red">${basicinfo.resumeUpdateResult}</font></h3>
                <!--------------------- 简历基本信息更新 ------------------------->
                <form action="JobServlet?type=update" method="post" onsubmit="return validate();">
                    <div class="table_style" style="margin-left:150px;">
                        <table width="350" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">职位名称：</td>
                                <td bgcolor="#F8F8F8" align="left">
                                    <input type="text" id="realName" name="jobName" value=" ">
                                    <font style="color: red">*</font></td>
                            </tr>
                        </table>

                        <div class="he"></div>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">结束日期：</td>
                                <td bgcolor="#F8F8F8" align="left">
                                    <input name="jobEnddate" type="text" id="birthday" onclick="SelectDate(this)"
                                           readonly="readonly" value='<fmt:formatDate value=""/>'/></td>
                            </tr>
                        </table>
                        <div class="he"></div>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">工作地区：</td>
                                <td bgcolor="#F8F8F8" align="left">
                                    <input type="text" name="jobArea" value=""></td>
                            </tr>
                        </table>
                        <div class="he"></div>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">薪资：</td>
                                <td bgcolor="#F8F8F8" align="left">
                                    <input type="text" name="jobSalary" value=""></td>
                            </tr>
                        </table>
                        <div align="center">
                            <input name="" type="submit" class="save1" value="保存">
                        </div>
                    </div>
                </form>
                <!--------------------- 简历基本信息添加 结束------------------->
            </div>
        </div>
    </div>
</div>
<!-- 我的简历页面结束 -->

<!-- 网站公共尾部 -->
<iframe src="foot.html" width="100%" height="150" scrolling="no"
        frameborder="0"></iframe>
</body>
</html>