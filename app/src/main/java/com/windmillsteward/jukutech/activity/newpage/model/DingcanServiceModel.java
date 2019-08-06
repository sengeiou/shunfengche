package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

public class DingcanServiceModel {

    /**
     * totalRow : 1
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"attribute_ids":"215,216","score":"4","star":3,"service_name":"广东软件园饭堂","review":502,"price":12,"service_id":1,"attributeList":["7折用餐","返现"],"juli":24379,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8904731558690363283640x1136%201.jpg","add_time":1548578406}]
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
         * attribute_ids : 215,216
         * score : 4
         * star : 3
         * service_name : 广东软件园饭堂
         * review : 502
         * price : 12
         * service_id : 1
         * attributeList : ["7折用餐","返现"]
         * juli : 24379
         * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8904731558690363283640x1136%201.jpg
         * add_time : 1548578406
         */

        private String attribute_ids;
        private String description;
        private String score;
        private int star;
        private String service_name;
        private int review;
        private String price;
        private int service_id;
        private int juli;
        private String pic_url;
        private String service_url;
        private int add_time;
        private List<String> attributeList;

        public String getService_url() {
            return service_url;
        }

        public void setService_url(String service_url) {
            this.service_url = service_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAttribute_ids() {
            return attribute_ids;
        }

        public void setAttribute_ids(String attribute_ids) {
            this.attribute_ids = attribute_ids;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public int getReview() {
            return review;
        }

        public void setReview(int review) {
            this.review = review;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getService_id() {
            return service_id;
        }

        public void setService_id(int service_id) {
            this.service_id = service_id;
        }

        public int getJuli() {
            return juli;
        }

        public void setJuli(int juli) {
            this.juli = juli;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public List<String> getAttributeList() {
            return attributeList;
        }

        public void setAttributeList(List<String> attributeList) {
            this.attributeList = attributeList;
        }
    }
}
