package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：红包支付实体
 * author:cyq
 * 2019-04-19
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class RedPacketPayModel extends BaseData {

    private int relate_id;//关联id/红包id
    private String order_name;//订单标题
    private String order_price;//	订单金额
    private int num;//	红包数量
    private int user_id;//	用户id
    private double money; //红包金额
    private String packet_desc;//红包描述
    private String nickname;//昵称
    private String user_avatar_url;//头像


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public int getRelate_id() {
        return relate_id;
    }

    public void setRelate_id(int relate_id) {
        this.relate_id = relate_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPacket_desc() {
        return packet_desc;
    }

    public void setPacket_desc(String packet_desc) {
        this.packet_desc = packet_desc;
    }
}
