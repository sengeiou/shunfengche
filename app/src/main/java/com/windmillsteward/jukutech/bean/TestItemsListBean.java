package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：检测项目列表
 * 时间：2018年8月1日 15:25:43
 * 作者：lv
 */
public class TestItemsListBean extends BaseData {

//    {
//        "msg":"成功",
//            "code":0,
//            "data":[
//        {
//            "class_id":18156,
//                "name":"楼顶"
//        },
//        {
//            "class_id":18157,
//                "name":"厕所"
//        }
//    ]
//    }

    private String name;
    private int class_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
}
