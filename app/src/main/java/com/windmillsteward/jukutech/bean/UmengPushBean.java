package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：友盟推送返回实体
 * author:cyq
 * 2018-04-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class UmengPushBean extends BaseData {

    private String relevance_id;
    private String type;
    private String publish_status;

    public String getRelevance_id() {
        return relevance_id;
    }

    public void setRelevance_id(String relevance_id) {
        this.relevance_id = relevance_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublish_status() {
        return publish_status;
    }

    public void setPublish_status(String publish_status) {
        this.publish_status = publish_status;
    }
}
