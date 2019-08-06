package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class IdeaThinkListBean extends BaseData {

    /**
     * totalRow : 1
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 12
     * list : [{"area_name":"辽源市油尖旺区","require_id":1,"second_class_name":"商标维权","price":1000,"title":"公司商标维权，懂得人联系我，重重有赏"}]
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
         * area_name : 辽源市油尖旺区
         * require_id : 1
         * second_class_name : 商标维权
         * price : 1000
         * title : 公司商标维权，懂得人联系我，重重有赏
         */

        private String area_name;
        private int require_id;
        private String second_class_name;
        private String second_area_name;
        private String third_area_name;
        private String consumption_type_name;
        private int price;
        private int consumption_id;
        private String title;

        public int getConsumption_id() {
            return consumption_id;
        }

        public void setConsumption_id(int consumption_id) {
            this.consumption_id = consumption_id;
        }

        public String getConsumption_type_name() {
            return consumption_type_name;
        }

        public void setConsumption_type_name(String consumption_type_name) {
            this.consumption_type_name = consumption_type_name;
        }

        public String getSecond_area_name() {
            return second_area_name;
        }

        public void setSecond_area_name(String second_area_name) {
            this.second_area_name = second_area_name;
        }

        public String getThird_area_name() {
            return third_area_name;
        }

        public void setThird_area_name(String third_area_name) {
            this.third_area_name = third_area_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getRequire_id() {
            return require_id;
        }

        public void setRequire_id(int require_id) {
            this.require_id = require_id;
        }

        public String getSecond_class_name() {
            return second_class_name;
        }

        public void setSecond_class_name(String second_class_name) {
            this.second_class_name = second_class_name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
