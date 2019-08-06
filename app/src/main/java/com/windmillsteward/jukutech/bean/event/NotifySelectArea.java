package com.windmillsteward.jukutech.bean.event;

import com.windmillsteward.jukutech.bean.ThirdAreaBean;

/**
 * @date: on 2018/10/19
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 返回发布页面并赋值
 */
public class NotifySelectArea {
    private String tag;
    private ThirdAreaBean thirdAreaBean;

    public ThirdAreaBean getThirdAreaBean() {
        return thirdAreaBean;
    }

    public void setThirdAreaBean(ThirdAreaBean thirdAreaBean) {
        this.thirdAreaBean = thirdAreaBean;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NotifySelectArea(String tag, ThirdAreaBean thirdAreaBean) {
        this.tag = tag;
        this.thirdAreaBean = thirdAreaBean;
    }



    public NotifySelectArea() {
    }
}
