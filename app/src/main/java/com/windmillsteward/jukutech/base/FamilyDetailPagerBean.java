package com.windmillsteward.jukutech.base;

/**
 * 描述：
 * 时间：2018/1/24
 * 作者：xjh
 */

public class FamilyDetailPagerBean extends BaseData {

    private boolean is_video_cover;
    private String url;

    public boolean isIs_video_cover() {
        return is_video_cover;
    }

    public void setIs_video_cover(boolean is_video_cover) {
        this.is_video_cover = is_video_cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
