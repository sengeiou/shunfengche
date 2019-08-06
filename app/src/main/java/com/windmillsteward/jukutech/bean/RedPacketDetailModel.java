package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;
import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * 描述：红包详情实体
 * author:cyq
 * 2019-04-19
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class RedPacketDetailModel extends BaseData {

    private int is_expired;//红包是否过期 0否 1是
    private double money;//红包金额
    private double get_money;//红包被抢总金额
    private double lave_money;//红包剩余多少钱
    private double user_get_money;//当前用户抢到多少钱
    private int get_num;//	红包被抢数
    private int num;//	红包总数
    private int is_my_packet;//	是否是我发的红包 0否1是
    private int status;//0 可以抢 1.已过期 2.已经抢过了 3.红包被抢完
    private String packet_desc;//红包描述
    private String nickname;//用户昵称
    private String user_avatar_url;//用户头像

    private List<ListBean> packetUsers;

    public List<ListBean> getPacketUsers() {
        return packetUsers;
    }

    public void setPacketUsers(List<ListBean> packetUsers) {
        this.packetUsers = packetUsers;
    }

    public int getIs_expired() {
        return is_expired;
    }

    public void setIs_expired(int is_expired) {
        this.is_expired = is_expired;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getGet_money() {
        return get_money;
    }

    public void setGet_money(double get_money) {
        this.get_money = get_money;
    }

    public double getLave_money() {
        return lave_money;
    }

    public void setLave_money(double lave_money) {
        this.lave_money = lave_money;
    }

    public double getUser_get_money() {
        return user_get_money;
    }

    public void setUser_get_money(double user_get_money) {
        this.user_get_money = user_get_money;
    }

    public int getGet_num() {
        return get_num;
    }

    public void setGet_num(int get_num) {
        this.get_num = get_num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIs_my_packet() {
        return is_my_packet;
    }

    public void setIs_my_packet(int is_my_packet) {
        this.is_my_packet = is_my_packet;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPacket_desc() {
        return packet_desc;
    }

    public void setPacket_desc(String packet_desc) {
        this.packet_desc = packet_desc;
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

    public static  class ListBean  {
      private double money;//金额
      private int packet_user_id;//数据id
      private int packet_id;//红包id
      private int user_id;//用户id
      private long add_time;//时间
      private String nickname;//用户昵称
      private String user_avatar_url;//用户头像

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getPacket_user_id() {
            return packet_user_id;
        }

        public void setPacket_user_id(int packet_user_id) {
            this.packet_user_id = packet_user_id;
        }

        public int getPacket_id() {
            return packet_id;
        }

        public void setPacket_id(int packet_id) {
            this.packet_id = packet_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
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
    }
}
