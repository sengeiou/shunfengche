package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/10
 * 作者：xjh
 */

public class ResumeListBean extends BaseData {

    /**
     * totalRow : 4
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"salary_show":"20000-25000元","expected_position":"随口一问","work_year_name":"3-5年","city_area_name":"北京市北京市","true_name":"哈哈","sex":2,"education_name":"本科","resume_id":11,"age":0,"user_avatar_url":"http://p2.so.qhimgs1.com/t0153d90e2115165f42.jpg"},{"salary_show":"25000元以上","expected_position":"偷摸","work_year_name":"5-10年","city_area_name":"nullnull","true_name":"哈哈","sex":1,"education_name":"本科","resume_id":10,"age":0,"user_avatar_url":"http://p2.so.qhimgs1.com/t0153d90e2115165f42.jpg"},{"salary_show":"1000元以下","expected_position":"android开发","work_year_name":"应届生","city_area_name":"四平市安徽省","true_name":"李元元","sex":2,"education_name":"高中","resume_id":6,"age":0,"user_avatar_url":"http://ab454c.jpg"},{"salary_show":"面议","expected_position":"java开发","work_year_name":"无经验","city_area_name":"四平市安徽省","true_name":"许铭源","sex":1,"education_name":"高中以下","resume_id":4,"age":0,"user_avatar_url":"http://abc.jpg"}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean lastPage;
    private boolean firstPage;
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

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
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
         * salary_show : 20000-25000元
         * expected_position : 随口一问
         * work_year_name : 3-5年
         * city_area_name : 北京市北京市
         * true_name : 哈哈
         * sex : 2
         * education_name : 本科
         * resume_id : 11
         * age : 0
         * user_avatar_url : http://p2.so.qhimgs1.com/t0153d90e2115165f42.jpg
         */

        private String salary_show;
        private String expected_position;
        private String work_year_name;
        private String city_area_name;
        private String true_name;
        private int sex;
        private String education_name;
        private int resume_id;
        private int age;
        private String user_avatar_url;
        private int is_show_match;
        private String match_str;

        public int getIs_show_match() {
            return is_show_match;
        }

        public void setIs_show_match(int is_show_match) {
            this.is_show_match = is_show_match;
        }

        public String getMatch_str() {
            return match_str;
        }

        public void setMatch_str(String match_str) {
            this.match_str = match_str;
        }

        public String getSalary_show() {
            return salary_show;
        }

        public void setSalary_show(String salary_show) {
            this.salary_show = salary_show;
        }

        public String getExpected_position() {
            return expected_position;
        }

        public void setExpected_position(String expected_position) {
            this.expected_position = expected_position;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public String getCity_area_name() {
            return city_area_name;
        }

        public void setCity_area_name(String city_area_name) {
            this.city_area_name = city_area_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }
    }
}
