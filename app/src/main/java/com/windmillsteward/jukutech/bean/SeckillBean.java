package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：秒杀实体
 * author:cyq
 * 2018-07-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SeckillBean extends BaseData {

    private long start_time;//活动开始时间
    private long end_time;//活动结束时间
    private List<ListBean> list;

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int commodity_id;//商品id
        private String commodity_model_image;//封面图
        private String commodity_price;//商品价格
        private String cost_price;//秒杀	价

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getCommodity_model_image() {
            return commodity_model_image;
        }

        public void setCommodity_model_image(String commodity_model_image) {
            this.commodity_model_image = commodity_model_image;
        }

        public String getCommodity_price() {
            return commodity_price;
        }

        public void setCommodity_price(String commodity_price) {
            this.commodity_price = commodity_price;
        }

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }
    }
}
