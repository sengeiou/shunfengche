package com.windmillsteward.jukutech.activity.newpage.model;

import com.google.gson.annotations.SerializedName;
import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * @date: on 2018/10/17
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 已发布职位
 */
public class PublishedPositionModel {

    /**
     * totalRow : 4
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 10
     * list : [{"recruitment_id":44,"work_third_area_name":"南山区","update_time":"2018-10-17","num":16,"title":"招聘水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名","add_time":1539765893,"work_second_area_name":"深圳市"},{"recruitment_id":43,"work_third_area_name":"南山区","update_time":"2018-10-17","num":16,"title":"招聘水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名","add_time":1539756160,"work_second_area_name":"深圳市"},{"recruitment_id":42,"work_third_area_name":"南山区","update_time":"2018-10-17","num":16,"title":"招聘水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名","add_time":1539755567,"work_second_area_name":"深圳市"},{"recruitment_id":41,"work_third_area_name":"南山区","update_time":"2018-10-17","num":16,"title":"招聘水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名","add_time":1539743850,"work_second_area_name":"深圳市"}]
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

    public static class ListBean implements MultiItemEntity {
        private int type;
        /**
         * recruitment_id : 44
         * work_third_area_name : 南山区
         * update_time : 2018-10-17
         * num : 16
         * title : 招聘水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名,水泥工1名,搬砖工1名,清洁工1名,其他工种1名
         * add_time : 1539765893
         * work_second_area_name : 深圳市
         */

        //公共模块
        private int recruitment_id;
        private String work_third_area_name;
        private String title;

        //劳工模块
        private String update_time;
        private int num;
        private long add_time;
        private String work_second_area_name;

        //保姆模块
        /**
         * work_third_area_id : 3040
         * area_name : 广州市天河区
         * work_second_area_id : 289
         * work_experience : 30
         */

        private int work_third_area_id;
        private String area_name;
        private int work_second_area_id;
        private int work_experience;

        //家教模块
        private int look_for_tutor_id;
        private String longitude;
        private String latitude;

        //钟点工
        /**
         * lookfor_bell_worker_id : 7
         * recruit_number : 1
         * add_time : 2018-10-25
         */

        private int lookfor_bell_worker_id;
        private int recruit_number;

        //求职招聘
        private String job_name;
        private int job_id;
        private int recruitment_num;
        private int difference_num;

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public int getRecruitment_num() {
            return recruitment_num;
        }

        public void setRecruitment_num(int recruitment_num) {
            this.recruitment_num = recruitment_num;
        }

        public int getDifference_num() {
            return difference_num;
        }

        public void setDifference_num(int difference_num) {
            this.difference_num = difference_num;
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

        public int getLook_for_tutor_id() {
            return look_for_tutor_id;
        }

        public void setLook_for_tutor_id(int look_for_tutor_id) {
            this.look_for_tutor_id = look_for_tutor_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getRecruitment_id() {
            return recruitment_id;
        }

        public void setRecruitment_id(int recruitment_id) {
            this.recruitment_id = recruitment_id;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
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

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
        }

        public int getWork_experience() {
            return work_experience;
        }

        public void setWork_experience(int work_experience) {
            this.work_experience = work_experience;
        }

        @Override
        public int getItemType() {
            return type;
        }

        public int getLookfor_bell_worker_id() {
            return lookfor_bell_worker_id;
        }

        public void setLookfor_bell_worker_id(int lookfor_bell_worker_id) {
            this.lookfor_bell_worker_id = lookfor_bell_worker_id;
        }

        public int getRecruit_number() {
            return recruit_number;
        }

        public void setRecruit_number(int recruit_number) {
            this.recruit_number = recruit_number;
        }
    }
}
