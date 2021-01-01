package com.xfb.servlet;

import com.xfb.bean.Applicant;
import com.xfb.bean.JobApply;
import com.xfb.dao.JobApplyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/JobBasicInfoServlet")
public class JobBasicInfoServlet {
    private static final long serialVersionUID = 1L;

    public JobBasicInfoServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");

//        response.setCharacterEncoding("UTF-8");

        response.setContentType("text/html;charset=UTF-8");
//		String
        PrintWriter out = response.getWriter();
        // 获取操作类型
        String type = request.getParameter("type");

        if ("apply".equals(type)) {

            // 获取职位编号
            String jobid = request.getParameter("jobid");
            // 获取登录用户
            Applicant applicant = (Applicant) request.getSession()
                    .getAttribute("SESSION_APPLICANT");
            if(applicant!=null) {
                // 添加此用户对此职位的申请
                JobApplyDAO dao = new JobApplyDAO();
                dao.save(jobid, applicant.getApplicantId());
                response.sendRedirect("JobApplyServlet?type=myapply");
            }
            else{
                out.print("<script type='text/javascript'>");
                out.print("alert('请先登录账号！');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }

        } else if ("myapply".equals(type)) {
            // 获取登录用户
            Applicant applicant = (Applicant) request.getSession()
                    .getAttribute("SESSION_APPLICANT");
            if(applicant!=null) {
                // 根据用户标识查询此用户申请的所有职位
                JobApplyDAO dao = new JobApplyDAO();
                List<JobApply> jobList = dao.getJobApplyList(applicant
                        .getApplicantId());
                request.setAttribute("jobList", jobList);
                request.getRequestDispatcher("applicant/jobApply.jsp").forward(
                        request, response);
            }		else{
                out.print("<script type='text/javascript'>");
                out.print("alert('请先登录账号！');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }
        }
    }

}
