package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public class MoreCarListBean extends BaseData {

    private List<MileageListBean> mileage_list;
    private List<DisplacementListBean> displacement_list;
    private List<GearboxListBean> gearbox_list;

    public List<MileageListBean> getMileage_list() {
        return mileage_list;
    }

    public void setMileage_list(List<MileageListBean> mileage_list) {
        this.mileage_list = mileage_list;
    }

    public List<DisplacementListBean> getDisplacement_list() {
        return displacement_list;
    }

    public void setDisplacement_list(List<DisplacementListBean> displacement_list) {
        this.displacement_list = displacement_list;
    }

    public List<GearboxListBean> getGearbox_list() {
        return gearbox_list;
    }

    public void setGearbox_list(List<GearboxListBean> gearbox_list) {
        this.gearbox_list = gearbox_list;
    }

    public static class MileageListBean {
        /**
         * mileage_name : 1万公里以下
         * mileage_id : 59
         */

        private String mileage_name;
        private int mileage_id;

        public String getMileage_name() {
            return mileage_name;
        }

        public void setMileage_name(String mileage_name) {
            this.mileage_name = mileage_name;
        }

        public int getMileage_id() {
            return mileage_id;
        }

        public void setMileage_id(int mileage_id) {
            this.mileage_id = mileage_id;
        }
    }

    public static class DisplacementListBean {
        /**
         * displacement_id : 64
         * displacement_name : 1.0以下
         */

        private int displacement_id;
        private String displacement_name;

        public int getDisplacement_id() {
            return displacement_id;
        }

        public void setDisplacement_id(int displacement_id) {
            this.displacement_id = displacement_id;
        }

        public String getDisplacement_name() {
            return displacement_name;
        }

        public void setDisplacement_name(String displacement_name) {
            this.displacement_name = displacement_name;
        }
    }

    public static class GearboxListBean {
        /**
         * gearbox_name : 手动
         * gearbox_id : 197
         */

        private String gearbox_name;
        private int gearbox_id;

        public String getGearbox_name() {
            return gearbox_name;
        }

        public void setGearbox_name(String gearbox_name) {
            this.gearbox_name = gearbox_name;
        }

        public int getGearbox_id() {
            return gearbox_id;
        }

        public void setGearbox_id(int gearbox_id) {
            this.gearbox_id = gearbox_id;
        }
    }
}
