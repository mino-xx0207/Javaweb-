package com.xfb.servlet;

import com.xfb.bean.Company;
import com.xfb.dao.CompanyDAO;
import com.xfb.dao.ResumeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;


    @MultipartConfig
    @WebServlet("/JobAddServlet")
    public class JobAddServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public JobAddServlet() {
            super();
        }

        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);
        }

        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            CompanyDAO company = new CompanyDAO();

            // 获取上传文件域
            Part part = request.getPart("companyPic");
            // 获取上传文件名称
            String cd = part.getHeader("Content-Disposition");
            //截取不同类型的文件需要自行判断
            String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
            // 为防止上传文件重名，对文件进行重命名
            String newFileName = System.currentTimeMillis()
                    + fileName.substring(fileName.lastIndexOf("."));
            // 将上传的文件保存在服务器项目发布路径的applicant/images目录下
            String filepath = getServletContext().getRealPath("/applicant/images");
            System.out.println("头像保存路径为：" + filepath);
            company.add(fileName);
            File f = new File(filepath);
            if (!f.exists())
                f.mkdirs();
            part.write(filepath + "/" + newFileName);
            Company company1 = new Company(null, null, null, null, null,
                    1,1,333, fileName);

            // 从会话对象中获取当前用户简历标识
            request.getSession().setAttribute("SESSION_COMPANY", company1);
           Company company2 = (Company)request.getSession().getAttribute("SESSION_COMPANY");
            // 更新简历照片
            CompanyDAO dao = new CompanyDAO();
            dao.updateCompanyPic( company2.getCompanyId(), fileName);
            // 照片更新成功，回到“我的简历”页面
            response.sendRedirect("addInfo.jsp");
        }

    }
