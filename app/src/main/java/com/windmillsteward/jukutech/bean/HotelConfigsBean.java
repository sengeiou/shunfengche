package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/26/026
 * 作者：xjh
 */
public class HotelConfigsBean extends BaseData {

    private List<PublicFacilityListBean> public_facility_list;
    private List<ActivityFacilityListBean> activity_facility_list;
    private List<RoomFacilityListBean> room_facility_list;
    private List<ServiceListBean> service_list;

    public List<PublicFacilityListBean> getPublic_facility_list() {
        return public_facility_list;
    }

    public void setPublic_facility_list(List<PublicFacilityListBean> public_facility_list) {
        this.public_facility_list = public_facility_list;
    }

    public List<ActivityFacilityListBean> getActivity_facility_list() {
        return activity_facility_list;
    }

    public void setActivity_facility_list(List<ActivityFacilityListBean> activity_facility_list) {
        this.activity_facility_list = activity_facility_list;
    }

    public List<RoomFacilityListBean> getRoom_facility_list() {
        return room_facility_list;
    }

    public void setRoom_facility_list(List<RoomFacilityListBean> room_facility_list) {
        this.room_facility_list = room_facility_list;
    }

    public List<ServiceListBean> getService_list() {
        return service_list;
    }

    public void setService_list(List<ServiceListBean> service_list) {
        this.service_list = service_list;
    }

    public static class PublicFacilityListBean {
        /**
         * name : 停车场
         * id : 1
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class ActivityFacilityListBean {
        /**
         * name : 咖啡厅
         * id : 3
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class RoomFacilityListBean {
        /**
         * name : 24小时热水
         * id : 8
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class ServiceListBean {
        /**
         * name : 行李寄存
         * id : 20
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
