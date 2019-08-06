package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/22
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class MarriageMsgModel {
    /**
     * message_id : 3
     * message : 恭喜：陈先生和陈女士牵手成功
     * add_time : 2038/01/19
     */

    private int message_id;
    private String message;
    private String add_time;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
