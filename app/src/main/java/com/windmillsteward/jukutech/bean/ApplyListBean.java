package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class ApplyListBean extends BaseData {


    /**
     * totalRow : 1
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 15
     * list : [{"salary_show":"面议","work_year_name":"无经验","city_area_name":"四平市安徽省","true_name":"许铭源","job_id":3,"sex":1,"is_view":0,"education_name":"高中以下","age":0,"user_avatar_url":"http://abc.jpg"}]
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
         * salary_show : 面议
         * work_year_name : 无经验
         * city_area_name : 四平市安徽省
         * true_name : 许铭源
         * job_id : 3
         * sex : 1
         * is_view : 0
         * education_name : 高中以下
         * age : 0
         * user_avatar_url : http://abc.jpg
         */

        private String salary_show;
        private String work_year_name;
        private String city_area_name;
        private String true_name;
        private int job_id;
        private int sex;
        private int is_view;
        private String education_name;
        private int age;
        private String user_avatar_url;

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

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
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

        public String getEducation_name() {
            return education_name;
        }

        public void setEducation_name(String education_name) {
            this.education_name = education_name;
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
