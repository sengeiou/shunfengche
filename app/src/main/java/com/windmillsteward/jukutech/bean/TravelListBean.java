package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public class TravelListBean extends BaseData {


    /**
     * totalRow : 2
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 12
     * list : [{"area_name":"希腊长治市","travel_id":2,"cover_url":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2671632699,2186072093&fm=27&gp=0.jpg","start_price":15000,"title":"组团泰国一周游","travel_class_id":45},{"area_name":"元朗区海口市","travel_id":1,"cover_url":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2671632699,2186072093&fm=27&gp=0.jpg","start_price":1000,"title":"曼谷一日游","travel_class_id":47}]
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
         * area_name : 希腊长治市
         * travel_id : 2
         * cover_url : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2671632699,2186072093&fm=27&gp=0.jpg
         * start_price : 15000
         * title : 组团泰国一周游
         * travel_class_id : 45
         */

        private String area_name;
        private int travel_id;
        private String cover_url;
        private int start_price;
        private String title;
        private int travel_class_id;
        private String travel_class_name;

        public String getArea_name() {
            return area_name;
        }

        public String getTravel_class_name() {
            return travel_class_name;
        }

        public void setTravel_class_name(String travel_class_name) {
            this.travel_class_name = travel_class_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getTravel_id() {
            return travel_id;
        }

        public void setTravel_id(int travel_id) {
            this.travel_id = travel_id;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public int getStart_price() {
            return start_price;
        }

        public void setStart_price(int start_price) {
            this.start_price = start_price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTravel_class_id() {
            return travel_class_id;
        }

        public void setTravel_class_id(int travel_class_id) {
            this.travel_class_id = travel_class_id;
        }
    }
}
