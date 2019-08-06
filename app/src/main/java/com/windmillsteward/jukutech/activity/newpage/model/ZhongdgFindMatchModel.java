package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/27
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class ZhongdgFindMatchModel {
    /**
     * totalRow : 1
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"work_third_area_name":"天河区","service_name":"打扫卫生","hour_matching_id":1,"sex":1,"latitude":"113.4064504678","name":"钟点工-啊超","contact_tel":"13727574858","work_second_area_name":"广州市","user_avatar_url":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/090263152153268100220180320_035759.jpg","longitude":"23.1199587650"}]
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
         * service_name : 打扫卫生
         * hour_matching_id : 1
         * sex : 1
         * latitude : 113.4064504678
         * name : 钟点工-啊超
         * contact_tel : 13727574858
         * work_second_area_name : 广州市
         * user_avatar_url : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/090263152153268100220180320_035759.jpg
         * longitude : 23.1199587650
         */

        private String work_third_area_name;
        private String service_name;
        private int hour_matching_id;
        private int sex;
        private String latitude;
        private String name;
        private String contact_tel;
        private String work_second_area_name;
        private String user_avatar_url;
        private String longitude;

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public int getHour_matching_id() {
            return hour_matching_id;
        }

        public void setHour_matching_id(int hour_matching_id) {
            this.hour_matching_id = hour_matching_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
