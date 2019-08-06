package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/25
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class WorkTypeModel {
    /**
     * job_class_id_one_name : 技术
     * job_class_id_one : 72
     * list : [{"job_class_id_two":78,"job_class_id_two_name":"后端开发"},{"job_class_id_two":79,"job_class_id_two_name":"移动开发"},{"job_class_id_two":80,"job_class_id_two_name":"前端开发"}]
     */

    private String job_class_id_one_name;
    private int job_class_id_one;
    private boolean isSelect;
    private boolean isHaveOneSelect;
    private List<ListBean> list;

    public boolean isHaveOneSelect() {
        return isHaveOneSelect;
    }

    public void setHaveOneSelect(boolean haveOneSelect) {
        isHaveOneSelect = haveOneSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getJob_class_id_one_name() {
        return job_class_id_one_name;
    }

    public void setJob_class_id_one_name(String job_class_id_one_name) {
        this.job_class_id_one_name = job_class_id_one_name;
    }

    public int getJob_class_id_one() {
        return job_class_id_one;
    }

    public void setJob_class_id_one(int job_class_id_one) {
        this.job_class_id_one = job_class_id_one;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * job_class_id_two : 78
         * job_class_id_two_name : 后端开发
         */

        private int job_class_id_two;
        private String job_class_id_two_name;
        private String description;//职业描述
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getJob_class_id_two() {
            return job_class_id_two;
        }

        public void setJob_class_id_two(int job_class_id_two) {
            this.job_class_id_two = job_class_id_two;
        }

        public String getJob_class_id_two_name() {
            return job_class_id_two_name;
        }

        public void setJob_class_id_two_name(String job_class_id_two_name) {
            this.job_class_id_two_name = job_class_id_two_name;
        }
    }
}
