package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class SpecialtyClassBean extends BaseData {


    /**
     * parent_id : 0
     * class_id : 208
     * name : 肉类海鲜
     * child : [{"parent_id":208,"class_id":209,"name":"蛋禽肉类","child":[{"parent_id":209,"class_id":210,"name":"皮/咸蛋","child":[]}]},{"parent_id":208,"class_id":213,"name":"海鲜水产","child":[{"parent_id":213,"class_id":214,"name":"鱼类","child":[]}]}]
     */

    private int parent_id;
    private int class_id;
    private int textColor;
    private int backgroundColor;
    private String name;
    private String image;
    private List<ChildBeanX> child;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildBeanX> getChild() {
        return child;
    }

    public void setChild(List<ChildBeanX> child) {
        this.child = child;
    }

    public static class ChildBeanX {
        /**
         * parent_id : 208
         * class_id : 209
         * name : 蛋禽肉类
         * child : [{"parent_id":209,"class_id":210,"name":"皮/咸蛋","child":[]}]
         */

        private int parent_id;
        private int class_id;
        private String name;
        private String image;
        private List<ChildBean> child;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * parent_id : 209
             * class_id : 210
             * name : 皮/咸蛋
             */

            private int parent_id;
            private int class_id;
            private String name;

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getClass_id() {
                return class_id;
            }

            public void setClass_id(int class_id) {
                this.class_id = class_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
