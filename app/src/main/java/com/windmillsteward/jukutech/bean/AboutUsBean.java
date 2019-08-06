package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：关于我们实体
 * author:cyq
 * 2018-03-13
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class AboutUsBean extends BaseData {

   private String top_module;
   private String config_id;
   private String sub_module;
   private String remark;
   private int type;
   private String value;

    public String getTop_module() {
        return top_module;
    }

    public void setTop_module(String top_module) {
        this.top_module = top_module;
    }

    public String getConfig_id() {
        return config_id;
    }

    public void setConfig_id(String config_id) {
        this.config_id = config_id;
    }

    public String getSub_module() {
        return sub_module;
    }

    public void setSub_module(String sub_module) {
        this.sub_module = sub_module;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
