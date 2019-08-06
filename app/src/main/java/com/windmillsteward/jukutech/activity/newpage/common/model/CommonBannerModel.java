package com.windmillsteward.jukutech.activity.newpage.common.model;

/**
 * @date: on 2018/10/9
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 通用Banner数据
 */
public class CommonBannerModel {

    /**
     * jump_code : 0
     * jump_id : 0
     * banner_id : 5
     * banner_position : 1
     * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     * jump_type : 0
     * is_top : 1
     * href_url : https://www.baidu.com/
     */

    private int jump_code;
    private int jump_id;
    private int banner_id;
    private int banner_position;
    private String pic_url;
    private int jump_type;
    private int is_top;
    private String href_url;

    public int getJump_code() {
        return jump_code;
    }

    public void setJump_code(int jump_code) {
        this.jump_code = jump_code;
    }

    public int getJump_id() {
        return jump_id;
    }

    public void setJump_id(int jump_id) {
        this.jump_id = jump_id;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public int getBanner_position() {
        return banner_position;
    }

    public void setBanner_position(int banner_position) {
        this.banner_position = banner_position;
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

    public int getIs_top() {
        return is_top;
    }

    public void setIs_top(int is_top) {
        this.is_top = is_top;
    }

    public String getHref_url() {
        return href_url;
    }

    public void setHref_url(String href_url) {
        this.href_url = href_url;
    }
}
