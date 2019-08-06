package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class AfterSealsListBean extends BaseData {


    /**
     * totalRow : 2
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"logistics_single_number":"","record_id":3,"commodity_title":"正宗农家咸鸭蛋","commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg","commodity_price":123,"price":123,"order_total_fee":123,"commodity_num":1,"order_sn":"2018041803360353952289","commodity_model_name":"不知道","status":1},{"logistics_single_number":"","record_id":2,"commodity_title":"正宗农家咸鸭蛋","commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg","commodity_price":123,"price":123,"order_total_fee":123,"commodity_num":1,"order_sn":"2018041803360353952289","commodity_model_name":"不知道","status":1}]
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
         * logistics_single_number :
         * record_id : 3
         * commodity_title : 正宗农家咸鸭蛋
         * commodity_cover_picture : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg
         * commodity_price : 123
         * price : 123
         * order_total_fee : 123
         * commodity_num : 1
         * order_sn : 2018041803360353952289
         * commodity_model_name : 不知道
         * status : 1
         */

        private String logistics_single_number;
        private int record_id;
        private String commodity_title;
        private String commodity_cover_picture;
        private String commodity_price;
        private String price;
        private String order_total_fee;
        private int commodity_num;
        private String order_sn;
        private String commodity_model_name;
        private int status;

        public String getLogistics_single_number() {
            return logistics_single_number;
        }

        public void setLogistics_single_number(String logistics_single_number) {
            this.logistics_single_number = logistics_single_number;
        }

        public int getRecord_id() {
            return record_id;
        }

        public void setRecord_id(int record_id) {
            this.record_id = record_id;
        }

        public String getCommodity_title() {
            return commodity_title;
        }

        public void setCommodity_title(String commodity_title) {
            this.commodity_title = commodity_title;
        }

        public String getCommodity_cover_picture() {
            return commodity_cover_picture;
        }

        public void setCommodity_cover_picture(String commodity_cover_picture) {
            this.commodity_cover_picture = commodity_cover_picture;
        }

        public String getCommodity_price() {
            return commodity_price;
        }

        public void setCommodity_price(String commodity_price) {
            this.commodity_price = commodity_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrder_total_fee() {
            return order_total_fee;
        }

        public void setOrder_total_fee(String order_total_fee) {
            this.order_total_fee = order_total_fee;
        }

        public int getCommodity_num() {
            return commodity_num;
        }

        public void setCommodity_num(int commodity_num) {
            this.commodity_num = commodity_num;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getCommodity_model_name() {
            return commodity_model_name;
        }

        public void setCommodity_model_name(String commodity_model_name) {
            this.commodity_model_name = commodity_model_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
