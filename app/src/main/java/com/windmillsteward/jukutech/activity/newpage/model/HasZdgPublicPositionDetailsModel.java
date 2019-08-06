package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/28
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HasZdgPublicPositionDetailsModel {

    /**
     * salary_end : 150
     * work_date : 2018-10-08
     * job_describe : 工作描述: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * service_name : 打扫卫生
     * latitude : 113.4064504678
     * match_time : 1538992800
     * order_price : 0.03
     * title : 招聘3名钟点工
     * salary_start : 100
     * work_third_area_name : 天河区
     * info_fee : 0.03
     * lookfor_bell_worker_id : 1
     * service_id : 60
     * recruit_number : 3
     * when_bell_worker_List : [{"work_third_area_name":"天河区","service_name":"打扫卫生","sex":1,"latitude":"113.4064504678","hour_matching_id":1,"name":"钟点工-啊超","contact_tel":"13727574858","is_pay":0,"work_second_area_name":"广州市","longitude":"23.1199587650","is_complaint":0,"user_avatar_url":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/090263152153268100220180320_035759.jpg"}]
     * pay_type : 2
     * order_sn : 2018101310015869805867
     * work_second_area_name : 广州市
     * longitude : 23.1199587650
     * num : 2。还差几人
     * consumer_hotline: 13800138001 客服电话
     */

    private String salary_end;
    private String work_date;
    private String job_describe;
    private String service_name;
    private String latitude;
    private int match_time;
    private double order_price;
    private String title;
    private String salary_start;
    private String work_third_area_name;
    private String info_fee;
    private int lookfor_bell_worker_id;
    private String service_id;
    private int recruit_number;
    private int pay_type;
    private String order_sn;
    private String work_second_area_name;
    private String longitude;
    private List<WhenBellWorkerListBean> when_bell_worker_List;
    private int work_hour;
    private String address;
    private int num;
    private String consumer_hotline;
    private int salary_type;
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

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public String getConsumer_hotline() {
        return consumer_hotline;
    }

    public void setConsumer_hotline(String consumer_hotline) {
        this.consumer_hotline = consumer_hotline;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public String getJob_describe() {
        return job_describe;
    }

    public void setJob_describe(String job_describe) {
        this.job_describe = job_describe;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getMatch_time() {
        return match_time;
    }

    public void setMatch_time(int match_time) {
        this.match_time = match_time;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary_end() {
        return salary_end;
    }

    public void setSalary_end(String salary_end) {
        this.salary_end = salary_end;
    }

    public String getSalary_start() {
        return salary_start;
    }

    public void setSalary_start(String salary_start) {
        this.salary_start = salary_start;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public String getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(String info_fee) {
        this.info_fee = info_fee;
    }

    public int getLookfor_bell_worker_id() {
        return lookfor_bell_worker_id;
    }

    public void setLookfor_bell_worker_id(int lookfor_bell_worker_id) {
        this.lookfor_bell_worker_id = lookfor_bell_worker_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public int getRecruit_number() {
        return recruit_number;
    }

    public void setRecruit_number(int recruit_number) {
        this.recruit_number = recruit_number;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getWork_second_area_name() {
        return work_second_area_name;
    }

    public void setWork_second_area_name(String work_second_area_name) {
        this.work_second_area_name = work_second_area_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<WhenBellWorkerListBean> getWhen_bell_worker_List() {
        return when_bell_worker_List;
    }

    public void setWhen_bell_worker_List(List<WhenBellWorkerListBean> when_bell_worker_List) {
        this.when_bell_worker_List = when_bell_worker_List;
    }

    public static class WhenBellWorkerListBean {
        /**
         * work_third_area_name : 天河区
         * service_name : 打扫卫生
         * sex : 1
         * latitude : 113.4064504678
         * hour_matching_id : 1
         * name : 钟点工-啊超
         * contact_tel : 13727574858
         * is_pay : 0
         * work_second_area_name : 广州市
         * longitude : 23.1199587650
         * is_complaint : 0
         * user_avatar_url : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/090263152153268100220180320_035759.jpg
         */

        private String work_third_area_name;
        private String service_name;
        private int sex;
        private String latitude;
        private int hour_matching_id;
        private String name;
        private String contact_tel;
        private int is_pay;
        private String work_second_area_name;
        private String longitude;
        private int is_complaint;
        private String user_avatar_url;
        private int evaluation_id;
        private int when_bell_worker_id;

        public int getWhen_bell_worker_id() {
            return when_bell_worker_id;
        }

        public void setWhen_bell_worker_id(int when_bell_worker_id) {
            this.when_bell_worker_id = when_bell_worker_id;
        }

        public int getEvaluation_id() {
            return evaluation_id;
        }

        public void setEvaluation_id(int evaluation_id) {
            this.evaluation_id = evaluation_id;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getHour_matching_id() {
            return hour_matching_id;
        }

        public void setHour_matching_id(int hour_matching_id) {
            this.hour_matching_id = hour_matching_id;
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

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getIs_complaint() {
            return is_complaint;
        }

        public void setIs_complaint(int is_complaint) {
            this.is_complaint = is_complaint;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }
    }
}
