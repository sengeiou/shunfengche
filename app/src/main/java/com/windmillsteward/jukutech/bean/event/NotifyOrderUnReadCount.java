package com.windmillsteward.jukutech.bean.event;

/**
 * 订单未读红点
 */
public class NotifyOrderUnReadCount {
    private int type;//1进行中2已完成
    private int unReadCount;
    private int totalUnreadNum;


    public NotifyOrderUnReadCount(int type, int unReadCount,int totalUnreadNum) {
        this.type = type;
        this.unReadCount = unReadCount;
        this.totalUnreadNum = totalUnreadNum;
    }

    public int getTotalUnreadNum() {
        return totalUnreadNum;
    }

    public void setTotalUnreadNum(int totalUnreadNum) {
        this.totalUnreadNum = totalUnreadNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public NotifyOrderUnReadCount() {
    }
}
