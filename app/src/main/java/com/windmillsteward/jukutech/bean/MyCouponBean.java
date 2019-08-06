package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;
import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/5
 * 作者：cyq
 */

public class MyCouponBean extends BaseData {


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

    public static class ListBean implements MultiItemEntity {

        /**
         * start_time : 1520042770
         * coupon_money : 5
         * coupon_name : 测试的优惠券
         * receive_id : 1
         * coupon_status : 0优惠状态【0：未使用，1：已使用，2：已过期】
         */
        private int start_time;
        private int end_time;
        private double coupon_money;
        private String coupon_name;
        private String share_url;
        private int receive_id;
        private int coupon_status;
        private boolean isSelect;
        private int type;
        private double money;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public double getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(double coupon_money) {
            this.coupon_money = coupon_money;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public int getReceive_id() {
            return receive_id;
        }

        public void setReceive_id(int receive_id) {
            this.receive_id = receive_id;
        }

        public int getCoupon_status() {
            return coupon_status;
        }

        public void setCoupon_status(int coupon_status) {
            this.coupon_status = coupon_status;
        }

        @Override
        public int getItemType() {
            return type;
        }
    }
}
