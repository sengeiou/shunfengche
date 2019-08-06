package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/5
 * 作者：cyq
 */

public class FundsTrusteeshipBean extends BaseData {


    /**
     * totalRow : 2
     * pageNumber : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     * pageSize : 20
     * list : [{"type_name":"智慧家庭","order_detail_status":2,"relate_id":1,"detail_status_name":"已确认","order_id":1,"order_type":41,"order_name":"智慧家庭 溜猫"},{"type_name":"住宿旅游","order_detail_status":1,"relate_id":5,"detail_status_name":"已入住","order_id":2,"order_type":61,"order_name":"黄埔大酒店 标准大床房"}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean lastPage;
    private boolean firstPage;
    private int totalPage;
    private int pageSize;
    private List<ListBean> list;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {


        /**
         * trusteeship_module : 4
         * status_name : 审核中
         * trusteeship_title : 世纪花园竹园顶层复式带大楼台精装未住14
         * id : 1
         */

        private int trusteeship_module;//托管模块 1人才驿站 2智慧家庭 3思想智库 4房屋租售 5住宿旅游
        private String trusteeship_module_name;//托管模块 1人才驿站 2智慧家庭 3思想智库 4房屋租售 5住宿旅游
        private String status_name;//状态名称(可以不用判断直接显示状态名称)
        private String trusteeship_title;//托管标题
        private int id;//托管id(查询详情时候用到)


        public String getTrusteeship_module_name() {
            return trusteeship_module_name;
        }

        public void setTrusteeship_module_name(String trusteeship_module_name) {
            this.trusteeship_module_name = trusteeship_module_name;
        }

        public int getTrusteeship_module() {
            return trusteeship_module;
        }

        public void setTrusteeship_module(int trusteeship_module) {
            this.trusteeship_module = trusteeship_module;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getTrusteeship_title() {
            return trusteeship_title;
        }

        public void setTrusteeship_title(String trusteeship_title) {
            this.trusteeship_title = trusteeship_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
