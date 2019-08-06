package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/24
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class LastPublicForZhaopinModel {

    /**
     * record : {"birthday":"1987-01-01","work_third_area_id":3047,"job_class_id_one_name":"市场与销售","work_year_name":"1-2年","salary_id":79,"sex":1,"benefit_id_list":[{"benefit_name":"五险一金","benefit_id":67},{"benefit_name":"旅游福利","benefit_id":68}],"job_class_id_one":74,"benefit_ids":"67,68","education_background_id":65,"salary_name":"10000-20000","job_class_id_two":86,"work_third_area_name":"黄埔区","update_time":1542201900,"true_name":"木木","mobile_phone":"13802739976","self_intro":"我是采购","job_class_id_two_name":"采购","work_second_area_id":289,"work_year_id":30,"age":31,"work_second_area_name":"广州市","education_background_name":"硕士"}
     * sex : 1
     * nickname : 木木
     * is_posted : 1
     * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/998307152360347334620180413_031111.jpg
     */

    private RecordBean record;
    private int sex;
    private String nickname;
    private int is_posted;
    private String user_avatar_url;

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getIs_posted() {
        return is_posted;
    }

    public void setIs_posted(int is_posted) {
        this.is_posted = is_posted;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public static class RecordBean {
        /**
         * birthday : 1987-01-01
         * work_third_area_id : 3047
         * job_class_id_one_name : 市场与销售
         * work_year_name : 1-2年
         * salary_id : 79
         * sex : 1
         * benefit_id_list : [{"benefit_name":"五险一金","benefit_id":67},{"benefit_name":"旅游福利","benefit_id":68}]
         * job_class_id_one : 74
         * benefit_ids : 67,68
         * education_background_id : 65
         * salary_name : 10000-20000
         * job_class_id_two : 86
         * work_third_area_name : 黄埔区
         * update_time : 1542201900
         * true_name : 木木
         * mobile_phone : 13802739976
         * self_intro : 我是采购
         * job_class_id_two_name : 采购
         * work_second_area_id : 289
         * work_year_id : 30
         * age : 31
         * work_second_area_name : 广州市
         * education_background_name : 硕士
         * salary_fee : 起始薪资
         * end_salary_fee : 结束薪资
         * salary_type : 2
         */
        private String salary_fee;
        private String end_salary_fee;
        private int salary_type;
        private String birthday;
        private int work_third_area_id;
        private String job_class_id_one_name;
        private String job_name;
        private String work_year_name;
        private int salary_id;
        private int sex;
        private String job_class_id_one;
        private String benefit_ids;
        private int education_background_id;
        private String salary_name;
        private String job_class_id_two;
        private String work_third_area_name;
        private int update_time;
        private String true_name;
        private String mobile_phone;
        private String self_intro;
        private String job_class_id_two_name;
        private int work_second_area_id;
        private int work_year_id;
        private int age;
        private String work_second_area_name;
        private String education_background_name;
        private List<BenefitIdListBean> benefit_id_list;

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(String salary_fee) {
            this.salary_fee = salary_fee;
        }

        public String getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(String end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public String getJob_class_id_one_name() {
            return job_class_id_one_name;
        }

        public void setJob_class_id_one_name(String job_class_id_one_name) {
            this.job_class_id_one_name = job_class_id_one_name;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public int getSalary_id() {
            return salary_id;
        }

        public void setSalary_id(int salary_id) {
            this.salary_id = salary_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getJob_class_id_one() {
            return job_class_id_one;
        }

        public void setJob_class_id_one(String job_class_id_one) {
            this.job_class_id_one = job_class_id_one;
        }

        public String getBenefit_ids() {
            return benefit_ids;
        }

        public void setBenefit_ids(String benefit_ids) {
            this.benefit_ids = benefit_ids;
        }

        public int getEducation_background_id() {
            return education_background_id;
        }

        public void setEducation_background_id(int education_background_id) {
            this.education_background_id = education_background_id;
        }

        public String getSalary_name() {
            return salary_name;
        }

        public void setSalary_name(String salary_name) {
            this.salary_name = salary_name;
        }

        public String getJob_class_id_two() {
            return job_class_id_two;
        }

        public void setJob_class_id_two(String job_class_id_two) {
            this.job_class_id_two = job_class_id_two;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getSelf_intro() {
            return self_intro;
        }

        public void setSelf_intro(String self_intro) {
            this.self_intro = self_intro;
        }

        public String getJob_class_id_two_name() {
            return job_class_id_two_name;
        }

        public void setJob_class_id_two_name(String job_class_id_two_name) {
            this.job_class_id_two_name = job_class_id_two_name;
        }

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
        }

        public int getWork_year_id() {
            return work_year_id;
        }

        public void setWork_year_id(int work_year_id) {
            this.work_year_id = work_year_id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getEducation_background_name() {
            return education_background_name;
        }

        public void setEducation_background_name(String education_background_name) {
            this.education_background_name = education_background_name;
        }

        public List<BenefitIdListBean> getBenefit_id_list() {
            return benefit_id_list;
        }

        public void setBenefit_id_list(List<BenefitIdListBean> benefit_id_list) {
            this.benefit_id_list = benefit_id_list;
        }

        public static class BenefitIdListBean {
            /**
             * benefit_name : 五险一金
             * benefit_id : 67
             */

            private String benefit_name;
            private int benefit_id;

            public String getBenefit_name() {
                return benefit_name;
            }

            public void setBenefit_name(String benefit_name) {
                this.benefit_name = benefit_name;
            }

            public int getBenefit_id() {
                return benefit_id;
            }

            public void setBenefit_id(int benefit_id) {
                this.benefit_id = benefit_id;
            }
        }
    }
}
