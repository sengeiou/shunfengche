package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：模块介绍实体
 * author:cyq
 * 2018-11-18
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ModuleIntroduceBean extends BaseData {

    private String name;//模块名称
    private String content;//模块介绍内容 富文本


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
