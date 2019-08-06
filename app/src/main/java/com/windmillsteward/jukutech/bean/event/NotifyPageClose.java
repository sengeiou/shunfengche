package com.windmillsteward.jukutech.bean.event;

/**
 * @date: on 2018/10/29
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class NotifyPageClose {
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NotifyPageClose(String tag) {
        this.tag = tag;
    }

    public NotifyPageClose() {
    }
}
