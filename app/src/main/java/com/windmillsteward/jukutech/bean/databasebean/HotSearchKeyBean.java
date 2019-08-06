package com.windmillsteward.jukutech.bean.databasebean;


import com.windmillsteward.jukutech.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 描述：热搜关键词本地数据库实体
 * author:cyq
 * 2018-04-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HotSearchKeyBean extends DataSupport implements Serializable {

    private int id ;
    private String keyword;//热搜关键词

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
