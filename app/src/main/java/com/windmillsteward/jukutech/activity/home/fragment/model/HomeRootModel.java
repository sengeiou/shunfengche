package com.windmillsteward.jukutech.activity.home.fragment.model;

import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * @date: on 2018/10/7
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HomeRootModel {
    public static final int TYPE_PIC = 1;
    public static final int TYPE_SINGLE = 2;
    public static final int TYPE_BANNER = 3;

    public static final int TYPE_RENCAI = 4;
    public static final int TYPE_SMART = 5;
    public static final int TYPE_FANGWU = 6;
    public static final int TYPE_FOOTER = 7;
    /**
     * list_news_flash : {"more_news_flash":"http://shunfengche.qishare.cn/windmillsteward/user_api/index/list_news_flash","list":[{"title":"管家快讯标题1","url":"http://shunfengche.qishare.cn/windmillsteward/user_api/index/get_news_flash?news_flash_id=10","news_flash_id":10},{"title":"管家快讯标题2","url":"http://shunfengche.qishare.cn/windmillsteward/user_api/index/get_news_flash?news_flash_id=11","news_flash_id":11},{"title":"管家快讯标题3","url":"http://shunfengche.qishare.cn/windmillsteward/user_api/index/get_news_flash?news_flash_id=12","news_flash_id":12},{"title":"管家快讯标题4","url":"http://shunfengche.qishare.cn/windmillsteward/user_api/index/get_news_flash?news_flash_id=13","news_flash_id":13},{"title":"管家快讯标题5","url":"http://shunfengche.qishare.cn/windmillsteward/user_api/index/get_news_flash?news_flash_id=14","news_flash_id":14}]}
     * recommend_banner : [{"banner_id":30,"title":"管家推荐1","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":31,"title":"管家推荐2","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":33,"title":"管家推荐4","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":34,"title":"管家推荐5","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]
     * module_type : [{"image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","name":"人才驿站"},{"image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","name":"智慧生活"},{"image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","name":"房屋信息"},{"image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","name":"便民信息"}]
     * bottom_banner : [{"banner_id":25,"title":"首页底部广告1","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":26,"title":"首页底部广告2","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":27,"title":"首页底部广告3","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":28,"title":"首页底部广告4","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]
     * top_banner : [{"banner_id":7,"title":"首页顶部广告3","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":6,"title":"首页顶部广告2","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":5,"title":"首页顶部广告1","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"banner_id":9,"title":"首页顶部广告5","pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]
     * recommend_module_data : [{"name":"人才信息","list":[{"data_id":1,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":2,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":3,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":4,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":5,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]},{"name":"房屋信息","list":[{"data_id":6,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]},{"name":"生活信息","list":[{"data_id":7,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":8,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":9,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":10,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":11,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]}]
     */

    private List<ListNewsFlashBean> list_news_flash;
    private List<RecommendBannerBean> recommend_banner;
    private ModuleTypeBean moduleRecord;
    private List<BottomBannerBean> bottom_banner;
    private List<TopBannerBean> top_banner;
    private List<RecommendModuleDataBean> recommend_module_data;
    private List<ClassListBean> class_list;
    private TitleListBean titleRecord;

    public ModuleTypeBean getModuleRecord() {
        return moduleRecord;
    }

    public void setModuleRecord(ModuleTypeBean moduleRecord) {
        this.moduleRecord = moduleRecord;
    }

    public TitleListBean getTitleRecord() {
        return titleRecord;
    }

    public void setTitleRecord(TitleListBean titleRecord) {
        this.titleRecord = titleRecord;
    }


    public List<ClassListBean> getClass_list() {
        return class_list;
    }

    public void setClass_list(List<ClassListBean> class_list) {
        this.class_list = class_list;
    }

    public List<ListNewsFlashBean> getList_news_flash() {
        return list_news_flash;
    }

    public void setList_news_flash(List<ListNewsFlashBean> list_news_flash) {
        this.list_news_flash = list_news_flash;
    }

    public List<RecommendBannerBean> getRecommend_banner() {
        return recommend_banner;
    }

    public void setRecommend_banner(List<RecommendBannerBean> recommend_banner) {
        this.recommend_banner = recommend_banner;
    }


    public List<BottomBannerBean> getBottom_banner() {
        return bottom_banner;
    }

    public void setBottom_banner(List<BottomBannerBean> bottom_banner) {
        this.bottom_banner = bottom_banner;
    }

    public List<TopBannerBean> getTop_banner() {
        return top_banner;
    }

    public void setTop_banner(List<TopBannerBean> top_banner) {
        this.top_banner = top_banner;
    }

    public List<RecommendModuleDataBean> getRecommend_module_data() {
        return recommend_module_data;
    }

    public void setRecommend_module_data(List<RecommendModuleDataBean> recommend_module_data) {
        this.recommend_module_data = recommend_module_data;
    }

    public static class ListNewsFlashBean {
        /**
         * title : 管家快讯标题1
         * url : http://shunfengche.qishare.cn/windmillsteward/user_api/index/get_news_flash?news_flash_id=10
         * news_flash_id : 10
         */

        private String title;
        private String url;
        private int news_flash_id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getNews_flash_id() {
            return news_flash_id;
        }

        public void setNews_flash_id(int news_flash_id) {
            this.news_flash_id = news_flash_id;
        }
    }

    public static class RecommendBannerBean {
        /**
         * banner_id : 30
         * title : 管家推荐1
         * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
         * jump_type : 0
         * href_url : https://www.baidu.com/
         */

        private int banner_id;
        private String title;
        private String pic_url;
        private int jump_type;
        private String href_url;

        public int getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(int banner_id) {
            this.banner_id = banner_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getJump_type() {
            return jump_type;
        }

        public void setJump_type(int jump_type) {
            this.jump_type = jump_type;
        }

        public String getHref_url() {
            return href_url;
        }

        public void setHref_url(String href_url) {
            this.href_url = href_url;
        }
    }

    public static class ModuleTypeBean {

        private String index_image;
        private List<ModuleTypeListBean> module_type;

        public List<ModuleTypeListBean> getModule_type() {
            return module_type;
        }

        public void setModule_type(List<ModuleTypeListBean> module_type) {
            this.module_type = module_type;
        }

        public String getIndex_image() {
            return index_image;
        }

        public void setIndex_image(String index_image) {
            this.index_image = index_image;
        }

        public static class ModuleTypeListBean {
            /**
             * image : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
             * name : 人才驿站
             */
            private String image;
            private String name;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class BottomBannerBean {
        /**
         * banner_id : 25
         * title : 首页底部广告1
         * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
         * jump_type : 0
         * jump_code : 1人才2智慧3房屋
         * href_url : https://www.baidu.com/
         */

        private int banner_id;
        private String title;
        private String pic_url;
        private int jump_type;
        private int jump_code;
        private String href_url;

        public int getJump_code() {
            return jump_code;
        }

        public void setJump_code(int jump_code) {
            this.jump_code = jump_code;
        }

        public int getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(int banner_id) {
            this.banner_id = banner_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getJump_type() {
            return jump_type;
        }

        public void setJump_type(int jump_type) {
            this.jump_type = jump_type;
        }

        public String getHref_url() {
            return href_url;
        }

        public void setHref_url(String href_url) {
            this.href_url = href_url;
        }
    }

    public static class TopBannerBean {
        /**
         * banner_id : 7
         * title : 首页顶部广告3
         * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
         * jump_type : 0
         * jump_code : 1人才2智慧3房屋
         * href_url : https://www.baidu.com/
         */

        private int banner_id;
        private String title;
        private String pic_url;
        private int jump_type;
        private int jump_code;
        private String href_url;

        public int getJump_code() {
            return jump_code;
        }

        public void setJump_code(int jump_code) {
            this.jump_code = jump_code;
        }

        public int getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(int banner_id) {
            this.banner_id = banner_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getJump_type() {
            return jump_type;
        }

        public void setJump_type(int jump_type) {
            this.jump_type = jump_type;
        }

        public String getHref_url() {
            return href_url;
        }

        public void setHref_url(String href_url) {
            this.href_url = href_url;
        }
    }

    public static class RecommendModuleDataBean {
        /**
         * name : 人才信息
         * list : [{"data_id":1,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":2,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":3,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":4,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"},{"data_id":5,"pic_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","jump_type":0,"href_url":"https://www.baidu.com/"}]
         */

        private String name;
        private List<ListBeanX> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * data_id : 1
             * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
             * jump_type : 0
             * jump_code : 1人才2智慧3房屋
             * href_url : https://www.baidu.com/
             */

            private int data_id;
            private String pic_url;
            private int jump_type;
            private int jump_code;
            private String href_url;

            public int getJump_code() {
                return jump_code;
            }

            public void setJump_code(int jump_code) {
                this.jump_code = jump_code;
            }

            public int getData_id() {
                return data_id;
            }

            public void setData_id(int data_id) {
                this.data_id = data_id;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public int getJump_type() {
                return jump_type;
            }

            public void setJump_type(int jump_type) {
                this.jump_type = jump_type;
            }

            public String getHref_url() {
                return href_url;
            }

            public void setHref_url(String href_url) {
                this.href_url = href_url;
            }
        }
    }

    public static class ClassListBean {
        private String image;//图标
        private String name;//名称
        private String index_image;//背景图
        private int class_id;//
        private int sort;//
        private int type;//
        private List<ClassChildListBean> child_list;



        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndex_image() {
            return index_image;
        }

        public void setIndex_image(String index_image) {
            this.index_image = index_image;
        }

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ClassChildListBean> getChild_list() {
            return child_list;
        }

        public void setChild_list(List<ClassChildListBean> child_list) {
            this.child_list = child_list;
        }


        public static class ClassChildListBean {
            private String image;//图标
            private String name;//名称
            private String index_image;//背景图
            private int class_id;//
            private int sort;//
            private int type;//
            private int index_type;//
            private int module_type;//

            private double price;
            private int two_address;
            private int is_ad;
            private String info_fee;
            private String class_name;

            public int getIndex_type() {
                return index_type;
            }

            public void setIndex_type(int index_type) {
                this.index_type = index_type;
            }

            public int getIs_ad() {
                return is_ad;
            }

            public void setIs_ad(int is_ad) {
                this.is_ad = is_ad;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getTwo_address() {
                return two_address;
            }

            public void setTwo_address(int two_address) {
                this.two_address = two_address;
            }

            public String getInfo_fee() {
                return info_fee;
            }

            public void setInfo_fee(String info_fee) {
                this.info_fee = info_fee;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIndex_image() {
                return index_image;
            }

            public void setIndex_image(String index_image) {
                this.index_image = index_image;
            }

            public int getClass_id() {
                return class_id;
            }

            public void setClass_id(int class_id) {
                this.class_id = class_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getModule_type() {
                return module_type;
            }

            public void setModule_type(int module_type) {
                this.module_type = module_type;
            }
        }
    }

    public static class TitleListBean  {

        private String name;
        private String index_image;
        private String image;
        private List<TitleChildListBean> titleList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndex_image() {
            return index_image;
        }

        public void setIndex_image(String index_image) {
            this.index_image = index_image;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<TitleChildListBean> getTitleList() {
            return titleList;
        }

        public void setTitleList(List<TitleChildListBean> titleList) {
            this.titleList = titleList;
        }

        public static class TitleChildListBean implements MultiItemEntity{
            private String name;//标题
            private String low_title;//标题下面的文字
            private int type;

            private int tag_type;//标识type，首页recycleview用

            public String getLow_title() {
                return low_title;
            }

            public void setLow_title(String low_title) {
                this.low_title = low_title;
            }

            public int getTag_type() {
                return tag_type;
            }

            public void setTag_type(int tag_type) {
                this.tag_type = tag_type;
            }

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

            @Override
            public int getItemType() {
                return tag_type;
            }
        }
    }

}
