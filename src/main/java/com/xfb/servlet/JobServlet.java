package com.xfb.servlet;

import com.xfb.bean.Applicant;
import com.xfb.bean.Company;
import com.xfb.bean.Job;
import com.xfb.bean.ResumeBasicinfo;
import com.xfb.dao.JobDAO;
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
 * 职位信息处理Servlet
 *
 *
 */
@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 获取操作类型
		String type = request.getParameter("type");
		if("select".equals(type)){
			// 获取职位编号
			String jobid = request.getParameter("jobid");//根据name取得
			// 根据职位编号查询职位详细信息
			JobDAO dao = new JobDAO();
			Job job = dao.getJobByID(jobid);

			// 将职位信息对象存入request对象
			request.setAttribute("job", job);
			// 将企业信息对象存入request对象
			request.setAttribute("company", job.getCompanyId());
			request.getRequestDispatcher("recruit/job.jsp").forward(request, response);
		}else if ("update".equals(type)) {
			// 封装请求数据
			Job job = this.requestDataObj(request);
			int jobid = Integer.parseInt(request.getParameter("jobid"));
			job.setJobId(jobid);
			// 更新职位信息
			job.setJobUpdate(job);
			request.setAttribute("job", job);
			request.getRequestDispatcher("managerIndex.jsp").forward(request, response);
		}else if("show".equals(type)){
			// 获取职位编号
			String jobid = request.getParameter("jobid");//根据name取得
			// 根据职位编号查询职位详细信息
			JobDAO dao = new JobDAO();
			Job job = dao.getJobByID(jobid);
			// 将职位信息对象存入request对象
			request.setAttribute("job", job);
			// 将企业信息对象存入request对象
			request.setAttribute("company", job.getCompanyId());
			request.getRequestDispatcher("manageInfo.jsp").forward(request, response);
		}else if("delete".equals(type)){
			// 获取职位编号
			String jobid = request.getParameter("jobid");//根据name取得
			JobDAO dao = new JobDAO();
			Job job = dao.getJobByID(jobid);
			dao.deleteJobByID(job);
			request.setAttribute("job", job);
		/*	out.print("<script type='text/javascript'>");
			out.print("alert('请先登录账号！');");
			out.print("window.location='managerIndex.jsp';");
			out.print("</script>");*/
			request.getRequestDispatcher("managerIndex.jsp").forward(request, response);
		} else if ("add".equals(type)) {
// 封装请求数据
		//request.getSession().setAttribute("SESSION_COMPANY", company1);
			//Company company2 = (Company)request.getSession().getAttribute("SESSION_COMPANY");
			Job job = this.requestDataObj(request);
			job.setJobState(1);
			String cIds = request.getParameter("companyId");
			job.setCompanyId(Integer.parseInt(cIds));
			// 从会话对象中获取当前登录用户标识
			//Applicant applicant = (Applicant) request.getSession().getAttribute("SESSION_APPLICANT");
			System.out.println("------------------");
			//System.out.println(applicant.getApplicantId());
			System.out.println("------------------");
			// 向数据库中添加当前用户的简历
			JobDAO dao = new JobDAO();
	        dao.add(job);
		/*		int basicinfoID = dao.add(job, applicant.getApplicantId());
				request.getSession().setAttribute("SESSION_RESUMEID", basicinfoID);*/
				// 操作成功，转向“主页”页面
				response.sendRedirect("managerIndex.jsp");
			}
		}

	/**
	 * 将请求的简历数据封装成一个对象
	 *
	 * @param request
	 * @return
	 * @throws
	 */
	private Job requestDataObj(HttpServletRequest request) {

		Job jobInfo = null;
		// 获得请求数据
		String jobName = request.getParameter("jobName");
		String jobSalary = request.getParameter("jobSalary");
		String jobArea = request.getParameter("jobArea");
	    String jobEnddate = request.getParameter("jobEnddate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date jobenddate = null;
		try {
			jobenddate = sdf.parse(jobEnddate);
		} catch (ParseException e) {
			jobenddate = null;
		}

		// 将请求数据封装成一个工作基本信息对象
		jobInfo = new Job(jobName, jobSalary,jobArea, jobenddate);
		return jobInfo;
	}
	}


