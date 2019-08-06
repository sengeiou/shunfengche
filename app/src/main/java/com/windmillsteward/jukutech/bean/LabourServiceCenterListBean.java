package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/9
 * 作者：xjh
 */

public class LabourServiceCenterListBean extends BaseData {

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
         * work_third_area_name : 天河区
         * end_salary : 8000
         * job_name : Android
         * contact_person : 强哥
         * labor_intermediary_id : 1
         * title : 招聘安卓开发一枚
         * start_salary : 5000
         * work_second_area_name : 广州市
         * is_application : 1
         */

        private String work_third_area_name;//工作区域(天河区)
        private int end_salary;//结束薪水
        private String job_name;//职位名称
        private String contact_person;//自我介绍
        private int labor_intermediary_id;//劳务中介id
        private String title;//标题
        private int start_salary;//起始薪水
        private String work_second_area_name;//工作区域(广州市)
        private int is_application;// 是否申请过 0没有 1有

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public int getEnd_salary() {
            return end_salary;
        }

        public void setEnd_salary(int end_salary) {
            this.end_salary = end_salary;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getContact_person() {
            return contact_person;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
        }

        public int getLabor_intermediary_id() {
            return labor_intermediary_id;
        }

        public void setLabor_intermediary_id(int labor_intermediary_id) {
            this.labor_intermediary_id = labor_intermediary_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStart_salary() {
            return start_salary;
        }

        public void setStart_salary(int start_salary) {
            this.start_salary = start_salary;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public int getIs_application() {
            return is_application;
        }

        public void setIs_application(int is_application) {
            this.is_application = is_application;
        }
    }
}
