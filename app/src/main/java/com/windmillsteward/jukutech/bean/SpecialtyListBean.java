package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class SpecialtyListBean extends BaseData {


    /**
     * totalRow : 1
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"commodity_title":"正宗农家咸鸭蛋","commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_reserve_price":188,"third_area_name":"天河区","third_area_id":3040}]
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
         * commodity_title : 正宗农家咸鸭蛋
         * commodity_id : 1
         * commodity_cover_picture : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg
         * commodity_reserve_price : 188
         * third_area_name : 天河区
         * third_area_id : 3040
         */

        private String commodity_title;
        private int commodity_id;
        private String commodity_cover_picture;
        private String commodity_reserve_price;
        private String third_area_name;
        private int third_area_id;

        public String getCommodity_title() {
            return commodity_title;
        }

        public void setCommodity_title(String commodity_title) {
            this.commodity_title = commodity_title;
        }

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getCommodity_cover_picture() {
            return commodity_cover_picture;
        }

        public void setCommodity_cover_picture(String commodity_cover_picture) {
            this.commodity_cover_picture = commodity_cover_picture;
        }

        public String getCommodity_reserve_price() {
            return commodity_reserve_price;
        }

        public void setCommodity_reserve_price(String commodity_reserve_price) {
            this.commodity_reserve_price = commodity_reserve_price;
        }

        public String getThird_area_name() {
            return third_area_name;
        }

        public void setThird_area_name(String third_area_name) {
            this.third_area_name = third_area_name;
        }

        public int getThird_area_id() {
            return third_area_id;
        }

        public void setThird_area_id(int third_area_id) {
            this.third_area_id = third_area_id;
        }
    }
}
