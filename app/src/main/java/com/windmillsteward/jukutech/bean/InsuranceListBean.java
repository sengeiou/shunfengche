package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public class InsuranceListBean extends BaseData {

    /**
     * totalRow : 2
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 5
     * list : [{"insurance_id":45,"insurance_type":174,"company_name":"平安保险（广州天河分部）","title":"想买保险的联系我,价格实惠","insurance_type_name":"人寿保险"},{"insurance_id":44,"insurance_type":173,"company_name":"平安保险","title":"卖保险咯","insurance_type_name":"健康保险"}]
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
         * insurance_id : 45
         * insurance_type : 174
         * company_name : 平安保险（广州天河分部）
         * title : 想买保险的联系我,价格实惠
         * insurance_type_name : 人寿保险
         */

        private int insurance_id;
        private int insurance_type;
        private String company_name;
        private String title;
        private String insurance_type_name;

        public int getInsurance_id() {
            return insurance_id;
        }

        public void setInsurance_id(int insurance_id) {
            this.insurance_id = insurance_id;
        }

        public int getInsurance_type() {
            return insurance_type;
        }

        public void setInsurance_type(int insurance_type) {
            this.insurance_type = insurance_type;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInsurance_type_name() {
            return insurance_type_name;
        }

        public void setInsurance_type_name(String insurance_type_name) {
            this.insurance_type_name = insurance_type_name;
        }
    }
}
