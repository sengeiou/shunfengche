package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class RentCarListBean extends BaseData {

    /**
     * totalRow : 1
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 5
     * list : [{"departure_place_name":"","car_rent_id":1,"go_off":1516263897,"unoccupied_num":3,"vehicle_module_name":"SUV","contact_tel":"13790097063","destination_place_name":"","type":1}]
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
         * departure_place_name :
         * car_rent_id : 1
         * go_off : 1516263897
         * unoccupied_num : 3
         * vehicle_module_name : SUV
         * contact_tel : 13790097063
         * destination_place_name :
         * type : 1
         */

        private String departure_place_name;
        private int car_rent_id;
        private int go_off;
        private int unoccupied_num;
        private String vehicle_module_name;
        private String contact_tel;
        private String destination_place_name;
        private int type;

        public String getDeparture_place_name() {
            return departure_place_name;
        }

        public void setDeparture_place_name(String departure_place_name) {
            this.departure_place_name = departure_place_name;
        }

        public int getCar_rent_id() {
            return car_rent_id;
        }

        public void setCar_rent_id(int car_rent_id) {
            this.car_rent_id = car_rent_id;
        }

        public int getGo_off() {
            return go_off;
        }

        public void setGo_off(int go_off) {
            this.go_off = go_off;
        }

        public int getUnoccupied_num() {
            return unoccupied_num;
        }

        public void setUnoccupied_num(int unoccupied_num) {
            this.unoccupied_num = unoccupied_num;
        }

        public String getVehicle_module_name() {
            return vehicle_module_name;
        }

        public void setVehicle_module_name(String vehicle_module_name) {
            this.vehicle_module_name = vehicle_module_name;
        }

        public String getContact_tel() {
            return contact_tel;
        }

        public void setContact_tel(String contact_tel) {
            this.contact_tel = contact_tel;
        }

        public String getDestination_place_name() {
            return destination_place_name;
        }

        public void setDestination_place_name(String destination_place_name) {
            this.destination_place_name = destination_place_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
