package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：思想智库可滑动类型viewpage数据实体
 * 时间：2018/7/31
 * 作者：lc
 */

public class IdeaThinkClassBeanTwo extends BaseData {

    private int class_id;
    private String image;
    private String name;
    private String type;// 1智慧消防 2其他

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
