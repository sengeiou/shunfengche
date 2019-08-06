package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：启动图实体
 * author:cyq
 * 2019-3-7
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WelcomeBean extends BaseData {

    private String image = "";//启动图url

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
