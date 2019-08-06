package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class ClassificationClassBean extends BaseData {

    private String image;
    private int id;
    private String text;
    private int id_two;
    private String text_two;

    public int getId_two() {
        return id_two;
    }

    public void setId_two(int id_two) {
        this.id_two = id_two;
    }

    public String getText_two() {
        return text_two;
    }

    public void setText_two(String text_two) {
        this.text_two = text_two;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
