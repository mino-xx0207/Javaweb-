package com.xfb.dao;

import com.xfb.bean.Company;
import com.xfb.bean.Job;
import com.xfb.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    /**
     * 查询所有正在招聘中的企业信息以及该企业的最新职位信息
     *
     * @return
     */
    public List<Company> getCompanyList() {
        List<Company> list = new ArrayList<Company>();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT tb_company.company_id,company_pic,job_id,job_name,job_salary,job_area,job_endtime "
                    + "FROM tb_company "
                    + "LEFT OUTER JOIN tb_job ON tb_job.company_id=tb_company.company_id "
                    + "WHERE company_state=1 and job_id IN ("
                    + "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
                    + ")";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                Job job = new Job();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyPic(rs.getString("company_pic"));
                job.setJobId(rs.getInt("job_id"));
                job.setJobName(rs.getString("job_name"));
                job.setJobSalary(rs.getString("job_salary"));
                job.setJobArea(rs.getString("job_area"));
                job.setJobEnddate(rs.getTimestamp("job_endtime"));
                company.getJobs().add(job);
                list.add(company);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据企业标识查询企业详情
     *
     * @param companyID
     * @return
     */
    public Company getCompanyByID(String companyID) {
        Company company = new Company();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM tb_company WHERE company_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(companyID));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyArea(rs.getString("company_area"));
                company.setCompanyBrief(rs.getString("company_brief"));
                company.setCompanyPic(rs.getString("company_pic"));
                company.setCompanySize(rs.getString("company_size"));
                company.setCompanyType(rs.getString("company_type"));
                company.setCompanyViewnum(rs.getInt("company_viewnum"));
                company.setCompayName(rs.getString("company_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs);
        }
        return company;
    }

    /**
     * 分页查询首页所需要的所有企业信息及职位信息
     * @return
     */
    public List<Company> getCompanyPageList(int pageNo,int pageSize) {
        // 定义本页记录索引值
        int firstIndex = pageSize * (pageNo-1);
        List<Company> list = new ArrayList<Company>();
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (connection == null) {
            return null;
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection
                    .prepareStatement("SELECT b.* FROM " +
                            "( SELECT a.*, @rownum := @rownum + 1 rn FROM " +
                            "( SELECT tb_company.company_id, company_pic, job_id, job_name, job_salary, job_area, job_endtime, @rownum := 0 " +
                            "FROM tb_company " +
                            "LEFT OUTER JOIN tb_job " +
                            "ON tb_company.company_id = tb_job.company_id " +
                            "WHERE company_state = 1 AND job_id IN ( SELECT MAX( job_id ) FROM tb_job WHERE job_state = 1 GROUP BY company_id ) ) a ) b " +
                            "WHERE rn <= ? AND rn >?");
            pstmt.setInt(1, firstIndex+pageSize);
            pstmt.setInt(2, firstIndex);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                Job job = new Job();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyPic(rs.getString("company_pic"));
                job.setJobId(rs.getInt("job_id"));
                job.setJobName(rs.getString("job_name"));
                job.setJobSalary(rs.getString("job_salary"));
                job.setJobArea(rs.getString("job_area"));
                job.setJobEnddate(rs.getTimestamp("job_endtime"));
                company.getJobs().add(job);
                list.add(company);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close( connection, pstmt,rs);
        }
        return list;
    }

    /**
     * 查询所需分页的总记录数
     * @return
     */
    public int getRecordCount() {
        int recordCount = 0;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT count(*) FROM tb_company "
                    + "LEFT OUTER JOIN tb_job ON tb_job.company_id=tb_company.company_id "
                    + "WHERE company_state=1 and job_id IN ("
                    + "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
                    + ")";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                recordCount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt, rs);
        }
        return recordCount;
    }
    /**
     * 更新企业的浏览次数
     * @param id
     */
    public void updateCompanyViewCount(int id) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE tb_company "
                    + "SET company_viewnum=company_viewnum+1 "
                    + "WHERE company_id=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close( conn, pstmt,rs);
        }
    }
    //更新照片
    public void updateCompanyPic(int companyId, String newFileName) {
        String sql = "UPDATE tb_company SET company_pic=? WHERE company_id=?";
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newFileName);
            pstmt.setInt(2, companyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,null );
        }
    }
    public void add(String company_pic) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO tb_company(company_name,company_size,company_brief,company_pic) VALUES(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, null);
            pstmt.setString(2, null);
            pstmt.setTimestamp(3, null);
            pstmt.setString(4,company_pic);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,null );
        }
    }



}
