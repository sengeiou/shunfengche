package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/30
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class OrderParentModel {

    /**
     * totalRow : 12
     * pageNumber : 1
     * firstPage : true
     * lastPage : false
     * totalPage : 2
     * pageSize : 10
     * list : [{"record":{"user_name":"小明","match_id":16,"sex":1,"personal_assets_status":"","live_second_area_name":"广州市","match_value":"90","age":23,"user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/998307152360347334620180413_031111.jpg"},"relate_id":5,"type":9}]
     */

    private int unReadNum;
    private int totalUnreadNum;

    private DataBean list;

    public int getTotalUnreadNum() {
        return totalUnreadNum;
    }

    public void setTotalUnreadNum(int totalUnreadNum) {
        this.totalUnreadNum = totalUnreadNum;
    }

    public int getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(int unReadNum) {
        this.unReadNum = unReadNum;
    }

    public DataBean getList() {
        return list;
    }

    public void setList(DataBean list) {
        this.list = list;
    }

    public static class DataBean {
        private int totalRow;
        private int pageNumber;
        private boolean firstPage;
        private boolean lastPage;
        private int totalPage;
        private int pageSize;
        private List<CommonOrderModel> list;

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

        public List<CommonOrderModel> getList() {
            return list;
        }

        public void setList(List<CommonOrderModel> list) {
            this.list = list;
        }
    }


}
