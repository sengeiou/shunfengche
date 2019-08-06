package com.windmillsteward.jukutech.bean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/11
 * 作者：xjh
 */

public class MoreBean {


    private List<WordYearListBean> word_year_list;
    private List<EducationListBean> education_list;

    public List<WordYearListBean> getWord_year_list() {
        return word_year_list;
    }

    public void setWord_year_list(List<WordYearListBean> word_year_list) {
        this.word_year_list = word_year_list;
    }

    public List<EducationListBean> getEducation_list() {
        return education_list;
    }

    public void setEducation_list(List<EducationListBean> education_list) {
        this.education_list = education_list;
    }

    public static class WordYearListBean {
        /**
         * work_year_show : 无经验
         * work_year_id : 1
         */

        private String work_year_show;
        private int work_year_id;
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getWork_year_show() {
            return work_year_show;
        }

        public void setWork_year_show(String work_year_show) {
            this.work_year_show = work_year_show;
        }

        public int getWork_year_id() {
            return work_year_id;
        }

        public void setWork_year_id(int work_year_id) {
            this.work_year_id = work_year_id;
        }
    }

    public static class EducationListBean {
        /**
         * education_name : 高中以下
         * education_background_id : 1
         */

        private String image;
        private String education_name;
        private int education_background_id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getEducation_name() {
            return education_name;
        }

        public void setEducation_name(String education_name) {
            this.education_name = education_name;
        }

        public int getEducation_background_id() {
            return education_background_id;
        }

        public void setEducation_background_id(int education_background_id) {
            this.education_background_id = education_background_id;
        }
    }
}
