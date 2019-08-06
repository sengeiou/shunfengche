package com.windmillsteward.jukutech.bean;

import android.support.annotation.NonNull;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class CarClassListBean extends BaseData implements Comparable<CarClassListBean> {


    private String pinyin;
    /**
     * brand_image : http://app.uni020.com./Uploads/CarBrandImg/2017/1130/5a1f64c2015d4.jpg
     * brand_name : 雷克萨斯
     * series_list : [{"series_id":0,"series_name":"","vehicle_module_list":[{"vehicle_module_id":1,"vehicle_module_name":"哈哈"}]}]
     * brand_id : 119
     */

    private String brand_image;
    private String brand_name;
    private int brand_id;
    private List<SeriesListBean> series_list;

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyin() {
        return pinyin;
    }

    @Override
    public int compareTo(@NonNull CarClassListBean o) {
        return pinyin.compareTo(o.pinyin);
    }

    public String getBrand_image() {
        return brand_image;
    }

    public void setBrand_image(String brand_image) {
        this.brand_image = brand_image;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public List<SeriesListBean> getSeries_list() {
        return series_list;
    }

    public void setSeries_list(List<SeriesListBean> series_list) {
        this.series_list = series_list;
    }

    public static class SeriesListBean extends BaseData {
        /**
         * series_id : 0
         * series_name :
         * vehicle_module_list : [{"vehicle_module_id":1,"vehicle_module_name":"哈哈"}]
         */

        private int series_id;
        private String series_name;
        private List<VehicleModuleListBean> vehicle_module_list;

        public int getSeries_id() {
            return series_id;
        }

        public void setSeries_id(int series_id) {
            this.series_id = series_id;
        }

        public String getSeries_name() {
            return series_name;
        }

        public void setSeries_name(String series_name) {
            this.series_name = series_name;
        }

        public List<VehicleModuleListBean> getVehicle_module_list() {
            return vehicle_module_list;
        }

        public void setVehicle_module_list(List<VehicleModuleListBean> vehicle_module_list) {
            this.vehicle_module_list = vehicle_module_list;
        }

        public static class VehicleModuleListBean extends BaseData {
            /**
             * vehicle_module_id : 1
             * vehicle_module_name : 哈哈
             */

            private int vehicle_module_id;
            private String vehicle_module_name;

            public int getVehicle_module_id() {
                return vehicle_module_id;
            }

            public void setVehicle_module_id(int vehicle_module_id) {
                this.vehicle_module_id = vehicle_module_id;
            }

            public String getVehicle_module_name() {
                return vehicle_module_name;
            }

            public void setVehicle_module_name(String vehicle_module_name) {
                this.vehicle_module_name = vehicle_module_name;
            }
        }
    }
}
