package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/2
 * 作者：xjh
 */

public class MyOrderBean extends BaseData {


    /**
     * totalRow : 2
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 20
     * list : [{"type_name":"智慧家庭","order_detail_status":2,"relate_id":1,"detail_status_name":"已确认","order_id":1,"order_type":41,"order_name":"智慧家庭 溜猫"},{"type_name":"住宿旅游","order_detail_status":1,"relate_id":5,"detail_status_name":"已入住","order_id":2,"order_type":61,"order_name":"黄埔大酒店 标准大床房"}]
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
         * type_name : 智慧家庭
         * order_detail_status : 2
         * relate_id : 1
         * detail_status_name : 已确认
         * order_id : 1
         * order_type : 41
         * order_name : 智慧家庭 溜猫
         */

        private String type_name;
        private int order_detail_status;
        private int relate_id;
        private String detail_status_name;
        private int order_id;
        private int order_type;
        private String order_name;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public int getOrder_detail_status() {
            return order_detail_status;
        }

        public void setOrder_detail_status(int order_detail_status) {
            this.order_detail_status = order_detail_status;
        }

        public int getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(int relate_id) {
            this.relate_id = relate_id;
        }

        public String getDetail_status_name() {
            return detail_status_name;
        }

        public void setDetail_status_name(String detail_status_name) {
            this.detail_status_name = detail_status_name;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }
    }
}
