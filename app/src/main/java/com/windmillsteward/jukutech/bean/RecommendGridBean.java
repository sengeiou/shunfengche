package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：首页推荐的item实体
 * author:cyq
 * 2018-06-07
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class RecommendGridBean extends BaseData {

    private int module_type;
    private String module_name;
    private String update_time;
    private int recommend_id;
    private List<ListBean> recommend_list;

    public int getModule_type() {
        return module_type;
    }

    public void setModule_type(int module_type) {
        this.module_type = module_type;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getRecommend_id() {
        return recommend_id;
    }

    public void setRecommend_id(int recommend_id) {
        this.recommend_id = recommend_id;
    }

    public List<ListBean> getRecommend_list() {
        return recommend_list;
    }

    public void setRecommend_list(List<ListBean> recommend_list) {
        this.recommend_list = recommend_list;
    }

    public static class ListBean{

        private int relate_id;
        private String title;
        private String price;
        private int type;//详情跳转类型：【1：房屋租售模块-卖房，2：房屋租售模块-买房，3：房屋租售模块-出租，4：房屋租售模块-求租，5：旅游，6：酒店，7：车辆买卖-卖车，
                        // 8：大健康，9：名优特产，10：资本管理-理财，11：资本管理-贷款】
        private String pic_url;

        public int getRelate_id() {
            return relate_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setRelate_id(int relate_id) {
            this.relate_id = relate_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
