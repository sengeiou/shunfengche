package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class IntelligentFamilyBean extends BaseData{


    /**
     * totalRow : 4
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"video_cover":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/3745321516776697119video_cover3.jpg","require_id":5,"distance":279,"description":"小强要寄养","pic_urls":[],"require_area":"nullnull","user_avatar_url":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9127731516602043732boy.jpg","user_id":2,"price":100,"nickname":"小铭","video_url":["http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9877991516776697245recording461755360.mp4"]},{"video_cover":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/0014391516765386385video_cover.jpg","require_id":4,"distance":319,"description":"没有","pic_urls":["http://p2.so.qhimgs1.com/bdr/326__/t01bf3f27969e5ff992.jpg"],"require_area":"nullnull","user_avatar_url":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9127731516602043732boy.jpg","user_id":2,"price":1000,"nickname":"小铭","video_url":["http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/4835121516765386889recording500426663.mp4"]},{"video_cover":"","require_id":2,"distance":14730,"description":"在家里陪小孩子玩一会，督促他写作业","pic_urls":["http://telltie-dealer.oss-ap-southeast-1.aliyuncs.com/ddff1bbef102bbe01871a8184a60f25a.png","http://telltie-dealer.oss-ap-southeast-1.aliyuncs.com/3cc5e8fbce8d96b0e1adfc351754c906.png"],"require_area":"那曲地区下花园区","user_avatar_url":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9127731516602043732boy.jpg","user_id":2,"price":200,"nickname":"小铭","video_url":[]},{"video_cover":"","require_id":1,"distance":12403663,"description":"在天河溜两只狸花猫","pic_urls":[],"require_area":"山西省内蒙古自治区","user_avatar_url":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2671632699,2186072093&fm=27&gp=0.jpg","user_id":1,"price":2000,"nickname":"奥观海","video_url":[]}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
    private int totalPage;
    private int pageSize;
    private List<ListBean> list;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * video_cover : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/3745321516776697119video_cover3.jpg
         * require_id : 5
         * distance : 279
         * description : 小强要寄养
         * pic_urls : []
         * require_area : nullnull
         * user_avatar_url : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9127731516602043732boy.jpg
         * user_id : 2
         * price : 100
         * nickname : 小铭
         * is_ad : 0
         * video_url : ["http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9877991516776697245recording461755360.mp4"]
         * class_image : "http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9877991516776697245recording461755360.png
         */

        private String video_cover;
        private int require_id;
        private double distance;
        private String description;
        private String require_area;
        private String user_avatar_url;
        private int user_id;
        private int is_ad;//是否是广告发布，0否1是
        private String price;
        private String nickname;
        private List<String> pic_urls;
        private String video_url;
        private String class_name;
        private String class_image;

        public int getIs_ad() {
            return is_ad;
        }

        public void setIs_ad(int is_ad) {
            this.is_ad = is_ad;
        }

        public String getClass_image() {
            return class_image;
        }

        public void setClass_image(String class_image) {
            this.class_image = class_image;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getVideo_cover() {
            return video_cover;
        }

        public void setVideo_cover(String video_cover) {
            this.video_cover = video_cover;
        }

        public int getRequire_id() {
            return require_id;
        }

        public void setRequire_id(int require_id) {
            this.require_id = require_id;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRequire_area() {
            return require_area;
        }

        public void setRequire_area(String require_area) {
            this.require_area = require_area;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public List<String> getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(List<String> pic_urls) {
            this.pic_urls = pic_urls;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }
    }
}
