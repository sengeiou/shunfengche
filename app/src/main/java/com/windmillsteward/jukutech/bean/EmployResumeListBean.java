package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/16
 * 作者：xjh
 */

public class EmployResumeListBean extends BaseData {

    /**
     * totalRow : 2
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"salary_show":"25000元以上","work_year_name":"5-10年","sex":1,"is_view":0,"user_avatar_url":"http://p2.so.qhimgs1.com/t0153d90e2115165f42.jpg","city_area_name":"nullnull","job_name":"汽车销售","true_name":"哈哈","job_id":1,"education_name":"本科","resume_id":10,"age":0},{"salary_show":"面议","work_year_name":"无经验","sex":1,"is_view":0,"user_avatar_url":"http://abc.jpg","city_area_name":"四平市安徽省","job_name":"汽车销售","true_name":"许铭源","job_id":1,"education_name":"高中以下","resume_id":4,"age":0}]
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
         * salary_show : 25000元以上
         * work_year_name : 5-10年
         * sex : 1
         * is_view : 0
         * user_avatar_url : http://p2.so.qhimgs1.com/t0153d90e2115165f42.jpg
         * city_area_name : nullnull
         * job_name : 汽车销售
         * true_name : 哈哈
         * job_id : 1
         * education_name : 本科
         * resume_id : 10
         * age : 0
         */

        private String salary_show;
        private String work_year_name;
        private int sex;
        private int is_view;
        private String user_avatar_url;
        private String city_area_name;
        private String job_name;
        private String true_name;
        private int job_id;
        private String education_name;
        private int resume_id;
        private int age;

        public String getSalary_show() {
            return salary_show;
        }

        public void setSalary_show(String salary_show) {
            this.salary_show = salary_show;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getIs_view() {
            return is_view;
        }

        public void setIs_view(int is_view) {
            this.is_view = is_view;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getCity_area_name() {
            return city_area_name;
        }

        public void setCity_area_name(String city_area_name) {
            this.city_area_name = city_area_name;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public String getEducation_name() {
            return education_name;
        }

        public void setEducation_name(String education_name) {
            this.education_name = education_name;
        }

        public int getResume_id() {
            return resume_id;
        }

        public void setResume_id(int resume_id) {
            this.resume_id = resume_id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
