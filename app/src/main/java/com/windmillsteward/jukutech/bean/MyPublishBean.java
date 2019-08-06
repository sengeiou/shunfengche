package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：我的发布，简历和职位
 * 时间：2018/1/12
 * 作者：xjh
 */

public class MyPublishBean extends BaseData {

    /**
     * totalRow : 3
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"update_time":1519181418,"status_name":"审核通过","id":21,"type":1,"title":"招聘遛狗","audit_reason":"","pic_url":""},{"update_time":1519180457,"status_name":"审核通过","id":51,"type":6,"title":"空空","audit_reason":"","pic_url":""},{"update_time":1519115296,"status_name":"审核通过","id":20,"type":1,"title":"高新招聘","audit_reason":"","pic_url":""}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
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

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
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
         * update_time : 1519181418
         * status_name : 审核通过
         * id : 21
         * type : 1
         * title : 招聘遛狗
         * audit_reason :
         * pic_url :
         */

        private int update_time;
        private String status_name;
        private int id;
        private int type;
        private String title;
        private String audit_reason;
        private String pic_url;
        private int publish_status;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getPublish_status() {

            return publish_status;
        }

        public void setPublish_status(int publish_status) {
            this.publish_status = publish_status;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAudit_reason() {
            return audit_reason;
        }

        public void setAudit_reason(String audit_reason) {
            this.audit_reason = audit_reason;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
