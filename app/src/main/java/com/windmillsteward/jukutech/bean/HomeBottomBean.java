package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;
import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/15/015
 * 作者：xjh
 */
public class HomeBottomBean extends BaseData  {




    /**
         * dataList : {"totalRow":5,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"distance":0,"service_name":"海底捞(万象汇店)","class_id":149,"latitude":"36.808165","description":"","contact_tel":"0533-6281833","service_address":"金晶大道66号万象汇4层L450号海底捞(万象汇店)","descriptions":[],"content":"","is_read":false,"price":0,"service_id":67,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/9d59a77943a9476a81be1fd0e574f813.png","longitude":"118.068128"},{"distance":0,"service_name":"老牌坊菜馆","class_id":149,"latitude":"36.845915","description":"","contact_tel":"0533-2992777","service_address":"政通路145号老牌坊菜馆","descriptions":[],"content":"","is_read":false,"price":0,"service_id":68,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/ea95f488527f4bbe9d690927a442519c.png","longitude":"118.068225"},{"distance":0,"service_name":"尚水元休闲自助餐厅(钻石店)","class_id":149,"latitude":"36.812509","description":"186","contact_tel":"0533-2317555  ","service_address":"共青团路95号钻石商务大厦1层","descriptions":[],"content":"","is_read":false,"price":0,"service_id":69,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/cd1ee8129e9a471fac66fbfb3184dc37.png","longitude":"118.054152"},{"distance":0,"service_name":"釜山自助烤肉(茂业店)","class_id":149,"latitude":"36.813095","description":"185","contact_tel":"0533-2129888 ","service_address":"柳泉路152号茂业天地F7釜山自助烤肉(茂业店)","descriptions":["烧烤"],"content":"","is_read":false,"price":0,"service_id":70,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/a9b6f9c5fd18462ca4fc9b01e22b8ef9.png","longitude":"118.05759"},{"distance":0,"service_name":"福口居(新村路店)","class_id":149,"latitude":"36.808266","description":"184","contact_tel":"0533-2867107","service_address":"新村西路138福口居(新村路店)","descriptions":["火锅"],"content":"","is_read":false,"price":0,"service_id":71,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/be82cf41bed54c7b8c8df345601f6f5d.png","longitude":"118.034024"}]}
         * titleList : [{"name":"美食餐厅","type":4},{"name":"智慧生活","type":3},{"name":"人才驿站","type":2},{"name":"房屋信息","type":1}]
         * type : 4
         */

        private DataListBean dataList;
        private int type;
        private List<TitleListBean> titleList;

        public DataListBean getDataList() {
            return dataList;
        }

