package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/20
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 保姆 月嫂 育儿嫂 配置信息
 */
public class BmYsYesConfig {
    /**
     * match_time : 15
     * deposit_fee : 30
     */

    private int match_time;
    private String deposit_fee;

    public int getMatch_time() {
        return match_time;
    }

    public void setMatch_time(int match_time) {
        this.match_time = match_time;
    }

    public String getDeposit_fee() {
        return deposit_fee;
    }

    public void setDeposit_fee(String deposit_fee) {
        this.deposit_fee = deposit_fee;
    }
}
