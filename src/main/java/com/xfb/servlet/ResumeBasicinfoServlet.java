package com.xfb.servlet;


import com.xfb.bean.Applicant;
import com.xfb.bean.ResumeBasicinfo;
import com.xfb.dao.ResumeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简历基本信息操作Servlet
 */
@WebServlet("/ResumeBasicinfoServlet")
public class ResumeBasicinfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResumeBasicinfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获取请求操作类型
        String type = request.getParameter("type");
        if ("add".equals(type)) {
            // 封装请求数据
            ResumeBasicinfo basicinfo = this.requestDataObj(request);
            // 从会话对象中获取当前登录用户标识
            Applicant applicant = (Applicant) request.getSession().getAttribute("SESSION_APPLICANT");
            System.out.println("------------------");
            System.out.println(applicant.getApplicantId());
            System.out.println("------------------");
            // 向数据库中添加当前用户的简历
            ResumeDAO dao = new ResumeDAO();
            if (applicant != null) {
                int basicinfoID = dao.add(basicinfo, applicant.getApplicantId());
                request.getSession().setAttribute("SESSION_RESUMEID", basicinfoID);
                // 操作成功，转向“我的简历”页面
                response.sendRedirect("ResumeBasicinfoServlet?type=select");
            } else {
                out.print("<script type='text/javascript'>");
                out.print("alert('请先登录账号！');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }
        } else if ("select".equals(type)) { // 简历查询操作
            // 从会话对象中获取当前登录用户标识
            Applicant applicant = (Applicant) request.getSession().getAttribute("SESSION_APPLICANT");
            if (applicant != null) {
                // 根据简历标识查询简历基本信息
                ResumeDAO dao = new ResumeDAO();
                ResumeBasicinfo basicinfo = dao.selectBasicinfoByID(applicant.getApplicantId());
                // 将简历基本信息存入request对象进行请求转发
                request.setAttribute("basicinfo", basicinfo);
                request.getRequestDispatcher("applicant/resume.jsp").forward(request, response);
            } else {
                // 用户登录信息错误，进行错误提示
                out.print("<script type='text/javascript'>");
                out.print("alert('请先登录账号！');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }
        } else if ("updateSelect".equals(type)) { // 简历更新前的查询
            // 从会话对象中获取当前登录用户标识
            Applicant applicant = (Applicant) request.getSession().getAttribute("SESSION_APPLICANT");
            // 根据简历标识查询简历基本信息
            ResumeDAO dao = new ResumeDAO();
            ResumeBasicinfo basicinfo = dao.selectBasicinfoByID(applicant.getApplicantId());
            // 将简历基本信息存入request对象进行请求转发
            request.setAttribute("basicinfo", basicinfo);
            request.getRequestDispatcher("applicant/resumeBasicinfoUpdate.jsp").forward(request, response);
        } else if ("update".equals(type)) {
            // 封装请求数据
            ResumeBasicinfo basicinfo = this.requestDataObj(request);
            int basicinfoId = Integer.parseInt(request.getParameter("basicinfoId"));
            basicinfo.setBasicinfoId(basicinfoId);
            // 更新简历信息
            basicinfo.setResumeUpdate(basicinfo);
            request.setAttribute("basicinfo", basicinfo);
            request.getRequestDispatcher("applicant/resumeBasicinfoUpdate.jsp").forward(request, response);
        }
    }

    /**
     * 将请求的简历数据封装成一个对象
     *
     * @param request
     * @return
     * @throws
     */
    private ResumeBasicinfo requestDataObj(HttpServletRequest request) {
        ResumeBasicinfo basicinfo = null;
        // 获得请求数据
        String realname = request.getParameter("realName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String currentLoca = request.getParameter("currentLoc");
        String residentLoca = request.getParameter("residentLoc");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String jobIntension = request.getParameter("jobIntension");
        String jobExperience = request.getParameter("jobExperience");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(birthday);
        } catch (ParseException e) {
            birthdayDate = null;
        }
        // 将请求数据封装成一个简历基本信息对象
        basicinfo = new ResumeBasicinfo(realname, gender, birthdayDate,
                currentLoca, residentLoca, telephone, email, jobIntension,
                jobExperience);
        return basicinfo;
    }
}
