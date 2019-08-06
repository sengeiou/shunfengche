package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class CapitalListBean extends BaseData {


    /**
     * totalRow : 1
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"capital_id":44,"product_type":202,"product_type_name":"养老保障","yield_rate":9,"title":"京安45天周期盈利~","deadline":60}]
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
         * capital_id : 44
         * product_type : 202
         * product_type_name : 养老保障
         * yield_rate : 9
         * title : 京安45天周期盈利~
         * deadline : 60
         */

        private int capital_id;
        private int product_type;
        private String product_type_name;
        private String yield_rate;
        private String title;
        private int deadline;
        private String capital_logo;
        private String minimum_amount;

        public String getMinimum_amount() {
            return minimum_amount;
        }

        public void setMinimum_amount(String minimum_amount) {
            this.minimum_amount = minimum_amount;
        }

        public String getCapital_logo() {

            return capital_logo;
        }

        public void setCapital_logo(String capital_logo) {
            this.capital_logo = capital_logo;
        }

        public int getCapital_id() {
            return capital_id;
        }

        public void setCapital_id(int capital_id) {
            this.capital_id = capital_id;
        }

        public int getProduct_type() {
            return product_type;
        }

        public void setProduct_type(int product_type) {
            this.product_type = product_type;
        }

        public String getProduct_type_name() {
            return product_type_name;
        }

        public void setProduct_type_name(String product_type_name) {
            this.product_type_name = product_type_name;
        }

        public String getYield_rate() {
            return yield_rate;
        }

        public void setYield_rate(String yield_rate) {
            this.yield_rate = yield_rate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDeadline() {
            return deadline;
        }

        public void setDeadline(int deadline) {
            this.deadline = deadline;
        }
    }
}
