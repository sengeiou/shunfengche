package com.windmillsteward.jukutech.bean;

/**
 * 检测新版本实体
 */
public class CheckVersionUpdateBean {

    private int version_num;// 新版本号
    private int force_update;// 是否强制更新,0非强制,1强制
    private String version_title;// 新版本号更新标题
    private String version_info;// 新版本更新信息
    private String download_url;// 新版本更新链接
    private int min_version_allowed;// 最低允许的版本，如果force_update是强制更新，该字段有效

    public int getMin_version_allowed() {
        return min_version_allowed;
    }

    public void setMin_version_allowed(int min_version_allowed) {
        this.min_version_allowed = min_version_allowed;
    }

    public int getVersion_num() {
        return version_num;
    }

    public void setVersion_num(int version_num) {
        this.version_num = version_num;
    }

    public int getForce_update() {
        return force_update;
    }

    public void setForce_update(int force_update) {
        this.force_update = force_update;
    }

    public String getVersion_title() {
        return version_title;
    }

    public void setVersion_title(String version_title) {
        this.version_title = version_title;
    }

    public String getVersion_info() {
        return version_info;
    }

    public void setVersion_info(String version_info) {
        this.version_info = version_info;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
