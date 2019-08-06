package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class SpecialtyOrderListBean extends BaseData {

    /**
     * totalRow : 8
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":1,"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":188,"cart_commodity_id":0,"commodity_model_id":1,"commodity_num":1,"order_commodity_id":25,"commodity_model_name":"正宗农家咸鸭蛋"}],"freight_fee":0,"status_name":"待付款","address_id":9,"total_commodity_num":1,"store_name":"啊强特产店222","total_pay_fee":188,"order_id":24,"order_sn":"2018041803230992315607"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":1,"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":188,"cart_commodity_id":0,"commodity_model_id":1,"commodity_num":1,"order_commodity_id":24,"commodity_model_name":"正宗农家咸鸭蛋"}],"freight_fee":0,"status_name":"待付款","address_id":9,"total_commodity_num":1,"store_name":"啊强特产店222","total_pay_fee":188,"order_id":23,"order_sn":"2018041803230391010695"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":2,"commodity_id":2,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":89,"cart_commodity_id":0,"commodity_model_id":2,"commodity_num":3,"order_commodity_id":21,"commodity_model_name":"正宗农家咸鸡蛋"}],"freight_fee":14,"status_name":"待付款","address_id":9,"total_commodity_num":3,"store_name":"牛6","total_pay_fee":281,"order_id":20,"order_sn":"2018041803210625680926"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":2,"commodity_id":2,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":89,"cart_commodity_id":0,"commodity_model_id":2,"commodity_num":3,"order_commodity_id":20,"commodity_model_name":"正宗农家咸鸡蛋"}],"freight_fee":14,"status_name":"待付款","address_id":9,"total_commodity_num":3,"store_name":"牛6","total_pay_fee":281,"order_id":19,"order_sn":"2018041803202047595486"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":2,"commodity_id":2,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":89,"cart_commodity_id":0,"commodity_model_id":2,"commodity_num":1,"order_commodity_id":19,"commodity_model_name":"正宗农家咸鸡蛋"}],"freight_fee":10,"status_name":"待付款","address_id":9,"total_commodity_num":1,"store_name":"牛6","total_pay_fee":99,"order_id":18,"order_sn":"2018041803182624551565"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":1,"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":188,"cart_commodity_id":0,"commodity_model_id":1,"commodity_num":1,"order_commodity_id":18,"commodity_model_name":"正宗农家咸鸭蛋"}],"freight_fee":0,"status_name":"待付款","address_id":9,"total_commodity_num":4,"store_name":"啊强特产店222","total_pay_fee":687,"order_id":17,"order_sn":"2018041803180704937910"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":2,"commodity_id":2,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":89,"cart_commodity_id":0,"commodity_model_id":2,"commodity_num":3,"order_commodity_id":17,"commodity_model_name":"正宗农家咸鸡蛋"}],"freight_fee":14,"status_name":"待付款","address_id":9,"total_commodity_num":3,"store_name":"牛6","total_pay_fee":281,"order_id":16,"order_sn":"2018041803132069622406"},{"logistics_single_number":"","order_status":1,"commodity_list":[{"store_id":2,"commodity_id":2,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":89,"cart_commodity_id":0,"commodity_model_id":2,"commodity_num":3,"order_commodity_id":16,"commodity_model_name":"正宗农家咸鸡蛋"}],"freight_fee":14,"status_name":"待付款","address_id":9,"total_commodity_num":3,"store_name":"牛6","total_pay_fee":281,"order_id":15,"order_sn":"2018041803124789624180"}]
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
         * logistics_single_number :
         * order_status : 1
         * commodity_list : [{"store_id":1,"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":188,"cart_commodity_id":0,"commodity_model_id":1,"commodity_num":1,"order_commodity_id":25,"commodity_model_name":"正宗农家咸鸭蛋"}]
         * freight_fee : 0
         * status_name : 待付款
         * address_id : 9
         * total_commodity_num : 1
         * store_name : 啊强特产店222
         * total_pay_fee : 188
         * order_id : 24
         * order_sn : 2018041803230992315607
         */

        private String logistics_single_number;
        private int order_status;
        private String freight_fee;
        private String status_name;
        private int address_id;
        private int total_commodity_num;
        private String store_name;
        private String total_pay_fee;
        private int order_id;
        private String order_sn;
        private List<CommodityListBean> commodity_list;

        public String getLogistics_single_number() {
            return logistics_single_number;
        }

        public void setLogistics_single_number(String logistics_single_number) {
            this.logistics_single_number = logistics_single_number;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(String freight_fee) {
            this.freight_fee = freight_fee;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getTotal_commodity_num() {
            return total_commodity_num;
        }

        public void setTotal_commodity_num(int total_commodity_num) {
            this.total_commodity_num = total_commodity_num;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getTotal_pay_fee() {
            return total_pay_fee;
        }

        public void setTotal_pay_fee(String total_pay_fee) {
            this.total_pay_fee = total_pay_fee;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public List<CommodityListBean> getCommodity_list() {
            return commodity_list;
        }

        public void setCommodity_list(List<CommodityListBean> commodity_list) {
            this.commodity_list = commodity_list;
        }

        public static class CommodityListBean {
            /**
             * store_id : 1
             * commodity_id : 1
             * commodity_cover_picture : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg
             * commodity_price : 188
             * cart_commodity_id : 0
             * commodity_model_id : 1
             * commodity_num : 1
             * order_commodity_id : 25
             * commodity_model_name : 正宗农家咸鸭蛋
             */

            private int store_id;
            private int commodity_id;
            private String commodity_cover_picture;
            private String commodity_price;
            private int cart_commodity_id;
            private int commodity_model_id;
            private int commodity_num;
            private int order_commodity_id;
            private String commodity_model_name;
            private String commodity_title;

            public String getCommodity_title() {
                return commodity_title;
            }

            public void setCommodity_title(String commodity_title) {
                this.commodity_title = commodity_title;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(int commodity_id) {
                this.commodity_id = commodity_id;
            }

            public String getCommodity_cover_picture() {
                return commodity_cover_picture;
            }

            public void setCommodity_cover_picture(String commodity_cover_picture) {
                this.commodity_cover_picture = commodity_cover_picture;
            }

            public String getCommodity_price() {
                return commodity_price;
            }

            public void setCommodity_price(String commodity_price) {
                this.commodity_price = commodity_price;
            }

            public int getCart_commodity_id() {
                return cart_commodity_id;
            }

            public void setCart_commodity_id(int cart_commodity_id) {
                this.cart_commodity_id = cart_commodity_id;
            }

            public int getCommodity_model_id() {
                return commodity_model_id;
            }

            public void setCommodity_model_id(int commodity_model_id) {
                this.commodity_model_id = commodity_model_id;
            }

            public int getCommodity_num() {
                return commodity_num;
            }

            public void setCommodity_num(int commodity_num) {
                this.commodity_num = commodity_num;
            }

            public int getOrder_commodity_id() {
                return order_commodity_id;
            }

            public void setOrder_commodity_id(int order_commodity_id) {
                this.order_commodity_id = order_commodity_id;
            }

            public String getCommodity_model_name() {
                return commodity_model_name;
            }

            public void setCommodity_model_name(String commodity_model_name) {
                this.commodity_model_name = commodity_model_name;
            }
        }
    }
}
