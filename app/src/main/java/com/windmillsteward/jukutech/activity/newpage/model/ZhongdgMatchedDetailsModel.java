package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/27
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class ZhongdgMatchedDetailsModel {

    /**
     * work_third_area_name : 天河区
     * work_date : 2018-10-08
     * salary_fee : 50
     * self_intro : 自我介绍: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * service_id : 60,61,62,63
     * name : 钟点工-啊超
     * contact_tel : 13727574858
     * service_list : ["打扫卫生","做家务","照顾老人","照顾小孩"]
     * match_value : 80
     * add_time : 2018-10-12
     * age : 22
     * user_avatar_url : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/090263152153268100220180320_035759.jpg
     */

    private String work_third_area_name;
    private String work_date;
    private int salary_fee;
    private String self_intro;
    private String service_id;
    private String name;
    private String contact_tel;
    private String match_value;
    private String add_time;
    private int age;
    private int sex;
    private String user_avatar_url;
    private List<String> service_list;
    private String salary_start;
    private String salary_end;
    private int salary_type;
    private int work_hour;
    private String voice_url;
    private String image_url;
    private String video_url;

    public String getVoice_url() {
        return voice_url;
    }

    public void setVoice_url(String voice_url) {
        this.voice_url = voice_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getWork_hour() {
        return work_hour;
    }

    public void setWork_hour(int work_hour) {
        this.work_hour = work_hour;
    }

    public String getSalary_start() {
        return salary_start;
    }

    public void setSalary_start(String salary_start) {
        this.salary_start = salary_start;
    }

    public String getSalary_end() {
        return salary_end;
    }

    public void setSalary_end(String salary_end) {
        this.salary_end = salary_end;
    }

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public int getSalary_fee() {
        return salary_fee;
    }

    public void setSalary_fee(int salary_fee) {
        this.salary_fee = salary_fee;
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getMatch_value() {
        return match_value;
    }

    public void setMatch_value(String match_value) {
        this.match_value = match_value;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public List<String> getService_list() {
        return service_list;
    }

    public void setService_list(List<String> service_list) {
        this.service_list = service_list;
    }
}
