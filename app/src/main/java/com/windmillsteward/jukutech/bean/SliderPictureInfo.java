package com.windmillsteward.jukutech.bean;


import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：轮播图实体类
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SliderPictureInfo extends BaseData {

    private int banner_id ;//轮播图片的id
    private int banner_position ;//轮播图片的id
    private int is_top ;//是否为置顶的，排首位【0否，1是】
    private String pic_url = "";//轮播图片URL
    private String jump_type = "";//1跳转链接,2跳转店铺,3跳转菜品,4跳转文章
    private String store_type = "";//店铺类型：1.单店，2.美食阁，3.综合咖啡店, 4.连锁店
    private String title = "";//轮播图片的标题
    private String href_url = "";//跳转到网页的链接
    private String jump_code = "";//跳转内部的某个页面，列表，详情等(store_detail,article_detail)
    private int jump_id ;//跳转内部时，详情的ID


    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public int getBanner_position() {
        return banner_position;
    }

    public void setBanner_position(int banner_position) {
        this.banner_position = banner_position;
    }

    public int getIs_top() {
        return is_top;
    }

    public void setIs_top(int is_top) {
        this.is_top = is_top;
    }

    public String getStore_type() {
        return store_type;
    }

    public void setStore_type(String store_type) {
        this.store_type = store_type;
    }


    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getJump_type() {
        return jump_type;
    }

    public void setJump_type(String jump_type) {
        this.jump_type = jump_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref_url() {
        return href_url;
    }

    public void setHref_url(String href_url) {
        this.href_url = href_url;
    }

    public String getJump_code() {
        return jump_code;
    }

    public void setJump_code(String jump_code) {
        this.jump_code = jump_code;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public int getJump_id() {
        return jump_id;
    }

    public void setJump_id(int jump_id) {
        this.jump_id = jump_id;
    }
}
