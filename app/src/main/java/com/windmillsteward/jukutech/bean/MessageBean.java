package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：消息列表实体
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MessageBean extends BaseData {


    private int totalRow;
    private int pageNumber;
    private boolean lastPage;
    private boolean firstPage;
    private int totalPage;
    private int pageSize;
    private List<MessageBean.ListBean> list;

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

    public  static class ListBean extends BaseData {

        private String add_time;//添加时间
        private String type;//推送类型,和推送文档上的type对应
        private String relevance_id;//关联模块id
        private int relevance_id_new;//原本的工作id改成了订单id
        private String publish_status;//审核状态(某些模块用到),没有则默认0
        private String title;//标题
        private String content;//内容
        private String is_read;//是否读取过0否1是
        private String msg_type;//1.系统公告 2.单个用户
        private String msg_id;//系统公告消息id
        private String longitude;
        private String latitude;
        private int module_type;
        private int index_type;

        public int getIndex_type() {
            return index_type;
        }

        public void setIndex_type(int index_type) {
            this.index_type = index_type;
        }

        public int getRelevance_id_new() {
            return relevance_id_new;
        }

        public void setRelevance_id_new(int relevance_id_new) {
            this.relevance_id_new = relevance_id_new;
        }

        public int getModule_type() {
            return module_type;
        }

        public void setModule_type(int module_type) {
            this.module_type = module_type;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRelevance_id() {
            return relevance_id;
        }

        public void setRelevance_id(String relevance_id) {
            this.relevance_id = relevance_id;
        }

        public String getPublish_status() {
            return publish_status;
        }

        public void setPublish_status(String publish_status) {
            this.publish_status = publish_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }
    }
}
