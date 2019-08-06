package com.windmillsteward.jukutech.activity.home.personnel.model;

import java.util.List;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class TalentInnListModel {

    /**
     * totalRow : 2
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"work_third_area_id":3040,"area_name":"广州市天河区","module_type":1,"relate_id":29,"work_second_area_id":289,"title":"招聘水泥工3名,搬砖工4名,特级搬砖工4名","add_time":1539152935},{"work_third_area_id":3040,"area_name":"广州市天河区","module_type":1,"relate_id":31,"work_second_area_id":289,"title":"招聘水泥工3名,搬砖工4名,特级搬砖工4名","add_time":1539162429}]
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
         * work_third_area_id : 3040
         * area_name : 广州市天河区
         * module_type : 1
         * relate_id : 29
         * work_second_area_id : 289
         * title : 招聘水泥工3名,搬砖工4名,特级搬砖工4名
         * add_time : 1539152935
         * type : 1
         * require_type : 1
         */

        private int work_third_area_id;
        private String area_name;
        private int module_type;
        private int relate_id;
        private int work_second_area_id;
        private String title;
        private long add_time;
        private int type;
        private int require_type;

        public int getRequire_type() {
            return require_type;
        }

        public void setRequire_type(int require_type) {
            this.require_type = require_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getModule_type() {
            return module_type;
        }

        public void setModule_type(int module_type) {
            this.module_type = module_type;
        }

        public int getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(int relate_id) {
            this.relate_id = relate_id;
        }

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }
    }
}
