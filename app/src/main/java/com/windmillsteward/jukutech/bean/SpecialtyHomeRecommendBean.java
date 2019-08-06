package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：特产首页推荐列表实体
 * author:cyq
 * 2018-06-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SpecialtyHomeRecommendBean extends BaseData {

    private int class_id;//分类id
    private String name;//推荐的分类名称
    private String update_time;//时间
    private String class_banner;//banner图片
    private List<ListBean> recommend_list;//推荐分类下的商品

    public String getClass_banner() {
        return class_banner;
    }

    public void setClass_banner(String class_banner) {
        this.class_banner = class_banner;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<ListBean> getRecommend_list() {
        return recommend_list;
    }

    public void setRecommend_list(List<ListBean> recommend_list) {
        this.recommend_list = recommend_list;
    }

    public static class ListBean{

        private int commodity_id;//商品id
        private String commodity_cover_picture;//商品封面图
        private String commodity_title;//商品名称
        private String commodity_reserve_price;//商品价格

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getCommodity_cover_picture() {
            return commodity_cover_picture;
        }

        public void setCommodity_cover_picture(String commodity_cover_picture) {
            this.commodity_cover_picture = commodity_cover_picture;
        }

        public String getCommodity_title() {
            return commodity_title;
        }

        public void setCommodity_title(String commodity_title) {
            this.commodity_title = commodity_title;
        }

        public String getCommodity_reserve_price() {
            return commodity_reserve_price;
        }

        public void setCommodity_reserve_price(String commodity_reserve_price) {
            this.commodity_reserve_price = commodity_reserve_price;
        }
    }
}
