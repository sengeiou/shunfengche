package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public class IdeaThinkClassBean extends BaseData {


    /**
     * first_class_id : 61
     * first_class_name : 企业体系
     * second_class_list : [{"second_class_name":"产业链发展趋势","second_class_id":65},{"second_class_name":"企业关键战略制定","second_class_id":66}]
     */

    private int first_class_id;
    private String first_class_name;
    private List<SecondClassListBean> second_class_list;

    public int getFirst_class_id() {
        return first_class_id;
    }

    public void setFirst_class_id(int first_class_id) {
        this.first_class_id = first_class_id;
    }

    public String getFirst_class_name() {
        return first_class_name;
    }

    public void setFirst_class_name(String first_class_name) {
        this.first_class_name = first_class_name;
    }

    public List<SecondClassListBean> getSecond_class_list() {
        return second_class_list;
    }

    public void setSecond_class_list(List<SecondClassListBean> second_class_list) {
        this.second_class_list = second_class_list;
    }

    public static class SecondClassListBean {
        /**
         * second_class_name : 产业链发展趋势
         * second_class_id : 65
         */

        private String second_class_name;
        private int second_class_id;
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSecond_class_name() {
            return second_class_name;
        }

        public void setSecond_class_name(String second_class_name) {
            this.second_class_name = second_class_name;
        }

        public int getSecond_class_id() {
            return second_class_id;
        }

        public void setSecond_class_id(int second_class_id) {
            this.second_class_id = second_class_id;
        }
    }
}
