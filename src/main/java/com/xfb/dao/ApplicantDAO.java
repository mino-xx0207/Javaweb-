package com.xfb.dao;


import com.xfb.utils.DBUtils;

import java.sql.*;

public class ApplicantDAO {

    /**
     * 验证Email是否已被注册
     *
     * @return
     */
    public boolean isExistEmail(String email) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_applicant WHERE applicant_email=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close( conn, pstmt,rs);
        }
        return false;
    }

    /**
     * 求职者信息注册保存
     *
     * @param email
     * @param password
     */
    public void save(String email, String password,String authority) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO tb_applicant(applicant_email,applicant_pwd,applicant_registdate,applicant_authority) VALUES(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(4,authority);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,null );
        }
    }



    /**
     * 注册用户登录
     *
     * @param email
     * @param password
     * @return
     */
    public int login(String email, String password) {
        int applicantID = 0;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT applicant_id FROM tb_applicant WHERE applicant_email=? and applicant_pwd=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                applicantID = rs.getInt("applicant_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return applicantID;
    }

    /**
     * 判断是否已有简历
     *
     * @return
     */
    public int isExistResume(int applicantID) {
        int resumeID = 0;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT basicinfo_id FROM tb_resume_basicinfo WHERE applicant_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, applicantID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                resumeID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return resumeID;
    }

    public String getAuthority(int applicantId) {
        String applicantAuthority=null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT applicant_authority FROM tb_applicant WHERE applicant_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, applicantId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                applicantAuthority= rs.getString("applicant_authority");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt,rs );
        }
        return applicantAuthority;
    }
}
