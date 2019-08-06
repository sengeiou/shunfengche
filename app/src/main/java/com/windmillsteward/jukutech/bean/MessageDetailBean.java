package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：消息详情实体
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MessageDetailBean extends BaseData {

   private  String msg_id ;//消息id
   private  String add_time ;//发送时间
   private  String title ;//标题
   private  String content ;//内容
   private  String admin_name ;//管理员名称

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }
}
