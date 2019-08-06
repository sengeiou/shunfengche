package com.windmillsteward.jukutech.bean.event;

/**
 * @date: on 2018/10/19
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 通知页面刷新
 */
public class NotifyPageRefresh {
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NotifyPageRefresh(String tag) {
        this.tag = tag;
    }



    public NotifyPageRefresh() {
    }
}
