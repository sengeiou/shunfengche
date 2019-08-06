package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/30
 * 作者：xjh
 */

public class HouseMoreBean extends BaseData {

    private List<RentTypeListBean> rent_type_list;
    private List<HouseAreaListBean> house_area_list;
//    private List<HouseTypeListBean> house_type_list;

    public List<RentTypeListBean> getRent_type_list() {
        return rent_type_list;
    }

    public void setRent_type_list(List<RentTypeListBean> rent_type_list) {
        this.rent_type_list = rent_type_list;
    }

    public List<HouseAreaListBean> getHouse_area_list() {
        return house_area_list;
    }

    public void setHouse_area_list(List<HouseAreaListBean> house_area_list) {
        this.house_area_list = house_area_list;
    }

//    public List<HouseTypeListBean> getHouse_type_list() {
//        return house_type_list;
//    }
//
//    public void setHouse_type_list(List<HouseTypeListBean> house_type_list) {
//        this.house_type_list = house_type_list;
//    }

    public static class RentTypeListBean {
        /**
         * rent_type_name : 1室
         * rent_type_id : 37
         */

        private String rent_type_name;
        private int rent_type_id;

        public String getRent_type_name() {
            return rent_type_name;
        }

        public void setRent_type_name(String rent_type_name) {
            this.rent_type_name = rent_type_name;
        }

        public int getRent_type_id() {
            return rent_type_id;
        }

        public void setRent_type_id(int rent_type_id) {
            this.rent_type_id = rent_type_id;
        }
    }

    public static class HouseAreaListBean {
        /**
         * house_area_id : 30
         * house_area_name : 60平以下
         */

        private int house_area_id;
        private String house_area_name;

        public int getHouse_area_id() {
            return house_area_id;
        }

        public void setHouse_area_id(int house_area_id) {
            this.house_area_id = house_area_id;
        }

        public String getHouse_area_name() {
            return house_area_name;
        }

        public void setHouse_area_name(String house_area_name) {
            this.house_area_name = house_area_name;
        }
    }

    public static class HouseTypeListBean {
        /**
         * house_type_id : 37
         * house_type_name : 整租
         */

        private int house_type_id;
        private String house_type_name;

        public int getHouse_type_id() {
            return house_type_id;
        }

        public void setHouse_type_id(int house_type_id) {
            this.house_type_id = house_type_id;
        }

        public String getHouse_type_name() {
            return house_type_name;
        }

        public void setHouse_type_name(String house_type_name) {
            this.house_type_name = house_type_name;
        }
    }
}
