package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：提现账号实体
 * author:cyq
 * 2018-03-06
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WithdrawBean extends BaseData {

    private int account_id;
    private String add_time = "";//添加时间
    private String true_name = "";//真实名字
    private String account = "";//账号
    private int type;//账号类型,1支付宝2银行卡
    private boolean isChecked = false;//是否选中
    private int tagId;//手动标记的id

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
