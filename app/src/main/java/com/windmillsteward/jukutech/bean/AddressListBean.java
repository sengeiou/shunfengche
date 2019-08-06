package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddressListBean extends BaseData {

    /**
     * totalRow : 1
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"address":"石牌东路","delete_time":0,"first_area_id":19,"user_name":"大海","address_id":7,"second_area_id":289,"is_default":1,"third_area_id":3040,"update_time":1524019041,"user_id":12,"mobile_phone":"18380496276","four_area_id":0,"total_address_name":"广东省广州市天河区null石牌东路","postal_code":"","add_time":1524019041}]
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
         * address : 石牌东路
         * delete_time : 0
         * first_area_id : 19
         * user_name : 大海
         * address_id : 7
         * second_area_id : 289
         * is_default : 1
         * third_area_id : 3040
         * update_time : 1524019041
         * user_id : 12
         * mobile_phone : 18380496276
         * four_area_id : 0
         * total_address_name : 广东省广州市天河区null石牌东路
         * postal_code :
         * add_time : 1524019041
         */

        private String address;
        private int delete_time;
        private int first_area_id;
        private String first_area_name;
        private String user_name;
        private int address_id;
        private int second_area_id;
        private String second_area_name;
        private int is_default;
        private int third_area_id;
        private String third_area_name;
        private int update_time;
        private int user_id;
        private String mobile_phone;
        private int four_area_id;
        private String total_address_name;
        private String postal_code;
        private int add_time;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getFirst_area_name() {
            return first_area_name;
        }

        public void setFirst_area_name(String first_area_name) {
            this.first_area_name = first_area_name;
        }

        public String getSecond_area_name() {
            return second_area_name;
        }

        public void setSecond_area_name(String second_area_name) {
            this.second_area_name = second_area_name;
        }

        public String getThird_area_name() {
            return third_area_name;
        }

        public void setThird_area_name(String third_area_name) {
            this.third_area_name = third_area_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(int delete_time) {
            this.delete_time = delete_time;
        }

        public int getFirst_area_id() {
            return first_area_id;
        }

        public void setFirst_area_id(int first_area_id) {
            this.first_area_id = first_area_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getSecond_area_id() {
            return second_area_id;
        }

        public void setSecond_area_id(int second_area_id) {
            this.second_area_id = second_area_id;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public int getThird_area_id() {
            return third_area_id;
        }

        public void setThird_area_id(int third_area_id) {
            this.third_area_id = third_area_id;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public int getFour_area_id() {
            return four_area_id;
        }

        public void setFour_area_id(int four_area_id) {
            this.four_area_id = four_area_id;
        }

        public String getTotal_address_name() {
            return total_address_name;
        }

        public void setTotal_address_name(String total_address_name) {
            this.total_address_name = total_address_name;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }
    }
}
