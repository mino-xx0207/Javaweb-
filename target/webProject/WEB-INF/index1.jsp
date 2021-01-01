<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.xfb.dao.CompanyDAO,com.xfb.bean.Company,com.xfb.bean.Job" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSU-software官网-大学生求职,大学生就业,IT行业招聘，IT企业快速入职 - JSU网</title>
    <meta name="renderer" content="ie-stand">
    <!--<link rel="shortcut icon" href="http://www.itoffer.cn/favicon.ico" type="image/x-icon" />-->
    <meta content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职" name="keywords">
    <meta content="JSU-software专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。" name="description">
    <link href="css/base.css" type="text/css" rel="stylesheet" />
    <link href="css/index.css" type="text/css" rel="stylesheet" />
</head>
<body class="tn-page-bg" >

<!-- 使用动态包含头文件  -->
<jsp:include page="top1.jsp"></jsp:include>

<!-- 实例化或从request对象获取一个用于实现分页功能的JavaBean对象 -->
<jsp:useBean id="pagination" class="com.xfb.bean.ComanyPageBean" scope="request"></jsp:useBean>
<!-- 为JavaBean对象属性指定每页显示的信息数量 -->
<jsp:setProperty property="pageSize" value="2" name="pagination"/>
<!-- 从pageNo请求参数中获取当前页码，JavaBean中该属性值默认为1 -->
<jsp:setProperty property="pageNo" param="pageNo" name="pagination"/>

<div id="tn-content">
    <!-- 招聘企业展示 -->
    <%
        List<Company> list = pagination.getPageData();
        if (list != null)
            for (Company c : list) {
    %>
    <div class="tn-grid">
        <div class="tn-box tn-widget tn-widget-content tn-corner-all it-home-box">
            <div class="tn-box-content tn-widget-content tn-corner-all">
                <!-- 企业图片展示 -->
                <div class="it-company-keyimg tn-border-bottom tn-border-gray">
                    <a href="CompanyServlet?type=select&id=<%=c.getCompanyId() %>">
                        <img src="recruit/images/<%=c.getCompanyPic() %>" width="990"> </a></div>
                <!-- 招聘职位展示 -->
                <%
                    Set<Job> jobset = c.getJobs();
                    if (jobset != null)
                        for (Job job : jobset) {
                %>
                <div class="it-home-present">
                    <div class="it-present-btn"><a class=" tn-button tn-button-home-apply" href="JobApplyServlet?type=apply&jobid=<%=job.getJobId()%>">
                        <span class="tn-button-text">我要申请</span> </a></div>
                    <div class="it-present-text" style="padding-left:185px;">
                        <div class="it-line01 it-text-bom">
                            <p class="it-text-tit">职位</p>
                            <p class="it-line01 it-text-explain"><span class="tn-icon it-home-arrow"></span>
                                <span class="tn-helper-right tn-action">
                  <a href="CompanyServlet?type=select&id=<%=c.getCompanyId() %>"
                     class="tn-button tn-corner-all tn-button-text-only tn-button-semidlong">
                  <span class="tn-button-text">更多职位</span> </a> </span>
                                <b><%=job.getJobName() %>
                                </b></p>
                        </div>
                        <div class="it-line01 it-text-top">
                            <p class="it-text-tit">薪资</p>
                            <p class="it-line01 it-text-explain"><span class="tn-icon it-home-arrow"></span>
                                <b><%=job.getJobSalary() %>
                                </b></p>
                        </div>
                    </div>
                    <div class="it-present-text">
                        <div class="it-line01 it-text-bom">
                            <p class="it-text-tit">到期时间</p>
                            <p class="it-line01 it-text-explain"><span class="tn-icon it-home-arrow"></span>
                                <b><%=job.getJobEnddate() %>
                                </b></p>
                        </div>
                        <div class="it-line01 it-text-top">
                            <p class="it-text-tit">工作地区</p>
                            <p class="it-line01 it-text-explain"><span class="tn-icon it-home-arrow"></span>
                                <b><%=job.getJobArea() %>
                                </b></p>
                        </div>
                    </div>
                </div>
            </div>
            <%} %>
        </div>
    </div>
    <%} %>
    <!---------------- 企业列表 结束 -------------------->
    <!-- 企业信息 -->
    <div class="page01">
        <div class="page02">&nbsp;</div>
        <div class="page03"><a href="index1.jsp?pageNo=1">首页 </a></div>
        <% if (pagination.isHasPreviousPage()) { %>
        <div class="page03">
            <a href='index1.jsp?pageNo=<%=pagination.getPageNo()-1%>'>上一页 </a></div>
        <% } %>
        <% if (pagination.isHasNextPage()) { %>
        <div class="page03"><a href="index1.jsp?pageNo=<%=pagination.getPageNo()+1%>">下一页 </a></div>
        <% } %>
        <div class="page03"><a href="index1.jsp?pageNo=<%=pagination.getTotalPages()%>">尾页</a></div>
        <div class="page03">当前是第
            <jsp:getProperty property="pageNo" name="pagination"/>
            页，共
            <jsp:getProperty property="totalPages" name="pagination"/>
            页
        </div>
    </div>
</div>
<!-- 网站公共尾部 -->
<iframe src="foot.html" width="100%" height="150" scrolling="no"
        frameborder="0"></iframe>
</body>
</html>