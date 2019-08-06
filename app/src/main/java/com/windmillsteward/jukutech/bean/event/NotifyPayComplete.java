package com.windmillsteward.jukutech.bean.event;

/**
 * @date: on 2018/10/14
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 通知支付完成
 */
public class NotifyPayComplete {
    private int code;
    private String msg;

    public NotifyPayComplete(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
