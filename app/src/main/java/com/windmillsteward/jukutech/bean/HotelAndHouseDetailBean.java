package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class HotelAndHouseDetailBean extends BaseData {


    /**
     * area_name : 广州市天河区广州 番禺区 汉溪长隆地铁站C出口，奥园城市天地6栋1109
     * leave_time : 12:00:00
     * latitude : 22.9930180000
     * description : 钧玺主题公寓（广州汉溪长隆地铁站店）位于汉溪长隆地铁站C出来50米，房间是按照星级标准打造的一所高档、儿童式，商务酒店主题公寓，风格独特，距离琶洲国际展览中心仅10公里，20分钟车程即可到达，距广州长隆欢乐世界、广州长隆高尔夫球场、长隆水上乐园、香江野生动物园仅5分钟的车程，周边景观有大夫山森林公园、广州鳄鱼公园、瀛洲生态公园，近享万博中心、奥特莱斯、天河城、沃尔玛、吉盛伟邦、海印又一城，7大旗舰领衔都会商业，另有中华美食城、四海一家、新南海城酒家、成记海鲜酒楼、广州番禺渔民新村、渔村皇宫、天下粮庄酒家、祈福食街、华南美食街、凤凰山庄等，交通便利，环境优美。是集娱乐、旅游、商业中心于一体的商务服务式公寓。酒店拥有各种儿童主题套房，高级商务套房、豪华法式韩式套房、温馨特色爱情套房供您选择，温馨舒适、豪华典雅。高起点建设，高标准管理，个性化管理，数字化装备，完善了酒店的宽频上网系统，网络覆盖全面，信息沟通快捷。酒店式的服务，公寓式的管理，有传统酒店的硬件配置，相当水准的上门服务，同时提供家庭式的居室布局，居室内配有全套家具，将星级酒店的高标准服务融于日常生活之中。
     * pic_urls : ["http://img3.imgtn.bdimg.com/it/u=4294022295,2887293064&fm=27&gp=0.jpg","http://img4.imgtn.bdimg.com/it/u=829989965,3053821157&fm=27&gp=0.jpg","http://img1.imgtn.bdimg.com/it/u=3739762023,2390353952&fm=27&gp=0.jpg"]
     * hotel_name : 钧玺主题公寓(广州汉溪长隆地铁站店)
     * is_food_supply : 0
     * public_facility_list : ["停车场","会议室","商务中心","24小时前台"]
     * is_collect : 0
     * can_take_pet : 1
     * activity_facility_list : ["桑拿","酒吧","娱乐场/棋牌室","KTV","SPA"]
     * hotel_type_name : 快捷酒店
     * room_facility_list : ["电热水壶","浴缸","暖气","液晶电视/等离子电视"]
     * service_list : ["行李寄存","洗衣服务","礼宾接待服务","外币兑换","租车"]
     * opening_date : 2016
     * longitude : 113.3301310000
     * room_list : [{"room_type_name":"小黄人主题房","orig_price":200,"remain_room_num":10,"discount_price":175,"room_type_id":1},{"room_type_name":"HelloKitty主题房","orig_price":250,"remain_room_num":20,"discount_price":195,"room_type_id":2}]
     */

    private int hotel_id;
    private String area_name;
    private String leave_time;
    private String latitude;
    private String description;
    private String hotel_name;
    private int is_food_supply;
    private int is_collect;
    private int can_take_pet;
    private String hotel_type_name;
    private String opening_date;
    private String longitude;
    private List<String> pic_urls;
    private List<String> public_facility_list;
    private List<String> activity_facility_list;
    private List<String> room_facility_list;
    private List<String> service_list;
    private List<RoomListBean> room_list;

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public int getIs_food_supply() {
        return is_food_supply;
    }

    public void setIs_food_supply(int is_food_supply) {
        this.is_food_supply = is_food_supply;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public int getCan_take_pet() {
        return can_take_pet;
    }

    public void setCan_take_pet(int can_take_pet) {
        this.can_take_pet = can_take_pet;
    }

    public String getHotel_type_name() {
        return hotel_type_name;
    }

    public void setHotel_type_name(String hotel_type_name) {
        this.hotel_type_name = hotel_type_name;
    }

    public String getOpening_date() {
        return opening_date;
    }

    public void setOpening_date(String opening_date) {
        this.opening_date = opening_date;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public List<String> getPublic_facility_list() {
        return public_facility_list;
    }

    public void setPublic_facility_list(List<String> public_facility_list) {
        this.public_facility_list = public_facility_list;
    }

    public List<String> getActivity_facility_list() {
        return activity_facility_list;
    }

    public void setActivity_facility_list(List<String> activity_facility_list) {
        this.activity_facility_list = activity_facility_list;
    }

    public List<String> getRoom_facility_list() {
        return room_facility_list;
    }

    public void setRoom_facility_list(List<String> room_facility_list) {
        this.room_facility_list = room_facility_list;
    }

    public List<String> getService_list() {
        return service_list;
    }

    public void setService_list(List<String> service_list) {
        this.service_list = service_list;
    }

    public List<RoomListBean> getRoom_list() {
        return room_list;
    }

    public void setRoom_list(List<RoomListBean> room_list) {
        this.room_list = room_list;
    }

    public static class RoomListBean extends BaseData {
        /**
         * room_type_name : 小黄人主题房
         * orig_price : 200
         * remain_room_num : 10
         * discount_price : 175
         * room_type_id : 1
         */

        private String room_type_name;
        private String orig_price;
        private int remain_room_num;
        private String discount_price;
        private int room_type_id;

        public String getRoom_type_name() {
            return room_type_name;
        }

        public void setRoom_type_name(String room_type_name) {
            this.room_type_name = room_type_name;
        }

        public String getOrig_price() {
            return orig_price;
        }

        public void setOrig_price(String orig_price) {
            this.orig_price = orig_price;
        }

        public int getRemain_room_num() {
            return remain_room_num;
        }

        public void setRemain_room_num(int remain_room_num) {
            this.remain_room_num = remain_room_num;
        }

        public String getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(String discount_price) {
            this.discount_price = discount_price;
        }

        public int getRoom_type_id() {
            return room_type_id;
        }

        public void setRoom_type_id(int room_type_id) {
            this.room_type_id = room_type_id;
        }
    }
}
