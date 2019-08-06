package com.windmillsteward.jukutech.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class EduExpBean extends BaseData implements Parcelable {


    /**
     * start_date  : 2013-01-01
     * end_date  : 2016-01-01
     * school_name : 清华大学
     * major : 计算机媒体
     */

    private String id;
    private String education_id;
    private String start_date;
    private String end_date;
    private String school_name;
    private String major;

    public EduExpBean(String id, String education_id, String start_date, String end_date, String school_name, String major) {
        this.id = id;
        this.education_id = education_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.school_name = school_name;
        this.major = major;
    }

    public String getEducation_id() {
        return education_id;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化过程：必须按成员变量声明的顺序进行封装
        dest.writeString(id);
        dest.writeString(education_id);
        dest.writeString(start_date);
        dest.writeString(end_date);
        dest.writeString(school_name);
        dest.writeString(major);
    }

    // 反序列过程：必须实现Parcelable.Creator接口，并且对象名必须为CREATOR
    // 读取Parcel里面数据时必须按照成员变量声明的顺序，Parcel数据来源上面writeToParcel方法，读出来的数据供逻辑层使用
    public static final Creator<EduExpBean> CREATOR = new Creator<EduExpBean>() {

        @Override
        public EduExpBean createFromParcel(Parcel source) {
            return new EduExpBean(source.readString(),source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }

        @Override
        public EduExpBean[] newArray(int size) {
            return new EduExpBean[size];
        }
    };
}
