package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/15/015
 * 作者：xjh
 */
public class ShoppingCarListBean extends BaseData {
    /**
     * cart_id : 6
     * update_time : 1523795342
     * store_commodity_list : {"totalRow":1,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"store_id":1,"commodity_list":[{"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","cart_commodity_id":11,"commodity_price":123,"commodity_model_id":4,"commodity_model_list":[{"commodity_price":188,"commodity_model_id":1,"commodity_inventory":100,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_model_name":"正宗农家咸鸭蛋"},{"commodity_price":123,"commodity_model_id":4,"commodity_inventory":321,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg","commodity_model_name":"不知道"}],"commodity_num":1,"commodity_model_name":"不知道"}],"store_name":"啊强特产店22","store_total_fee":123}]}
     * total_fee : 123.0
     * commodity_num : 1
     */

    private int cart_id;
    private int update_time;
    private StoreCommodityListBean store_commodity_list;
    private String total_fee;
    private int commodity_num;

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public StoreCommodityListBean getStore_commodity_list() {
        return store_commodity_list;
    }

    public void setStore_commodity_list(StoreCommodityListBean store_commodity_list) {
        this.store_commodity_list = store_commodity_list;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public int getCommodity_num() {
        return commodity_num;
    }

    public void setCommodity_num(int commodity_num) {
        this.commodity_num = commodity_num;
    }

    public static class StoreCommodityListBean {
        /**
         * totalRow : 1
         * pageNumber : 1
         * lastPage : true
         * firstPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"store_id":1,"commodity_list":[{"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","cart_commodity_id":11,"commodity_price":123,"commodity_model_id":4,"commodity_model_list":[{"commodity_price":188,"commodity_model_id":1,"commodity_inventory":100,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_model_name":"正宗农家咸鸭蛋"},{"commodity_price":123,"commodity_model_id":4,"commodity_inventory":321,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg","commodity_model_name":"不知道"}],"commodity_num":1,"commodity_model_name":"不知道"}],"store_name":"啊强特产店22","store_total_fee":123}]
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

        /**
         * 店铺
         */
        public static class ListBean {
            /**
             * store_id : 1
             * commodity_list : [{"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","cart_commodity_id":11,"commodity_price":123,"commodity_model_id":4,"commodity_model_list":[{"commodity_price":188,"commodity_model_id":1,"commodity_inventory":100,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_model_name":"正宗农家咸鸭蛋"},{"commodity_price":123,"commodity_model_id":4,"commodity_inventory":321,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg","commodity_model_name":"不知道"}],"commodity_num":1,"commodity_model_name":"不知道"}]
             * store_name : 啊强特产店22
             * store_total_fee : 123.0
             */

            private int store_id;
            private String store_name;
            private double store_total_fee;
            private boolean isEdit;
            private boolean isSelect;
            private List<CommodityListBean> commodity_list;

            public boolean isEdit() {
                return isEdit;
            }

            public void setEdit(boolean edit) {
                isEdit = edit;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public double getStore_total_fee() {
                return store_total_fee;
            }

            public void setStore_total_fee(double store_total_fee) {
                this.store_total_fee = store_total_fee;
            }

            public List<CommodityListBean> getCommodity_list() {
                return commodity_list;
            }

            public void setCommodity_list(List<CommodityListBean> commodity_list) {
                this.commodity_list = commodity_list;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "store_id=" + store_id +
                        ", store_name='" + store_name + '\'' +
                        ", store_total_fee=" + store_total_fee +
                        ", isEdit=" + isEdit +
                        ", isSelect=" + isSelect +
                        ", commodity_list=" + commodity_list +
                        '}';
            }

            /**
             * 商品
             */
            public static class CommodityListBean {
                /**
                 * commodity_id : 1
                 * commodity_cover_picture : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg
                 * cart_commodity_id : 11
                 * commodity_price : 123.0
                 * commodity_model_id : 4
                 * commodity_model_list : [{"commodity_price":188,"commodity_model_id":1,"commodity_inventory":100,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_model_name":"正宗农家咸鸭蛋"},{"commodity_price":123,"commodity_model_id":4,"commodity_inventory":321,"commodity_model_image":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg","commodity_model_name":"不知道"}]
                 * commodity_num : 1
                 * commodity_model_name : 不知道
                 */

                private int commodity_id;
                private String commodity_title;
                private String commodity_cover_picture;
                private int cart_commodity_id;
                private double commodity_price;
                private int commodity_model_id;
                private int commodity_num;
                private String commodity_model_name;
                private boolean isEdit;
                private boolean isSelect;
                private List<CommodityModelListBean> commodity_model_list;
                private int commodity_status;

                public int getCommodity_status() {
                    return commodity_status;
                }

                public void setCommodity_status(int commodity_status) {
                    this.commodity_status = commodity_status;
                }

                public String getCommodity_title() {
                    return commodity_title;
                }

                public void setCommodity_title(String commodity_title) {
                    this.commodity_title = commodity_title;
                }

                public boolean isEdit() {
                    return isEdit;
                }

                public void setEdit(boolean edit) {
                    isEdit = edit;
                }

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
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

                public int getCart_commodity_id() {
                    return cart_commodity_id;
                }

                public void setCart_commodity_id(int cart_commodity_id) {
                    this.cart_commodity_id = cart_commodity_id;
                }

                public double getCommodity_price() {
                    return commodity_price;
                }

                public void setCommodity_price(double commodity_price) {
                    this.commodity_price = commodity_price;
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

                public String getCommodity_model_name() {
                    return commodity_model_name;
                }

                public void setCommodity_model_name(String commodity_model_name) {
                    this.commodity_model_name = commodity_model_name;
                }

                public List<CommodityModelListBean> getCommodity_model_list() {
                    return commodity_model_list;
                }

                public void setCommodity_model_list(List<CommodityModelListBean> commodity_model_list) {
                    this.commodity_model_list = commodity_model_list;
                }

                @Override
                public String toString() {
                    return "CommodityListBean{" +
                            "commodity_id=" + commodity_id +
                            ", commodity_title='" + commodity_title + '\'' +
                            ", commodity_cover_picture='" + commodity_cover_picture + '\'' +
                            ", cart_commodity_id=" + cart_commodity_id +
                            ", commodity_price=" + commodity_price +
                            ", commodity_model_id=" + commodity_model_id +
                            ", commodity_num=" + commodity_num +
                            ", commodity_model_name='" + commodity_model_name + '\'' +
                            ", isEdit=" + isEdit +
                            ", isSelect=" + isSelect +
                            ", commodity_model_list=" + commodity_model_list +
                            '}';
                }

                /**
                 * 商品型号
                 */
                public static class CommodityModelListBean {
                    /**
                     * commodity_price : 188.0
                     * commodity_model_id : 1
                     * commodity_inventory : 100
                     * commodity_model_image : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg
                     * commodity_model_name : 正宗农家咸鸭蛋
                     */

                    private double commodity_price;
                    private int commodity_model_id;
                    private int commodity_inventory;
                    private String commodity_model_image;
                    private String commodity_model_name;

                    public double getCommodity_price() {
                        return commodity_price;
                    }

                    public void setCommodity_price(double commodity_price) {
                        this.commodity_price = commodity_price;
                    }

                    public int getCommodity_model_id() {
                        return commodity_model_id;
                    }

                    public void setCommodity_model_id(int commodity_model_id) {
                        this.commodity_model_id = commodity_model_id;
                    }

                    public int getCommodity_inventory() {
                        return commodity_inventory;
                    }

                    public void setCommodity_inventory(int commodity_inventory) {
                        this.commodity_inventory = commodity_inventory;
                    }

                    public String getCommodity_model_image() {
                        return commodity_model_image;
                    }

                    public void setCommodity_model_image(String commodity_model_image) {
                        this.commodity_model_image = commodity_model_image;
                    }

                    public String getCommodity_model_name() {
                        return commodity_model_name;
                    }

                    public void setCommodity_model_name(String commodity_model_name) {
                        this.commodity_model_name = commodity_model_name;
                    }

                    @Override
                    public String toString() {
                        return "CommodityModelListBean{" +
                                "commodity_price=" + commodity_price +
                                ", commodity_model_id=" + commodity_model_id +
                                ", commodity_inventory=" + commodity_inventory +
                                ", commodity_model_image='" + commodity_model_image + '\'' +
                                ", commodity_model_name='" + commodity_model_name + '\'' +
                                '}';
                    }
                }
            }
        }
    }
}
