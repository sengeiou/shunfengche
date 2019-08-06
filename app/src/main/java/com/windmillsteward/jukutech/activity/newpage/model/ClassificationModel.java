package com.windmillsteward.jukutech.activity.newpage.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: on 2018/11/1
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class ClassificationModel {

    private ArrayList<ListBean> smart_home;
    private ArrayList<ListBean> work;
    private ArrayList<ListBean> house;

    public ArrayList<ListBean> getSmart_home() {
        return smart_home;
    }

    public void setSmart_home(ArrayList<ListBean> smart_home) {
        this.smart_home = smart_home;
    }

    public ArrayList<ListBean> getWork() {
        return work;
    }

    public void setWork(ArrayList<ListBean> work) {
        this.work = work;
    }

    public ArrayList<ListBean> getHouse() {
        return house;
    }

    public void setHouse(ArrayList<ListBean> house) {
        this.house = house;
    }

    public static class ListBean implements Parcelable{

        /**
         * name : 服务分类
         * class_list : [{"class_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","info_fee":2,"price":50,"require_class_id":1,"class_name":"跑腿"},{"class_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","info_fee":2,"price":50,"require_class_id":2,"class_name":"家电维修"},{"class_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","info_fee":2,"price":50,"require_class_id":3,"class_name":"保洁"},{"class_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","info_fee":2,"price":50,"require_class_id":4,"class_name":"家具维修"},{"class_image":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png","info_fee":2,"price":50,"require_class_id":5,"class_name":"通渠服务"}]
         */

        private String name;
        private List<ClassListBean> class_list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean implements Parcelable{

            //智慧生活
            /**
             * class_image : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
             * info_fee : 2
             * price : 50
             * require_class_id : 1
             * class_name : 跑腿
             */

            private String class_image;
            private int info_fee;
            private int price;
            private int require_class_id;
            private String class_name;

            //人才驿站
            /**
             * image_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/032751154104107733231.png
             * name : 我要找工作
             * type : 1
             */

            private String image_url;
            private String name;
            private int type;

            //房屋信息
            /**
             * image :
             * house_type_id : 6
             * house_type_name : 住宅
             */

            private String image;
            private int house_type_id;
            private String house_type_name;

            public String getClass_image() {
                return class_image;
            }

            public void setClass_image(String class_image) {
                this.class_image = class_image;
            }

            public int getInfo_fee() {
                return info_fee;
            }

            public void setInfo_fee(int info_fee) {
                this.info_fee = info_fee;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getRequire_class_id() {
                return require_class_id;
            }

            public void setRequire_class_id(int require_class_id) {
                this.require_class_id = require_class_id;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getHouse_type_id() {
                return house_type_id;
            }

            public void setHouse_type_id(int house_type_id) {
                this.house_type_id = house_type_id;
            }

            public String getHouse_type_name() {
                return house_type_name;
            }

            public void setHouse_type_name(String house_type_name) {
                this.house_type_name = house_type_name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.class_image);
                dest.writeInt(this.info_fee);
                dest.writeInt(this.price);
                dest.writeInt(this.require_class_id);
                dest.writeString(this.class_name);
                dest.writeString(this.image_url);
                dest.writeString(this.name);
                dest.writeInt(this.type);
                dest.writeString(this.image);
                dest.writeInt(this.house_type_id);
                dest.writeString(this.house_type_name);
            }

            public ClassListBean() {
            }

            protected ClassListBean(Parcel in) {
                this.class_image = in.readString();
                this.info_fee = in.readInt();
                this.price = in.readInt();
                this.require_class_id = in.readInt();
                this.class_name = in.readString();
                this.image_url = in.readString();
                this.name = in.readString();
                this.type = in.readInt();
                this.image = in.readString();
                this.house_type_id = in.readInt();
                this.house_type_name = in.readString();
            }

            public static final Creator<ClassListBean> CREATOR = new Creator<ClassListBean>() {
                @Override
                public ClassListBean createFromParcel(Parcel source) {
                    return new ClassListBean(source);
                }

                @Override
                public ClassListBean[] newArray(int size) {
                    return new ClassListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeTypedList(this.class_list);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.name = in.readString();
            this.class_list = in.createTypedArrayList(ClassListBean.CREATOR);
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }
}