        public void setDataList(DataListBean dataList) {
            this.dataList = dataList;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<TitleListBean> getTitleList() {
            return titleList;
        }

        public void setTitleList(List<TitleListBean> titleList) {
            this.titleList = titleList;
        }

        public static class DataListBean implements MultiItemEntity {
            /**
             * totalRow : 5
             * pageNumber : 1
             * lastPage : true
             * firstPage : true
             * totalPage : 1
             * pageSize : 10
             * list : [{"distance":0,"service_name":"海底捞(万象汇店)","class_id":149,"latitude":"36.808165","description":"","contact_tel":"0533-6281833","service_address":"金晶大道66号万象汇4层L450号海底捞(万象汇店)","descriptions":[],"content":"","is_read":false,"price":0,"service_id":67,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/9d59a77943a9476a81be1fd0e574f813.png","longitude":"118.068128"},{"distance":0,"service_name":"老牌坊菜馆","class_id":149,"latitude":"36.845915","description":"","contact_tel":"0533-2992777","service_address":"政通路145号老牌坊菜馆","descriptions":[],"content":"","is_read":false,"price":0,"service_id":68,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/ea95f488527f4bbe9d690927a442519c.png","longitude":"118.068225"},{"distance":0,"service_name":"尚水元休闲自助餐厅(钻石店)","class_id":149,"latitude":"36.812509","description":"186","contact_tel":"0533-2317555  ","service_address":"共青团路95号钻石商务大厦1层","descriptions":[],"content":"","is_read":false,"price":0,"service_id":69,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/cd1ee8129e9a471fac66fbfb3184dc37.png","longitude":"118.054152"},{"distance":0,"service_name":"釜山自助烤肉(茂业店)","class_id":149,"latitude":"36.813095","description":"185","contact_tel":"0533-2129888 ","service_address":"柳泉路152号茂业天地F7釜山自助烤肉(茂业店)","descriptions":["烧烤"],"content":"","is_read":false,"price":0,"service_id":70,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/a9b6f9c5fd18462ca4fc9b01e22b8ef9.png","longitude":"118.05759"},{"distance":0,"service_name":"福口居(新村路店)","class_id":149,"latitude":"36.808266","description":"184","contact_tel":"0533-2867107","service_address":"新村西路138福口居(新村路店)","descriptions":["火锅"],"content":"","is_read":false,"price":0,"service_id":71,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/be82cf41bed54c7b8c8df345601f6f5d.png","longitude":"118.034024"}]
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

            @Override
            public int getItemType() {
                return 0;
            }

            public static class ListBean implements  MultiItemEntity {
                /**
                 * distance : 0
                 * service_name : 海底捞(万象汇店)
                 * class_id : 149
                 * latitude : 36.808165
                 * description :
                 * contact_tel : 0533-6281833
                 * service_address : 金晶大道66号万象汇4层L450号海底捞(万象汇店)
                 * descriptions : []
                 * content :
                 * is_read : false
                 * price : 0
                 * service_id : 67
                 * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/20181118/9d59a77943a9476a81be1fd0e574f813.png
                 * longitude : 118.068128
                 * require_id : 118
                 */
                private int type;
                private int distance;//距离 （美食餐厅，智慧生活）
                private String service_name;//服务名称 （美食餐厅）
                private String contact_tel;//联系电话 （美食餐厅）
                private String service_address;//服务地址 （美食餐厅）
                private String content;//内容 （美食餐厅）
                private double price;//人均价格 （美食餐厅） 悬赏价格（智慧生活）
                private String pic_url;//图片 （美食餐厅）
                private String desc_url;//网页url （美食餐厅）
                private List<String> descriptions;//描述列表(美食餐厅)

                private int relate_id;//id （人才驿站）
                private String area_name;//地址 （人才驿站）
                private long  add_time;//时间 （人才驿站）
                private int  require_type;//（人才驿站）
                private int  person_type;//（人才驿站）

                private int require_class_id;//需求分类id（智慧生活）
                private int require_id;//需求id（智慧生活）
                private int is_ad;//是否广告（智慧生活）
                private String class_name; //分类名称（智慧生活）
                private String description;//描述（智慧生活）
                private String require_area	;//地址（智慧生活）
                private String class_image	;//分类图片（智慧生活）
                private String video_cover;//视频封面图(智慧生活)
                private long  update_time;//时间（智慧生活）
                private String longitude;//经度(智慧生活)
                private String latitude;//纬度(智慧生活)

                private int house_id;//房屋id （房屋信息）
                private double total_price;//价格 （房屋信息）
                private String user_avatar_url;//头像 （房屋信息）
                private String house_fourth_name;//街道名称 （房屋信息）
                private String house_third_name;//区名称 （房屋信息）
                private String house_type_name;//房屋类型名称 （房屋信息）

                private int class_id;//文档没有
                private boolean is_read;//文档没有
                private int service_id;//文档没有
                private int two_address;//文档没有
                private String info_fee;//文档没有

                public int getIs_ad() {
                    return is_ad;
                }

                public void setIs_ad(int is_ad) {
                    this.is_ad = is_ad;
                }

                public String getDesc_url() {
                    return desc_url;
                }

                public void setDesc_url(String desc_url) {
                    this.desc_url = desc_url;
                }

                public int getRequire_type() {
                    return require_type;
                }

                public void setRequire_type(int require_type) {
                    this.require_type = require_type;
                }

                public int getPerson_type() {
                    return person_type;
                }

                public void setPerson_type(int person_type) {
                    this.person_type = person_type;
                }

                public String getInfo_fee() {
                    return info_fee;
                }

                public void setInfo_fee(String info_fee) {
                    this.info_fee = info_fee;
                }

                public int getTwo_address() {
                    return two_address;
                }

                public void setTwo_address(int two_address) {
                    this.two_address = two_address;
                }

                //共用
                private String title;//标题 （人才驿站，房屋信息）
                private String video_url;//视频链接（智慧生活，房屋）
                private List<String>  pic_urls;//图片数组（智慧生活，房屋）

                public String getVideo_cover() {
                    return video_cover;
                }

                public void setVideo_cover(String video_cover) {
                    this.video_cover = video_cover;
                }

                @Override
                public int getItemType() {
                    return type;
                }

                public void setDescriptions(List<String> descriptions) {
                    this.descriptions = descriptions;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getRelate_id() {
                    return relate_id;
                }

                public void setRelate_id(int relate_id) {
                    this.relate_id = relate_id;
                }

                public int getRequire_class_id() {
                    return require_class_id;
                }

                public void setRequire_class_id(int require_class_id) {
                    this.require_class_id = require_class_id;
                }

                public String getClass_name() {
                    return class_name;
                }

                public void setClass_name(String class_name) {
                    this.class_name = class_name;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getArea_name() {
                    return area_name;
                }

                public void setArea_name(String area_name) {
                    this.area_name = area_name;
                }

                public String getVideo_url() {
                    return video_url;
                }

                public void setVideo_url(String video_url) {
                    this.video_url = video_url;
                }

                public String getRequire_area() {
                    return require_area;
                }

                public void setRequire_area(String require_area) {
                    this.require_area = require_area;
                }

                public String getClass_image() {
                    return class_image;
                }

                public void setClass_image(String class_image) {
                    this.class_image = class_image;
                }

                public List<String> getPic_urls() {
                    return pic_urls;
                }

                public void setPic_urls(List<String> pic_urls) {
                    this.pic_urls = pic_urls;
                }

                public long getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(long update_time) {
                    this.update_time = update_time;
                }

                public long getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(long add_time) {
                    this.add_time = add_time;
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

                public String getUser_avatar_url() {
                    return user_avatar_url;
                }

                public void setUser_avatar_url(String user_avatar_url) {
                    this.user_avatar_url = user_avatar_url;
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

                public String getHouse_type_name() {
                    return house_type_name;
                }

                public void setHouse_type_name(String house_type_name) {
                    this.house_type_name = house_type_name;
                }

                public int getDistance() {
                    return distance;
                }

                public void setDistance(int distance) {
                    this.distance = distance;
                }

                public String getService_name() {
                    return service_name;
                }

                public void setService_name(String service_name) {
                    this.service_name = service_name;
                }

                public int getClass_id() {
                    return class_id;
                }

                public void setClass_id(int class_id) {
                    this.class_id = class_id;
                }

                public String getLatitude() {
                    return latitude;
                }

                public void setLatitude(String latitude) {
                    this.latitude = latitude;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getContact_tel() {
                    return contact_tel;
                }

                public void setContact_tel(String contact_tel) {
                    this.contact_tel = contact_tel;
                }

                public String getService_address() {
                    return service_address;
                }

                public void setService_address(String service_address) {
                    this.service_address = service_address;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public boolean isIs_read() {
                    return is_read;
                }

                public void setIs_read(boolean is_read) {
                    this.is_read = is_read;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getService_id() {
                    return service_id;
                }

                public void setService_id(int service_id) {
                    this.service_id = service_id;
                }

                public String getPic_url() {
                    return pic_url;
                }

                public void setPic_url(String pic_url) {
                    this.pic_url = pic_url;
                }

                public String getLongitude() {
                    return longitude;
                }

                public void setLongitude(String longitude) {
                    this.longitude = longitude;
                }

                public List<String> getDescriptions() {
                    return descriptions;
                }

                public int getRequire_id() {
                    return require_id;
                }

                public void setRequire_id(int require_id) {
                    this.require_id = require_id;
                }
            }
        }

        public static class TitleListBean {
            /**
             * name : 美食餐厅
             * type : 4
             */

            private String name;
            private int type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

}
