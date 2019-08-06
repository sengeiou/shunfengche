package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：搜索模块类别实体
 * author:cyq
 * 2018-03-30
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SearchModuleBean extends BaseData {

    private String module_name;//模块名称
    private int num;//思想智库数量
    private int type;//模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧生活，4：房屋租售，5：住宿旅行】

    private List<ListBean> child_list;//子模块列表

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ListBean> getChild_list() {
        return child_list;
    }

    public void setChild_list(List<ListBean> child_list) {
        this.child_list = child_list;
    }

    public class ListBean extends BaseData{
        private int num;//子模块数量
        private int type;//子模块类型
        private String module_name;//子模块名称

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getModule_name() {
            return module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }
    }


}
