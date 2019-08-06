package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/9
 * 作者：xjh
 */

public class PositionListBean extends BaseData {

    /**
     * totalRow : 2
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"salary_show":"无经验","job_name":"汽车技工","job_id":3,"third_area_name":"河北省","company_name":"观海科技公司","second_area_name":"天津市","benefit_names":["五险一金","租房补贴","话费补贴"],"title":"招聘一名技工X1"},{"salary_show":"无经验","job_name":"汽车销售","job_id":1,"third_area_name":"阿坝县","company_name":"","second_area_name":"威海市","benefit_names":["五险一金","租房补贴"],"title":"招聘汽车销售"}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
    private int totalPage;
    private int pageSize;
    private List<ListBean> list;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * salary_show : 无经验
         * job_name : 汽车技工
         * job_id : 3
         * third_area_name : 河北省
         * company_name : 观海科技公司
         * second_area_name : 天津市
         * benefit_names : ["五险一金","租房补贴","话费补贴"]
         * title : 招聘一名技工X1
         */

        private String salary_show;
        private String job_name;
        private int job_id;
        private String third_area_name;
        private String company_name;
        private String second_area_name;
        private String title;
        private List<String> benefit_names;

        public String getSalary_show() {
            return salary_show;
        }

        public void setSalary_show(String salary_show) {
            this.salary_show = salary_show;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public String getThird_area_name() {
            return third_area_name;
        }

        public void setThird_area_name(String third_area_name) {
            this.third_area_name = third_area_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getSecond_area_name() {
            return second_area_name;
        }

        public void setSecond_area_name(String second_area_name) {
            this.second_area_name = second_area_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getBenefit_names() {
            return benefit_names;
        }

        public void setBenefit_names(List<String> benefit_names) {
            this.benefit_names = benefit_names;
        }
    }
}
