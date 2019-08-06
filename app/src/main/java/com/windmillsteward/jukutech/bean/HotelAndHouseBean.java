package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class HotelAndHouseBean extends BaseData {


    /**
     * totalRow : 2
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 15
     * list : [{"cover_url":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=152335851,3517511161&fm=27&gp=0.jpg","hotel_type_name":"五星/豪华","third_area_name":"河北省","hotel_id":4,"second_area_name":"天津市","start_price":0,"hotel_name":"广州黄埔大酒店"},{"cover_url":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=152335851,3517511161&fm=27&gp=0.jpg","hotel_type_name":"五星/豪华","third_area_name":"河北省","hotel_id":5,"second_area_name":"天津市","start_price":0,"hotel_name":"广州黄埔大酒店"}]
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
         * cover_url : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=152335851,3517511161&fm=27&gp=0.jpg
         * hotel_type_name : 五星/豪华
         * third_area_name : 河北省
         * hotel_id : 4
         * second_area_name : 天津市
         * start_price : 0
         * hotel_name : 广州黄埔大酒店
         */

        private String cover_url;
        private String hotel_type_name;
        private String third_area_name;
        private int hotel_id;
        private String second_area_name;
        private String start_price;
        private String hotel_name;

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getHotel_type_name() {
            return hotel_type_name;
        }

        public void setHotel_type_name(String hotel_type_name) {
            this.hotel_type_name = hotel_type_name;
        }

        public String getThird_area_name() {
            return third_area_name;
        }

        public void setThird_area_name(String third_area_name) {
            this.third_area_name = third_area_name;
        }

        public int getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(int hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getSecond_area_name() {
            return second_area_name;
        }

        public void setSecond_area_name(String second_area_name) {
            this.second_area_name = second_area_name;
        }

        public String getStart_price() {
            return start_price;
        }

        public void setStart_price(String start_price) {
            this.start_price = start_price;
        }

        public String getHotel_name() {
            return hotel_name;
        }

        public void setHotel_name(String hotel_name) {
            this.hotel_name = hotel_name;
        }
    }
}
