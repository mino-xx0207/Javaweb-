package com.xfb.listener;

/**
 * @description:
 * @author: xfb
 * @time: 2020/11/15 22:17
 */

import com.xfb.dao.CompanyDAO;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 企业信息浏览次数统计监听器
 *
 */
@WebListener
public class CompanyViewCountListener implements ServletRequestListener {

    public CompanyViewCountListener() {

    }

    public void requestDestroyed(ServletRequestEvent sre) {

    }

    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre
                .getServletRequest();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString() == null ? "" : request
                .getQueryString();
        // 判断是否是向企业处理Servlet发出的请求，并且含有表示企业信息查看的请求参数
        if (requestURI.indexOf("CompanyServlet") >= 0
                && (queryString.indexOf("select") >= 0)) {
            // 从请求字符串参数中获取企业编号
            int id = Integer.parseInt(queryString.substring(queryString
                    .lastIndexOf('=') + 1));
            // 更新此企业信息的浏览次数
            CompanyDAO dao = new CompanyDAO();
            try {
                dao.updateCompanyViewCount(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
