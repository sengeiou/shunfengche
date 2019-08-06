package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：钱包明细
 * author:cyq
 * 2018-03-29
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WalletDetailBean extends BaseData {

    private int total_row;
    private int page_number;
    private int total_page;
    private int page_size;
    private List<GroupListBean>  list;

    public int getTotal_row() {
        return total_row;
    }

    public void setTotal_row(int total_row) {
        this.total_row = total_row;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }


    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<GroupListBean> getList() {
        return list;
    }

    public void setList(List<GroupListBean> list) {
        this.list = list;
    }

    public static class GroupListBean{
        private String month_name;//月份名字
        private String month_id;//月份id
        private List<ListBean> wallet_list;//


        public String getMonth_name() {
            return month_name;
        }

        public void setMonth_name(String month_name) {
            this.month_name = month_name;
        }

        public String getMonth_id() {
            return month_id;
        }

        public void setMonth_id(String month_id) {
            this.month_id = month_id;
        }

        public List<ListBean> getWallet_list() {
            return wallet_list;
        }

        public void setWallet_list(List<ListBean> wallet_list) {
            this.wallet_list = wallet_list;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            GroupListBean other = (GroupListBean) obj;
            if (month_id == null) {
                if (other.month_id != null)
                    return false;
            } else if (!month_id.equals(other.month_id))
                return false;
            return true;
        }
    }

    public static class ListBean {
        private int detail_id;//明细id
        private int add_time;//时间戳
        private int detail_type;//类型：1:是其它模块， 2：是提现。注： 如果detail_type=2，金额显示负，detail_type=1的话看transaction_type字段
        private String title;//明细标题
        private String time;//明细时间
        private String price;//金额
        private String month_id;//月份id
        private int tag;//下标
        private int transaction_type;//其它模块的类型：1.收入，2.支出，3.退款。 如果detail_type=1，transaction_type=1or3 金额显示正 transaction_type=2 金额显示负

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getMonth_id() {
            return month_id;
        }

        public void setMonth_id(String month_id) {
            this.month_id = month_id;
        }

        public int getDetail_id() {
            return detail_id;
        }

        public void setDetail_id(int detail_id) {
            this.detail_id = detail_id;
        }

        public int getDetail_type() {
            return detail_type;
        }

        public void setDetail_type(int detail_type) {
            this.detail_type = detail_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getTransaction_type() {
            return transaction_type;
        }

        public void setTransaction_type(int transaction_type) {
            this.transaction_type = transaction_type;
        }
    }
}
