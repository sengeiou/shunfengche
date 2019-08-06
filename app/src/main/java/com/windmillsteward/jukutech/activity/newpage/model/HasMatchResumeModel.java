package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/17
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 已匹配到的简历
 */
public class HasMatchResumeModel {

    /**
     * totalRow : 1
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"work_third_area_name":"天河区","labor_recruitment_info_name":"水泥工","contact_person":"","sex":2,"latitude":"113.4064504678","relate_id":16,"name":"啊超","contact_tel":"13727574859","work_second_area_name":"广州市","longitude":"23.1199587650"}]
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
         * work_third_area_name : 天河区
         * labor_recruitment_info_name : 水泥工
         * contact_person :
         * sex : 2
         * latitude : 113.4064504678
         * relate_id : 16
         * name : 啊超
         * contact_tel : 13727574859
         * work_second_area_name : 广州市
         * longitude : 23.1199587650
         */

        //公共
        private int sex;
        private int relate_id;

        //劳工 特种工
        private String work_third_area_name;
        private String labor_recruitment_info_name;
        private String contact_person;
        private String latitude;
        private String name;
        private String contact_tel;
        private String work_second_area_name;
        private String longitude;
        private String user_avatar_url;
        /**
         * work_third_area_id : 3040
         * area_name : 广州市天河区
         * require_id : 1
         * sex_name : 男
         * user_id : 1
         * user_name : 方小小
         * work_second_area_id : 289
         * match_value : 80
         * add_time : 0
         */

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(int relate_id) {
            this.relate_id = relate_id;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getLabor_recruitment_info_name() {
            return labor_recruitment_info_name;
        }

        public void setLabor_recruitment_info_name(String labor_recruitment_info_name) {
            this.labor_recruitment_info_name = labor_recruitment_info_name;
        }

        public String getContact_person() {
            return contact_person;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact_tel() {
            return contact_tel;
        }

        public void setContact_tel(String contact_tel) {
            this.contact_tel = contact_tel;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }
    }
}
