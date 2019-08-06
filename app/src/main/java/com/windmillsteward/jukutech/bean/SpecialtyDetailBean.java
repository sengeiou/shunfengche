package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class SpecialtyDetailBean extends BaseData {


    /**
     * store_id : 1
     * commodity_sales_volume : 88
     * commodity_title : 正宗农家咸鸭蛋
     * first_area_id : 0
     * commodity_reserve_price : 188
     * commodity_image : ["http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/2023041522139261668icon_closet.jpg","http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/7200441522139262183icon_closet.jpg","http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg"]
     * first_area_name :
     * second_area_name : 广州市
     * commodity_detail : 鸭蛋的营养价值1.鸭蛋含有蛋白质、磷脂、维生素A、维生素B2、维生素B1、维生素D、钙、钾、铁、磷等营养物质; 2.鸭蛋中蛋白质的含量和鸡蛋一样,有强壮身体的作用; 3.鸭蛋中各种矿物质的总量超过鸡蛋很多,特别是身体中迫切需要的铁和钙在咸鸭蛋中更是丰富,对骨骼发育有善,并能预防贫血; 4.鸭蛋含有较多的维生素B2,是补充B族维生素的理想食品之一。 5.鸭蛋中的蛋白质含量和鸡蛋相当,而矿物质总量远胜鸡蛋,尤其铁、钙含量极为丰富,能预防贫血,促进骨骼发育。 鸭蛋的食用功效 鸭蛋味甘、咸,性凉;入肺、脾经;有大补虚劳、滋阴养血、润肺美肤的功效;用于膈热、咳嗽、喉痛、齿痛、泄疾。 中医认为,咸鸭蛋清肺火、降阴火功能比未腌制的鸭蛋更胜一筹,煮食可治愈泻痢。其中咸蛋黄油可治小儿积食,外敷可治烫伤、湿疹
     * freight_stencil_id : 1
     * second_area_id : 289
     * commodity_peak_price : 366
     * store_thumbnail : http://telltie-client.oss-ap-southeast-1.aliyuncs.com/1519443010548ttf848832411846156928.jpg
     * commodity_is_collect : 0
     * third_area_id : 3040
     * four_area_name :
     * commodity_id : 1
     * third_area_name : 天河区
     * four_area_id : 0
     * store_name : 啊强特产店
     * commodity_model_list : [{"commodity_price":188,"commodity_model_id":1,"commodity_item_no":"1234548613156","commodity_inventory":100,"commodity_weight":1000,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_model_name":"正宗农家咸鸭蛋"}]
     * commodity_carriage : 0
     * store_is_collect : 0
     */

    private int store_id;
    private int commodity_sales_volume;
    private String commodity_title;
    private int first_area_id;
    private String commodity_reserve_price;
    private String first_area_name;
    private String second_area_name;
    private String commodity_detail;
    private int freight_stencil_id;
    private int second_area_id;
    private String commodity_peak_price;
    private String store_thumbnail;
    private int commodity_is_collect;
    private int third_area_id;
    private String four_area_name;
    private int commodity_id;
    private String third_area_name;
    private int four_area_id;
    private int restriction_num;// 限购数量，注：如果是秒杀商品，返回
    private int start_time;// 开始抢购时间
    private int end_time;// 抢购结束时间
    private int commodity_inventory;// 库存
    private String store_name;
    private String commodity_carriage;
    private int store_is_collect;
    private List<String> commodity_image;
    private List<CommodityModelListBean> commodity_model_list;

    public int getCommodity_inventory() {
        return commodity_inventory;
    }

    public void setCommodity_inventory(int commodity_inventory) {
        this.commodity_inventory = commodity_inventory;
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

    public int getRestriction_num() {
        return restriction_num;
    }

    public void setRestriction_num(int restriction_num) {
        this.restriction_num = restriction_num;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getCommodity_sales_volume() {
        return commodity_sales_volume;
    }

    public void setCommodity_sales_volume(int commodity_sales_volume) {
        this.commodity_sales_volume = commodity_sales_volume;
    }

    public String getCommodity_title() {
        return commodity_title;
    }

    public void setCommodity_title(String commodity_title) {
        this.commodity_title = commodity_title;
    }

    public int getFirst_area_id() {
        return first_area_id;
    }

    public void setFirst_area_id(int first_area_id) {
        this.first_area_id = first_area_id;
    }

    public String getCommodity_reserve_price() {
        return commodity_reserve_price;
    }

    public void setCommodity_reserve_price(String commodity_reserve_price) {
        this.commodity_reserve_price = commodity_reserve_price;
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

    public String getCommodity_detail() {
        return commodity_detail;
    }

    public void setCommodity_detail(String commodity_detail) {
        this.commodity_detail = commodity_detail;
    }

    public int getFreight_stencil_id() {
        return freight_stencil_id;
    }

    public void setFreight_stencil_id(int freight_stencil_id) {
        this.freight_stencil_id = freight_stencil_id;
    }

    public int getSecond_area_id() {
        return second_area_id;
    }

    public void setSecond_area_id(int second_area_id) {
        this.second_area_id = second_area_id;
    }

    public String getCommodity_peak_price() {
        return commodity_peak_price;
    }

    public void setCommodity_peak_price(String commodity_peak_price) {
        this.commodity_peak_price = commodity_peak_price;
    }

    public String getStore_thumbnail() {
        return store_thumbnail;
    }

    public void setStore_thumbnail(String store_thumbnail) {
        this.store_thumbnail = store_thumbnail;
    }

    public int getCommodity_is_collect() {
        return commodity_is_collect;
    }

    public void setCommodity_is_collect(int commodity_is_collect) {
        this.commodity_is_collect = commodity_is_collect;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    public String getFour_area_name() {
        return four_area_name;
    }

    public void setFour_area_name(String four_area_name) {
        this.four_area_name = four_area_name;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getThird_area_name() {
        return third_area_name;
    }

    public void setThird_area_name(String third_area_name) {
        this.third_area_name = third_area_name;
    }

    public int getFour_area_id() {
        return four_area_id;
    }

    public void setFour_area_id(int four_area_id) {
        this.four_area_id = four_area_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getCommodity_carriage() {
        return commodity_carriage;
    }

    public void setCommodity_carriage(String commodity_carriage) {
        this.commodity_carriage = commodity_carriage;
    }

    public int getStore_is_collect() {
        return store_is_collect;
    }

    public void setStore_is_collect(int store_is_collect) {
        this.store_is_collect = store_is_collect;
    }

    public List<String> getCommodity_image() {
        return commodity_image;
    }

    public void setCommodity_image(List<String> commodity_image) {
        this.commodity_image = commodity_image;
    }

    public List<CommodityModelListBean> getCommodity_model_list() {
        return commodity_model_list;
    }

    public void setCommodity_model_list(List<CommodityModelListBean> commodity_model_list) {
        this.commodity_model_list = commodity_model_list;
    }

    public static class CommodityModelListBean {
        /**
         * commodity_price : 188
         * commodity_model_id : 1
         * commodity_item_no : 1234548613156
         * commodity_inventory : 100
         * commodity_weight : 1000
         * commodity_model_image : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg
         * commodity_model_name : 正宗农家咸鸭蛋
         */

        private String commodity_price;
        private int commodity_model_id;
        private String commodity_item_no;
        private int commodity_inventory;
        private int commodity_weight;
        private String commodity_model_image;
        private String commodity_model_name;

        public String getCommodity_price() {
            return commodity_price;
        }

        public void setCommodity_price(String commodity_price) {
            this.commodity_price = commodity_price;
        }

        public int getCommodity_model_id() {
            return commodity_model_id;
        }

        public void setCommodity_model_id(int commodity_model_id) {
            this.commodity_model_id = commodity_model_id;
        }

        public String getCommodity_item_no() {
            return commodity_item_no;
        }

        public void setCommodity_item_no(String commodity_item_no) {
            this.commodity_item_no = commodity_item_no;
        }

        public int getCommodity_inventory() {
            return commodity_inventory;
        }

        public void setCommodity_inventory(int commodity_inventory) {
            this.commodity_inventory = commodity_inventory;
        }

        public int getCommodity_weight() {
            return commodity_weight;
        }

        public void setCommodity_weight(int commodity_weight) {
            this.commodity_weight = commodity_weight;
        }

        public String getCommodity_model_image() {
            return commodity_model_image;
        }

        public void setCommodity_model_image(String commodity_model_image) {
            this.commodity_model_image = commodity_model_image;
        }

        public String getCommodity_model_name() {
            return commodity_model_name;
        }

        public void setCommodity_model_name(String commodity_model_name) {
            this.commodity_model_name = commodity_model_name;
        }
    }
}
