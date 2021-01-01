package com.xfb.dao;

import com.xfb.bean.Company;
import com.xfb.bean.Job;
import com.xfb.bean.ResumeBasicinfo;
import com.xfb.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JobDAO {

	public List<Job> getJobListByCompanyID(String companyID) {
		List<Job> list = new ArrayList<Job>();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tb_job WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(companyID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		return list;
	}

	public Job getJobByID(String jobid) {
		Job job = new Job();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT tb_job.*,company_pic "
					+ "FROM tb_job "
					+ "INNER JOIN tb_company on tb_job.company_id =  tb_company.company_id "
					+ "WHERE job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(jobid));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobHiringnum(rs.getInt("job_hiringnum"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobDesc(rs.getString("job_desc"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				job.setJobState(rs.getInt("job_state"));

				job.setCompanyId(rs.getInt("company_id"));


			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		return job;
	}

	public void update(Job jobInfo, int id) {

		String sql = "UPDATE tb_job "
				+ "SET job_name=?, job_salary=?,job_area=?,job_endtime=?"
				+ "WHERE job_id=?";
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jobInfo.getJobName());
			pstmt.setString(2, jobInfo.getJobSalary());
			pstmt.setString(3, jobInfo.getJobArea());
			pstmt.setTimestamp(4, jobInfo.getJobEnddate() == null ? null
					: new Timestamp(jobInfo.getJobEnddate().getTime()));
			pstmt.setInt(5, jobInfo.getJobId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, null);
		}
	}

	public void deleteJobByID(Job jobInfo) {
		String sql = "delete from tb_job where job_id=?";
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jobInfo.getJobId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, null);
		}
	}
	public void add(Job jobInfo) {
		String sql = "INSERT INTO tb_job(company_id,job_name,job_salary,job_area,job_endtime,job_state) VALUES(?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		try {
			// 关闭自动提交
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jobInfo.getCompanyId());
			pstmt.setString(2, jobInfo.getJobName());
			pstmt.setString(3, jobInfo.getJobSalary());
			pstmt.setString(4, jobInfo.getJobArea());
			pstmt.setTimestamp(5, jobInfo.getJobEnddate()== null ? null
					: new Timestamp(jobInfo.getJobEnddate().getTime()));
			pstmt.setInt(6, jobInfo.getJobState());
			pstmt.executeUpdate();
			pstmt.close();
			/*// 获取当前生成的简历标识
			String sql2 = "SELECT BASICINFO_ID FROM tb_resume_basicinfo where APPLICANT_ID=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1,applicantID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				basicinfoID = rs.getInt(1);
			}
			// 事务提交*/
			conn.commit();
		} catch (SQLException e) {
			try {
				// 事务回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt,null );
		}
		/*return basicinfoID;*/
	}
}



