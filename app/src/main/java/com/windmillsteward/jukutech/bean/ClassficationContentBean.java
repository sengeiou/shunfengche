package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

/**
 * 描述：
 * 时间：2018/2/24/024
 * 作者：xjh
 */
public class ClassficationContentBean implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;

    private int itemType;
    private int spanSize;
    private String content;

    public ClassficationContentBean(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
