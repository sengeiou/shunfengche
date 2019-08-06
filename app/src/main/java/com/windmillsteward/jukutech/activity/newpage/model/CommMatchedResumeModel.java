package com.windmillsteward.jukutech.activity.newpage.model;

import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

/**
 * @date: on 2018/10/21
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 匹配到的简历公用model
 */
public class CommMatchedResumeModel implements MultiItemEntity {
    public static final int TYPE_LG_TZG = 1;
    public static final int TYPE_BM_YS_YES = 2;
    public static final int TYPE_JIAJIAO = 3;
    public static final int TYPE_ZHONGDIANGONG = 4;
    public static final int TYPE_ZHAOPIN = 5;
    private int type;
    private int smallType;

    public int getSmallType() {
        return smallType;
    }

    public void setSmallType(int smallType) {
        this.smallType = smallType;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
