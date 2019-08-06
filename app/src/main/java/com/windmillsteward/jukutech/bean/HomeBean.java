package com.windmillsteward.jukutech.bean;

import java.io.Serializable;

/**
 * 描述：
 * author:cyq
 * 2018-05-19
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeBean implements Serializable {

    private String viewType = "";

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
