package com.windmillsteward.jukutech.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述：工作经历
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class WorkExpBean implements Parcelable {


    /**
     * start_date  : 2016-01-01
     * end_date  : 2017-01-01
     * company_name : 阿里巴巴
     * position : 美工
     */

    private String id;
    private String work_experience_id;
    private String start_date;
    private String end_date;
    private String company_name;
    private String position;

    public WorkExpBean(String id, String work_experience_id, String start_date, String end_date, String company_name, String position) {
        this.id = id;
        this.work_experience_id = work_experience_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.company_name = company_name;
        this.position = position;
    }

    public String getWork_experience_id() {
        return work_experience_id;
    }

    public void setWork_experience_id(String work_experience_id) {
        this.work_experience_id = work_experience_id;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化过程：必须按成员变量声明的顺序进行封装
        dest.writeString(id);
        dest.writeString(work_experience_id);
        dest.writeString(start_date);
        dest.writeString(end_date);
        dest.writeString(company_name);
        dest.writeString(position);
    }

    // 反序列过程：必须实现Parcelable.Creator接口，并且对象名必须为CREATOR
    // 读取Parcel里面数据时必须按照成员变量声明的顺序，Parcel数据来源上面writeToParcel方法，读出来的数据供逻辑层使用
    public static final Creator<WorkExpBean> CREATOR = new Creator<WorkExpBean>() {

        @Override
        public WorkExpBean createFromParcel(Parcel source) {
            return new WorkExpBean(source.readString(),source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }

        @Override
        public WorkExpBean[] newArray(int size) {
            return new WorkExpBean[size];
        }
    };
}
