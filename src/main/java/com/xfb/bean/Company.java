package com.xfb.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 企业信息实体类
 *
 * @author QST青软实训
 *
 */
public class Company {
    // 企业标识
    private int companyId;
    // 企业名称
    private String compayName;
    // 企业所在地区
    private String companyArea;
    // 企业规模
    private String companySize;
    // 企业性质
    private String companyType;
    // 企业简介
    private String companyBrief;
    // 招聘状态:1招聘中 2已暂停 3已结束
    private int companyState;
    // 排序序号
    private int comanySort;
    // 浏览数
    private int companyViewnum;
    // 宣传图片
    private String companyPic;
    // 职位
    private Set<Job> jobs = new HashSet<Job>();

    public Company() {
        super();
    }

    public Company(String compayName, String companyArea, String companySize, String companyType, String companyBrief, int companyState, int comanySort, int companyViewnum, String companyPic) {
        this.compayName = compayName;
        this.companyArea = companyArea;
        this.companySize = companySize;
        this.companyType = companyType;
        this.companyBrief = companyBrief;
        this.companyState = companyState;
        this.comanySort = comanySort;
        this.companyViewnum = companyViewnum;
        this.companyPic = companyPic;
    }

    public Company(int companyId, String compayName, String companyArea,
                   String companySize, String companyType, String companyBrief,
                   int companyState, int comanySort, int companyViewnum,
                   String companyPic) {
        super();
        this.companyId = companyId;
        this.compayName = compayName;
        this.companyArea = companyArea;
        this.companySize = companySize;
        this.companyType = companyType;
        this.companyBrief = companyBrief;
        this.companyState = companyState;
        this.comanySort = comanySort;
        this.companyViewnum = companyViewnum;
        this.companyPic = companyPic;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompayName() {
        return compayName;
    }

    public void setCompayName(String compayName) {
        this.compayName = compayName;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyBrief() {
        return companyBrief;
    }

    public void setCompanyBrief(String companyBrief) {
        this.companyBrief = companyBrief;
    }

    public int getCompanyState() {
        return companyState;
    }

    public void setCompanyState(int companyState) {
        this.companyState = companyState;
    }

    public int getComanySort() {
        return comanySort;
    }

    public void setComanySort(int comanySort) {
        this.comanySort = comanySort;
    }

    public int getCompanyViewnum() {
        return companyViewnum;
    }

    public void setCompanyViewnum(int companyViewnum) {
        this.companyViewnum = companyViewnum;
    }

    public String getCompanyPic() {
        return companyPic;
    }

    public void setCompanyPic(String companyPic) {
        this.companyPic = companyPic;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

}
