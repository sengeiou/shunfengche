package com.windmillsteward.jukutech.activity.newpage.model.request;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @date: on 2018/10/28
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class YinyuanTransferModel implements Parcelable{

    private String user_name;
    private String mobile_phone;
    private int sex;
    private String height;
    private String weight;
    private String birthday;
    private int education_background_id;
    private String job_class_id_one;
    private String job_class_id_two;
    private int salary_id;
    private int first_area_id;
    private int live_second_area_id;
    private int live_third_area_id;
    private int marital_status;
    private int child_status;
    private int release_third_area_id;
    private String self_intro;
    private List<String> pic_urls;
    private List<String> video_url;
    private List<String> voice_url;
    private int type;

    public int getRelease_third_area_id() {
        return release_third_area_id;
    }

    public void setRelease_third_area_id(int release_third_area_id) {
        this.release_third_area_id = release_third_area_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public List<String> getVideo_url() {
        return video_url;
    }

    public void setVideo_url(List<String> video_url) {
        this.video_url = video_url;
    }

    public List<String> getVoice_url() {
        return voice_url;
    }

    public void setVoice_url(List<String> voice_url) {
        this.voice_url = voice_url;
    }

    private boolean only;

    public boolean isOnly() {
        return only;
    }

    public void setOnly(boolean only) {
        this.only = only;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getEducation_background_id() {
        return education_background_id;
    }

    public void setEducation_background_id(int education_background_id) {
        this.education_background_id = education_background_id;
    }

    public String getJob_class_id_one() {
        return job_class_id_one;
    }

    public void setJob_class_id_one(String job_class_id_one) {
        this.job_class_id_one = job_class_id_one;
    }

    public String getJob_class_id_two() {
        return job_class_id_two;
    }

    public void setJob_class_id_two(String job_class_id_two) {
        this.job_class_id_two = job_class_id_two;
    }

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
    }

    public int getFirst_area_id() {
        return first_area_id;
    }

    public void setFirst_area_id(int first_area_id) {
        this.first_area_id = first_area_id;
    }

    public int getLive_second_area_id() {
        return live_second_area_id;
    }

    public void setLive_second_area_id(int live_second_area_id) {
        this.live_second_area_id = live_second_area_id;
    }

    public int getLive_third_area_id() {
        return live_third_area_id;
    }

    public void setLive_third_area_id(int live_third_area_id) {
        this.live_third_area_id = live_third_area_id;
    }

    public int getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(int marital_status) {
        this.marital_status = marital_status;
    }

    public int getChild_status() {
        return child_status;
    }

    public void setChild_status(int child_status) {
        this.child_status = child_status;
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.user_name);
        dest.writeString(this.mobile_phone);
        dest.writeInt(this.sex);
        dest.writeString(this.height);
        dest.writeString(this.weight);
        dest.writeString(this.birthday);
        dest.writeInt(this.education_background_id);
        dest.writeString(this.job_class_id_one);
        dest.writeString(this.job_class_id_two);
        dest.writeInt(this.salary_id);
        dest.writeInt(this.first_area_id);
        dest.writeInt(this.live_second_area_id);
        dest.writeInt(this.live_third_area_id);
        dest.writeInt(this.marital_status);
        dest.writeInt(this.child_status);
        dest.writeInt(this.release_third_area_id);
        dest.writeString(this.self_intro);
        dest.writeStringList(this.pic_urls);
        dest.writeStringList(this.video_url);
        dest.writeStringList(this.voice_url);
        dest.writeByte(this.only ? (byte) 1 : (byte) 0);
    }

    public YinyuanTransferModel() {
    }

    protected YinyuanTransferModel(Parcel in) {
        this.user_name = in.readString();
        this.mobile_phone = in.readString();
        this.sex = in.readInt();
        this.height = in.readString();
        this.weight = in.readString();
        this.birthday = in.readString();
        this.education_background_id = in.readInt();
        this.job_class_id_one = in.readString();
        this.job_class_id_two = in.readString();
        this.salary_id = in.readInt();
        this.first_area_id = in.readInt();
        this.live_second_area_id = in.readInt();
        this.live_third_area_id = in.readInt();
        this.marital_status = in.readInt();
        this.child_status = in.readInt();
        this.release_third_area_id = in.readInt();
        this.self_intro = in.readString();
        this.pic_urls = in.createStringArrayList();
        this.video_url = in.createStringArrayList();
        this.voice_url = in.createStringArrayList();
        this.only = in.readByte() != 0;
    }

    public static final Creator<YinyuanTransferModel> CREATOR = new Creator<YinyuanTransferModel>() {
        @Override
        public YinyuanTransferModel createFromParcel(Parcel source) {
            return new YinyuanTransferModel(source);
        }

        @Override
        public YinyuanTransferModel[] newArray(int size) {
            return new YinyuanTransferModel[size];
        }
    };
}
