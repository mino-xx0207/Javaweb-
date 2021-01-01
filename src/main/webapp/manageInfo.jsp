<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/error.jsp" %>
<%@ page import="com.xfb.bean.Company,com.xfb.bean.Job" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%String path = request.getContextPath() + "/";%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>职位信息展示</title>
    <base href="<%=path%>">
    <link href="css/base.css" type="text/css" rel="stylesheet">
    <link href="css/job.css" type="text/css" rel="stylesheet">
    <!-- 日期控件js -->
    <script src="js/Calendar6.js" type="text/javascript" language="javascript"></script>
</head>
<body>
<jsp:include page="/managerTop.jsp"></jsp:include>

<div class="tn-grid" align="center">
    <div class="bottomban">
        <div class="bottombanbox">

            <img src="recruit/images/${requestScope.company.companyPic }"></div>
    </div>
</div>

<div class="tn-grid">
    <div class="tn-box-content">
        <div class="it-main">
            <div class="it-ctn-heading">
                <div class="it-title-line">
                    <h3>${requestScope.job.jobName }</h3>
                </div>
            </div>
            <form action="JobServlet?type=update" method="post"
                  onsubmit="return validate();">
                <div class="job">
                    <table class="it-table" style="width:700px">
                        <tbody>
                        <tr>
                            <input hidden name="jobid" value="${requestScope.job.jobId}">
                        </tr>
                        <tr>
                            <td class="it-table-title"> 职位：</td>
                            <td class="tn-border-rb">
                                <input type="text" name="jobName" value=" ${requestScope.job.jobName }">

                            </td>
                            <td class="it-table-title"> 薪资：</td>
                            <td class="tn-border-rb"><input type="text" name="jobSalary"
                                                            value="${requestScope.job.jobSalary }">
                            </td>
                            </td>
                        </tr>
                        <tr>
                            <td class="it-table-title"> 工作地区：</td>
                            <td class="tn-border-rb">
                                <input type="text" name="jobArea" value="${requestScope.job.jobArea }">
                            </td>
                            <td class="it-table-title"> 结束日期：</td>
                            <td class="tn-border-rb">
                                <input name="jobEnddate" type="text" id="birthday" onclick="SelectDate(this)"
                                       readonly="readonly" value="${requestScope.job.jobEnddate}">
                            </td>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                        <div class="btn_bot">
                            <input name="submit" type="submit" class="tn-button-text"
                                   value="更新">
                            <a  onclick="DeleteSure(${requestScope.job.jobId})">
                                <input name="" type="button" class="tn-button-text" value="删除"></a>
                            <input class="tn-button-text" name="" type="button"
                                   onclick="javascript:window.location.href='managerIndex.jsp';" class="cancel2"
                                   value="返回">
                            <%--   <span style="color:#3399ff" class="tn-button-text">更新</span>--%>
                        </div>
                    <script type="text/javascript">
                        function DeleteSure(id){
                         if(confirm("确定取消吗？")){
                             window.location.href="JobServlet?type=delete&jobid="+id;
                         }else {
                             window.location.href ="JobServlet?type=show&jobid="+id;
                         }
                        }
                    </script>
                </div>
            </form>
        </div>
        <div class="clear"></div>
    </div>
</div>
<iframe src="foot.html" width="100%" height="150" scrolling="no" frameborder="0"></iframe>
</body>
</html>