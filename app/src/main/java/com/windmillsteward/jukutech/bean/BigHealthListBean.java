package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public class BigHealthListBean extends BaseData {

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

//        {
//            "health_id":1,
//                "cover_url":"http://jk-arbitration.oss-cn-shenzhen.aliyuncs.com/6472981531986541737ZC%E7%88%B1%E6%96%B0%E8%A7%89%E7%BD%97.png",
//                "second_title":"大健康标题2",
//                "price":8000,
//                "title":"大健康标题1",
//                "add_time":1531984041
//        }

        private int health_id;
        private double price;
        private int add_time;
        private String cover_url;
        private String second_title;
        private String title;

        public int getHealth_id() {
            return health_id;
        }

        public void setHealth_id(int health_id) {
            this.health_id = health_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getSecond_title() {
            return second_title;
        }

        public void setSecond_title(String second_title) {
            this.second_title = second_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
