package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/29
 * 作者：xjh
 */

public class HouseSealListBean extends BaseData {

    /**
     * totalRow : 2
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"house_id":2,"total_price":300,"latitude":"113.4064504678","pic_urls":["http://sfcgj.oss-cn-qingdao.aliyuncs.com/40417415381224485261.jpg","http://sfcgj.oss-cn-qingdao.aliyuncs.com/15273415381224497612.jpg"],"title":"天河区车陂惠月楼小区三房两厅-2","house_fourth_id":29025,"video_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/65093515381222502781.mp4","house_fourth_name":"车陂街道","house_third_name":"天河区","house_third_id":3040,"house_type":10,"house_type_name":"写字楼","longitude":"23.1199587650"},{"house_id":1,"total_price":299,"latitude":"113.4064504678","pic_urls":["http://sfcgj.oss-cn-qingdao.aliyuncs.com/40417415381224485261.jpg","http://sfcgj.oss-cn-qingdao.aliyuncs.com/15273415381224497612.jpg"],"title":"天河区车陂惠月楼小区三房两厅-1","house_fourth_id":29025,"video_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/65093515381222502781.mp4","house_fourth_name":"车陂街道","house_third_name":"天河区","house_third_id":3040,"house_type":6,"house_type_name":"住宅","longitude":"23.1199587650"}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean lastPage;
    private boolean firstPage;
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

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
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
         * house_id : 2
         * total_price : 300
         * latitude : 113.4064504678
         * pic_urls : ["http://sfcgj.oss-cn-qingdao.aliyuncs.com/40417415381224485261.jpg","http://sfcgj.oss-cn-qingdao.aliyuncs.com/15273415381224497612.jpg"]
         * title : 天河区车陂惠月楼小区三房两厅-2
         * house_fourth_id : 29025
         * video_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/65093515381222502781.mp4
         * house_fourth_name : 车陂街道
         * house_third_name : 天河区
         * house_third_id : 3040
         * house_type : 10
         * house_type_name : 写字楼
         * longitude : 23.1199587650
         */

        private int house_id;
        private double total_price;
        private String latitude;
        private String title;
        private int house_fourth_id;
        private String video_url;
        private String house_fourth_name;
        private String house_third_name;
        private int house_third_id;
        private int house_type;
        private String house_type_name;
        private String longitude;
        private List<String> pic_urls;
        private String video_cover;
        private String user_avatar_url;

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getVideo_cover() {
            return video_cover;
        }

        public void setVideo_cover(String video_cover) {
            this.video_cover = video_cover;
        }

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getHouse_fourth_id() {
            return house_fourth_id;
        }

        public void setHouse_fourth_id(int house_fourth_id) {
            this.house_fourth_id = house_fourth_id;
        }


        public String getHouse_fourth_name() {
            return house_fourth_name;
        }

        public void setHouse_fourth_name(String house_fourth_name) {
            this.house_fourth_name = house_fourth_name;
        }

        public String getHouse_third_name() {
            return house_third_name;
        }

        public void setHouse_third_name(String house_third_name) {
            this.house_third_name = house_third_name;
        }

        public int getHouse_third_id() {
            return house_third_id;
        }

        public void setHouse_third_id(int house_third_id) {
            this.house_third_id = house_third_id;
        }

        public int getHouse_type() {
            return house_type;
        }

        public void setHouse_type(int house_type) {
            this.house_type = house_type;
        }

        public String getHouse_type_name() {
            return house_type_name;
        }

        public void setHouse_type_name(String house_type_name) {
            this.house_type_name = house_type_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public List<String> getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(List<String> pic_urls) {
            this.pic_urls = pic_urls;
        }
    }
}
