package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：首页抢红包钱实体
 * author:cyq
 * 2019-01-28
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */

public class IsHaveHongBaoBean extends BaseData {

   private int is_have;//是否有现金红包可以抢 0否 1是
   private long start_time;//抢现金红包的开始时间，时间戳
   private long end_time;//抢现金红包的结束时间，时间戳
   private long time;//服务器当前时间戳

    private int is_get;//是否抢到现金红包 0否 1是
    private double money;//抢到的金额

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIs_have() {
        return is_have;
    }

    public void setIs_have(int is_have) {
        this.is_have = is_have;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public int getIs_get() {
        return is_get;
    }

    public void setIs_get(int is_get) {
        this.is_get = is_get;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
