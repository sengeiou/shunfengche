package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：客服列表实体
 * author:cyq
 * 2018-04-19
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class CustomerBean extends BaseData {

    private int totalRow;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
    private int totalPage;
    private int pageSize;
    private List<CustomerBean.ListBean> list;

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

    public class ListBean {

        private String phone;//客服电话
        private String nickname;//客服昵称
        private String is_online;//是否在线
        private String customer_id;//客服id
        private String unread_number;//未读消息数
        private String jiguang_user_name;//极光用户名
        private String jiguang_app_key;//极光appkey

        public String getJiguang_user_name() {
            return jiguang_user_name;
        }

        public void setJiguang_user_name(String jiguang_user_name) {
            this.jiguang_user_name = jiguang_user_name;
        }

        public String getJiguang_app_key() {
            return jiguang_app_key;
        }

        public void setJiguang_app_key(String jiguang_app_key) {
            this.jiguang_app_key = jiguang_app_key;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getIs_online() {
            return is_online;
        }

        public void setIs_online(String is_online) {
            this.is_online = is_online;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getUnread_number() {
            return unread_number;
        }

        public void setUnread_number(String unread_number) {
            this.unread_number = unread_number;
        }
    }
}
