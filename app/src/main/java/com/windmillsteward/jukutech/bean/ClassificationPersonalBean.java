package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class ClassificationPersonalBean extends BaseData {

    private String personalTitle;
    private List<ClassificationClassBean> list;

    public String getPersonalTitle() {
        return personalTitle;
    }

    public void setPersonalTitle(String personalTitle) {
        this.personalTitle = personalTitle;
    }

    public List<ClassificationClassBean> getList() {
        return list;
    }

    public void setList(List<ClassificationClassBean> list) {
        this.list = list;
    }
}
