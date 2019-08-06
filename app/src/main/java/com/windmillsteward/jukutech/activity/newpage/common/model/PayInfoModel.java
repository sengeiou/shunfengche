package com.windmillsteward.jukutech.activity.newpage.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * @date: on 2018/10/13
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 支付信息
 */
public class PayInfoModel {

    /**
     * package : Sign=WXPay
     * appid : wx58fd613158c1dde6
     * sign : FC1E33E5E55B9B5C526ECA2F275E1C25
     * prepayid : wx14011338461521c2214e14bf1665425278
     * partnerid : 1498872502
     * body : 房屋模块联系费用
     * noncestr : 72b2ede552744b6c884a1c5330982a0d
     * order_sn : 2018101401133826254669
     * timestamp : 1539450818
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private String prepayid;
    private String partnerid;
    private String body;
    private String noncestr;
    private String order_sn;
    private String timestamp;

    private String alipay;

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
