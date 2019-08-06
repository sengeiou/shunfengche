package com.windmillsteward.jukutech.bean.event;

/**
*收到通知就刷新一下首页订单，用来显示订单未读红点
 */
public class NotifyReceivedMsgRefreshOrder {

    int type ;

    public NotifyReceivedMsgRefreshOrder(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
