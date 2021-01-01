<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>简历基本信息 - JSU网</title>
    <base href="<%=basePath%>">
    <link href="css/base.css" type="text/css" rel="stylesheet" />
    <link href="css/resume.css" type="text/css" rel="stylesheet" />
    <meta
            content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
            name="keywords">
    <meta
            content="JSU专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。"
            name="description">
    <script type="text/javascript">
        function validate() {
            var headShot = document.getElementById("headShot");
            if (headShot.value == "") {
                alert("请选择要上传的头像！");
                headShot.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<!-- 网站公共头部 -->
<jsp:include page="/managerTop.jsp"></jsp:include>
<%--<iframe src="top.jsp" width="100%" height="100" scrolling="no"
		frameborder="0"></iframe>--%>

<!-- 简历照片上传页面 开始 -->
<div class="resume_con">
    <%--<!--tab设置-->
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
                <div style="float: left">公司照片</div>
            </div>
            <div class="all_resume">
                <!--------------------- 简历照片修改------------------------->
                <form action="JobAddServlet" method="post"
                      enctype="multipart/form-data" onsubmit="return validate();">
                    <div class="table_style" style="margin-left: 150px;">
                        <div class="uploade">
                            <div align="center">
                                <img src="images/anonymous.png" width="150" height="150">
                                <p>&nbsp;</p>
                                <input name="companyPic" id="companyPic" type="file" value="上传照片">
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="he"></div>
                        <div align="center">
                            <input name="submit" type="submit" class="save1" value="保存">
                            <a style="backgroundcolor: white" href="addInfo.jsp">取消</a>
                            <%--		<input src="" name="reset" type="reset" class="cancel2" value="取消">--%>
                        </div>
                    </div>
                </form>
                <!--------------------- 简历照片修改 结束---------------------->
            </div>
        </div>
      <%--  <!-- 右侧公共部分：简历完善度 -->
        <iframe src="applicant/resume_right.jsp" width="297" height="440"
                scrolling="no" frameborder="0"></iframe>

        <div style="clear: both"></div>--%>
    </div>
</div>
<!-- 我的简历页面结束 -->
<!-- 网站公共尾部 -->
<iframe src="foot.html" width="100%" height="150" scrolling="no"
        frameborder="0"></iframe>
</body>
</html>