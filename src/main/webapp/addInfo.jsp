<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    // 获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>职位基本信息 - JSU网</title>
    <base href="<%=basePath%>">
    <link href="css/base.css" type="text/css" rel="stylesheet" />
    <link href="css/resume.css" type="text/css" rel="stylesheet" />
    <meta content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职" name="keywords">
    <meta content="JSU专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。" name="description">
    <!-- 日期控件js -->
    <script src="js/Calendar6.js" type="text/javascript" language="javascript"></script>
    <script type="text/javascript">
        function validate() {
            var jobname = document.getElementById("jobname");

            if (realname.value == ""){
                alert("职位不能为空！");
                realname.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<!-- 从request对象中获取一个JavaBean对象 -->
<jsp:useBean id="company" class="com.xfb.bean.Company" scope="request"></jsp:useBean>
<!-- 网站公共头部 -->
<jsp:include page="/managerTop.jsp"></jsp:include>

<!-- 我的简历页面 开始 -->
<div class="resume_con">
   <%-- <!--tab设置-->
    <div class="user_operate">
        <ul style="float: left">
            <li> <a href="ResumeBasicinfoServlet?type=select">我的简历</a></li>
            <li class="tn-tabs-selected"> <a href="JobApplyServlet?type=myapply">我的申请</a></li>
        </ul>
        <div class="clear"></div>
    </div>--%>
    <!--主体部分-->
    <div class="resume_main">
        <!--左边-->
        <div class="resume_left">
            <div class="resume_title">
                <div style="float: left">基本信息</div>
            </div>
            <div class="all_resume" style="text-align: center;" align="center">
                <!--------------------- 职位基本信息添加 ------------------------->
                <form action="JobServlet?type=add" method="post" onsubmit="return validate();">
                    <div class="table_style" style="margin-left:150px;">
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">职位：</td>
                                <td bgcolor="#F8F8F8" align="left"><input type="text" id="realname"
                                                                          name="jobName"> <font style="color: red">*</font></td>
                            </tr>
                        </table>

                        <div class="he"></div>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">结束日期：</td>
                                <td bgcolor="#F8F8F8" align="left"><input name="jobEnddate" type="text"
                                                                          id="birthday" onclick="SelectDate(this)" readonly="readonly" /></td>
                            </tr>
                        </table>
                        <div class="he"></div>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">工作地：</td>
                                <td bgcolor="#F8F8F8" align="left"><input type="text"
                                                                          name="jobArea"></td>
                            </tr>
                        </table>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">公司信息：</td>
                                <td bgcolor="#F8F8F8" align="left">
                                    <select name="companyId">
                                        <option>-请选择-</option>
                                        <option value="1">凌志软件股份有限公司</option>
                                        <option value="2">苏州大宇宙信息创造有限公司</option>
                                        <option value="3">北京日立华胜信息系统有限公司</option>
                                        <option value="4">上海日立华胜信息系统有限公司</option>
                                    </select>

                                </td>
                            </tr>
                        </table>
                        <div class="he"></div>
                        <table width="350" border="0" cellpadding="3" cellspacing="1"
                               bgcolor="#EEEEEE">
                            <tr>
                                <td width="110" align="right" bgcolor="#F8F8F8">薪资：</td>
                                <td bgcolor="#F8F8F8" align="left"><input type="text"
                                                                          name="jobSalary"></td>
                            </tr>
                        </table>
                        <div align="center">
                            <input name="" type="submit" class="save1" value="保存">
                            <input name="" type="reset" class="cancel2" value="取消">
                        </div>
                    </div>
                </form>
                <!--------------------- 简历基本信息添加 结束------------------->
            </div>
        </div>
      <%--  <!--右边-->
        <div class="it-aside">
            <div class="it-aside-fixed">
                <div style="float: right" class="uploade">
                    <% if("".equals(company.getCompanyPic()) || null==company.getCompanyPic()){ %>
                    <img src="applicant/images/anonymous.png">
                    <% }else{ %>
                    <img src="applicant/images/<jsp:getProperty property="companyPic" name="company"/>" width="140" height="140">
                    <% } %>
                    <p>&nbsp;</p>
                    <div align="center">
                        <a href="companyPicUpload.jsp" class="uploade_btn">更换照片</a>
                    </div>
                </div>
            </div>
        </div>
--%>
    </div>
</div>
<!-- 我的简历页面结束 -->

<!-- 网站公共尾部 -->
<iframe src="foot.html" width="100%" height="150" scrolling="no"
        frameborder="0"></iframe>
</body>
</html>