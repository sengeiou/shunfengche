package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：猜你喜欢实体
 * author:cyq
 * 2018-06-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class GuessYouLikeBean extends BaseData {

    private String list_name;
    private List<ListBean> list;

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean{
        int commodity_id;
        String commodity_title;
        String commodity_cover_picture;
        String commodity_reserve_price;

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getCommodity_title() {
            return commodity_title;
        }

        public void setCommodity_title(String commodity_title) {
            this.commodity_title = commodity_title;
        }

        public String getCommodity_cover_picture() {
            return commodity_cover_picture;
        }

        public void setCommodity_cover_picture(String commodity_cover_picture) {
            this.commodity_cover_picture = commodity_cover_picture;
        }

        public String getCommodity_reserve_price() {
            return commodity_reserve_price;
        }

        public void setCommodity_reserve_price(String commodity_reserve_price) {
            this.commodity_reserve_price = commodity_reserve_price;
        }
    }
}
