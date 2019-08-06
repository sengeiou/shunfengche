package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 秒杀列表实体
 */

public class SeckillListBean extends BaseData {

    /**
     * date_str : 10日
     * start_time : 1531191600
     * update_time : 0
     * delete_time : 0
     * spike_id : 1
     * c_list : {"totalRow":4,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"commodity_id":7,"commodity_price":15,"commodity_model_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg","cost_price":10},{"commodity_id":7,"commodity_price":14,"commodity_model_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg","cost_price":10},{"commodity_id":7,"commodity_price":13,"commodity_model_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg","cost_price":10},{"commodity_id":6,"commodity_price":250,"commodity_model_image":"qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","cost_price":10}]}
     * end_time : 1531198800
     * time_str : 11:00
     * add_time : 1530602984
     */

    private String date_str;
    private int start_time;// 活动开启时间
    private int update_time;
    private int delete_time;
    private int spike_id;
    private CListBean c_list;// 商品list
    private int end_time;// 活动结束时间
    private String time_str;// 时间 如【13:00】
    private int add_time;

    public String getDate_str() {
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(int delete_time) {
        this.delete_time = delete_time;
    }

    public int getSpike_id() {
        return spike_id;
    }

    public void setSpike_id(int spike_id) {
        this.spike_id = spike_id;
    }

    public CListBean getC_list() {
        return c_list;
    }

    public void setC_list(CListBean c_list) {
        this.c_list = c_list;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getTime_str() {
        return time_str;
    }

    public void setTime_str(String time_str) {
        this.time_str = time_str;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public static class CListBean {
        /**
         * totalRow : 4
         * pageNumber : 1
         * firstPage : true
         * lastPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"commodity_id":7,"commodity_price":15,"commodity_model_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg","cost_price":10},{"commodity_id":7,"commodity_price":14,"commodity_model_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg","cost_price":10},{"commodity_id":7,"commodity_price":13,"commodity_model_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg","cost_price":10},{"commodity_id":6,"commodity_price":250,"commodity_model_image":"qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","cost_price":10}]
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
             * commodity_id : 7
             * commodity_price : 15.0
             * commodity_model_image : http://sfcgj.oss-cn-qingdao.aliyuncs.com/0538591524624776642%E6%9D%BF%E6%A0%971.jpg
             * cost_price : 10.0
             */

            private int commodity_id;// 商品id
            private double commodity_price;// 商品原价
            private String commodity_model_image;// 封面
            private String commodity_title;// 商品名
            private double cost_price;// 商品秒杀价
            private double discount;// 商品折数

            public String getCommodity_title() {
                return commodity_title;
            }

            public void setCommodity_title(String commodity_title) {
                this.commodity_title = commodity_title;
            }

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
                this.discount = discount;
            }

            public int getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(int commodity_id) {
                this.commodity_id = commodity_id;
            }

            public double getCommodity_price() {
                return commodity_price;
            }

            public void setCommodity_price(double commodity_price) {
                this.commodity_price = commodity_price;
            }

            public String getCommodity_model_image() {
                return commodity_model_image;
            }

            public void setCommodity_model_image(String commodity_model_image) {
                this.commodity_model_image = commodity_model_image;
            }

            public double getCost_price() {
                return cost_price;
            }

            public void setCost_price(double cost_price) {
                this.cost_price = cost_price;
            }
        }
    }
}
